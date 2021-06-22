package kr.ac.kopo.dao;

import java.sql.*;
import java.util.*;

import kr.ac.kopo.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {
	public static BoardItemDaoImpl instance = new BoardItemDaoImpl();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rset;
	static String dbURL = "jdbc:mysql://192.168.23.27:3306/boardsystem";
	static String dbID = "root";
	static String dbPassword = "kopoctc";

	public static BoardItemDaoImpl getInstance() {
		if (instance == null) {
			instance = new BoardItemDaoImpl();
		}
		return instance;
	}

	private BoardItemDaoImpl() {
	}
	
	@Override
	public BoardItem create(BoardItem boardItem) {
		String sql = "insert into board_item_table(date, parent_id, title, content, author) values(now(), ?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, boardItem.getParent_id());
			pstmt.setString(2, boardItem.getTitle());
			pstmt.setString(3, boardItem.getContent());
			pstmt.setString(4, boardItem.getAuthor());
			pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();

			if (rset.next()) {
				boardItem.setId(rset.getInt(1));
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}
			return boardItem;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public Optional<BoardItem> selectOne(int post_id) {
		String sql = "select id, date, title, content, author, parent_id from board_item_table where id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_id);
			rset = pstmt.executeQuery();
			
			BoardItem boardItem = new BoardItem();
			if (rset.next()) {
				boardItem.setId(rset.getInt(1));
				boardItem.setDate(rset.getDate(2));
				boardItem.setTitle(rset.getString(3));
				boardItem.setContent(rset.getString(4));
				boardItem.setAuthor(rset.getString(5));
				boardItem.setParent_id(rset.getInt(6));
			} else {
				throw new SQLException("게시글이 존재하지 않습니다.");
			}
			return Optional.of(boardItem);
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public List<BoardItem> selectAll(int board_id) {
		String sql = "SELECT * FROM board_item_table i INNER JOIN board_table b ON i.parent_id = b.id and b.id = ?";
		
		List<BoardItem> items = new ArrayList<BoardItem>();
		BoardItem item = new BoardItem();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				item.setId(rset.getInt(1));
				item.setDate(rset.getDate(2));
				item.setContent(rset.getString(3));
				item.setAuthor(rset.getString(4));
				item.setParent_id(rset.getInt(5));
				item.setRelevel(rset.getInt(6));
				item.setViewcnt(rset.getInt(7));
				item.setTitle(rset.getString(9));
				items.add(item);
			}
			return items;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}
	
	@Override
	public List<BoardItem> selectAll() {
		String sql = "SELECT * FROM board_item_table";
		
		List<BoardItem> items = new ArrayList<BoardItem>();
		BoardItem item = new BoardItem();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				item.setId(rset.getInt(1));
				item.setDate(rset.getDate(2));
				item.setContent(rset.getString(3));
				item.setAuthor(rset.getString(4));
				item.setParent_id(rset.getInt(5));
				item.setRelevel(rset.getInt(6));
				item.setViewcnt(rset.getInt(7));
				item.setTitle(rset.getString(9));
				items.add(item);
			}
			return items;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}
	
	@Override
	public List<BoardItem> selectOnePageResult(int board_id, int page_num) {
		String sql = "select id, date, author, title, content from board_item_table where parent_id=? limit ?, ?;";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<BoardItem> items = new ArrayList<BoardItem>();
		BoardItem item = new BoardItem();
		int postNumPerPage = 5;
		int beginNumOfThisPage = (page_num-1)*5;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.setInt(2, beginNumOfThisPage);
			pstmt.setInt(3, postNumPerPage);
			rset = pstmt.executeQuery();

			if (rset != null) {
				while (rset.next()) {				
					item.setId(rset.getInt(1));
					item.setDate(rset.getDate(2));
					item.setAuthor(rset.getString(3));
					item.setTitle(rset.getString(4));
					item.setContent(rset.getString(5));
					items.add(item);
				}
			} else {
				 System.out.println("게시글이 없습니다."); 
			}
			return items;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public BoardItem update(BoardItem boardItem) {
		String sql = "update board_item_table(title, content, author) set title = ?, content = ?, author = ? where id = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardItem.getTitle());
			pstmt.setString(2, boardItem.getContent());
			pstmt.setString(3, boardItem.getAuthor());
			pstmt.setInt(4, boardItem.getId());
			pstmt.executeUpdate();
			
			BoardItem item = new BoardItem();
			if (rset != null) {
				while (rset.next()) {				
					item.setId(rset.getInt(1));
					item.setDate(rset.getDate(2));
					item.setAuthor(rset.getString(3));
					item.setTitle(rset.getString(4));
					item.setContent(rset.getString(5));
				}
			}
			return item;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public void delete(BoardItem boardItem) {
		String sql = "delete from board_item_table where id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardItem.getId());
			pstmt.executeUpdate();
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public int newPostId() throws Exception {
		String sql = "select * from board_item_table";
		int result=0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				result = rset.getInt(1);
			}
			return result+1;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
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
