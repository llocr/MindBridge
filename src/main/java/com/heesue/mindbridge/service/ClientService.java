package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
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

    public ClientService(ClientRepository clientRepository, MajorRepository majorRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.majorRepository = majorRepository;
        this.modelMapper = modelMapper;
    }

    public List<MajorDTO> findAllMajor() {
        List<Major> majorList = majorRepository.findAll(Sort.by("majorNo"));

        return majorList.stream().map(major -> modelMapper.map(major, MajorDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public Long join(ClientDTO clientDTO) {

        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setPwd(clientDTO.getPwd());
        client.setName(clientDTO.getName());
        client.setStudentNo(clientDTO.getStudentNo());
        client.setMajor(clientDTO.getMajor());
        client.setAddress(clientDTO.getAddress());
        client.setBirth(clientDTO.getBirth());
        client.setEnrollDate(LocalDateTime.now());


        clientRepository.save(client);
        return client.getClientNo();
    }
}
