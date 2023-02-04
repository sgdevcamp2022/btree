package com.btree.post.entity;

import com.btree.post.dto.boardrequestdto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static com.btree.post.entity.salesstate.SALE;

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
    @CreationTimestamp
    @Column
    private LocalDateTime updatetime;
    @Column
    private String locate;
    @Column
    @ColumnDefault("0")
    private int likenum;

    @Builder
    public boardpost(Long boardpostid, String title, String content, String contentimg, String username, int commentnum, LocalDateTime updatetime, String locate) {
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
