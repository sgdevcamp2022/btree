package com.btree.post.dto;

import com.btree.post.entity.salespost;
import org.springframework.stereotype.Service;



@Service
public class salesMapper {

    public salespost toEntity (salesrequestdto salesrequestdto){
        return salespost.builder()
                .useremail(salesrequestdto.getUseremail())
                .nickname(salesrequestdto.getNickname())
                .title(salesrequestdto.getTitle())
                .content(salesrequestdto.getContent())
                .salesimg(salesrequestdto.getSalesimg())
                .category(salesrequestdto.getCategory())
                .locate(salesrequestdto.getLocate())
                .price(salesrequestdto.getPrice())
                .ispoststate(salesrequestdto.getIspoststate())
                .build();

    }

    public salesresponsedto fromEntity (salespost salespost){
        return salesresponsedto.builder()
                .salespostid(salespost.getSalespostid())
                .title(salespost.getTitle())
                .content(salespost.getContent())
                .salesimg(salespost.getSalesimg())
                .price(salespost.getPrice())
                .useremail(salespost.getUseremail())
                .nickname(salespost.getNickname())
                .category(salespost.getCategory())
                .locate(salespost.getLocate())
                .updatetime(salespost.getUpdatetime())
                .likenum(salespost.getLikenum())
                .chatnum(salespost.getChatnum())
                .ispoststate(salespost.getIspoststate())
                .viewcount(salespost.getViewcount())
                .build();
    }

}
