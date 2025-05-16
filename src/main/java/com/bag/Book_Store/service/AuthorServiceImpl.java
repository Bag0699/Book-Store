package com.bag.Book_Store.service;

import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.repository.InMemoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final InMemoryData inMemoryData;

    @Override
    public Map<String, Long> getBookCountByAuthor() {
        return inMemoryData.books.stream()
                .collect(Collectors.groupingBy(book ->
                        book.getAuthor().getName(), Collectors.counting()));
    }

    @Override
    public List<Author> findAll() {
        return inMemoryData.authors;
    }

    @Override
    public Author findById(Long id) {
        return inMemoryData.authors.stream()
                .filter( author -> id.equals(author.getId()))
                .findFirst()
                .orElse(null);
    }
}
