package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.AuthorNotFoundException;
import com.bag.Book_Store.mapper.AuthorMapper;
import com.bag.Book_Store.model.dto.request.CreateAuthorRequest;
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
        return authorRepository.findById(id)
                .map(authorMapper::toAuthorResponse)
                .orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public AuthorResponse save(CreateAuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        return authorMapper.toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponse update(Long id, CreateAuthorRequest request) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(request.getName());
                     return authorRepository.save(author);
                })
                .map(authorMapper::toAuthorResponse)
                .orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException();
        }
        authorRepository.deleteById(id);
    }
}
