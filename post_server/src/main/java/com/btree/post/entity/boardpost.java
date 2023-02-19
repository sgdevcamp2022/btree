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
    private String nickname;
    @Column
    private String useremail;
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

    @Column
    @ColumnDefault("0")
    private int viewcount;

    @Builder
    public boardpost(Long boardpostid, String title, String content, String contentimg, String useremail, int commentnum, Timestamp updatetime, String locate,String nickname) {
        this.boardpostid = boardpostid;
        this.title = title;
        this.content = content;
        this.contentimg = contentimg;
        this.useremail = useremail;
        this.updatetime = updatetime;
        this.locate = locate;
        this.nickname=nickname;
    }

    public boardpost update(boardrequestdto boardrequestdto){
        this.title=boardrequestdto.getTitle();
        this.content=boardrequestdto.getContent();
        this.contentimg=boardrequestdto.getContentimg();
        return this;
    }

    @PrePersist
    public void prePersist(){
        this.useremail=this.useremail==null?"empty":this.useremail;
        this.locate=this.locate==null?"empty":this.locate;
    }

    @Override
    public String toString() {
        return "boardpost{" +
                "boardpostid=" + boardpostid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentimg='" + contentimg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", useremail='" + useremail + '\'' +
                ", commentnum=" + commentnum +
                ", updatetime=" + updatetime +
                ", locate='" + locate + '\'' +
                ", likenum=" + likenum +
                ", viewcount=" + viewcount +
                '}';
    }
}
