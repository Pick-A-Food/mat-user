package com.matjo.pickafood.user.service;

import com.matjo.pickafood.user.dto.BoardDTO;
import com.matjo.pickafood.user.dto.PageRequestDTO;
import com.matjo.pickafood.user.dto.PageResponseDTO;

public interface BoardService {

    Integer register(BoardDTO boardDTO);//등록

    BoardDTO readOne(Integer bno); //상세조회

    void modifyOne(BoardDTO boardDTO); //수정

    void removeOne(Integer bno); //삭제

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO); //보드 목록
}
