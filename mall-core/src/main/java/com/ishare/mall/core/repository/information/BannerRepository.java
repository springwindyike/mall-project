package com.ishare.mall.core.repository.information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.cms.Banner;

public interface BannerRepository extends JpaRepository<Banner, Integer>,JpaSpecificationExecutor {

}
