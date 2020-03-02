package sen.com.uicomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by korneliussendy on 2019-10-08,
 * at 04:42.
 * Ajaib
 */
public class WrapContentViewPager extends ViewPager {

    private View currentView;
    private Context context;
    private View parentView;

    public WrapContentViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 1;
        //TODO uncomment this to set height default as a parentView
        /*if (parentView != null) {
            height = parentView.getMeasuredHeight() -
                    context.getResources().getDimensionPixelSize(R.dimen.tab_height);
        }*/
        if (currentView != null){
            currentView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = currentView.getMeasuredHeight();
            if (h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void measureCurrentView(View currentView, @Nullable View parentView) {
        this.currentView = currentView;
        if (parentView != null) this.parentView = parentView;
        requestLayout();
    }

    public void initHeight(View view){
        this.parentView = view;
        requestLayout();
    }

    public int measureFragment(View view) {
        if (view == null)
            return 0;

        view.measure(0, 0);
        return view.getMeasuredHeight();
    }
}