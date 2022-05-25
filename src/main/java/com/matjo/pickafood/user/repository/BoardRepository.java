package com.matjo.pickafood.user.repository;

import com.matjo.pickafood.user.entity.Board;
import com.matjo.pickafood.user.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardSearch {


}
