package com.btree.post.controller;

import com.btree.post.dto.*;
import com.btree.post.entity.boardcomment;
import com.btree.post.entity.boardpost;
import com.btree.post.service.boardlikeserviceimpl;
import com.btree.post.service.boardserviceimpl;
import com.btree.post.service.commentserviceimpl;
import com.btree.post.util.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class boardcontroller {
    private final boardserviceimpl boardservice;
    private final boardMapper boardmapper;
    private final boardlikeserviceimpl boardlikeservice;
    private final commentserviceimpl commentservice;
    private final commentMapper commentmapper;

    private final User user;

    @PostMapping//게시글 작성
    public ResponseEntity<String> createPost(@RequestBody boardrequestdto boardrequestdto){
        boardpost boardpost=boardservice.save(boardmapper.toEntity(boardrequestdto,user));
        return ResponseEntity.ok()
                .body("게시글 작성 성공");
    }
    @GetMapping("/{id}")//상세 페이지 조회
    public ResponseEntity<boardresponsedto> detailPost (@PathVariable("id") Long id,User user){
        boardpost post=boardservice.findById(id);
        boardresponsedto boardresponsedto=boardmapper.fromEntity(post);
        return ResponseEntity.ok()
                .body(boardresponsedto);
    }
    @GetMapping//게시글 목록
    public List<boardresponsedto>findAllPost(@RequestParam int page,@RequestParam int size){
        PageRequest sortByPostid=PageRequest.of(page, size, Sort.by("boardpostid").descending());
        return boardservice.findAllposts(sortByPostid);
    }
    @PutMapping("/{id}")//게시글 수정
    public void updatePost(@PathVariable Long id,@RequestBody boardrequestdto boardrequestdto){
        boardservice.updateById(id,boardrequestdto,user);
    }
    @DeleteMapping("/{id}")//게시글 삭제
    public void deletePost (@PathVariable Long id){
        boardservice.deletePost(id);
    }

    @PostMapping("/{boardlike}") //좋아요 기능
    public void clickSalesLike(User user, @PathVariable Long postid){
        boardlikeservice.clickpostlike(user,postid);
    }

    @PostMapping("/comment/{postid}") //댓글 작성
    public ResponseEntity<commentresponsedto> createComment(@PathVariable("postid") Long postid, @RequestBody commentrequestdto commentrequestdto){
        commentrequestdto.setBoardpostid(Math.toIntExact(postid));
        boardcomment newcomment = commentmapper.toEntity(commentrequestdto,user);
        commentservice.commentsave(newcomment);
        commentresponsedto responsedto=commentmapper.fromEntity(newcomment);
        return ResponseEntity.ok()
                .body(responsedto);
    }
    @DeleteMapping("/comment/{postid}")
    public void deleteComment(@PathVariable("postid") Long commentid){
        commentservice.deleteComment(commentid);
    }
}
