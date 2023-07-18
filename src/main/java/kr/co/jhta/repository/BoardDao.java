package kr.co.jhta.repository;

import kr.co.jhta.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BoardDao {

    void insertBoard(Board board);
    List<Board> findAllBoardsPaginated(Map<String, Objects> params);
    Board findById(int id);
    void update(Board board); // column `deleted` in the table of `Board` is to be changed as `Y`
}
