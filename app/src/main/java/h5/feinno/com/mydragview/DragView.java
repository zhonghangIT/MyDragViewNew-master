package h5.feinno.com.mydragview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * <pre>
 *     author : zhonghang
 *     e-mail : zhonghang@feinno.com
 *     time   : 2017/06/21 15:14
 *     desc   : 用于拖动放大缩小的控件,不是一个View,只是一个承载绘制的内容的实体，负责绘制其中的内容，不可以单独使用，必须在DragViewContainer中使用
 *     @see DragViewContainer
 *     version: 1.0
 * </pre>
 */
public abstract class DragView {
    /**
     * 绘制的拖动组件的当前的矩阵
     */
    protected Matrix mMatrix = new Matrix();

    public Matrix getMatrix() {
        return mMatrix;
    }

    public void setMatrix(Matrix mMatrix) {
        this.mMatrix = mMatrix;
    }

    /**
     * 选中时矩形框的颜色
     */
    protected int mBorderColor;

    /**
     * 组件的绘制方法
     *
     * @param canvas 绘制时的画布，由DragViewContainer传递过来
     */
    public abstract void draw(Canvas canvas);

    /**
     * 判断该点是否在该控件的范围内，绘制的控件的范围根据矩阵变换。
     *
     * @param x
     * @param y
     * @return true 表示在此范围
     */
    public boolean contains(float x, float y) {
        //判断该点是否在该控件的范围中，
        // 控件的范围是一个0，0原点至getWidth和getHeight的矩形经过matrix变形后的矩形范围。
        // matrix的变形包括旋转位移和缩放
        //创建空的矩阵
//        Matrix tempMatrix = new Matrix();
//        //将矩阵逆向旋转
////        tempMatrix.setRotate(-getCurrentAngle());
//        //当前点按照矩阵进行变换，得到变换后的点
//        tempMatrix.mapPoints(unrotatedPoint, point);
//
//        //得到控件的矩形
//        getBoundPoints(boundPoints);
//        //将该矩形按照矩阵进行变换，得到当前控件的变换后的矩形
//        getMappedPoints(mappedBounds, boundPoints);
//        //不旋转的矩形
//        tempMatrix.mapPoints(unrotatedWrapperCorner, mappedBounds);
//
//        //
//        StickerUtils.trapToRect(trappedRect, unrotatedWrapperCorner);
//        return trappedRect.contains(unrotatedPoint[0], unrotatedPoint[1]);
        return true;
    }

    /**
     * 得到控件的宽度
     *
     * @return
     */
    public abstract int getWidth();

    /**
     * 得到控件的高度
     *
     * @return
     */
    public abstract int getHeight();

}
