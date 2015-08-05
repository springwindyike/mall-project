package com.ishare.mall.old.repository;

import com.ishare.mall.old.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dongqi on 15/7/13.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParent(Category parent);
}
