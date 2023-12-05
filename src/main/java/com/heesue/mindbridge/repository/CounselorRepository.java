package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.entity.ApprovalStatus;
import com.heesue.mindbridge.entity.Counselor;
import com.heesue.mindbridge.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    Page<Counselor> findByApprovalStatus(ApprovalStatus approvalStatus, Pageable pageable);

    Optional<Counselor> findByCounselorId(Member member);

    List<Counselor> findByBoardNoIsNotNull();

    Optional<Counselor> findByBoardNo_CounselorBoardNo(Long counselorBoardNo);

    @Query("SELECT c FROM Counselor c WHERE c.counselorId.id = :memberId")
    Optional<Counselor> findByMemberId(String memberId);
}
