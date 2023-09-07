package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
