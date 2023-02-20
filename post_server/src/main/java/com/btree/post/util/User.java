package com.btree.post.util;

import lombok.*;
import org.springframework.stereotype.Service;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
@Setter
@Builder
public class User {

    private String useremail;
    @Builder.Default
    private boolean gpsauth=false;
    private String locate;
    private String nickname;

    @Override
    public String toString() {
        return "User{" +
                "useremail='" + useremail + '\'' +
                ", gpsauth=" + gpsauth +
                ", locate='" + locate + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
