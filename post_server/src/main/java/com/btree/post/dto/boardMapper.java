package com.btree.post.dto;

import com.btree.post.entity.boardpost;
import com.btree.post.util.User;
import org.springframework.stereotype.Service;

@Service
public class boardMapper {

    public boardpost toEntity(boardrequestdto boardrequestdto, User user){
        return boardpost.builder()
                .title(boardrequestdto.getTitle())
                .useremail(user.getUseremail())
                .nickname(user.getNickname())
                .content(boardrequestdto.getContent())
                .contentimg(boardrequestdto.getContentimg())
                .locate(user.getLocate())
                .build();
    }

    public boardresponsedto fromEntity(boardpost boardpost){
        return boardresponsedto.builder()
                .title(boardpost.getTitle())
                .boardpostid(boardpost.getBoardpostid())
                .content(boardpost.getContent())
                .contentimg(boardpost.getContentimg())
                .useremail(boardpost.getUseremail())
                .commentnum(boardpost.getCommentnum())
                .updatetime(boardpost.getUpdatetime())
                .locate(boardpost.getLocate())
                .likenum(boardpost.getLikenum())
                .viewcount(boardpost.getViewcount())
                .build();
    }

}
