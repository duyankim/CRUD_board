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
		String sql = "insert into board_item(date, title, content, author, board_id) values(now(), ?, ?, ?, ?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, boardItem.getTitle());
			pstmt.setString(2, boardItem.getContent());
			pstmt.setString(3, boardItem.getAuthor());
			pstmt.setInt(4, boardItem.getBoard_id());
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
	public BoardItem createComment(BoardItem boardItem) {
		String sql = "insert into board_item(date, author, content, board_id, parent_id, relevel) values(now(), ?, ?, ?, ?, ?)";
		String sql2 = "update board_item set reorder = ? where id = ?";
		
		PreparedStatement pstmt2 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardItem.getAuthor());
			pstmt.setString(2, boardItem.getContent());
			pstmt.setInt(3, boardItem.getBoard_id());
			pstmt.setInt(4, boardItem.getParent_id());
			pstmt.setInt(5, boardItem.getRelevel());
			pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();

			if (rset.next()) {
				boardItem.setId(rset.getInt(1));
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, findReorderNum(boardItem.getParent_id(), boardItem.getRelevel()));
			pstmt2.setInt(2, boardItem.getId());
			pstmt.executeUpdate();
			return boardItem;
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

	@Override
	public Optional<BoardItem> selectOne(int post_id) {
		String sql = "select id, date, title, content, author, board_id from board_item where id = ?";

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
				boardItem.setBoard_id(rset.getInt(6));
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
		String sql = "SELECT * FROM board_item i INNER JOIN board b ON i.board_id = b.id and b.id = ?";
		
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
				item.setTitle(rset.getString(3));
				item.setContent(rset.getString(4));
				item.setAuthor(rset.getString(5));
				item.setBoard_id(rset.getInt(6));
				item.setParent_id(rset.getInt(7));
				item.setRelevel(rset.getInt(8));
				item.setReorder(rset.getInt(9));
				item.setViewcnt(rset.getInt(10));
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
		String sql = "SELECT * FROM board_item";
		
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
				item.setTitle(rset.getString(3));
				item.setContent(rset.getString(4));
				item.setAuthor(rset.getString(5));
				item.setBoard_id(rset.getInt(6));
				item.setParent_id(rset.getInt(7));
				item.setRelevel(rset.getInt(8));
				item.setReorder(rset.getInt(9));
				item.setViewcnt(rset.getInt(10));
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
		String sql = "select id, date, title, content, author from board_item where board_id=? limit ?, ?;";
		
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
					item.setTitle(rset.getString(3));
					item.setContent(rset.getString(4));
					item.setAuthor(rset.getString(5));
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
		String sql = "update board_item set title = ?, content = ?, author = ? where id = ?";
		
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
			
			return boardItem;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public void delete(BoardItem boardItem) {
		String sql = "delete from board_item where id = ?";

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
		String sql = "select * from board_item";
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

	@Override
	public int findBoardIdByPostId(int post_id) throws Exception {
		String sql = "select board_id from board_item where id = ?";

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
			
			int board_id;
			if (rset.next()) {
				board_id = rset.getInt(1);
			} else {
				throw new SQLException("게시글이 존재하지 않습니다.");
				}
			return board_id;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}

	@Override
	public int findReorderNum(int parent_id, int relevel) throws Exception {
		String sql = "select max(reorder) from board_item where parent_id=? and relevel=?";
		int reorderNum;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, parent_id);
			pstmt.setInt(2, relevel);
			pstmt.executeUpdate();
			rset = pstmt.executeQuery();

			if (!rset.next()) {
				reorderNum = 0;
			} else if (rset.next()) {
				reorderNum = rset.getInt(1); 
			} else {
				throw new SQLException("id가 존재하지 않습니다.");
			}	
			return reorderNum+1;
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

	@Override
	public List<BoardItem> selectAllComment(int parent_id) throws Exception {
		String sql = "SELECT * FROM board_item where parent_id = ?";
		
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
			pstmt.setInt(1, parent_id);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				item.setId(rset.getInt(1));
				item.setDate(rset.getDate(2));
				item.setTitle(rset.getString(3));
				item.setContent(rset.getString(4));
				item.setAuthor(rset.getString(5));
				item.setBoard_id(rset.getInt(6));
				item.setParent_id(rset.getInt(7));
				item.setRelevel(rset.getInt(8));
				item.setReorder(rset.getInt(9));
				item.setViewcnt(rset.getInt(10));
				items.add(item);
			}
			return items;
		} catch (Exception e1) {
			throw new IllegalStateException(e1);
		} finally {
			close(conn, pstmt, rset);
		}
	}
}
