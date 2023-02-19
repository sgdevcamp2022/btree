package com.btree.post.dto;

import com.btree.post.entity.salesstate;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class salesresponsedto {
    private Long salespostid;

    private String title;

    private String content;

    private String salesimg;

    private int price;

    private String useremail;

    private String category;

    private String locate;

    private LocalDateTime updatetime;

    private int likenum;

    private int chatnum;

    private salesstate ispoststate;

    private String nickname;

    private int viewcount;
}
