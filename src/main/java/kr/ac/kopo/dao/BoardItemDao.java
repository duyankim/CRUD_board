package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.domain.BoardItem;

public interface BoardItemDao {
	BoardItem create(BoardItem boardItem);
	BoardItem selectOne(int id);
	List<BoardItem> selectAll();
	void update(BoardItem boardItem);
	void delete(BoardItem boardItem);
}
