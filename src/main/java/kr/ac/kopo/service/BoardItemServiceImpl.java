package kr.ac.kopo.service;

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
	public void create(BoardItem boardItem) {
		dao.create(boardItem);
	}

	@Override
	public void update(BoardItem boardItem) {
		dao.update(boardItem);
	}

	@Override
	public void delete(BoardItem boardItem) {
		dao.delete(boardItem);
	}

	@Override
	public Optional<BoardItem> viewOne(int id) {
		return dao.selectOne(id);
	}

	@Override
	public List<BoardItem> viewAll(int board_id) {
		return dao.selectAll(board_id);
	}

	@Override
	public List<BoardItem> selectOnePageResult(int board_id, int page_num) {
		return dao.selectOnePageResult(board_id, page_num);
	}

}
