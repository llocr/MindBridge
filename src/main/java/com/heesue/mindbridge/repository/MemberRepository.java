package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsMemberById(String id);

    boolean existsMemberByStudentNo(Long studentNo);
}
