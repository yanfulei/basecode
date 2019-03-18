package top.lsmod.me.basecode.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/3/18 6:22 PM
 */
public class ViewUtils {

    /**
     * gridview计算高度
     * */
    public static void setGridViewHeightByChildren(GridView gridView){
        //获取gridview高度
        ListAdapter listAdaper=gridView.getAdapter();
        if(listAdaper==null){
            return;
        }
        //总高度
        int totalHeight=0;
        //计算行数 向上取整
        int lineNum= (int) Math.ceil((double)listAdaper.getCount()/(double)gridView.getNumColumns());
        View item=listAdaper.getView(0,null,gridView);
        item.measure(0,0);
        //获取高度和
        totalHeight=item.getMeasuredHeight()*lineNum;
        //布局参数
        ViewGroup.LayoutParams params=gridView.getLayoutParams();
        //设置布局高度
        params.height=totalHeight;
        //设置margin
        ((ViewGroup.MarginLayoutParams)params).setMargins(10,10,10,10);
        //设置参数
        gridView.setLayoutParams(params);
    }
}
