package top.lsmod.me.basecode.utils;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: ui界面操作的一些回调函数
 * @date: 2019/3/26 4:36 PM
 */
public class ViewInteface {

    /**
     * pupo点击
     */
    public interface OnXPupoItem{
        // XPupo条目点击事件
        void click(int postion, String text);
    }
}
