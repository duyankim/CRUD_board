package kr.ac.kopo.dao;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.domain.BoardItem;

public interface BoardItemDao {
	BoardItem create(BoardItem boardItem) throws Exception;
	BoardItem createComment(BoardItem boardItem) throws Exception;
	BoardItem update(BoardItem boardItem) throws Exception;
	void delete(BoardItem boardItem) throws Exception;
	Optional<BoardItem> selectOne(int post_id) throws Exception;
	List<BoardItem> selectAll(int board_id) throws Exception;
	List<BoardItem> selectAllComment(int parent_id) throws Exception;
	List<BoardItem> selectAll() throws Exception;
	List<BoardItem> selectOnePageResult(int board_id, int page_num) throws Exception;
	int newPostId() throws Exception;
	int findBoardIdByPostId(int post_id) throws Exception;
	int findReorderNum(int parent_id, int relevel) throws Exception;
}
