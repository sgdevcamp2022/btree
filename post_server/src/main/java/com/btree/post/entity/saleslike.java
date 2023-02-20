package com.btree.post.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class saleslike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleslikeid;
    @Column
    private Long postid;
    @Column
    private String username;
}
