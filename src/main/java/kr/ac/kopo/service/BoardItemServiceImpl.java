package kr.ac.kopo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import kr.ac.kopo.dao.*;
import kr.ac.kopo.domain.BoardItem;

public class BoardItemServiceImpl implements BoardItemService {
	public static BoardItemServiceImpl instance = new BoardItemServiceImpl();
	BoardItemDaoImpl dao = BoardItemDaoImpl.getInstance();

	private BoardItemServiceImpl() {
		
	}
	
	public static BoardItemServiceImpl getInstance() {
		if (instance == null) {
			instance = new BoardItemServiceImpl();
		}
		return instance;
	}

	@Override
	public BoardItem create(String author, String title, String content) {
		BoardItem boardItem = new BoardItem(author, title, content);
		return dao.create(boardItem);
	}
	
	@Override
	public BoardItem create(BoardItem boardItem) {
		return dao.create(boardItem);
	}

	@Override
	public BoardItem update(String author, String title, String content) {
		BoardItem boardItem = new BoardItem(author, title, content);
		return dao.update(boardItem);
	}

	@Override
	public void delete(BoardItem boardItem) {
		dao.delete(boardItem);
	}

	@Override
	public BoardItem viewOne(int post_id) {
		return dao.selectOne(post_id).get();
	}

	@Override
	public List<BoardItem> viewAll(int board_id) {
		return dao.selectAll(board_id);
	}
	
	@Override
	public List<BoardItem> viewAll() {
		return dao.selectAll();
	}

	@Override
	public List<BoardItem> viewOnePageResult(int board_id, int page_num) {
		return dao.selectOnePageResult(board_id, page_num);
	}

	@Override
	public int newPostId() throws Exception {
		return dao.newPostId();
	}

	@Override
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
