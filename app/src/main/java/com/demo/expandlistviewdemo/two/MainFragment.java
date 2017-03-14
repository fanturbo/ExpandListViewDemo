package com.demo.expandlistviewdemo.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.demo.expandlistviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment{

    private ExpandableListView expandableListView;
    private List<Sort> sortList;
    private SortExpandListViewAdapter mAdapter;
    private RelativeLayout mSearchRealtive;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater);
    }

    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        initFindViewById(view);
        initData();
        return view;
    }

    protected void initFindViewById(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
    }

    protected void initData() {
        sortList = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            Sort sort = new Sort();
            sort.cname = "Group_" + j;
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                Node node = new Node();
                node.setId(i + "");
                node.setName("G_"+j+"_Node_" + i);
                list.add(node);
            }
            sort.nodes = list;
            sortList.add(sort);
        }
        fillData();
    }

    private void fillData() {
        mAdapter = new SortExpandListViewAdapter(getContext(), sortList);
        expandableListView.setAdapter(mAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < expandableListView.getExpandableListAdapter().getGroupCount(); i++) {
                    if (expandableListView.isGroupExpanded(i) && i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
        expandableListView.expandGroup(0);
    }
}
