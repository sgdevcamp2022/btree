package com.btree.post.controller;

import com.btree.post.dto.salesMapper;
import com.btree.post.dto.salesrequestdto;
import com.btree.post.dto.salesresponsedto;
import com.btree.post.entity.salespost;
import com.btree.post.service.saleslikeserviceimpl;
import com.btree.post.service.salesserviceimpl;
import com.btree.post.util.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class salescontroller {
    private final salesserviceimpl saleservice;
    private final salesMapper salesmapper;
    private final saleslikeserviceimpl saleslikeservice;

    private final User user;

    @PostMapping//게시글 작성
    public ResponseEntity<String> createPost (salesrequestdto salesrequestdto){
        salespost salespost = saleservice.save(salesmapper.toEntity(salesrequestdto,user));
        return ResponseEntity.ok()
                .body("게시글 작성 성공");
    }

    @GetMapping("/{id}") //상세 페이지 조회
    public ResponseEntity<salesresponsedto> detailPost (@PathVariable("id") int postid, User user){
        salespost post = saleservice.findById((long) postid);
        salesresponsedto salesresponsedto = salesmapper.fromEntity(post);
        return ResponseEntity.ok()
                .body(salesresponsedto);
    }

    @GetMapping// 게시글 목록
    public List<salesresponsedto> findAllPost (@RequestParam int page, @RequestParam int size){
        PageRequest sortByPostid = PageRequest.of(page, size, Sort.by("salespostid").descending());
        return saleservice.findAllposts(sortByPostid);
    }

    @PutMapping("/{id}") // 게시글 수정
    public void updatePost (@PathVariable Long postId, @RequestBody salesrequestdto salesrequestdto){
        saleservice.updateById(postId,salesrequestdto,user);
    }

    @DeleteMapping("/{id}") // 게시글 삭제
    public void deletePost (@PathVariable Long postId){
        saleservice.deletepost(postId);
    }

    @PostMapping("/saleslike/{postid}") //좋아요 기능
    public void clickSalesLike(User user, @PathVariable("postid") Long id){
        saleslikeservice.clickpostlike(user, id);
    }
}
