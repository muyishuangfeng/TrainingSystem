package com.tianyu.customtreee.filebean;

import com.tianyu.customtreee.utils.annotation.TreeNodeId;
import com.tianyu.customtreee.utils.annotation.TreeNodeLable;
import com.tianyu.customtreee.utils.annotation.TreeNodePId;

public class FileBean {
	// 子节点
	@TreeNodeId
	private int id;
	// 父节点
	@TreeNodePId
	private int parentID;
	// 标签
	@TreeNodeLable
	private String lable;
	// 描述
	private String desc;

	public FileBean(int id, int parentID, String lable) {
		super();
		this.id = id;
		this.parentID = parentID;
		this.lable = lable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
