package com.btree.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salespost")
@Entity
public class salespost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long salespost_id;
    @Column
    private String title;
    @Column
    private String salesimg;
    @Column
    private int price;
    @Column
    private String username;
    @Column
    private String category;
    @Column
    private String locate;
    @CreationTimestamp
    @Column
    private LocalDateTime update;
    @Column
    private int likenum;
    @Column
    private int chatnum;

    @Builder
    public salespost(Long salespost_id, String title, String salesimg, int price, String username, String category, String locate) {
        this.salespost_id = salespost_id;
        this.title = title;
        this.salesimg = salesimg;
        this.price = price;
        this.username = username;
        this.category = category;
        this.locate = locate;
    }

    @Override
    public String toString() {
        return "salespost{" +
                "salespost_id=" + salespost_id +
                ", title='" + title + '\'' +
                ", salesimg='" + salesimg + '\'' +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", category='" + category + '\'' +
                ", locate='" + locate + '\'' +
                ", update=" + update +
                ", likenum=" + likenum +
                ", chatnum=" + chatnum +
                '}';
    }
}
