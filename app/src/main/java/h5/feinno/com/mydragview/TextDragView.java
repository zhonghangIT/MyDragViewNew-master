package h5.feinno.com.mydragview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.widget.TextView;

/**
 * <pre>
 *     author : zhonghang
 *     e-mail : zhonghang@feinno.com
 *     time   : 2017/06/21 15:14
 *     desc   : 用于文本的拖动控件的实现
 *     @see DragViewContainer
 *     version: 1.0
 * </pre>
 */

public class TextDragView extends DragView {
    /**
     * 字号
     */
    protected int mTextSize;
    /**
     * 字体颜色
     */
    protected int mTextColor;
    /**
     * 字体类型，宋体仿宋等等
     */
    protected String mTextFont;
    /**
     * 文字背景色
     */
    protected int mTextBgColor;

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public String getTextFont() {
        return mTextFont;
    }

    public void setTextFont(String mTextFont) {
        this.mTextFont = mTextFont;
    }

    public int getTextBgColor() {
        return mTextBgColor;
    }

    public void setTextBgColor(int mTextBgColor) {
        this.mTextBgColor = mTextBgColor;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
