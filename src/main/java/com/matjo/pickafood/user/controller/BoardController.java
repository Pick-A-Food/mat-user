package com.matjo.pickafood.user.controller;

import com.matjo.pickafood.user.dto.BoardDTO;
import com.matjo.pickafood.user.dto.PageRequestDTO;
import com.matjo.pickafood.user.dto.PageResponseDTO;
import com.matjo.pickafood.user.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET() {

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO
                               // validation 진행 할거야
            , BindingResult bindingResult
            , RedirectAttributes rttr) {

        log.info("");

        if(bindingResult.hasErrors()){
            log.info("has errors.........");
            rttr.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Integer bno = boardService.register(boardDTO);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Integer bno, PageRequestDTO pageRequestDTO, Model model){
        log.info("read: "+bno);
        log.info("read: "+pageRequestDTO);

        BoardDTO boardDTO = boardService.readOne(bno);
        log.info("boardDTO[readOne]: "+boardDTO);

        model.addAttribute("dto", boardDTO);
        model.addAttribute("list",pageRequestDTO);
    }

}

