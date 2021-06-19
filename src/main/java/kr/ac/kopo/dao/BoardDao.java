package kr.ac.kopo.dao;

import java.util.List;
import java.util.Optional;

import kr.ac.kopo.domain.Board;

public interface BoardDao {
	Board create(Board board) throws Exception;
	Optional<Board> selectOne(int id) throws Exception;
	List<Board> selectAll() throws Exception;
	Board update(Board board) throws Exception;
	void delete(Board board) throws Exception;
}

