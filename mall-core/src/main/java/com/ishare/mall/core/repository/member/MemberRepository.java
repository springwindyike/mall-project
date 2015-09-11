package com.ishare.mall.core.repository.member;

import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor {
	List<Member> findByAccount(String account);

	@Query("SELECT  m FROM Member m WHERE m.channel.id = ?1")
	Page<Member> findByChannelId(Integer channelId, Pageable pageable);

	@Query("SELECT m FROM Member m, MemberRole mr WHERE mr.role.id=?1 AND m.id = mr.member.id")
	Page<Member> findByRoleId(Integer roleId, Pageable pageable);

	Page<Member> findByAccountLikeOrNameLikeOrMobileLike(String account, String name, String mobile,Pageable pageable);
}

