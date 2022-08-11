package com.abcb.dto;

public class ActivityLogDTO {
	
	private int user_id;
	private String activity_type, activity_status;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	
	
	public String getActivity_status() {
		return activity_status;
	}
	public void setActivity_status(String activity_status) {
		this.activity_status = activity_status;
	}
	
}
