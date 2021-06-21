package kr.ac.kopo.service;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.domain.Board;

public interface BoardService {
	void create(Board board) throws Exception;
	void update(Board board) throws Exception;
	void delete(Board board) throws Exception;
	Optional<Board> viewOne(int id) throws Exception;
	List<Board> viewAll() throws Exception;
	String[] viewAllTitles() throws Exception;
}
