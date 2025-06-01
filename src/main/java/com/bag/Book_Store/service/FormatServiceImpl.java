package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.FormatNotFoundException;
import com.bag.Book_Store.mapper.FormatMapper;
import com.bag.Book_Store.model.dto.request.CreateFormarRequest;
import com.bag.Book_Store.model.dto.response.FormatResponse;
import com.bag.Book_Store.model.entity.Format;
import com.bag.Book_Store.repository.FormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormatServiceImpl implements FormatService {

    private final FormatMapper formatMapper;
    private final FormatRepository formatRepository;

    @Override
    public FormatResponse save(CreateFormarRequest request) {
        Format format = formatMapper.toFormat(request);
        return formatMapper.toFormatResponse(formatRepository.save(format));
    }

    @Override
    public List<FormatResponse> findAll() {
        return formatRepository.findAll()
                .stream()
                .map(formatMapper::toFormatResponse)
                .toList();
    }

    @Override
    public FormatResponse findById(Long id) {
        return formatRepository.findById(id)
                .map(formatMapper::toFormatResponse)
                .orElseThrow(FormatNotFoundException::new);
    }

    @Override
    public FormatResponse update(Long id, CreateFormarRequest request) {
        return formatRepository.findById(id)
                .map(format -> {
                    format.setName(request.getName());
                    return formatRepository.save(format);
                })
                .map(formatMapper::toFormatResponse)
                .orElseThrow(FormatNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!formatRepository.existsById(id)) {
            throw new FormatNotFoundException();
        }
        formatRepository.deleteById(id);
    }
}
