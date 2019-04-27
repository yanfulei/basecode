package top.lsmod.me.basecode.customui.springactionmenu;

import android.view.View;

/**
 * Created by lilei on 2016/11/10.
 */

public interface OnActionItemClickListener {

    public void onItemClick(int index, View view, boolean isopen);

    public void onAnimationEnd(boolean isOpen);
}
