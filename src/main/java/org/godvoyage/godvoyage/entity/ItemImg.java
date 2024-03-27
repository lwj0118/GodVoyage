package org.godvoyage.godvoyage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="item_img")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SequenceGenerator(name="itemimg_seq", sequenceName = "itemimg_seq", allocationSize = 1)
public class ItemImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "itemimg_seq")
    @Column(name = "item_img_id")
    private Long id;
    private String imgName;         //이미지이름. 경로로 통해 받은 파일명으로 저장
    private String oriImgName;      //원본이미지 이름
    private String imgUrl;          //이미지url 전체경로
    private String repimgYn;        //대표이미지 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;              //다대일관계

    //setter사용하지않고 update함
    public void update(String oriImgName, String imgName, String imgUrl) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.oriImgName = oriImgName;
    }

}
