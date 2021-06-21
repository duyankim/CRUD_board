package kr.ac.kopo.service;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.dao.BoardDao;
import kr.ac.kopo.dao.BoardDaoImpl;
import kr.ac.kopo.domain.Board;

public class BoardServiceImpl implements BoardService {
	public static BoardServiceImpl instance = new BoardServiceImpl();
	BoardDaoImpl dao = BoardDaoImpl.getInstance();

	private BoardServiceImpl() {
		
	}
	
	public static BoardServiceImpl getInstance() {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	@Override
	public void create(Board board) {
		dao.create(board);
	}

	@Override
	public void update(Board board) {
		dao.update(board);
	}

	@Override
	public void delete(Board board) {
		dao.delete(board);
	}

	@Override
	public Optional<Board> viewOne(int id) {
		return dao.selectOne(id);
	}

	@Override
	public List<Board> viewAll() {
		return dao.selectAll();
	}

	@Override
	public String[] viewAllTitles() throws Exception {
		List<String> titleList = dao.selectAllTitles();
		String[] titles = new String[titleList.size()];
		for (int i = 0; i < titles.length; i++) {
			titles[i] =  titleList.get(i);
		}
		return titles;
	}

}
