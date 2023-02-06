package com.btree.post.service;

import com.btree.post.entity.saleslike;
import com.btree.post.entity.salespost;
import com.btree.post.repository.saleslikerepository;
import com.btree.post.repository.salesrepository;
import com.btree.post.util.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class saleslikeserviceimpl implements saleslikeservice{
    private final saleslikerepository saleslikerepository;
    private final salesrepository salesrepository;

    @Transactional
    public void clickpostlike(User user, Long postid){
        salespost salespost = salesrepository.findById(postid).orElseThrow(null);

        saleslike postlike=saleslikerepository.findByPostidAndUsername(postid,user.getUsername());

        if(saleslikerepository.existsByPostidAndUsername(postid, user.getUsername())){
            saleslikerepository.deleteById(postlike.getSaleslikeid());
            salesrepository.minusLike(postid);
        }
        else{
            saleslike like= saleslike.builder()
                    .postid(postid)
                    .username(user.getUsername())
                    .build();
            saleslikerepository.save(like);
            salesrepository.plusLike(postid);
        }
        salesrepository.save(salespost);
    }
}
