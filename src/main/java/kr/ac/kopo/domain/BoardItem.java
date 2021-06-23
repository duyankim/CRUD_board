package kr.ac.kopo.domain;

import java.sql.Date;

public class BoardItem {
	private int id, board_id, parent_id;
	private Date date;
	private String title, content, author;
	private int relevel, reorder, viewcnt;
	
	public BoardItem() {
		
	}
	
	public BoardItem(String author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	public BoardItem(String author, String title, String content, int board_id) {
		this.author = author;
		this.title = title;
		this.content = content;
		this.board_id = board_id;
	}
	
	public BoardItem(int board_id, String title, String content, String author, int relevel, int viewcnt) {
		this.board_id = board_id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.relevel = relevel;
		this.viewcnt = viewcnt;
	}

	public BoardItem(int id, Date date, String title, String content, String author, int board_id, int relevel, int reorder, int viewcnt) {
		this.id = id;
		this.date = date;
		this.board_id = board_id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.relevel = relevel;
		this.reorder = reorder;
		this.viewcnt = viewcnt;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getRelevel() {
		return relevel;
	}

	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}

	public int getReorder() {
		return reorder;
	}

	public void setReorder(int reorder) {
		this.reorder = reorder;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
}
