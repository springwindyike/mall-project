package com.ishare.mall.core.repository.manageuser;

import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManageUserRepository extends JpaRepository<ManageUser, Integer>, JpaSpecificationExecutor {
	List<ManageUser> findByUsername(String username);
}

