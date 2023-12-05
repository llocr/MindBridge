package com.heesue.mindbridge.repository;

import com.heesue.mindbridge.entity.ApprovalStatus;
import com.heesue.mindbridge.entity.CounselingRequest;
import com.heesue.mindbridge.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselingRequestRepository extends JpaRepository<CounselingRequest, Long> {
    Long countByCounselorAndStatus(Counselor counselor, ApprovalStatus approvalStatus);
}
