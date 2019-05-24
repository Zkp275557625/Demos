package com.zkp.demos.modules.rader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zkp.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.modules.rader
 * @time: 2019/5/24 11:16
 * @description: 雷达图
 */
public class RadarView extends View {

    /**
     * 最外圈到中心点的半径
     */
    private float mRadius;
    /**
     * 画几圈--半径分割成几份
     */
    private int mIntervalCount;

    /**
     * 每个顶点的文字
     */
    private List<String> mTitles;

    private List<Float> mValues;

    /**
     * 边的数量
     */
    private int mEdgeNum;
    /**
     * 夹角 用弧度表示
     */
    private float mAngle;

    /**
     * 每一圈多边形顶点
     */
    private ArrayList<ArrayList<PointF>> mPointsArrayList;
    /**
     * 画线的笔
     */
    private Paint mLinePaint;

    /**
     * 画边框和线条的笔
     */
    private Paint mStorkPaint;
    /**
     * 画数值的笔
     */
    private Paint mValuesPaint;
    /**
     * 画数值边框的笔
     */
    private Paint mValueStorkPaint;
    /**
     * 画顶点文字的笔
     */
    private Paint mTitlesPaint;
    /**
     * view的宽高
     */
    private int width, height;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadarView, defStyleAttr, 0);
        mRadius = typedArray.getDimension(R.styleable.RadarView_Radius, dp2pxF(context, 180));
        mIntervalCount = typedArray.getInt(R.styleable.RadarView_intervalCount, 5);

        //画线的笔
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置线宽度
        mLinePaint.setStrokeWidth(dp2px(context, 1f));

        //画边框线条的笔
        mStorkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置线宽度
        mStorkPaint.setStrokeWidth(dp2px(context, 1f));
        mStorkPaint.setColor(typedArray.getColor(R.styleable.RadarView_storkColor, context.getResources().getColor(R.color.color1)));

        //画文字的笔
        mValuesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文字居中
        mValuesPaint.setTextAlign(Paint.Align.CENTER);
        mValuesPaint.setColor(typedArray.getColor(R.styleable.RadarView_valueColor, Color.parseColor("#E96153")));
        mValuesPaint.setTextSize(sp2pxF(context, 14f));

        //画文字的笔
        mValueStorkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文字居中
        mValueStorkPaint.setTextAlign(Paint.Align.CENTER);
        mValueStorkPaint.setColor(typedArray.getColor(R.styleable.RadarView_valueStorkColor, Color.parseColor("#E96153")));
        mValueStorkPaint.setTextSize(sp2pxF(context, 14f));

        //画文字的笔
        mTitlesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文字居中
        mTitlesPaint.setTextAlign(Paint.Align.CENTER);
        mTitlesPaint.setColor(typedArray.getColor(R.styleable.RadarView_titleColor, Color.BLACK));
        mTitlesPaint.setTextSize(sp2pxF(context, 14f));

        typedArray.recycle();

        if (mTitles != null) {
            mEdgeNum = mTitles.size();
            mAngle = (float) ((2 * Math.PI) / mEdgeNum);
            initPoints();
        }

    }

    public static float dp2pxF(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float sp2pxF(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    private void initPoints() {
        mPointsArrayList = new ArrayList<>();
        float x;
        float y;
        for (int i = 0; i < mIntervalCount; i++) {
            //创建一个存储点的数组
            ArrayList<PointF> points = new ArrayList<>();
            for (int j = 0; j < mEdgeNum; j++) {
                //每一圈的半径都按比例减少
                float r = mRadius * ((float) (mIntervalCount - i) / mIntervalCount);
                //这里减去Math.PI / 2 是为了让多边形逆时针旋转90度，所以后面的所有用到cos,sin的都要减
                x = (float) (r * Math.cos(j * mAngle - Math.PI / 2));
                y = (float) (r * Math.sin(j * mAngle - Math.PI / 2));
                points.add(new PointF(x, y));
            }
            mPointsArrayList.add(points);
        }
    }

    public void setValues(List<Float> mValues) {
        this.mValues = mValues;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mTitles != null) {
            mEdgeNum = mTitles.size();
            mAngle = (float) ((2 * Math.PI) / mEdgeNum);
            setMeasuredDimension(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //把画布的原点移动到控件的中心点
        canvas.translate(width * 1.0f / 2, height * 1.0f / 2);

        //绘制多边形
        drawPolygon(canvas);

        //绘制轮廓
        drawOutLine(canvas);

        //绘制数值区域
        drawValue(canvas);

        //绘制文字
        drawText(canvas);

    }

    /**
     * 回执多边形
     *
     * @param canvas 画布
     */
    private void drawPolygon(Canvas canvas) {
        canvas.save();//保存画布当前状态(平移、放缩、旋转、裁剪等),和canvas.restore()配合使用

        //填充且描边
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //路径
        Path path = new Path();
        for (int i = 0; i < mIntervalCount; i++) {
            //循环、一层一层的绘制
            //每一层的颜色都都不同
            for (int j = 0; j < mEdgeNum; j++) {
                //每一层有n个点
                float x = mPointsArrayList.get(i).get(j).x;
                float y = mPointsArrayList.get(i).get(j).y;
                if (j == 0) {
                    //如果是每层的第一个点就把path的起点设置为这个点
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();  //设置为闭合的
            if (i == 0) {
                mLinePaint.setColor(Color.parseColor("#D4F0F3"));
            } else if (i == 1) {
                mLinePaint.setColor(Color.parseColor("#99DCE2"));
            } else if (i == 2) {
                mLinePaint.setColor(Color.parseColor("#56C1C7"));
            } else if (i == 3) {
                mLinePaint.setColor(Color.parseColor("#36AAB1"));
            } else if (i == 4) {
                mLinePaint.setColor(Color.parseColor("#278891"));
            }
            canvas.drawPath(path, mLinePaint);
            path.reset();
        }
        canvas.restore();
    }

    private void drawOutLine(Canvas canvas) {
        canvas.save();
        //设置空心的
        mStorkPaint.setStyle(Paint.Style.STROKE);

        //绘制多边形轮廓
        Path path = new Path();
        for (int i = 0; i < mIntervalCount; i++) {
            for (int j = 0; j < mEdgeNum; j++) {
                //只需要第一组的点
                float x = mPointsArrayList.get(i).get(j).x;
                float y = mPointsArrayList.get(i).get(j).y;
                if (j == 0) {
                    //如果是第一个点就把path的起点设置为这个点
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close(); //闭合路径
            canvas.drawPath(path, mStorkPaint);
            path.reset();
        }
        //再画顶点到中心的线
        for (int i = 0; i < mEdgeNum; i++) {
            float x = mPointsArrayList.get(0).get(i).x;
            float y = mPointsArrayList.get(0).get(i).y;
            canvas.drawLine(0, 0, x, y, mStorkPaint);
        }
        canvas.restore();
    }

    private void drawValue(Canvas canvas) {
        canvas.save();

        if (mValues == null) {
            return;
        }

        //先把能力点初始化出来
        ArrayList<PointF> valuePoints = new ArrayList<>();
        for (int i = 0; i < mEdgeNum; i++) {
            //能力值/100再乘以半径就是所占的比例
            float r = mRadius * (mValues.get(i) / 10.0f);
            float x = (float) (r * Math.cos(i * mAngle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * mAngle - Math.PI / 2));
            valuePoints.add(new PointF(x, y));
        }

        mLinePaint.setStrokeWidth(dp2px(getContext(), 2f));
        mLinePaint.setColor(Color.parseColor("#22E96153"));
        mLinePaint.setStyle(Paint.Style.FILL);

        mValueStorkPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        for (int i = 0; i < mEdgeNum; i++) {
            float x = valuePoints.get(i).x;
            float y = valuePoints.get(i).y;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, mLinePaint);
        canvas.drawPath(path, mValueStorkPaint);

        //绘制数值区域文字
        ArrayList<PointF> textPoints = new ArrayList<>();
        for (int i = 0; i < mEdgeNum; i++) {
            float r = mRadius * (mValues.get(i) / 10.0f) - dp2px(getContext(), 15);
            float x = (float) (r * Math.cos(i * mAngle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * mAngle - Math.PI / 2));
            textPoints.add(new PointF(x, y));
        }
        Paint.FontMetrics metrics = mValuesPaint.getFontMetrics();
        for (int i = 0; i < mEdgeNum; i++) {
            float x = textPoints.get(i).x;
            //ascent:上坡度，是文字的基线到文字的最高处的距离
            //descent:下坡度，文字的基线到文字的最低处的距离
            float y = textPoints.get(i).y - (metrics.ascent + metrics.descent) / 2;
            canvas.drawText(mValues.get(i).toString(), x, y, mValuesPaint);
        }

        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.save();

        ArrayList<PointF> textPoints = new ArrayList<>();
        for (int i = 0; i < mEdgeNum; i++) {
            float r = mRadius + dp2px(getContext(), 15);
            float x = (float) (r * Math.cos(i * mAngle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * mAngle - Math.PI / 2));
            textPoints.add(new PointF(x, y));
        }
        Paint.FontMetrics metrics = mTitlesPaint.getFontMetrics();
        for (int i = 0; i < mEdgeNum; i++) {
            float x = textPoints.get(i).x;
            //ascent:上坡度，是文字的基线到文字的最高处的距离
            //descent:下坡度,，文字的基线到文字的最低处的距离
            float y = textPoints.get(i).y - (metrics.ascent + metrics.descent);
            canvas.drawText(mTitles.get(i), x, y, mTitlesPaint);
        }

        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = getResources().getDisplayMetrics().widthPixels;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = getResources().getDisplayMetrics().heightPixels;
        }

        setMeasuredDimension(width, height);
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
        mEdgeNum = mTitles.size();
        mAngle = (float) ((2 * Math.PI) / mEdgeNum);
        initPoints();
        invalidate();

    }
}
