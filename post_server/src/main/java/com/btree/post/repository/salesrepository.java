package com.btree.post.repository;

import com.btree.post.entity.salespost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface salesrepository extends JpaRepository<salespost,Long> {
   Page<salespost> findAllByOrderBySalespostidDesc(Pageable pageable);

   Page<salespost> findByLocateContaining(Pageable pageable,String keyword);

   @Modifying
   @Query("update salespost a set a.likenum = a.likenum - 1 where a.salespostid = :id")
   void minusLike (@Param("id") Long id);
   @Modifying
   @Query("update salespost a set a.likenum = a.likenum + 1 where a.salespostid = :id")
   void plusLike (@Param("id") Long id);
}
