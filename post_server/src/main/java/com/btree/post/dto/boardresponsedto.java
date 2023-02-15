package com.btree.post.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class boardresponsedto {
    private Long boardpostid;

    private String title;

    private String content;

    private String contentimg;

    private String username;

    private int commentnum;

    private Timestamp updatetime;

    private String locate;
}
