//package com.heesue.mindbridge.service;
//
//import com.heesue.mindbridge.DTO.ClientDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//class ClientServiceTest {
//
//    @Autowired
//    ClientService clientService;
//
//    @Autowired
//    ClientRepository clientRepository;
//
//    @Test
//    public void 회원가입() {
//        //given
//        ClientDTO client = new ClientDTO();
//        client.setId("hello");
//        client.setPassword("12345");
//        client.setName("이희수");
//        client.setStudentNo(2020100539L);
//        client.setEmail("lkj;kj@naver.com");
//        client.setAddress("오동로 32");
//
//        //when
//        String joinId = clientService.join(client);
//
//        //then
//        Client findClient = clientRepository.findById(joinId).get();
//        Assertions.assertEquals(client.getName(),findClient.getName());
//    }
//
//    @Test
//    public void 아이디_중복_확인() throws Exception {
//        //given
//        ClientDTO client = new ClientDTO();
//        client.setId("hello");
//        client.setPassword("12345");
//        client.setName("이희수");
//        client.setStudentNo(2020100539L);
//        client.setEmail("lkj;kj@naver.com");
//        client.setAddress("오동로 32");
//
//        String checkId = "hello";
//
//        //when
//        clientService.join(client);
//        boolean result = clientService.validateDuplicateClientId(checkId);
//
//        //then
//        Assertions.assertEquals(result, true);
//    }
//
//    @Test
//    public void 학번_중복_확인() throws Exception {
//        //given
//        ClientDTO client = new ClientDTO();
//        client.setId("hello");
//        client.setPassword("12345");
//        client.setName("이희수");
//        client.setStudentNo(2020100539L);
//        client.setEmail("lkj;kj@naver.com");
//        client.setAddress("오동로 32");
//
//        Long checkStudentNo = 2020100539L;
//
//        //when
//        clientService.join(client);
//        boolean result = clientService.validateDuplicateClientSudentNo(checkStudentNo);
//
//        //then
//        Assertions.assertEquals(result, true);
//    }
//
//}