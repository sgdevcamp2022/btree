package com.btree.post.service;

import com.btree.post.dto.userdto;
import com.btree.post.entity.saleslike;
import com.btree.post.entity.salespost;
import com.btree.post.repository.saleslikerepository;
import com.btree.post.repository.salesrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class saleslikeserviceimpl implements saleslikeservice{
    private final saleslikerepository saleslikerepository;
    private final salesrepository salesrepository;

    @Transactional
    public void clickpostlike(userdto userdto, Long postid){
        salespost salespost = salesrepository.findById(postid).orElseThrow(null);

        saleslike postlike=saleslikerepository.findByPostidAndUsername(postid,userdto.getUseremail());

        if(saleslikerepository.existsByPostidAndUsername(postid, userdto.getUseremail())){
            saleslikerepository.deleteById(postlike.getSaleslikeid());
            salesrepository.minusLike(postid);
        }
        else{
            saleslike like= saleslike.builder()
                    .postid(postid)
                    .username(userdto.getUseremail())
                    .build();
            saleslikerepository.save(like);
            salesrepository.plusLike(postid);
        }
        salesrepository.save(salespost);
    }
}
