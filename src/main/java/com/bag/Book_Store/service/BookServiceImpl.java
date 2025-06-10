package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.*;
import com.bag.Book_Store.mapper.BookMapper;
import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.BookResponse;
import com.bag.Book_Store.model.dto.response.BookSearchResponse;
import com.bag.Book_Store.model.entity.*;
import com.bag.Book_Store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService{


    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final EditorialRepository editorialRepository;
    private final FormatRepository formatRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponse> findAllByCategory(Long id){

        return   bookRepository.findAllByCategory_Id(id)
                .stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toBookResponse)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!bookRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(id);
    }


    @Override
    public List<BookResponse> searchByQuery(String query) {
        String lowerCaseTitle = query.toLowerCase().trim();
        return bookRepository.findAll().stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(lowerCaseTitle) ||
                        book.getAuthor().getName().toLowerCase().contains(lowerCaseTitle))
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSearchResponse> findAllBySuggestion(String query) {
        String lowerCaseTitle = query.toLowerCase().trim();
        return bookRepository.findAll().stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(lowerCaseTitle) ||
                        book.getAuthor().getName().toLowerCase().contains(lowerCaseTitle))
                .map(book -> new BookSearchResponse(book.getId(), book.getTitle()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse save(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElse(null);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElse(null);
        Editorial editorial = editorialRepository.findById(request.getEditorialId())
                .orElse(null);
        Format format = formatRepository.findById(request.getFormatId())
                .orElse(null);

        if(author != null && category != null && editorial != null && format != null ) {
            Book book = bookMapper.toBook(request);
            book.setAuthor(author);
            book.setCategory(category);
            book.setFormat(format);
            book.setEditorial(editorial);
            return bookMapper.toBookResponse(bookRepository.save(book));
        } else {
            throw new IllegalArgumentException("El autor o la categoria o formato o editorial no existe");
        }
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {
        return bookRepository.findById(id)
                .map( book -> authorRepository.findById(request.getAuthorId())
                        .map(author -> categoryRepository.findById(request.getCategoryId())
                                .map(category -> editorialRepository.findById(request.getEditorialId())
                                        .map(editorial -> formatRepository.findById(request.getFormatId())
                                                .map(format -> {
                                                    book.setTitle(request.getTitle());
                                                    book.setSinopsis(request.getSinopsis());
                                                    book.setPrice(request.getPrice());
                                                    book.setIsbn(request.getIsbn());
                                                    book.setDescription(request.getDescription());
                                                    book.setUrlImg(request.getUrlImg());
                                                    book.setStock(request.getStock());
                                                    book.setDimension(request.getDimension());
                                                    book.setCategory(category);
                                                    book.setAuthor(author);
                                                    book.setFormat(format);
                                                    book.setEditorial(editorial);
                                                    return bookRepository.save(book);
                                                })
                                                .orElseThrow(FormatNotFoundException::new))
                                        .orElseThrow(EditorialNotFoundException::new))
                                .orElseThrow(CategoryNotFoundException::new))
                        .orElseThrow(AuthorNotFoundException::new))
                .map(bookMapper::toBookResponse)
                .orElseThrow(BookNotFoundException::new);
    }
}
