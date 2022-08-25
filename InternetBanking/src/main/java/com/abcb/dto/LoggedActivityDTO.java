package com.abcb.dto;

public class LoggedActivityDTO {

	private int user_id, activity_id;
	private String activity_type, activity_status, activity_time;

	
	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public String getActivity_time() {
		return activity_time;
	}

	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}

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
