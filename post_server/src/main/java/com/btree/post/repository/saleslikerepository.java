package com.btree.post.repository;

import com.btree.post.entity.saleslike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface saleslikerepository extends JpaRepository<saleslike,Long> {
    saleslike findByPostidAndUsername (Long postid,String username);
    Boolean existsByPostidAndUsername(Long postid,String username);
}
