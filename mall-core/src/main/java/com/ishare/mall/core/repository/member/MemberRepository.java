package com.ishare.mall.core.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.member.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor {
	List<Member> findByAccount(String account);
}

