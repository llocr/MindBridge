package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.entity.Client;
import com.heesue.mindbridge.entity.Major;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existsClientById(String id);

    boolean existsClientByStudentNo(Long studentNo);

    Page<Client> findClientByNameContaining(Pageable pageable, String clientName);
}
