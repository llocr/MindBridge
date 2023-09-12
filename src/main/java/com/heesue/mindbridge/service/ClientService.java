package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        clientDTO.setEnrollDate(LocalDateTime.now());
        clientDTO.setRole(Role.CLIENT);

        Client client = modelMapper.map(clientDTO, Client.class);

        clientRepository.save(client);
        return client.getId();
    }

    public Page<ClientDTO> findAllClient(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("name"));

        Page<Client> clientList = clientRepository.findAll(pageable);

        return clientList.map(client -> modelMapper.map(client, ClientDTO.class));
    }

    @Transactional
    public void modifyClient(ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientDTO.getId()).orElseThrow(IllegalAccessError::new);

        modelMapper.map(clientDTO, client);
        clientRepository.save(client);
    }

    public ClientDTO findClientById(String id) {
        Client client = clientRepository.findById(id).orElseThrow(IllegalAccessError::new);

        return modelMapper.map(client, ClientDTO.class);
    }

    public Page<ClientDTO> findSearchClient(Pageable pageable, String clientName) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("name"));

        Page<Client> searchList = clientRepository.findClientByNameContaining(pageable, clientName);

        return searchList.map(client -> modelMapper.map(client, ClientDTO.class));
    }
}
