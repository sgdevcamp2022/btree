package com.btree.post.repository;

import com.btree.post.entity.boardlike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boardlikerepository extends JpaRepository<boardlike,Long> {
    boardlike findByPostidAndUsername (Long postid, String username);
    Boolean existsByPostidAndUsername(Long postid,String username);
}
