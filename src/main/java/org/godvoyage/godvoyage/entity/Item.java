package org.godvoyage.godvoyage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.godvoyage.godvoyage.constant.ItemSellStatus;
import org.godvoyage.godvoyage.dto.ItemDTO;

@Entity
@Table(name="item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SequenceGenerator(name="item_seq", sequenceName = "item_seq", allocationSize = 1)
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    private Long id;                //지역 id

    @Column(nullable = false, length = 400)
    private String itemNm;          //지역명

    @Column(nullable = false)
    private int price;              //가격

    @Lob
    @Column(nullable = false)
    private String itemDetail;      //상세설명

    @Column(nullable = false)
    private int stockNumber;        //재고수량

    @Enumerated(EnumType.STRING)         //ENUM타입을 문자로 리턴
    private ItemSellStatus itemSellStatus; 	//상품판매상태==> SELL, SOLD_OUT

    //상품수정하기
    public void updateItem(ItemDTO dto){
        this.itemNm = dto.getItemNm();
        this.itemDetail = dto.getItemDetail();
        this.price = dto.getPrice();
        this.stockNumber = dto.getStockNumber();
        this.itemSellStatus = dto.getItemSellStatus();
    }

}
