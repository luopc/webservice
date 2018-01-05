package cn.gov.web.system.user.entity;

import java.io.Serializable;

public class PmsUser implements Serializable {

	private static final long serialVersionUID = -2682305557890221059L;
	private Integer id;
	private String username;
	private String remark;

	public PmsUser() {
		super();
	}

	public PmsUser(Integer id, String username, String remark) {
		super();
		this.id = id;
		this.username = username;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", password=" + remark + ", username=" + username + "]";
	}
}