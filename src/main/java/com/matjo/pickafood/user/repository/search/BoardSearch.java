package com.matjo.pickafood.user.repository.search;

import com.matjo.pickafood.user.dto.BoardListReplyCountDTO;
import com.matjo.pickafood.user.dto.BoardListWithImageDTO;
import com.matjo.pickafood.user.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    void search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    Page<BoardListWithImageDTO> searchWithImage(String[] types, String keyword, Pageable pageable);

}
