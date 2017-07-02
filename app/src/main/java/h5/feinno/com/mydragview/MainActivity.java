package h5.feinno.com.mydragview;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public DragViewContainer mDragViewContainer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDragViewContainer = (DragViewContainer) findViewById(R.id.dragcontainer);
        ImageDragView imageDragView = new ImageDragView(getDrawable(R.drawable.ic_launcher));
        mDragViewContainer.addDragView(imageDragView);
    }
}
