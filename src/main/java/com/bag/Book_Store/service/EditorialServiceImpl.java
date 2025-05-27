package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.EditorialNotFoundException;
import com.bag.Book_Store.mapper.EditorialMapper;
import com.bag.Book_Store.model.dto.request.CreateEditorialRequest;
import com.bag.Book_Store.model.dto.response.EditorialResponse;
import com.bag.Book_Store.model.entity.Editorial;
import com.bag.Book_Store.repository.EditorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorialServiceImpl implements EditorialService{

    private final EditorialRepository editorialRepository;
    private final EditorialMapper editorialMapper;

    @Override
    public EditorialResponse save(CreateEditorialRequest request) {
        Editorial editorial = editorialMapper.toEditorial(request);
        return editorialMapper.toEditorialResponse(editorialRepository.save(editorial));
    }

    @Override
    public List<EditorialResponse> findAll() {
        return editorialRepository.findAll()
                .stream()
                .map(editorialMapper::toEditorialResponse)
                .toList();
    }

    @Override
    public EditorialResponse findById(Long id) {
        return editorialRepository.findById(id)
                .map(editorialMapper::toEditorialResponse)
                .orElseThrow(EditorialNotFoundException::new);
    }

    @Override
    public EditorialResponse update(Long id, CreateEditorialRequest request) {
        return editorialRepository.findById(id)
                .map(editorial -> {
                    editorial.setName(request.getName());
                    return editorialRepository.save(editorial);
                })
                .map(editorialMapper::toEditorialResponse)
                .orElseThrow(EditorialNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!editorialRepository.existsById(id)) {
            throw new EditorialNotFoundException();
        }
        editorialRepository.deleteById(id);
    }
}
