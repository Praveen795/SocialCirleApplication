package com.socialCircle.entityDTO;

public class AuthDto {
	
	private String userNameOrEmail;
	private String passWord;
	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}
	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public AuthDto(String userNameOrEmail, String passWord) {
		super();
		this.userNameOrEmail = userNameOrEmail;
		this.passWord = passWord;
	}
	public AuthDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
