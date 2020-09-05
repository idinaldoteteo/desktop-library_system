package application.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Issue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="Book_ID")
	private String bookID;
	@Column(name="User_ID")
	private String userID;
	@Column(name="TimeIssue")
	private LocalDateTime timeIssue;
	
	public Issue(){
	}

	public Issue(String bookID, String userID, LocalDateTime time){
		this.bookID = bookID;
		this.userID = userID;
		this.timeIssue = time;
	}
	
	public Issue(Integer id, String bookID, String userID, LocalDateTime time){
		this.id = id;
		this.bookID = bookID;
		this.userID = userID;
		this.timeIssue = time;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getUserID() {
		return userID;
	}

	public LocalDateTime getTimeIssue() {
		return timeIssue;
	}

	public void setTimeIssue(LocalDateTime timeIssue) {
		this.timeIssue = timeIssue;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
