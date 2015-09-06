package com.ishare.mall.core.repository.member;

import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor {
	List<Member> findByAccount(String account);

	@Query("SELECT  t FROM t_member t WHERE t.channel.id = ?1")
	Page<Member> findByChannelId(Integer channelId, PageRequest pageRequest);

	@Query("SELECT t FROM t_member t LEFT JOIN t_member_role m ON t.account = m.account WHERE m.id = ?1 ")
	Page<Member> findByRolId(Integer rolId, PageRequest pageRequest);
}

