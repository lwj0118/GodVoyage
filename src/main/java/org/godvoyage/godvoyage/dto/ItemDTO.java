package org.godvoyage.godvoyage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.godvoyage.godvoyage.constant.ItemSellStatus;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    //메인페이지 상단 상위 지역 페이지 dto
    private Long id;                //지역 id
    private String itemNm;          //지역명
    private int price;              //가격
    private String itemDetail;      //상세설명
    private int stockNumber;        //재고수량
    private ItemSellStatus itemSellStatus;
    private List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();
}
