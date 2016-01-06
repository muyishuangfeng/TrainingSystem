package com.tianyu.customtreee.utils;

import com.tianyu.customtreee.utils.annotation.TreeNodeId;
import com.tianyu.customtreee.utils.annotation.TreeNodeLable;
import com.tianyu.customtreee.utils.annotation.TreeNodePId;

public class OrgBean {
	@TreeNodeId
	public int _id;
	@TreeNodePId
	public int parentID;
	@TreeNodeLable
	public String name;

	public OrgBean(int _id, int parentID, String name) {
		super();
		this._id = _id;
		this.parentID = parentID;
		this.name = name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
