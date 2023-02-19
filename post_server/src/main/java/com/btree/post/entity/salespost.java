package com.btree.post.entity;

import com.btree.post.dto.salesrequestdto;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static com.btree.post.entity.salesstate.SALE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salespost")
@Entity
public class salespost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salespostid;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String salesimg;
    @Column
    private int price;
    @Column
    private String useremail;
    @Column
    private String nickname;
    @Column
    private String category;
    @Column
    private String locate;
    @CreationTimestamp
    @Column
    private LocalDateTime updatetime;
    @Column
    @ColumnDefault("0")
    private int likenum;
    @Column
    @ColumnDefault("0")
    private int chatnum;
    @Enumerated(EnumType.STRING)@Setter
    @Column
    private salesstate ispoststate;
    @Column
    @ColumnDefault("0")
    private int viewcount;

    @Builder
    public salespost(Long salespostId, String title, String content, String salesimg, int price, String useremail, String category, String locate, salesstate ispoststate,String nickname) {
        this.salespostid = salespostid;
        this.title = title;
        this.content = content;
        this.salesimg = salesimg;
        this.price = price;
        this.useremail = useremail;
        this.category = category;
        this.locate = locate;
        this.ispoststate=ispoststate;
        this.nickname=nickname;
    }

    public salespost update(salesrequestdto salesrequestdto){
        this.title=salesrequestdto.getTitle();
        this.content=salesrequestdto.getContent();
        this.salesimg=salesrequestdto.getSalesimg();
        this.category=salesrequestdto.getCategory();
        this.price=salesrequestdto.getPrice();
        this.ispoststate=salesrequestdto.getIspoststate();
        return this;
    }

    @PrePersist
    public void prePersist(){
        this.useremail=this.useremail==null?"empty":this.useremail;
        this.locate=this.locate==null?"empty":this.locate;
        this.ispoststate=this.ispoststate==null?SALE:this.ispoststate;
        this.nickname=this.nickname==null?"empty":this.nickname;
    }

    @Override
    public String toString() {
        return "salespost{" +
                "salespostid=" + salespostid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", salesimg='" + salesimg + '\'' +
                ", price=" + price +
                ", useremail='" + useremail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", category='" + category + '\'' +
                ", locate='" + locate + '\'' +
                ", updatetime=" + updatetime +
                ", likenum=" + likenum +
                ", chatnum=" + chatnum +
                ", ispoststate=" + ispoststate +
                ", viewcount=" + viewcount +
                '}';
    }

    public void viewCountUp(salespost salespost){
        salespost.viewcount++;
    }
}