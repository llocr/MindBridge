package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.repository.MajorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

//    @BeforeEach
//    public void beforeEach() {
//        clientService = new ClientService(ClientRepository clientRepository, MajorRepository majorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder);
//    }

    @Test
    public void 회원가입() {
        //given
        ClientDTO client = new ClientDTO();
        client.setId("kh010114");
        client.setPassword("lklj3");
        client.setName("이희수");
        client.setStudentNo(2020100539L);
        client.setEmail("lkj;kj@naver.com");
        client.setAddress("오동로 32");

        //when
        String joinId = clientService.join(client);

        //then
        Client findClient = clientRepository.findById(joinId).get();
        Assertions.assertEquals(client.getName(),findClient.getName());
    }

}