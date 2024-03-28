package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.MainItemDTO;
import org.godvoyage.godvoyage.dto.PageRequestDTO;
import org.godvoyage.godvoyage.dto.PageResultDTO;
import org.godvoyage.godvoyage.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;
    @GetMapping("/")
    public String mainPage(Model model, PageRequestDTO requestDTO){
        PageResultDTO<MainItemDTO, Object[]> result = itemService.getMainList(requestDTO);
        model.addAttribute("result", result);
        List<MainItemDTO> list = result.getDtoList();
        for (MainItemDTO dto: list){
            System.out.println(dto.toString());
        }
        return "main";
    }
}
