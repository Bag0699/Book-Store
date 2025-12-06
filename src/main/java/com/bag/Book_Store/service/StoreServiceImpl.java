package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.StoreNotFoundException;
import com.bag.Book_Store.mapper.StoreMapper;
import com.bag.Book_Store.model.dto.request.CreateStoreRequest;
import com.bag.Book_Store.model.dto.response.StoreResponse;
import com.bag.Book_Store.model.entity.Store;
import com.bag.Book_Store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public StoreResponse save(CreateStoreRequest request) {
        Store store = storeMapper.toStore(request);
        return storeMapper.toStoreResponse(storeRepository.save(store));
    }

    @Override
    public List<StoreResponse> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(storeMapper::toStoreResponse)
                .toList();
    }

    @Override
    public StoreResponse findById(Long id) {
        return storeRepository.findById(id)
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    @Override
    public StoreResponse update(Long id, CreateStoreRequest request) {
        return storeRepository.findById(id)
                .map(store -> {
                    store.setName(request.getName());
                    store.setAddress(request.getAddress());
                    store.setAttention(request.getAttention());
                    store.setMap(request.getMap());
                    return storeRepository.save(store);
                })
                .map(storeMapper::toStoreResponse)
                .orElseThrow(StoreNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!storeRepository.existsById(id)) {
            throw new StoreNotFoundException();
        }
        storeRepository.deleteById(id);
    }
}
