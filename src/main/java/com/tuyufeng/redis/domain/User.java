package com.tuyufeng.redis.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: User
 * @Description: 用户对象
 * @author:queen
 * @date: 2019年8月10日 上午9:07:45
 */
public class User implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @fieldName: id
	 * @fieldType: int
	 * @Description: TODO
	 */
	private int id;
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 用户名
	 */
	private String name;
	/**
	 * @fieldName: password
	 * @fieldType: String
	 * @Description: 密码
	 */
	private String password;
	/**
	 * @fieldName: telphone
	 * @fieldType: String
	 * @Description: 电话
	 */
	private String telphone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String password, String telphone) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.telphone = telphone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", telphone=" + telphone + "]";
	}

}
