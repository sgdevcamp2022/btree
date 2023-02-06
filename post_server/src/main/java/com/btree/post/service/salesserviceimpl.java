package com.btree.post.service;

import com.btree.post.dto.salesrequestdto;
import com.btree.post.dto.salesresponsedto;
import com.btree.post.entity.salespost;
import com.btree.post.exception.NotFoundException;
import com.btree.post.repository.salesrepository;
import com.btree.post.util.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class salesserviceimpl implements salesserivce{


    private final salesrepository salesrepository;

    private final User user;

    public salespost save (salespost salespost){
        return salesrepository.save(salespost);
    }

    public List<salespost> findAll(){
        return salesrepository.findAll();
    }

    public List<salesresponsedto> findAllposts (PageRequest pageRequest){
        List<salespost> posts= salesrepository.findAllByOrderBySalespostidDesc(pageRequest).getContent();
        List<salesresponsedto> Allposts=new ArrayList<>();
        for (salespost post : posts){
            salesresponsedto salesresponsedto = responsedto(post);
            Allposts.add(salesresponsedto);
        }
        return Allposts;
    }

    private salesresponsedto responsedto (salespost salespost){
        return new salesresponsedto(
                salespost.getSalespostid(),
                salespost.getTitle(),
                salespost.getContent(),
                salespost.getSalesimg(),
                salespost.getPrice(),
                salespost.getUsername(),
                salespost.getCategory(),
                salespost.getLocate(),
                salespost.getUpdatetime(),
                salespost.getLikenum(),
                salespost.getChatnum(),
                salespost.getIspoststate()
        );
    }

    public salespost findById(Long id){
        return salesrepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public salespost updateById (Long id, salesrequestdto salesrequestdto, User user){
        salespost targetpost = findById(id);
        return save(targetpost.update(salesrequestdto));
    }

    public void deletepost (Long id){
        Optional<salespost> post= salesrepository.findById(id);
        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않은 게시글");
        }
        salesrepository.deleteById(id);
    }
}
