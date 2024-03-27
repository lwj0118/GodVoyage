package org.godvoyage.godvoyage.service;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.entity.ItemImg;
import org.godvoyage.godvoyage.repository.ItemImgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemImgServiceImpl implements  ItemImgService {
    @Value("${itemImgLocation}")
    private String itemImgLocation;
    private final FileService fileService;
    private final ItemImgRepository itemImgRepository;

    //파일 업로드, itemImg엔티티영속저장
    @Override
    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        //uploadFile(경로, 원본이미지이름, 파일)파일이 바이트 형태로 받아짐. 예외처리 지정.
        String oriImgName = itemImgFile.getOriginalFilename();
        //비어있지 않을 때 파일업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            //imgName<===dog.jpg--->sdfsdfwe.jpg
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName
                    , itemImgFile.getBytes());
            // /images/item/sdfsdfwe.jpg
            String imgUrl = "/images/item/" + imgName;
            //엔티티 필드값 입력
            itemImg.update(oriImgName, imgName, imgUrl);
            //엔티티 영속저장
            itemImgRepository.save(itemImg);
        }

    }

  @Override
    public List<ItemImg> findByItemid(Long itemId) {
      List<ItemImg> itemImgList = itemImgRepository.findByItemId(itemId);
        return itemImgList;
    }

    @Override
    public void updateItemImg(Long itemImgId, MultipartFile multipartFile) throws Exception {
        Optional<ItemImg> result = itemImgRepository.findById(itemImgId);
        ItemImg itemImg = result.get();
        //이미지 이름이 비어있지 않으면 해당 파일 삭제
        String oriImgName = multipartFile.getOriginalFilename();
        System.out.println("이미지이름 "+ oriImgName+"수정이름");
        if(!StringUtils.isEmpty(itemImg.getImgName())){
            fileService.deleteFile(itemImgLocation+"/"+ itemImg.getImgName());
        }
        //이미지 업로드

        if(!StringUtils.isEmpty(oriImgName)) {
            String imgName = fileService.uploadFile(itemImgLocation,
                    oriImgName, multipartFile.getBytes());
            //상품이미지
            String imgUrl = "/images/item/" + imgName;
            itemImg.update(oriImgName, imgName, imgUrl);
            itemImgRepository.save(itemImg);
        }

    }
}