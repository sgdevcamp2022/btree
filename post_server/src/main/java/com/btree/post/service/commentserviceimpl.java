package com.btree.post.service;

import com.btree.post.dto.commentresponsedto;
import com.btree.post.entity.boardcomment;
import com.btree.post.repository.boardrepository;
import com.btree.post.repository.commentrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    public List<commentresponsedto> findAllCommentbyPostid (PageRequest pageRequest,int postid){
        List<boardcomment> posts= commentrepository.findByBoardpostid(pageRequest,postid).getContent();
        List<commentresponsedto> Allposts=new ArrayList<>();
        for (boardcomment post : posts){
            commentresponsedto commentresponsedto = responsedto(post);
            Allposts.add(commentresponsedto);
        }
        return Allposts;
    }

    private commentresponsedto responsedto (boardcomment boardcomment){
        return new commentresponsedto(
                boardcomment.getBoardcommentid(),
                boardcomment.getContent(),
                boardcomment.getBoardpostid(),
                boardcomment.getUsername(),
                boardcomment.getCommenttime()
        );
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
