package com.tianyu.customtreee.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tianyu.customtreee.R;
import com.tianyu.customtreee.utils.Node;
import com.tianyu.customtreee.utils.TreeHelper;
import com.tianyu.customtreee.utils.adapter.NodeAdapter;

public class SimpleTreeListViewAdapter<T> extends NodeAdapter<T> {

	public SimpleTreeListViewAdapter(ListView tree, Context context,
			List<T> datas, int defaultExpand) throws IllegalArgumentException,
			IllegalAccessException {
		super(tree, context, datas, defaultExpand);
	}

	@Override
	public View getConvertView(Node node, int position, View convertView,
			ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.mTxtContent = (TextView) convertView
					.findViewById(R.id.txt_content);
			holder.mImgPhoto = (ImageView) convertView
					.findViewById(R.id.img_photo);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (node.getIcon() == -1) {
			holder.mImgPhoto.setVisibility(View.INVISIBLE);
		} else {
			holder.mImgPhoto.setVisibility(View.VISIBLE);
			holder.mImgPhoto.setImageResource(node.getIcon());
		}
		holder.mTxtContent.setText(node.getName());
		return convertView;
	}

	class ViewHolder {
		ImageView mImgPhoto;
		TextView mTxtContent;
	}

	/**
	 * 动态插入节点
	 * 
	 * @param position
	 * @param string
	 */
	public void addExtraNode(int position, String string) {
		Node node = mVisibleNode.get(position);
		int index = mAllNode.indexOf(node);
		Node extraNode = new Node(-1, node.getId(), string);
		extraNode.setParent(node);
		node.getChildRen().add(extraNode);
		mAllNode.add(index + 1, extraNode);
		mVisibleNode = TreeHelper.filterVisibleNode(mAllNode);
		notifyDataSetChanged();
	}
}
