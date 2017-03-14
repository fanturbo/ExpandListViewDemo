package com.demo.expandlistviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by turbo on 2016/3/25.
 */
public class FirstAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GrandFather> grandFatherList;
    private int[] colorList = new int[]{0xfff95d51, 0xffff9c00,
            0xff7dcd43, 0xfff95d51, 0xff34b3ee, 0xff90369a};
    private int[] mipmapList = new int[]{R.mipmap.heart, R.mipmap.coffee, R.mipmap.diamond,
            R.mipmap.portfolio, R.mipmap.hat, R.mipmap.language};
    private GroupViewHolder mGroupViewHolder;

    public FirstAdapter(Context context, List<GrandFather> list) {
        this.mContext = context;
        this.grandFatherList = list;
    }

    @Override
    public int getGroupCount() {
        return grandFatherList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return grandFatherList.get(groupPosition).getFatherList().size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return grandFatherList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return grandFatherList.get(groupPosition).getFatherList().get(childPosition);
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
        mGroupViewHolder = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = View.inflate(mContext, R.layout.sort_fragment_listview_group_item, null);
            mGroupViewHolder = new GroupViewHolder();
            mGroupViewHolder.typeImageView = (ImageView) convertView.findViewById(R.id.sort_icon_iv1);
            mGroupViewHolder.textView = (TextView) convertView.findViewById(R.id.sort_tv1);
            mGroupViewHolder.arrowImageView = (ImageView) convertView.findViewById(R.id.sort_iv1);
            mGroupViewHolder.view = convertView.findViewById(R.id.view_sort_group_item);
            convertView.setTag(mGroupViewHolder);
        } else {
            mGroupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        mGroupViewHolder.typeImageView.setImageResource(mipmapList[groupPosition]);
        mGroupViewHolder.textView.setText(grandFatherList.get(groupPosition).cname);
        if (isExpanded) {
            mGroupViewHolder.view.setVisibility(View.GONE);
            mGroupViewHolder.arrowImageView.setImageResource(R.mipmap.on);
            mGroupViewHolder.textView.setTextColor(colorList[groupPosition % 6]);
        } else {
            mGroupViewHolder.view.setVisibility(View.VISIBLE);
            mGroupViewHolder.arrowImageView.setImageResource(R.mipmap.off);
            mGroupViewHolder.textView.setTextColor(0xff000000);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null || convertView.getTag() == null) {
        convertView = View.inflate(mContext, R.layout.item_fragment_main_child, null);
        viewHolder = new ChildViewHolder();
        viewHolder.expandableListView = (ExpandableListView) convertView.findViewById(R.id.expandableListView);
        convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        //设置二级列表
        SecondAdapter mAdapter = new SecondAdapter(mContext, grandFatherList.get(groupPosition).getFatherList());
        viewHolder.expandableListView.setAdapter(mAdapter);
        final ChildViewHolder finalViewHolder = viewHolder;
        viewHolder.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < finalViewHolder.expandableListView.getExpandableListAdapter().getGroupCount(); i++) {
                    if (finalViewHolder.expandableListView.isGroupExpanded(i) && i != groupPosition) {
                        finalViewHolder.expandableListView.collapseGroup(i);
                    }
                }
            }
        });
        viewHolder.expandableListView.expandGroup(0);
        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ChildViewHolder {
        ExpandableListView expandableListView;
    }

    static class GroupViewHolder {
        ImageView typeImageView;
        TextView textView;
        ImageView arrowImageView;
        View view;
    }
}
