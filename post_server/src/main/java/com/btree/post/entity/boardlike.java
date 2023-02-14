package com.btree.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class boardlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardlikeid;

    @Column
    private Long postid;
    @Column
    private String username;
}
