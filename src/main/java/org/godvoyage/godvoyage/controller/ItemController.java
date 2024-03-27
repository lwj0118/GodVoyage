package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.ItemDTO;
import org.godvoyage.godvoyage.dto.PageRequestDTO;
import org.godvoyage.godvoyage.dto.PageResultDTO;
import org.godvoyage.godvoyage.entity.Item;
import org.godvoyage.godvoyage.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    //상품 추가
    @GetMapping("/admin/item/new")
    public String itemadd(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
        return "/item/itemadd";
    }
    //상품 등록 시 이미지도 함께 들어가야 함
    @PostMapping("/admin/item/new")
    // itemImgFile: File타입의 input이름
    public String itemadd(ItemDTO itemDTO
            , @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) throws Exception {

        //첫번째 이미지 파일이 없을 경우 다시 등록페이지로
        if(itemImgFileList.get(0).isEmpty()) {
            model.addAttribute("errorMessage"
                    , "첫번째 이미지는 필수");
            return "/item/itemadd";
        }
        itemService.saveItem(itemDTO, itemImgFileList);
        System.out.println(itemDTO.toString());
        return "redirect:/";
    }

    //관리자 상품관리 pageRequestDTO를 통해 몇번째 페이지인지 전달받음.
    // 관리자 계정으로 /admin/itemlist으로 들어갔을 때 전체 상품 조회
    @GetMapping("/admin/itemlist")
    public String itemList(Model model, PageRequestDTO pageRequestDTO) {
        PageResultDTO<ItemDTO, Item> result = itemService.getList(pageRequestDTO);
        //model에 result타입에 result담아라
        model.addAttribute("result", result);
        return  "/item/itemlist";
    }

    //상품 하나 조회
    @GetMapping("/admin/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {
        ItemDTO itemDTO = itemService.getItem(itemId);
        model.addAttribute("itemDTO", itemDTO);
        System.out.println(itemDTO.toString());
        return "item/itemdetail";
    }
}
