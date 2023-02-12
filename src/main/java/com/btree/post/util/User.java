package com.btree.post.util;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    private String address_name;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", gpsauth=" + gpsauth +
                ", locate='" + locate + '\'' +
                ", address_name='" + address_name + '\'' +
                '}';
    }
}
