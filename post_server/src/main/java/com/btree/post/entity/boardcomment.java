package com.btree.post.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class boardcomment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardcommentid;
    @Column
    private String content;
    @Column
    private int boardpostid;
    @Column
    private String username;
    @CreationTimestamp
    @Column
    private LocalDateTime commenttime;
}
