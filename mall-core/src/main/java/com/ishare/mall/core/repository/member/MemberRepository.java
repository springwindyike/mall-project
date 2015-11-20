package com.ishare.mall.core.repository.member;

import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor {
	List<Member> findByAccount(String account);

	@Query("SELECT  m FROM Member m WHERE m.channel.id = ?1 and m.use = true")
	Page<Member> findByChannelId(Integer channelId, Pageable pageable);

	@Query("SELECT m FROM Member m, MemberRole mr WHERE mr.role.id=?1 AND m.id = mr.member.id")
	Page<Member> findByRoleId(Integer roleId, Pageable pageable);

	@Query("SELECT  m FROM Member m WHERE (m.account like ?1 or m.name like ?2 or m.mobile like ?3) and m.channel.id = ?4 and m.use = true")
	Page<Member> findBycondition(String account, String name, String mobile, Integer channelId, Pageable pageable);

	List<Member> findAll();

	@Query("SELECT  m from Member m where m.createTime >= ?1")
	Page<Member> findThisWeek(Date date, Pageable pageable);

}
