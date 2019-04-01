package com.zkp.demos.excel;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.excel
 * @time: 2019/4/1 14:52
 * @description: 自定义HorizontalScrollView
 */
public class MyHorizontalScrollView extends HorizontalScrollView {

    /**
     * 记录当前滚动的距离
     */
    private int currentX = -9999999;
    /**
     * 当前滚动状态
     */
    private ScrollType scrollType = ScrollType.IDLE;

    /**
     * 横向滚动监听
     */
    private OnHorizontalScrollListener listener;
    /**
     * 滚动状态监听
     */
    private ScrollViewListener scrollViewListener;

    private Handler mHandler;

    /**
     * 滚动监听runnable
     */
    private Runnable scrollRunnable = new Runnable() {

        @Override
        public void run() {
            if (getScrollX() == currentX) {
                //滚动停止  取消监听线程
                Log.d("", "停止滚动");
                scrollType = ScrollType.IDLE;
                if (scrollViewListener != null) {
                    scrollViewListener.onScrollChanged(scrollType);
                }
                mHandler.removeCallbacks(this);
                return;
            } else {
                //手指离开屏幕    view还在滚动的时候
                Log.d("", "Fling。。。。。");
                scrollType = ScrollType.FLING;
                if (scrollViewListener != null) {
                    scrollViewListener.onScrollChanged(scrollType);
                }
            }
            currentX = getScrollX();
            mHandler.postDelayed(this, 50);
        }
    };

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnHorizontalScrollListener(OnHorizontalScrollListener listener) {
        this.listener = listener;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mHandler != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    this.scrollType = ScrollType.TOUCH_SCROLL;
                    scrollViewListener.onScrollChanged(scrollType);
                    //手指在上面移动的时候   取消滚动监听线程
                    mHandler.removeCallbacks(scrollRunnable);
                    break;
                case MotionEvent.ACTION_UP:
                    //手指移动的时候
                    mHandler.post(scrollRunnable);
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void fling(int velocityX) {
        //修改滚动速度
        super.fling(velocityX / 2);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // 通知自定义的listener
        if (listener != null) {
            listener.onHorizontalScrolled(this, l, t, oldl, oldt);
        }
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    /**
     * 滚动状态
     */
    public enum ScrollType {
        /**
         * 滚动停止
         */
        IDLE,
        /**
         * 手指拖动滚动
         */
        TOUCH_SCROLL,
        /**
         * 滚动
         */
        FLING
    }

    public interface OnHorizontalScrollListener {
        void onHorizontalScrolled(MyHorizontalScrollView view, int left, int top, int oldLeft, int oldTop);
    }

    public interface ScrollViewListener {
        void onScrollChanged(ScrollType scrollType);
    }
}
