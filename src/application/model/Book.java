package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="Title")
	private String title;
	@Column(name="Number")
	private String number;
	@Column(name="Author")
	private String author;
	@Column(name="Publisher")
	private String publisher;
	@Column(name="Available")
	private String available;
	
	public Book(){
	}

	public Book(String title, String number, String author, String publisher, String available){
		this.title = title;
		this.number = number;
		this.author = author;
		this.publisher = publisher;
		this.available = available;
	}
	
	public Book(Integer id, String title, String number, String author, String publisher, String available){
		this.id = id;
		this.title = title;
		this.number = number;
		this.author = author;
		this.publisher = publisher;
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	
	

}
