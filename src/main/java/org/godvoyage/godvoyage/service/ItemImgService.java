package org.godvoyage.godvoyage.service;

import org.godvoyage.godvoyage.dto.ItemImgDTO;
import org.godvoyage.godvoyage.entity.ItemImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ItemImgService {

    //추상메소드. itemservice가 saveItemImg호출함.
    //저장
    void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception;

    //itemid에 해당하는 itemImg조회
    List<ItemImg> findByItemid(Long itemId);

    //수정하기
    void updateItemImg(Long itemImgId, MultipartFile multipartFile) throws Exception;

    //엔티티를 dto로 변환해주는 메소드 생성
    default ItemImgDTO entityToDto(ItemImg itemImg) {
        ItemImgDTO itemImgDTO = ItemImgDTO.builder()
                .id(itemImg.getId())
                .imgName(itemImg.getImgName())
                .imgUrl(itemImg.getImgUrl())
                .oriImgName(itemImg.getOriImgName())
                .repImgYn(itemImg.getRepimgYn())
                .build();
        return itemImgDTO; 
    }
}
