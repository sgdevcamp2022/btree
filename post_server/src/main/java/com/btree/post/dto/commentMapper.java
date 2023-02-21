package com.btree.post.dto;

import com.btree.post.entity.boardcomment;
import com.btree.post.util.User;
import org.springframework.stereotype.Service;

@Service
public class commentMapper {
    public boardcomment toEntity(commentrequestdto commentrequestdto, userdto userdto){
        return boardcomment.builder()
                .content(commentrequestdto.getContent())
                .username(userdto.getUseremail())
                .boardpostid(commentrequestdto.getBoardpostid())
                .build();
    }

    public commentresponsedto fromEntity(boardcomment boardcomment){
        return commentresponsedto.builder()
                .boardcommentid(boardcomment.getBoardcommentid())
                .commenttime(boardcomment.getCommenttime())
                .boardpostid(boardcomment.getBoardpostid())
                .content(boardcomment.getContent())
                .username(boardcomment.getUsername())
                .build();
    }
}
