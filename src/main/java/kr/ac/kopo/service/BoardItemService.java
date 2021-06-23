package kr.ac.kopo.service;

import java.util.*;

import kr.ac.kopo.domain.BoardItem;

public interface BoardItemService {
	BoardItem create(String author, String title, String content, int board_id) throws Exception;
	BoardItem create(BoardItem boardItem) throws Exception;
	BoardItem update(String author, String title, String content) throws Exception;
	void delete(int post_id) throws Exception;
	BoardItem viewOne(int post_id) throws Exception;
	List<BoardItem> viewAll(int board_id) throws Exception;
	List<BoardItem> viewAll() throws Exception;
	List<BoardItem> viewOnePageResult(int board_id, int page_num) throws Exception;
	String getCurrentDate() throws Exception;
	int newPostId() throws Exception;
	int findBoardIdByPostId(int post_id) throws Exception;
}
