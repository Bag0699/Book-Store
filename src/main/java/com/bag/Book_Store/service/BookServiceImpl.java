package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.BookResponse;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.repository.InMemoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final InMemoryData inMemoryData;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Override
    public List<Book> findAllByCategory(Long id){
        return inMemoryData.books.stream()
                .filter(book -> id.equals(book.getCategory().getId()))
                .toList();
    }

    @Override
    public Book findById(Long id){
        return inMemoryData.books.stream()
                .filter(book -> id.equals(book.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        inMemoryData.books.stream()
                .filter(book -> id.equals(book.getId()))
                .findFirst()
                .ifPresent(inMemoryData.books::remove);
    }

    @Override
    public List<Book> searchByQuery(String query) {
        String lowerCaseTitle = query.toLowerCase().trim();
        return inMemoryData.books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(lowerCaseTitle) ||
                        book.getAuthor().getName().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return inMemoryData.books;
    }

    @Override
    public List<BookResponse> findAllBySuggestion(String query) {
        String lowerCaseTitle = query.toLowerCase().trim();
        return inMemoryData.books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(lowerCaseTitle) ||
                        book.getAuthor().getName().toLowerCase().contains(lowerCaseTitle))
                .map(book -> new BookResponse(book.getId(), book.getTitle()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Book save(BookRequest request) {
        Author author = authorService.findById(request.getAuthorId());
        Category category = categoryService.findById(request.getCategoryId());

        if(author != null && category != null ) {
            Book book = new Book();
            book.setId(request.getId());
            book.setTitle(request.getTitle());
            book.setAuthor(author);
            book.setSinopsis(request.getSinopsis());
            book.setPrice(request.getPrice());
            book.setIsbn(request.getIsbn());
            book.setDescription(request.getDescription());
            book.setUrlImg(request.getUrlImg());
            book.setCategory(category);
            inMemoryData.books.add(book);
            return book;
        } else {
            throw new IllegalArgumentException("El autor o la categoria no existe");
        }
    }
}
