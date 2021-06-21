package kr.ac.kopo.service;

import java.util.*;

import kr.ac.kopo.domain.BoardItem;

public interface BoardItemService {
	void create(BoardItem boardItem) throws Exception;
	void update(BoardItem boardItem) throws Exception;
	void delete(BoardItem boardItem) throws Exception;
	Optional<BoardItem> viewOne(int id) throws Exception;
	List<BoardItem> viewAll(int board_id) throws Exception;
	List<BoardItem> selectOnePageResult(int board_id, int page_num);
}
