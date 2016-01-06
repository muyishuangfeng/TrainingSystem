package com.tianyu.customtreee.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tianyu.customtreee.R;
import com.tianyu.customtreee.utils.annotation.TreeNodeId;
import com.tianyu.customtreee.utils.annotation.TreeNodeLable;
import com.tianyu.customtreee.utils.annotation.TreeNodePId;

public class TreeHelper {
	/**
	 * 将用户的数据转化为树形数据(用注解和反射) 注解设置，反射获取值
	 * 
	 * @param datas
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T> List<Node> convertDatas2Notes(List<T> datas)
			throws IllegalArgumentException, IllegalAccessException {
		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for (T t : datas) {
			int id = -1;
			int pId = -1;
			String label = null;
			node = new Node();
			Class clazz = t.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getAnnotation(TreeNodeId.class) != null) {
					field.setAccessible(true);
					id = field.getInt(t);
				}
				if (field.getAnnotation(TreeNodePId.class) != null) {
					field.setAccessible(true);
					pId = field.getInt(t);
				}
				if (field.getAnnotation(TreeNodeLable.class) != null) {
					field.setAccessible(true);
					label = (String) field.get(t);
				}
			}
			node = new Node(id, pId, label);
			nodes.add(node);
		}
		// 设置关联关系
		// 父节点
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			// 子节点
			for (int j = i + 1; j < nodes.size(); j++) {
				Node m = nodes.get(j);
				if (m.getParentID() == n.getId()) {
					n.getChildRen().add(m);
					m.setParent(n);
				} else if (m.getId() == n.getParentID()) {
					m.getChildRen().add(n);
					n.setParent(m);
				}
			}
		}
		for (Node n : nodes) {
			setNodeIcon(n);
		}
		return nodes;
	}

	public static <T> List<Node> getSortedNodes(List<T> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException {
		List<Node> result = new ArrayList<Node>();
		List<Node> nodes = convertDatas2Notes(datas);
		// 获取树的根节点
		List<Node> rootNodes = getRootNodes(nodes);
		for (Node node : rootNodes) {
			addNode(result, node, defaultExpandLevel, 1);
		}

		return result;

	}

	/**
	 * 把一个节点的所有子节点都放入result
	 * 
	 * @param result
	 * @param node
	 * @param defaultExpandLevel
	 * @param i
	 */
	private static void addNode(List<Node> result, Node node,
			int defaultExpandLevel, int currentLevel) {
		result.add(node);
		if (defaultExpandLevel >= currentLevel) {
			node.setIsExpand(true);
		}
		if (node.isLeaf())
			return;
		for (int i = 0; i < node.getChildRen().size(); i++) {
			addNode(result, node.getChildRen().get(i), defaultExpandLevel,
					currentLevel + 1);
		}
	}

	/**
	 * 过滤出可见的节点
	 * 
	 * @param nodes
	 * @return
	 */
	public static List<Node> filterVisibleNode(List<Node> nodes) {
		List<Node> result = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot() || node.isParentExpand()) {
				setNodeIcon(node);
				result.add(node);
			}
		}
		return result;
	}

	/**
	 * 从所有节点中过滤出根节点
	 * 
	 * @param nodes
	 * @return
	 */
	private static List<Node> getRootNodes(List<Node> nodes) {
		List<Node> root = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot()) {
				root.add(node);
			}
		}

		return root;
	}

	/**
	 * 为Node设置图标
	 * 
	 * @param n
	 */
	private static void setNodeIcon(Node n) {
		if (n.getChildRen().size() > 0 && n.isExpand()) {
			n.setIcon(R.drawable.tree_ex);
		} else if (n.getChildRen().size() > 0 && !n.isExpand()) {
			n.setIcon(R.drawable.tree_ec);
		} else {
			n.setIcon(-1);
		}
	}
}
