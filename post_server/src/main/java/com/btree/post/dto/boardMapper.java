package com.btree.post.dto;

import com.btree.post.entity.boardpost;
import org.springframework.stereotype.Service;

@Service
public class boardMapper {

    public boardpost toEntity(boardrequestdto boardrequestdto){
        return boardpost.builder()
                .title(boardrequestdto.getTitle())
                .useremail(boardrequestdto.getUseremail())
                .nickname(boardrequestdto.getNickname())
                .content(boardrequestdto.getContent())
                .contentimg(boardrequestdto.getContentimg())
                .locate(boardrequestdto.getLocate())
                .build();
    }

    public boardresponsedto fromEntity(boardpost boardpost){
        return boardresponsedto.builder()
                .title(boardpost.getTitle())
                .boardpostid(boardpost.getBoardpostid())
                .content(boardpost.getContent())
                .contentimg(boardpost.getContentimg())
                .nickname(boardpost.getNickname())
                .useremail(boardpost.getUseremail())
                .commentnum(boardpost.getCommentnum())
                .updatetime(boardpost.getUpdatetime())
                .locate(boardpost.getLocate())
                .likenum(boardpost.getLikenum())
                .viewcount(boardpost.getViewcount())
                .build();
    }

}
