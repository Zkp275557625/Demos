package com.zkp.demos.excel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zkp.demos.App;
import com.zkp.demos.MainListViewAdapter;
import com.zkp.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos
 * @time: 2019/4/1 14:42
 * @description:
 */
public class ExcelActivity extends AppCompatActivity {

    private LinearLayout mLlTitle;
    private ListView mListViewName, mListViewContent;
    private MyHorizontalScrollView mScrollViewContent;
    private MyHorizontalScrollView mScrollViewTitle;

    private List<String> mNames;
    private List<String> mTitles;
    private List<ExcelInfoBean> mExcelInfoBeans;

    private ExcelListViewContentAdapter mAdapter;

    private HorizontalScrollListener horizontalScrollListener = new HorizontalScrollListener();
    private VerticalScrollListener verticalScrollListener = new VerticalScrollListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);

        App.getApplication().addActivity(this);

        initUI();

        initEvent();
    }

    private void initUI() {
        mLlTitle = findViewById(R.id.llTitle);
        mListViewName = findViewById(R.id.listViewName);
        mListViewContent = findViewById(R.id.listViewContent);
        mScrollViewContent = findViewById(R.id.scrollViewContent);
        mScrollViewTitle = findViewById(R.id.scrollViewTitle);

        mNames = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mNames.add("用户" + i);
        }
        mListViewName.setAdapter(new MainListViewAdapter(this, mNames));

        mTitles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mTitles.add("字段" + i);
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                dip2px(this, 75), dip2px(this, 35));
        for (String title : mTitles) {
            TextView textView = new TextView(this);
            textView.setTextSize(14);
            textView.getPaint().setFakeBoldText(true);
            textView.setText(title);
            textView.setGravity(Gravity.CENTER);
            mLlTitle.addView(textView, params);
        }

        mExcelInfoBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mExcelInfoBeans.add(new ExcelInfoBean());
        }

        mAdapter = new ExcelListViewContentAdapter(this, mExcelInfoBeans);
        mListViewContent.setAdapter(mAdapter);

    }

    private void initEvent() {
        mScrollViewTitle.setOnHorizontalScrollListener(horizontalScrollListener);
        mScrollViewContent.setOnHorizontalScrollListener(horizontalScrollListener);
        mListViewName.setOnScrollListener(verticalScrollListener);
        mListViewContent.setOnScrollListener(verticalScrollListener);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * HorizontalScrollView的滑动监听（水平方向同步控制）
     */
    private class HorizontalScrollListener implements MyHorizontalScrollView.OnHorizontalScrollListener {
        @Override
        public void onHorizontalScrolled(MyHorizontalScrollView view, int l, int t, int oldl, int oldt) {
            if (view == mScrollViewContent) {
                mScrollViewTitle.scrollTo(l, t);
            } else {
                mScrollViewContent.scrollTo(l, t);
            }
        }
    }

    /**
     * ListViewName和ListViewContent的滑动监听（垂直方向同步控制）
     */
    private class VerticalScrollListener implements AbsListView.OnScrollListener {

        int scrollState;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            this.scrollState = scrollState;
            if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                View subView = view.getChildAt(0);
                if (subView != null && view == mListViewContent) {
                    mListViewName.setSelectionFromTop(view.getFirstVisiblePosition(), subView.getTop());
                } else if (subView != null && view == mListViewName) {
                    mListViewContent.setSelectionFromTop(view.getFirstVisiblePosition(), subView.getTop());
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            //判断滑动是否终止，以停止自动对齐，否则该方法会一直被调用，影响性能
            if (scrollState == SCROLL_STATE_IDLE) {
                return;
            }
            View subView = view.getChildAt(0);
            if (subView != null && view == mListViewContent) {
                mListViewName.setSelectionFromTop(firstVisibleItem, subView.getTop());
            } else if (subView != null && view == mListViewName) {
                mListViewContent.setSelectionFromTop(firstVisibleItem, subView.getTop());
            }
        }
    }
}
