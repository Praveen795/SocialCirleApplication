package com.socialCircle.entityDTO;


public class RegisterDto {
	
	private String name;
	private String userName;
	private String email;
	private String passWord;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public RegisterDto(String name, String userName, String email, String passWord) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.passWord = passWord;
	}
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
