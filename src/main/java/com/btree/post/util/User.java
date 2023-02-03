package com.btree.post.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
@Setter
public class User {

    private String username;

    private String locate;

    @Override
    public String toString() {
        return "userutil{" +
                "username='" + username + '\'' +
                ", userlocate='" + locate + '\'' +
                '}';
    }
}
