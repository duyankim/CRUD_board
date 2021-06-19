package kr.ac.kopo.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import kr.ac.kopo.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {
	public static BoardItemDaoImpl instance = new BoardItemDaoImpl();

	private BoardItemDaoImpl() {
		
	}
	
	public static BoardItemDaoImpl getInstance() {
		if (instance == null) {
			instance = new BoardItemDaoImpl();
		}
		return instance;
	}
	
	@Override
	public BoardItem create(BoardItem boardItem) {
		String sql = "insert into board_item_table(content, author) values(?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardItem item = new BoardItem();

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://192.168.23.27:3306/boardsystem", 
					"root", 
					"kopoctc");
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, item.getContent());
			pstmt.setString(2, item.getAuthor());
			pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();

			if (rset.next()) {
				item.setId(rset.getInt(1));
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}
			return item;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public BoardItem selectOne(int id) {
		return null;
	}

	@Override
	public List<BoardItem> selectAll() {
		return null;
	}

	@Override
	public void update(BoardItem boardItem) {
		
	}

	@Override
	public void delete(BoardItem boardItem) {
		
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
