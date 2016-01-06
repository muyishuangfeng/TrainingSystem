package com.tianyu.customtreee.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Node() {
	}

	public Node(int id, int parentID, String name) {
		super();
		this.id = id;
		this.parentID = parentID;
		this.name = name;
	}

	private int id;
	// 根节点的PID为0
	private int parentID = 0;
	private String name;
	// 树的层次
	private int level;
	// 是否展开
	private boolean isExpand = false;
	// 图标
	private int icon;
	// 跟节点
	private Node parent;
	private List<Node> childRen = new ArrayList<Node>();

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

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public List<Node> getChildRen() {
		return childRen;
	}

	public void setChildRen(List<Node> childRen) {
		this.childRen = childRen;
	}

	/**
	 * 是否是根节点
	 * 
	 * @return
	 */
	public boolean isRoot() {

		return parent == null;
	}

	/**
	 * 判断当前父节点收缩状态
	 * 
	 * @return
	 */
	public boolean isParentExpand() {
		if (parent == null) {
			return false;
		}
		return parent.isExpand();
	}

	/**
	 * 是否是子节点
	 * 
	 * @return
	 */
	public boolean isLeaf() {

		return childRen.size() == 0;
	}

	/**
	 * 得到当前节点的层级
	 * 
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 0 : parent.getLevel() + 1;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isExpand() {
		return isExpand;
	}

	public void setIsExpand(boolean isExpand) {
		this.isExpand = isExpand;
		if (!isExpand) {
			for (Node node : childRen) {
				node.setIsExpand(false);
			}
		}
	}
}
