package com.btree.post.dto;

import com.btree.post.entity.boardpost;
import com.btree.post.util.User;
import org.springframework.stereotype.Service;

@Service
public class boardMapper {

    public boardpost toEntity(boardrequestdto boardrequestdto, User user){
        return boardpost.builder()
                .title(boardrequestdto.getTitle())
                .username(user.getUsername())
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
                .username(boardpost.getUsername())
                .commentnum(boardpost.getCommentnum())
                .updatetime(boardpost.getUpdatetime())
                .locate(boardpost.getLocate())
                .build();
    }

}
