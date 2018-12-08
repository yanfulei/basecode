package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/2
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityGjt extends BaseActivityTitle {
    private View view;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        view = getLayoutInflater().inflate(R.layout.activity_gjt, null);
        return view;
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllChildViews(view);
    }

    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                if (viewchild.getClass().getName().contains("LinearLayout")){
                    allchildren.add(viewchild);
                }
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }
}
