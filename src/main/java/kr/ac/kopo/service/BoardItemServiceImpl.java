package kr.ac.kopo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import kr.ac.kopo.dao.BoardItemDaoImpl;
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
	public BoardItem create(String author, String title, String content, int board_id) {
		BoardItem boardItem = new BoardItem(author, title, content, board_id);
		return dao.create(boardItem);
	}

	@Override
	public BoardItem create(int parent_id, int board_id, String author, String content, int relevel){
		BoardItem boardItem = new BoardItem(parent_id, board_id, content, author, relevel);
		return dao.createComment(boardItem);
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
	public void delete(int post_id) {
		dao.delete(viewOne(post_id));
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

	@Override
	public int findBoardIdByPostId(int post_id) throws Exception {
		return dao.findBoardIdByPostId(post_id);
	}

	@Override
	public List<BoardItem> viewAllComment(int parent_id) throws Exception {
		return dao.selectAllComment(parent_id);
	}
	
	@Override
	public List<BoardItem> viewRelevelComments(int relevel, int post_id) {
		List<BoardItem> comments = null;
		try {
			comments = viewAllComment(post_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BoardItem> relevelComments = comments.stream().filter(c -> c.getRelevel() == 1).collect(Collectors.toList());
		return relevelComments;
	}

	@Override
	public List<BoardItem> viewSearchPost(String word) throws Exception {
		List<BoardItem> allPosts = dao.selectAll();
		List<BoardItem> matchedPosts = allPosts.stream()
				.filter(p -> 
						p.getTitle().toUpperCase()
						.contains(word.toUpperCase())
				).collect(Collectors.toList());
		return matchedPosts;
	}
}
