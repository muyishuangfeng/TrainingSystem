package com.tianyu.customtreee.utils.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.tianyu.customtreee.utils.Node;
import com.tianyu.customtreee.utils.TreeHelper;

public abstract class NodeAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<Node> mAllNode;
	protected List<Node> mVisibleNode;
	protected LayoutInflater mInflater;
	protected ListView mTree;
	protected OnTreeNodeClickListener mListener;

	public NodeAdapter(ListView tree, Context context, List<T> datas,
			int defaultExpand) throws IllegalArgumentException,
			IllegalAccessException {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		this.mAllNode = TreeHelper.getSortedNodes(datas, defaultExpand);
		this.mVisibleNode = TreeHelper.filterVisibleNode(mAllNode);
		this.mTree = tree;
		mTree.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				expandOrCollapse(position);
				if (mListener != null) {
					mListener.onClick(mVisibleNode.get(position), position);
				}
			}

		});
	}

	/**
	 * 点击即展开或者收缩
	 */
	private void expandOrCollapse(int position) {
		Node n = mVisibleNode.get(position);
		if (n != null) {
			if (n.isLeaf())
				return;
			n.setIsExpand(!n.isExpand());
			mVisibleNode = TreeHelper.filterVisibleNode(mAllNode);
			this.notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return mVisibleNode.size();
	}

	@Override
	public Object getItem(int position) {
		return mVisibleNode.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Node node = mVisibleNode.get(position);
		convertView = getConvertView(node, position, convertView, parent);
		// 设置内边距
		convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
		return convertView;
	}

	/**
	 * 设置Node的点击回调
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnTreeNodeClickListener {
		void onClick(Node node, int position);
	}

	public void setOnTreeNodeClickListener(OnTreeNodeClickListener listener) {
		this.mListener = listener;
	}

	public abstract View getConvertView(Node node, int position,
			View convertView, ViewGroup parent);
}
