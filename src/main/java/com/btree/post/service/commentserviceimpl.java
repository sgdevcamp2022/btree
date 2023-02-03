package com.btree.post.service;

import com.btree.post.dto.commentrequestdto;
import com.btree.post.entity.boardcomment;
import com.btree.post.entity.boardpost;
import com.btree.post.repository.boardrepository;
import com.btree.post.repository.commentrepository;
import com.btree.post.util.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class commentserviceimpl implements commentservice{
    private final commentrepository commentrepository;
    private final boardrepository boardrepository;

    @Transactional
    public boardcomment commentsave (boardcomment boardcomment){
        boardrepository.pluscommentnum((long) boardcomment.getBoardpostid());
        return commentrepository.save(boardcomment);
    }

    @Transactional
    public void deleteComment(Long commentid){
        Optional<boardcomment> comment = commentrepository.findById(commentid);
        if(!comment.isPresent()){
            throw new NullPointerException("유효하지 않은 댓글");
        }
        boardrepository.minuscommentnum((long) comment.get().getBoardpostid());
        commentrepository.deleteById(commentid);
    }
}
