package com.spring.user.vo;

import java.util.Date;

public class UserVo {
	
	private String userId;
	private String userPw;
	private String userAge;
	private String gender;
	private int	number;
	private String userEmail;
	private Date Date;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userPw=" + userPw + ", userAge=" + userAge + ", gender=" + gender
				+ ", number=" + number + ", userEmail=" + userEmail + ", Date=" + Date + "]";
	}
	
	
	
}
