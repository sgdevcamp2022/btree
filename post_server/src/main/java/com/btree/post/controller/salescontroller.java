package com.btree.post.controller;

import com.btree.post.dto.salesMapper;
import com.btree.post.dto.salesrequestdto;
import com.btree.post.dto.salesresponsedto;
import com.btree.post.dto.userdto;
import com.btree.post.entity.salespost;
import com.btree.post.service.saleslikeserviceimpl;
import com.btree.post.service.salesserviceimpl;
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
@RequestMapping("/post/api/posts")
@RestController
public class salescontroller {
    private final salesserviceimpl saleservice;
    private final salesMapper salesmapper;
    private final saleslikeserviceimpl saleslikeservice;



    @PostMapping//게시글 작성
    public ResponseEntity<String> createPost (@RequestBody salesrequestdto salesrequestdto,@RequestBody userdto userdto){
        salespost salespost = saleservice.save(salesmapper.toEntity(salesrequestdto,userdto));
        System.out.println(userdto.getLocate());
        return ResponseEntity.ok()
                .body("게시글 작성 성공");
    }

    @GetMapping("/{id}") //상세 페이지 조회
    public ResponseEntity<salesresponsedto> detailPost (@PathVariable("id") int postid,HttpServletRequest req, HttpServletResponse res){
        viewCountUp((long) postid,req,res);
        salespost post = saleservice.findById((long) postid);
        salesresponsedto salesresponsedto = salesmapper.fromEntity(post);
        return ResponseEntity.ok()
                .body(salesresponsedto);
    }

    @GetMapping// 게시글 목록
    public List<salesresponsedto> findAllPostbyLocation (@RequestParam int page, @RequestParam int size,@RequestBody userdto userdto){
        PageRequest sortByPostid = PageRequest.of(page, size, Sort.by("salespostid").descending());
        return saleservice.findAllpostsbylocate(sortByPostid, userdto.getLocate());
    }

    @GetMapping("/user")// useremail로 게시글 목록 불러오기
    public List<salesresponsedto> findAllPostbyUseremail (@RequestParam int page, @RequestParam int size,@RequestBody userdto userdto){
        PageRequest sortByPostid = PageRequest.of(page, size, Sort.by("salespostid").descending());
        return saleservice.findAllpostsbyUseremail(sortByPostid, userdto.getUseremail(), userdto.getLocate());
    }

    @GetMapping("/category")// 카테고리로 게시글 목록 불러오기
    public List<salesresponsedto> findAllPostbyCategory (@RequestParam int page, @RequestParam int size,@RequestParam String category,@RequestBody userdto userdto){
        PageRequest sortByPostid = PageRequest.of(page, size, Sort.by("salespostid").descending());
        return saleservice.findAllpostsbyCategory(sortByPostid, category, userdto.getLocate());
    }

    @PutMapping("/{id}") // 게시글 수정
    public void updatePost (@PathVariable Long id, @RequestBody salesrequestdto salesrequestdto,@RequestBody userdto userdto){
        saleservice.updateById(id,salesrequestdto,userdto);
    }

    @DeleteMapping("/{id}") // 게시글 삭제
    public void deletePost (@PathVariable Long id){
        saleservice.deletepost(id);
    }

    @PostMapping("/saleslike/{postid}") //좋아요 기능
    public void clickSalesLike(@RequestBody userdto userdto, @PathVariable("postid") Long id){
        saleslikeservice.clickpostlike(userdto, id);
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
                saleservice.viewCountUp(id);
                oldCookie.setValue((oldCookie.getValue()+"_["+id+"]"));
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60*60*24);
                res.addCookie(oldCookie);
            }
        }else {
            saleservice.viewCountUp(id);
            Cookie newCookie=new Cookie("boardView","["+id+"]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60*60*24);
            res.addCookie(newCookie);
        }
    }
}
