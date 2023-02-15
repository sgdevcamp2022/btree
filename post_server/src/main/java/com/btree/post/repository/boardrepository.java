package com.btree.post.repository;

import com.btree.post.entity.boardpost;
import com.btree.post.entity.salespost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface boardrepository extends JpaRepository<boardpost,Long> {
    Page<boardpost> findAllByOrderByBoardpostidDesc(Pageable pageable);

    Page<boardpost> findByLocateContaining(Pageable pageable,String keyword);
    @Modifying
    @Query("update boardpost a set a.likenum = a.likenum - 1 where a.boardpostid = :id")
    void minusLike (@Param("id") Long id);
    @Modifying
    @Query("update boardpost a set a.likenum = a.likenum + 1 where a.boardpostid = :id")
    void plusLike (@Param("id") Long id);
    @Modifying
    @Query("update boardpost a set a.commentnum = a.commentnum + 1 where a.boardpostid = :id")
    void pluscommentnum (@Param("id") Long id);
    @Modifying
    @Query("update boardpost a set a.commentnum = a.commentnum - 1 where a.boardpostid = :id")
    void minuscommentnum (@Param("id") Long id);
}
