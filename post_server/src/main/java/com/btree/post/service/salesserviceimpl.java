package com.btree.post.service;

import com.btree.post.dto.salesrequestdto;
import com.btree.post.dto.salesresponsedto;
import com.btree.post.dto.userdto;
import com.btree.post.entity.salespost;
import com.btree.post.exception.NotFoundException;
import com.btree.post.repository.salesrepository;
import com.btree.post.util.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class salesserviceimpl implements salesserivce{


    private final salesrepository salesrepository;

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

    public List<salesresponsedto> findAllpostsbylocate (PageRequest pageRequest,String locate){
        List<salespost> posts= salesrepository.findByLocateContaining(pageRequest, locate).getContent();
        List<salesresponsedto> Allposts=new ArrayList<>();
        for (salespost post : posts){
            salesresponsedto salesresponsedto = responsedto(post);
            Allposts.add(salesresponsedto);
        }
        return Allposts;
    }

    public List<salesresponsedto> findAllpostsbyUseremail (PageRequest pageRequest,String useremail,String locate){
        List<salespost> posts= salesrepository.findByUseremailContainingAndLocateContaining(pageRequest, useremail,locate).getContent();
        List<salesresponsedto> Allposts=new ArrayList<>();
        for (salespost post : posts){
            salesresponsedto salesresponsedto = responsedto(post);
            Allposts.add(salesresponsedto);
        }
        return Allposts;
    }

    public List<salesresponsedto> findAllpostsbyCategory (PageRequest pageRequest,String category,String locate){
        List<salespost> posts= salesrepository.findByCategoryContainingAndLocateContaining(pageRequest, category,locate).getContent();
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
                salespost.getUseremail(),
                salespost.getCategory(),
                salespost.getLocate(),
                salespost.getUpdatetime(),
                salespost.getLikenum(),
                salespost.getChatnum(),
                salespost.getIspoststate(),
                salespost.getNickname(),
                salespost.getViewcount()
        );
    }

    public salespost findById(Long id){
        return salesrepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public salespost updateById (Long id, salesrequestdto salesrequestdto, userdto userdto){
        salespost targetpost = findById(id);
        return save(targetpost.update(salesrequestdto));
    }

    @Transactional
    public void deletepost (Long id){
        Optional<salespost> post= salesrepository.findById(id);
        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않은 게시글");
        }
        salesrepository.deleteById(id);
    }

    @Transactional
    public void viewCountUp(Long id){
        salesrepository.viewCountUp(id);
    }
}
