package com.demo.expandlistviewdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turbo on 2016/3/25.
 */
public class SortExpandListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Sort> mSorts;
    private List<String> mNodes;
    private int[] colorList = new int[]{0xfff95d51, 0xffff9c00,
            0xff7dcd43, 0xfff95d51, 0xff34b3ee, 0xff90369a};
    private int[] mipmapList = new int[]{R.mipmap.heart, R.mipmap.coffee, R.mipmap.diamond,
            R.mipmap.portfolio, R.mipmap.hat, R.mipmap.language};
    private GroupViewHolder mGroupViewHolder;

    public SortExpandListViewAdapter(Context context, List<Sort> list) {
        this.mContext = context;
        this.mSorts = list;
    }

    @Override
    public int getGroupCount() {
        return mSorts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mSorts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSorts.get(groupPosition).nodes.get(childPosition);
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
        mGroupViewHolder.textView.setText(mSorts.get(groupPosition).cname);
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
            convertView = View.inflate(mContext, R.layout.item_expandlistview_sort, null);
            viewHolder = new ChildViewHolder();
            viewHolder.sort_gridview = (MyGridView) convertView.findViewById(R.id.sort_gridview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.sort_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, mSorts.get(groupPosition).nodes.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mNodes = new ArrayList<>();
        mNodes.clear();
        for (Node sortNodes : mSorts.get(groupPosition).nodes) {
            mNodes.add(sortNodes.name);

        }
        SortGridViewAdapter adapter = new SortGridViewAdapter(
                mContext, mNodes);
        viewHolder.sort_gridview.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ChildViewHolder {
        MyGridView sort_gridview;
    }

    static class GroupViewHolder {
        ImageView typeImageView;
        TextView textView;
        ImageView arrowImageView;
        View view;
    }
}
