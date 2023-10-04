package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Client client = clientRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        modelMapper.map(client, clientDTO);

        return clientDTO;
    }
}


