package kr.ac.kopo.dao;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.domain.BoardItem;

public interface BoardItemDao {
	BoardItem create(BoardItem boardItem) throws Exception;
	void update(BoardItem boardItem) throws Exception;
	void delete(BoardItem boardItem) throws Exception;
	Optional<BoardItem> selectOne(int id) throws Exception;
	List<BoardItem> selectAll(int board_id) throws Exception;
	List<BoardItem> selectOnePageResult(int board_id, int page_num) throws Exception;
}
