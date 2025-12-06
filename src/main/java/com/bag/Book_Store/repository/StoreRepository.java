package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>{
}
