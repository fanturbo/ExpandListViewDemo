package com.demo.expandlistviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turbo on 2016/3/25.
 */
public class SecondAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GrandFather.Father> fatherList;
    private int[] colorList = new int[]{0xfff95d51, 0xffff9c00,
            0xff7dcd43, 0xfff95d51, 0xff34b3ee, 0xff90369a};
    private int[] mipmapList = new int[]{R.mipmap.heart, R.mipmap.coffee, R.mipmap.diamond,
            R.mipmap.portfolio, R.mipmap.hat, R.mipmap.language};
    private int lastExpandedGroupPosition;

    public SecondAdapter(Context context, List<GrandFather.Father> list) {
        this.mContext = context;
        this.fatherList = list;
    }

    @Override
    public int getGroupCount() {
        Log.i("======","GroupCount = "+fatherList.size());
        return fatherList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("======","childerCount = "+fatherList.get(groupPosition).getSonList().size());
        return fatherList.get(groupPosition).getSonList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return fatherList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return fatherList.get(groupPosition).getSonList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder mGroupViewHolder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = View.inflate(mContext, R.layout.item_fragment_main_child_group, null);
            mGroupViewHolder = new GroupViewHolder();
            mGroupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            mGroupViewHolder.tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
            convertView.setTag(mGroupViewHolder);
        } else {
            mGroupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        mGroupViewHolder.tvTitle.setText(fatherList.get(groupPosition).getName());
        if (isExpanded) {
            mGroupViewHolder.tvStatus.setText("-");
            mGroupViewHolder.tvTitle.setTextColor(colorList[groupPosition % 6]);
        } else {
            mGroupViewHolder.tvStatus.setText("+");
            mGroupViewHolder.tvTitle.setTextColor(0xff000000);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = View.inflate(mContext, R.layout.item_fragment_main_child_child, null);
            viewHolder = new ChildViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(fatherList.get(groupPosition).getSonList().get(childPosition).getName() + "");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ChildViewHolder {
        TextView tv_title;
    }

    static class GroupViewHolder {
        TextView tvTitle;
        TextView tvStatus;
    }
}
