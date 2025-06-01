package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.AuthorNotFoundException;
import com.bag.Book_Store.mapper.AuthorMapper;
import com.bag.Book_Store.model.dto.response.AuthorResponse;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.repository.AuthorRepository;
import com.bag.Book_Store.repository.BookRepository;
import com.bag.Book_Store.repository.InMemoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;

    @Override
    public Map<String, Long> getBookCountByAuthor() {
//        return inMemoryData.books.stream()
//                .collect(Collectors.groupingBy(book ->
//                        book.getAuthor().getName(), Collectors.counting()));
        return bookRepository.findAll().stream()
                .collect(Collectors.groupingBy( book ->
                        book.getAuthor().getName(), Collectors.counting()));
    }

    @Override
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toAuthorResponse)
                .toList();
    }

    @Override
    public AuthorResponse findById(Long id) {
//        return inMemoryData.authors.stream()
//                .filter( author -> id.equals(author.getId()))
//                .findFirst()
//                .orElse(null);
        return authorRepository.findById(id)
                .map(authorMapper::toAuthorResponse)
                .orElseThrow(AuthorNotFoundException::new);
    }
}
