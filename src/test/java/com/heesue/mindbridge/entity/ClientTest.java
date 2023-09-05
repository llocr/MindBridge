package com.heesue.mindbridge.entity;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class ClientTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

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
        Long joinClientNo = clientService.join(client);

        //then
        Assertions.assertEquals(client, clientRepository.findById(joinClientNo).get() );
    }

}