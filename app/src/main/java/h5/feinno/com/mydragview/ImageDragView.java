package h5.feinno.com.mydragview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * <pre>
 *     author : zhonghang
 *     e-mail : zhonghang@feinno.com
 *     time   : 2017/06/21 15:14
 *     desc   :用于图片的拖动组件
 *     @see DragViewContainer
 *     version: 1.0
 * </pre>
 */
public class ImageDragView extends DragView {
    /**
     * 展示图片的路径，此处为图片的绝对路径
     * TODO 后期添加新的byte[]数组的方式，和url以及uri的方式
     */
    private String mPath;
    /**
     * 需要绘制的图片的drawable
     */
    private Drawable mDrawable;
    /**
     * 需要绘制的图片的范围
     */
    private Rect mRealBounds;

    /**
     * 创建时需要传递进来图片资源
     *
     * @param path 图片的路径
     */
    public ImageDragView(String path) {
        setPath(path);
    }

    public ImageDragView(Drawable drawable) {
        mDrawable = drawable;
        mRealBounds = new Rect(0, 0, getWidth(), getHeight());
    }

    public String getPath() {
        return mPath;
    }

    /**
     * 设置图片的path路径
     *
     * @param mPath
     */
    public void setPath(String mPath) {
        this.mPath = mPath;
        mDrawable = Drawable.createFromPath(mPath);
        mRealBounds = new Rect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.concat(mMatrix);
        mDrawable.setBounds(mRealBounds);
        mDrawable.draw(canvas);
        canvas.restore();
    }


    @Override
    public int getWidth() {
        return mDrawable.getIntrinsicWidth();
    }

    @Override
    public int getHeight() {
        return mDrawable.getIntrinsicHeight();
    }
}
