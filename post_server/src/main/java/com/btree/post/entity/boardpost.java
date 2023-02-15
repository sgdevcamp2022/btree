package com.btree.post.entity;

import com.btree.post.dto.boardrequestdto;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boardpost")
@Entity
public class boardpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardpostid;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String contentimg;
    @Column
    private String username;
    @Column
    @ColumnDefault("0")
    private int commentnum;
    @UpdateTimestamp
    @Column(name="updatetime",nullable = false, insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private Timestamp updatetime;
    @Column
    private String locate;
    @Column
    @ColumnDefault("0")
    private int likenum;

    @Builder
    public boardpost(Long boardpostid, String title, String content, String contentimg, String username, int commentnum, Timestamp updatetime, String locate) {
        this.boardpostid = boardpostid;
        this.title = title;
        this.content = content;
        this.contentimg = contentimg;
        this.username = username;
        this.updatetime = updatetime;
        this.locate = locate;
    }

    public boardpost update(boardrequestdto boardrequestdto){
        this.title=boardrequestdto.getTitle();
        this.content=boardrequestdto.getContent();
        this.contentimg=boardrequestdto.getContentimg();
        return this;
    }

    @PrePersist
    public void prePersist(){
        this.username=this.username==null?"empty":this.username;
        this.locate=this.locate==null?"empty":this.locate;
    }

    @Override
    public String toString() {
        return "boardpost{" +
                "boardpostid=" + boardpostid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentimg='" + contentimg + '\'' +
                ", username='" + username + '\'' +
                ", commentnum=" + commentnum +
                ", updatetime=" + updatetime +
                ", locate='" + locate + '\'' +
                ", likenum=" + likenum +
                '}';
    }
}
