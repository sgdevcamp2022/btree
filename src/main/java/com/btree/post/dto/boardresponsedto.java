package com.btree.post.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime updatetime;

    private String locate;
}
