package com.btree.post.repository;

import com.btree.post.entity.boardcomment;
import com.btree.post.entity.boardpost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentrepository extends JpaRepository<boardcomment,Long> {
    Page<boardcomment> findByBoardpostid(Pageable pageable, int postid);
}
