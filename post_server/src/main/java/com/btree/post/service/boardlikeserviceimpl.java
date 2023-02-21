package com.btree.post.service;

import com.btree.post.dto.userdto;
import com.btree.post.entity.boardlike;
import com.btree.post.entity.boardpost;
import com.btree.post.repository.boardlikerepository;
import com.btree.post.repository.boardrepository;
import com.btree.post.util.User;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class boardlikeserviceimpl implements boardlikeservice{

    private final boardlikerepository boardlikerepository;
    private final boardrepository boardrepository;

    @Transactional
    public void clickpostlike(userdto userdto, Long postid){
        boardpost boardpost = boardrepository.findById(postid).orElseThrow(null);

        boardlike postlike=boardlikerepository.findByPostidAndUsername(postid,userdto.getUseremail());

        if(boardlikerepository.existsByPostidAndUsername(postid, userdto.getUseremail())){
            boardlikerepository.deleteById(postlike.getBoardlikeid());
            boardrepository.minusLike(postid);
        }
        else{
            boardlike like= boardlike.builder()
                    .postid(postid)
                    .username(userdto.getUseremail())
                    .build();
            boardlikerepository.save(like);
            boardrepository.plusLike(postid);
        }
        boardrepository.save(boardpost);
    }
}
