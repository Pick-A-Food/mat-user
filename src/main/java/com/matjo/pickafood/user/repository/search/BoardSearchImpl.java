package com.matjo.pickafood.user.repository.search;

import com.matjo.pickafood.user.entity.Board;
import com.matjo.pickafood.user.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public void search1(Pageable pageable) {
        log.info("search1------------------");

        QBoard board = QBoard.board;
        //QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        //query.leftJoin(reply).on(reply.board.eq(board));

        //query.select 를 하면 Tuple 이 나옴
        JPQLQuery<Tuple> tupleQuery = query.select(board.boardSeq, board.title, board.nickname ); //reply.count()
        tupleQuery.groupBy(board);

        List<Tuple> tupleList = tupleQuery.fetch();

        //페이징
        getQuerydsl().applyPagination(pageable, tupleQuery);

        long totalCount = tupleQuery.fetchCount();

        List<Object[]> arr =
                tupleList.stream().map(tuple -> tuple.toArray())
                        .collect(Collectors.toList());
        //tuple 은 가져오지 않는다. object 로 바꾼 후 프로덕스의 bean 을 이용하여 dto 로 바꿔서 사용하기

        // query.select - 원하는 것 뽑아내기
        // query.where - ㅇㅇ가 true 인 애들..
        // query.fetchCount(); - 자동으로 카운트는 안해주니 직접 가져와야 함

    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        //types 가 널이 아닐 때만 체킹하기
        if(types != null){
            // com.querydsl.core. 로 import 하기
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type:types){
                if(type.equals("t")){
                    booleanBuilder.or(board.title.contains(keyword));
                }else if(type.equals("c")){
                    booleanBuilder.or(board.content.contains(keyword));
                }else if(type.equals("n")){
                    booleanBuilder.or(board.nickname.contains(keyword));
                }
            }//end for
            query.where(booleanBuilder);
        }//end if
        query.where(board.boardSeq.gt(0));
        // board.bno 가 0 보다 크다는 조건식

        //실제로 데이터를 가져오는 게 fetch
        //실제 데이터 수를 가져오는 게 fetchCount

        //페이징 처리
        getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();
        // 데이터가 많을 수 있기 때문에 int 사용 안하고 보통 long 을 사용한다.

        return new PageImpl<>(list, pageable, count);
    }

}
