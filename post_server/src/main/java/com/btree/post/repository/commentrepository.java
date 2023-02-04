package com.btree.post.repository;

import com.btree.post.entity.boardcomment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentrepository extends JpaRepository<boardcomment,Long> {
}
