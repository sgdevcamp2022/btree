package com.btree.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class commentresponsedto {
    private Long boardcommentid;

    private String content;

    private int boardpostid;

    private String username;

    private LocalDateTime commenttime;
}
