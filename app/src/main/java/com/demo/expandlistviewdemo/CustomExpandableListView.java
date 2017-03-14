package com.demo.expandlistviewdemo;

/**
 * Created by turbo on 2017/3/14.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 *
 * 自定义ExpandableListView  解决嵌套之下显示不全的问题
 * 参考：http://blog.csdn.net/lisdye2/article/details/51693434
 */
public class CustomExpandableListView extends ExpandableListView {


    public CustomExpandableListView(Context context) {
        super(context);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 解决显示不全的问题
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
