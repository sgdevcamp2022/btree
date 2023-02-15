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

    private String username;
    private boolean gpsauth=false;
    private String locate;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", gpsauth=" + gpsauth +
                ", locate='" + locate + '\'' +
                '}';
    }
}
