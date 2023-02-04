package com.btree.post.service;

import com.btree.post.dto.boardrequestdto;
import com.btree.post.dto.boardresponsedto;
import com.btree.post.entity.boardpost;
import com.btree.post.exception.NotFoundException;
import com.btree.post.repository.boardrepository;
import com.btree.post.util.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class boardserviceimpl implements boardservice {

    private final boardrepository boardrepository;
    private final User suer;

    public boardpost save (boardpost boardpost){
        return boardrepository.save(boardpost);
    }

    public List<boardresponsedto> findAllposts (PageRequest pageRequest){
        List<boardpost> posts= boardrepository.findAllByOrderByBoardpostidDesc(pageRequest).getContent();
        List<boardresponsedto> Allposts=new ArrayList<>();
        for (boardpost post : posts){
            boardresponsedto boardresponsedto = responsedto(post);
            Allposts.add(boardresponsedto);
        }
        return Allposts;
    }

    private boardresponsedto responsedto (boardpost boardpost){
        return new boardresponsedto(
                boardpost.getBoardpostid(),
                boardpost.getTitle(),
                boardpost.getContent(),
                boardpost.getContentimg(),
                boardpost.getUsername(),
                boardpost.getCommentnum(),
                boardpost.getUpdatetime(),
                boardpost.getLocate()
        );
    }

    public boardpost findById(Long id){
        return boardrepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public boardpost updateById(Long id, boardrequestdto boardrequestdto,User user){
        boardpost targetpost=findById(id);
        return save(targetpost.update(boardrequestdto));
    }

    public void deletePost(Long id){
        Optional<boardpost> post=boardrepository.findById(id);
        if(!post.isPresent()){
            throw new NullPointerException("유효하지 않은 게시글");
        }
        boardrepository.deleteById(id);
    }

}
