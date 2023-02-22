package com.btree.post.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class boardrequestdto {
    private String title;

    private String content;

    private String contentimg;

    private String useremail;

    private String locate;

    private String nickname;

    @Builder.Default
    private boolean gpsauth=false;

}
