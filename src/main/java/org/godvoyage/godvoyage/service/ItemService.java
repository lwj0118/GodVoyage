package org.godvoyage.godvoyage.service;


import org.godvoyage.godvoyage.dto.ItemDTO;
import org.godvoyage.godvoyage.dto.MainItemDTO;
import org.godvoyage.godvoyage.dto.PageRequestDTO;
import org.godvoyage.godvoyage.dto.PageResultDTO;
import org.godvoyage.godvoyage.entity.Item;
import org.godvoyage.godvoyage.entity.ItemImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    //상품(item) 저장
    Long saveItem(ItemDTO itemDTO, List<MultipartFile> itemImgFileList) throws Exception;

    //아이템 조회(페이징조회)
    PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO);

    //아이템 하나만 상세조회
    ItemDTO getItem(Long itemid);

    
    //메인에서 상품조회
   //PageResultDTO<MainItemDTO, Object[]> getMainList(PageRequestDTO requestDTO);
    
   default Item dtoToEntity(ItemDTO dto){
        Item item = Item.builder()
                .itemNm(dto.getItemNm())
                .itemDetail(dto.getItemDetail())
                .stockNumber(dto.getStockNumber())
                .price(dto.getPrice())
                .itemSellStatus(dto.getItemSellStatus())
                .build();
        return item;
    }

    //엔티티를 dto로 변환
    default ItemDTO entityToDto(Item item){
        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .itemNm(item.getItemNm())
                .itemDetail(item.getItemDetail())
                .price(item.getPrice())
                .stockNumber(item.getStockNumber())
                .itemSellStatus(item.getItemSellStatus())
                .build();
        return itemDTO;
    }
    //object배열 MainItemDTO로 변환
    default MainItemDTO  entityObjToDTO(Item item , ItemImg itemImg){
        MainItemDTO mainDTO = MainItemDTO.builder()
                .id(item.getId())
                .itemNm(item.getItemNm())
                .stockNumber(item.getStockNumber())
                .price(item.getPrice())
                .itemSellStatus(item.getItemSellStatus())
                .imgUrl(itemImg.getImgUrl())
                .build();
        return mainDTO;
    }
}
