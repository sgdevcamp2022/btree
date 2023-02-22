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
public class userdto {
    private String useremail;

    @Builder.Default
    private boolean gpsauth=false;

    private String locate;

    private String nickname;

}
