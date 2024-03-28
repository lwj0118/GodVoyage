package org.godvoyage.godvoyage.service;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.*;

import org.godvoyage.godvoyage.entity.Item;

import org.godvoyage.godvoyage.entity.ItemImg;
import org.godvoyage.godvoyage.repository.ItemImgRepository;
import org.godvoyage.godvoyage.repository.ItemRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;

    @Override
    public Long saveItem(ItemDTO itemDTO, List<MultipartFile> itemImgFileList) throws Exception {
        //item영속저장
        Item item = dtoToEntity(itemDTO);
        itemRepository.save(item);

        //itemImg등록. Item item과 itemImg의 연관관계로 인해 for문에 넣음. 배열의 0번째가 대표이미지 됨.
        //파일은 각각 itemimg테이블에 저장됨.
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0) {
                itemImg.setRepimgYn("Y");
            } else {
                itemImg.setRepimgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Override
    public PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO) {
        //최종적으로 PageResultDTO를 리턴받아야 함. 컨트롤러에서 서비스호출하기
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Item> result = itemRepository.findAll(pageable);
        //Item의 변수명:entity. 람다식으로 !!
        Function<Item, ItemDTO> fn = (entity->entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    //일반 사용자가 여행상품 하나 상세 조회
    @Override
    public ItemDTO getItem(Long itemid) {
        //아이템조회하기
        Optional<Item> result= itemRepository.findById(itemid);
        //result.get() --- Item객체 반환
        //dto객체생성
        ItemDTO dto = entityToDto(result.get());
        //itemimg 리스트 조회
        List<ItemImg> itemImgList = itemImgService.findByItemid(itemid);
        //itemimg엔티티 리스트를 itemImgDTO 리스트로 변환
        //리스트 -> stream() -> map(itemImg -> itemImgDTO)   -> List<ItemImgDTO>
        List<ItemImgDTO> itemImgDTOList = itemImgList.stream().map(itemImg ->
                itemImgService.entityToDto(itemImg)).collect(Collectors.toList());
        dto.setItemImgDTOList(itemImgDTOList);
        return dto;
    }

    @Override
    public Long updateItem(ItemDTO itemDTO, List<MultipartFile> itemImgFileList) throws Exception {
        //아이템 조회. get()으로 받음
        Item item= itemRepository.findById(itemDTO.getId()).get();
        //item필드값 변경
        item.updateItem(itemDTO);
        //업데이트 하여 저장하기
        itemRepository.save(item);
        //itemimg 수정. itemDTO의 itemImgIDS와 맵핑
        List<Long> itemImgIds = itemDTO.getItemImgIds();
        for (int i = 0; i < itemImgFileList.size(); i++) {
            if(itemImgIds.get(i)==null) {
                System.out.println("이미지가 없습니다");
                //ItemImg필드를 넣기 위한 엔티티 생성
                ItemImg itemImg = new ItemImg();
                itemImg.setItem(item);
                if(i==0){
                    itemImg.setRepimgYn("Y");
                }else{
                    itemImg.setRepimgYn("N");
                }
                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            }else {
                //itemImgFileList.get(i)는 파일 전달.
                itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
            }
        }
        return item.getId();
    }

    //여행상품 9개 단위로 조회
    @Override
    public PageResultDTO<MainItemDTO, Object[]> getShopList(PageRequestDTO requestDTO) {
        Page<Object[]> result = itemRepository.getList(requestDTO.getPageable(Sort.by("id").descending())
                , requestDTO.getKeyword());
        Function<Object[], MainItemDTO> fn = (arr->{
            return entityObjToDTO((Item) arr[0], (ItemImg) arr[1]);
        });
        return new PageResultDTO<>(result,fn);
    }

   @Override
    public PageResultDTO<MainItemDTO, Object[]> getMainList(PageRequestDTO requestDTO) {
        Page<Object[]> result= itemRepository.getListPage(requestDTO.getPageable(Sort.by("id").descending()));
        Function<Object[], MainItemDTO> fn = (arr->{
            return entityObjToDTO((Item) arr[0], (ItemImg) arr[1]);
        });

        return new PageResultDTO<>(result,fn);
    }
}






