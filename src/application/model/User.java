package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="Name")
	private String name;
	@Column(name="UserID")
	private String userId;
	@Column(name="Mobile")
	private String mobile;
	@Column(name="Email")
	private String email;
	
	public User(){
	}
	
	public User(String name, String userId, String mobile, String email){
		this.name = name;
		this.userId = userId;
		this.mobile = mobile;
		this.email = email;
	}
	
	public User(Integer id, String name, String userId, String mobile, String email){
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.mobile = mobile;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
