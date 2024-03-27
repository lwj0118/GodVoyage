package org.godvoyage.godvoyage.dto;

import lombok.*;
import org.godvoyage.godvoyage.constant.ItemSellStatus;


@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainItemDTO {
    private Long id;
    private String itemNm;
    private int price;
    private String imgUrl;
    private int stockNumber;
    private ItemSellStatus itemSellStatus;

}
