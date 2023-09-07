package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;
    private final MajorRepository majorRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, MajorRepository majorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.majorRepository = majorRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //학과 조회
    public List<MajorDTO> findAllMajor() {
        List<Major> majorList = majorRepository.findAll(Sort.by("majorNo"));

        return majorList.stream().map(major -> modelMapper.map(major, MajorDTO.class)).collect(Collectors.toList());
    }

    //겹치는 아이디 조회
    public boolean validateDuplicateClientId(String id) {
        return clientRepository.existsClientById(id);
    }

    //겹치는 학번 조회
    public boolean validateDuplicateClientSudentNo(Long studentNo) {
        return clientRepository.existsClientByStudentNo(studentNo);
    }

    //회원가입
    @Transactional
    public String join(ClientDTO clientDTO) {

//        if(validateDuplicateClientId(clientDTO)) {
//            throw new IllegalStateException("이미 존재하는 아이디입니다.");
//        }
//        if (validateDuplicateClientSudentNo(clientDTO)) {
//            throw new IllegalStateException("이미 존재하는 학번입니다.");
//        }

        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        clientDTO.setEnrollDate(LocalDateTime.now());
        clientDTO.setRole(Role.CLIENT);

        Client client = modelMapper.map(clientDTO, Client.class);

        clientRepository.save(client);
        return client.getId();
    }
}
