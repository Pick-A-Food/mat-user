package com.matjo.pickafood.user.repository;

import com.matjo.pickafood.user.entity.Board;
import com.matjo.pickafood.user.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardSearch {

    List<Board> findByTitleContaining(String keyword);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);


    @EntityGraph(attributePaths = "boardImages")
    @Query("select b from Board b")
    Page<Board> getList1(Pageable pageable);
    
}
