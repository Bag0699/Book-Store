package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory_Id(Long id);

    @Query("SELECT b FROM Book b WHERE b.author.id = :authorId ORDER BY b.title ASC")
    List<Book> findBooksByAuthorIdQuery(@Param("authorId") Long authorId);
}
