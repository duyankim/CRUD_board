package kr.ac.kopo.dao;

import java.sql.*;
import java.util.*;

import kr.ac.kopo.domain.Board;

public class BoardDaoImpl implements BoardDao{
	public static BoardDaoImpl instance = new BoardDaoImpl();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rset;
	static String dbURL = "jdbc:mysql://192.168.23.27:3306/boardsystem";
	static String dbID = "root";
	static String dbPassword = "kopoctc";
	
	public static BoardDaoImpl getInstance() {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}

	private BoardDaoImpl() {
		
	}

	@Override
	public Board create(Board board) {
		String sql = "insert into board_table(title) values(?)";
		Board b = new Board();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, board.getTitle());
			pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();

			if (rset.next()) {
				b.setId(rset.getInt(1));
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}
			return b;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public Optional<Board> selectOne(int id) {
		String sql = "select * from board_table where id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Board b = new Board(rset.getInt("id"), rset.getString("title"));
				return Optional.of(b);
			}
			return Optional.empty();
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public List<Board> selectAll() {
		String sql = "select * from board_table";
		Board b;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			List<Board> boards = new ArrayList<Board>();
			while(rset.next()) {
				b = new Board(rset.getInt("id"), rset.getString("title"));
				boards.add(b);
			}
			return boards;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public List<String> selectAllTitles() throws Exception {
		String sql = "select title from board_table";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			List<String> titles = new ArrayList<String>();
			while(rset.next()) {
				titles.add(rset.getString("title"));
			}
			return titles;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public Board update(Board board) {
		// 바뀐 제목 + 바꾸고 싶은 아이디를 객체로!
		String sql = "update board_table(title) set title = ? where id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setInt(2, board.getId());
			pstmt.executeUpdate();

			if (rset.next()) {
				board.setId(rset.getInt(1));
				board.setTitle(rset.getString(2));
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}
			return board;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public void delete(Board board) {
		String sql = "delete from board_table where id = ?";
		String sql2 = "delete from board_item_table where board_id = ?";

		PreparedStatement pstmt2 = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, board.getId());
			pstmt2.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getId());
			pstmt.executeUpdate();

		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			try {
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(conn, pstmt, rset);
		}
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
