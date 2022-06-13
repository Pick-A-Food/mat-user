package com.matjo.pickafood.user.controller;

import com.matjo.pickafood.user.dto.FoodDTO;
import com.matjo.pickafood.user.dto.ListDTO;
import com.matjo.pickafood.user.dto.PageRequestDTO;
import com.matjo.pickafood.user.dto.PageResponseDTO;
import com.matjo.pickafood.user.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
@Log4j2
public class FoodController {

    private final FoodService foodService;
    @GetMapping("/list")
    public void listGET(PageRequestDTO pageRequestDTO, Model model) {
        log.info(pageRequestDTO);
        PageResponseDTO<FoodDTO> responseDTO = foodService.list(pageRequestDTO);
        responseDTO.getDtoList().forEach(foodDTO -> log.info(foodDTO));
        model.addAttribute("responseDTO", responseDTO);
    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<FoodDTO> listPOST(@RequestParam(value = "pageRequestDTO") PageRequestDTO pageRequestDTO, ListDTO<String> listDTO) {
        log.info(pageRequestDTO);
        PageResponseDTO<FoodDTO> responseDTO = foodService.list(pageRequestDTO);
        responseDTO.getDtoList().forEach(foodDTO -> log.info(foodDTO));
        return responseDTO;
    }

    @GetMapping("/listWithImage")
    public void listWithImage() {

    }


}
