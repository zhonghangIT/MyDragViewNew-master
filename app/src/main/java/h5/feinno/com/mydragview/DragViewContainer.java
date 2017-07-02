package h5.feinno.com.mydragview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : zhonghang
 *     e-mail : zhonghang@feinno.com
 *     time   : 2017/06/21 09:13
 *     desc   : 用于存放可以拖动的数据的容器，响应各种事件
 *     version: 1.0
 * </pre>
 */
public class DragViewContainer extends FrameLayout {
    /**
     * 注解的方式声明不同的触控事件
     */
    @IntDef({
            ActionMode.NONE, ActionMode.DRAG, ActionMode.ZOOM_WITH_TWO_FINGER, ActionMode.ICON, ActionMode.CLICK
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionMode {
        int NONE = 0;
        int DRAG = 1;
        int ZOOM_WITH_TWO_FINGER = 2;
        int ICON = 3;
        int CLICK = 4;
    }

    @ActionMode
    private int mCurrentMode = ActionMode.NONE;
    /**
     * 当前选中的拖动组件
     */
    private DragView mSelectedDragView;
    /**
     * 存放所有的拖动组件的集合
     */
    private List<DragView> mDragViewList;

    /**
     * 得到容器中的所有的控件
     *
     * @return 所有拖动控件的集合
     */
    public List<DragView> getDragViewList() {
        return mDragViewList;
    }

    /**
     * 按下事件的x坐标
     */
    private float downX;
    /**
     * 按下事件的y坐标
     */
    private float downY;
    /**
     * 按下时选中的dragView的矩阵
     */
    private Matrix mSelectedDownMatrix = new Matrix();

    private Matrix mMoveMatrix = new Matrix();

    /**
     * 添加拖动控件到集合中
     *
     * @param dragView 要添加的控件
     */
    public void addDragView(DragView dragView) {
        if (mDragViewList != null) {
            mDragViewList.add(dragView);
            invalidate();
        }
    }

    public DragViewContainer(Context context) {
        super(context);
        setUp();
    }

    public DragViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public DragViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragViewContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp();
    }

    private void setUp() {
        //初始化存放拖动组件数据的集合
        if (mDragViewList == null) {
            mDragViewList = new ArrayList<>();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        /**
         *  ViewGroup中绘制内容在此处进行绘制，和View的onDraw需要区别。
         *  如果有背景的话ViewGroup也会执行onDraw()方法，在onDraw方法中调用dispathDraw()方法
         */
        for (DragView dragView : mDragViewList) {
            dragView.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理事件
        //得到详细的事件状态，这里使用getActionMasked能得到多点操作的事件
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                //处理位移旋转以及缩放的手势
                Log.d("------", "--------移动组件" + mSelectedDragView);
                onTouchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        invalidate();
        return true;
    }

    private void onTouchMove(MotionEvent event) {
        switch (mCurrentMode) {
            case ActionMode.DRAG:
                //处理拖动事件
                if (mSelectedDragView != null) {
                    Log.d("------", "--------移动组件");
                    mMoveMatrix.set(mSelectedDownMatrix);
                    mMoveMatrix.postTranslate(event.getX() - downX, event.getY() - downY);
                    mSelectedDragView.setMatrix(mMoveMatrix);
                }
                break;
        }
    }

    private void onTouchDown(MotionEvent event) {
        //此处判断是否点击到了拖动组
        mCurrentMode = ActionMode.DRAG;
        downX = event.getX();
        downY = event.getY();
        mSelectedDragView = findFocusDragView(event.getX(), event.getY());
        if (mSelectedDragView != null) {
            //如果不为空的话选中的矩阵为此
            mSelectedDownMatrix.set(mSelectedDragView.getMatrix());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //是否拦截事件。点击到内部的拖动组件时拦截事件
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (findFocusDragView(ev.getX(), ev.getY()) != null) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 得到该点所在的拖动控件
     *
     * @param x
     * @param y
     * @return 返回选中的拖动控件，如果没有选中则返回null
     */
    @Nullable
    protected DragView findFocusDragView(float x, float y) {
        for (DragView dragView : mDragViewList) {
            if (dragView.contains(x, y)) {
                return dragView;
            }
        }
        return null;
    }
}
