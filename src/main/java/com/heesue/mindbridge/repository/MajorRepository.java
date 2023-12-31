package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.entity.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    boolean existsMajorByName(String name);

    Page<Major> findMajorByNameContaining(Pageable pageable, String majorName);
}
