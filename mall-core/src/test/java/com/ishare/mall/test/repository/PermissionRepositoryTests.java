package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.permission.Permission;
import com.ishare.mall.core.repository.permission.PermissionRepository;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public class PermissionRepositoryTests extends RepositoryTestTemplate {
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void setUp() {

	}

	@Override
	public void tearDown() {

	}

	@Override
	public void testCreate() {

	}

	@Override
	public void testRetrieve() {
		List<Permission> permissions = permissionRepository.queryByMemberId(1);
		Permission permission = permissions.get(0);
		log.debug(permission.getName());
		Assert.assertTrue(permissions.size() == 1);
	}

	@Override
	public void testUpdate() {

	}

	@Override
	public void testDelete() {

	}
}
