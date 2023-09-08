package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MajorService {
    private final MajorRepository majorRepository;
    private final ModelMapper modelMapper;

    public MajorService(MajorRepository majorRepository, ModelMapper modelMapper) {
        this.majorRepository = majorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Long join(MajorDTO majorDTO) {
        Major major = modelMapper.map(majorDTO, Major.class);
        majorRepository.save(major);
        return major.getMajorNo();
    }

    public boolean validateDuplicateName(String name) {
        return majorRepository.existsMajorByName(name);
    }

    public Page<MajorDTO> findAllMajor(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("majorNo"));

        Page<Major> majorList = majorRepository.findAll(pageable);

        return majorList.map(major -> modelMapper.map(major, MajorDTO.class));
    }

    public Page<MajorDTO> findSearchMajor(Pageable pageable, String majorName) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("majorNo"));

        Page<Major> searchList = majorRepository.findMajorByNameContaining(pageable, majorName);

        return searchList.map(major -> modelMapper.map(major, MajorDTO.class));
    }
}
