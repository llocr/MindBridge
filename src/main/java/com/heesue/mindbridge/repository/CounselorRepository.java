package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.entity.Counselor;
import com.heesue.mindbridge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
}
