package application.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistroMovimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@Column(name="Book_ID")
	private String bookID;
	@Column(name="User_ID")
	private String userID;
	@Column(name="TimeIssue")
	private LocalDateTime timeIssue;
	@Column(name="TimeSubmission")
	private LocalDateTime timeSubmission;
	
	public RegistroMovimentacao() {
	}

	public RegistroMovimentacao(String bookID, String userID, LocalDateTime timeIssue, LocalDateTime timeSub){
		this.bookID = bookID;
		this.userID = userID;
		this.timeIssue = timeIssue;
		this.timeSubmission = timeSub;
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

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public LocalDateTime getTimeIssue() {
		return timeIssue;
	}

	public void setTimeIssue(LocalDateTime timeIssue) {
		this.timeIssue = timeIssue;
	}

	public LocalDateTime getTimeSubmission() {
		return timeSubmission;
	}

	public void setTimeSubmission(LocalDateTime timeSubmission) {
		this.timeSubmission = timeSubmission;
	}
		
	
	
	
	
}
