// package com.btree.post;

// import com.btree.post.dto.boardMapper;
// import com.btree.post.dto.commentMapper;
// import com.btree.post.dto.salesMapper;
// import com.btree.post.service.*;
// import com.btree.post.util.User;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;

// import java.util.stream.Collectors;

// import static org.springframework.http.ResponseEntity.ok;

// @SpringBootTest
// class PostApplicationTests {

// 	private final salesserviceimpl saleservice;

// 	private final salesMapper salesmapper;

// 	private final User user;
// 	private final saleslikeserviceimpl saleslikeservice;
// 	private final boardMapper boardmapper;
// 	private final boardserviceimpl boardservice;
// 	private final boardlikeserviceimpl boardlikeservice;
// 	private final commentserviceimpl commentservice;
// 	private final commentMapper commentmapper;
// 	private final com.btree.post.repository.boardrepository boardrepository;

// 	PostApplicationTests(@Autowired salesserviceimpl saleservice, @Autowired salesMapper salesmapper, @Autowired User user, @Autowired saleslikeserviceimpl saleslikeservice, @Autowired boardMapper boardmapper, @Autowired boardserviceimpl boardservice, @Autowired boardlikeserviceimpl boardlikeservice, @Autowired commentserviceimpl commentservice, @Autowired commentMapper commentmapper, @Autowired com.btree.post.repository.boardrepository boardrepository) {
// 		this.saleservice = saleservice;
// 		this.salesmapper = salesmapper;
// 		this.user = user;
// 		this.saleslikeservice = saleslikeservice;
// 		this.boardmapper = boardmapper;
// 		this.boardservice = boardservice;
// 		this.boardlikeservice = boardlikeservice;
// 		this.commentservice = commentservice;
// 		this.commentmapper = commentmapper;
// 		this.boardrepository = boardrepository;
// 	}


// 	@Test
// 	void contextLoads() {
// 		PageRequest sortByPostid=PageRequest.of(0, 1, Sort.by("boardpostid").descending());
// 		System.out.println(boardrepository.findAllByOrderByBoardpostidDesc(sortByPostid).getContent());
// 	}

// }
