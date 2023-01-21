package com.btree.post.repository;

import com.btree.post.entity.salespost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface salesrepository extends JpaRepository<salespost,Long> {
}
