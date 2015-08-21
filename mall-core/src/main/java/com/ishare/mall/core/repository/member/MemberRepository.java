package com.ishare.mall.core.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.member.Member;

public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor {
}

