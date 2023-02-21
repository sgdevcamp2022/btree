package com.btree.post.dto;

import com.btree.post.entity.boardcomment;
import org.springframework.stereotype.Service;

@Service
public class commentMapper {
    public boardcomment toEntity(commentrequestdto commentrequestdto){
        return boardcomment.builder()
                .content(commentrequestdto.getContent())
                .username(commentrequestdto.getUsername())
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
