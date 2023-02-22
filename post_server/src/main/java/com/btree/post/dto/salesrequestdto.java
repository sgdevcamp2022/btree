package com.btree.post.dto;

import com.btree.post.entity.salesstate;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class salesrequestdto {

    private String title;

    private String content;

    private String salesimg;

    private String category;

    private int price;

    private salesstate ispoststate;

    private String useremail;

    @Builder.Default
    private boolean gpsauth=false;

    private String locate;

    private String nickname;

}
