package com.btree.post.controller;

import com.btree.post.dto.*;
import com.btree.post.entity.boardcomment;
import com.btree.post.entity.boardpost;
import com.btree.post.service.boardlikeserviceimpl;
import com.btree.post.service.boardserviceimpl;
import com.btree.post.service.commentserviceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post/api/board")
@RestController
public class boardcontroller {
    private final boardserviceimpl boardservice;
    private final boardMapper boardmapper;
    private final boardlikeserviceimpl boardlikeservice;
    private final commentserviceimpl commentservice;
    private final commentMapper commentmapper;

    @PostMapping//게시글 작성
    public ResponseEntity<String> createPost(@RequestBody boardrequestdto boardrequestdto){

        boardpost boardpost=boardservice.save(boardmapper.toEntity(boardrequestdto));

        return ResponseEntity.ok()
                .body("게시글 작성 성공");
    }
    @GetMapping("/{id}")//상세 페이지 조회
    public ResponseEntity<boardresponsedto> detailPost (@PathVariable("id") Long id,HttpServletRequest req, HttpServletResponse res){
        viewCountUp((long) id,req,res);
        boardpost post=boardservice.findById(id);
        System.out.println("post nickname : " + post.getNickname());
        boardresponsedto boardresponsedto=boardmapper.fromEntity(post);
        System.out.println("dto nickname : " + boardresponsedto.getNickname());
        return ResponseEntity.ok()
                .body(boardresponsedto);
    }
    @PostMapping("/list")//게시글 목록
    public List<boardresponsedto>findAllPost(@RequestParam int page,@RequestParam int size,@RequestBody userdto userdto){
        PageRequest sortByPostid=PageRequest.of(page, size, Sort.by("boardpostid").descending());
        return boardservice.findAllpostsbylocate(sortByPostid, userdto.getLocate());
    }
    @PutMapping("/{id}")//게시글 수정
    public void updatePost(@PathVariable Long id,@RequestBody boardrequestdto boardrequestdto){
        boardservice.updateById(id,boardrequestdto);
    }
    @DeleteMapping("/{id}")//게시글 삭제
    public void deletePost (@PathVariable Long id){
        boardservice.deletePost(id);
    }

    @PostMapping("/{boardlike}") //좋아요 기능
    public void clickSalesLike(@RequestBody userdto userdto, @PathVariable Long postid){
        boardlikeservice.clickpostlike(userdto,postid);
    }

    @PostMapping("/comment/{postid}") //댓글 작성
    public ResponseEntity<commentresponsedto> createComment(@PathVariable("postid") Long postid, @RequestBody commentrequestdto commentrequestdto){
        commentrequestdto.setBoardpostid(Math.toIntExact(postid));
        boardcomment newcomment = commentmapper.toEntity(commentrequestdto);
        commentservice.commentsave(newcomment);
        commentresponsedto responsedto=commentmapper.fromEntity(newcomment);
        return ResponseEntity.ok()
                .body(responsedto);
    }

    @GetMapping("/comment/{postid}")//댓글 목록
    public List<commentresponsedto>findCommnetbyPostid(@PathVariable("postid") int postid,@RequestParam int page,@RequestParam int size){
        PageRequest sortByPostid=PageRequest.of(page, size, Sort.by("boardcommentid").descending());
        return commentservice.findAllCommentbyPostid(sortByPostid, postid);
    }

    @DeleteMapping("/comment/{commentid}") //댓글 삭제
    public void deleteComment(@PathVariable("commentid") Long commentid){
        commentservice.deleteComment(commentid);
    }

    private void viewCountUp(Long id, HttpServletRequest req, HttpServletResponse res){
        Cookie oldCookie=null;

        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("boardView")){
                    oldCookie=cookie;
                }
            }
        }
        if (oldCookie != null){
            if(!oldCookie.getValue().contains("["+id.toString()+"]")){
                boardservice.viewCountUp(id);
                oldCookie.setValue((oldCookie.getValue()+"_["+id+"]"));
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60*60*24);
                res.addCookie(oldCookie);
            }
        }else {
            boardservice.viewCountUp(id);
            Cookie newCookie=new Cookie("boardView","["+id+"]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60*60*24);
            res.addCookie(newCookie);
        }
    }
}
