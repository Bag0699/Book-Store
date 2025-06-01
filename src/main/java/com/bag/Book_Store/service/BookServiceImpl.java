package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.BookNotFoundException;
import com.bag.Book_Store.mapper.BookMapper;
import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.BookResponse;
import com.bag.Book_Store.model.dto.response.BookSearchResponse;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.repository.AuthorRepository;
import com.bag.Book_Store.repository.BookRepository;
import com.bag.Book_Store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{


    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
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

        if(author != null && category != null ) {
            Book book = new Book();
            book.setTitle(request.getTitle());
            book.setAuthor(author);
            book.setSinopsis(request.getSinopsis());
            book.setPrice(request.getPrice());
            book.setIsbn(request.getIsbn());
            book.setDescription(request.getDescription());
            book.setUrlImg(request.getUrlImg());
            book.setCategory(category);
            return bookMapper.toBookResponse(bookRepository.save(book));
        } else {
            throw new IllegalArgumentException("El autor o la categoria no existe");
        }
    }
}
