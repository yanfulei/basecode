package top.lsmod.me.basecode.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.enums.PopupType;
import com.lxj.xpopup.impl.BottomListPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.XPopupCallback;

import java.util.List;

import top.lsmod.me.basecode.customui.CheckBoxBottomPopup;
import top.lsmod.me.basecode.customui.CustomAttachPopup;
import top.lsmod.me.basecode.customui.CustomAttachQSPopup;
import top.lsmod.me.basecode.customui.CustomAttachYtmsPopup;
import top.lsmod.me.basecode.customui.CustomAttachkfzPopup;
import top.lsmod.me.basecode.customui.MenuPopup;
import top.lsmod.me.basecode.customui.ZhihuCommentPopup;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: 页面的ui相关操作
 * @date: 2019/3/26 4:28 PM
 */
public class PageUiUtils {

    /**
     * 展示pupo窗口 附着模式
     *
     * @param context
     * @param view
     * @param datas
     * @param viewInteface
     */
//    public static void showXpupoOnViewWatch(Context context, View view, List<String> datas, ViewInteface.OnXPupoItem viewInteface) {
//        XPopup.get(context).watch(view);
//        String[] _gtemp = new String[datas.size()];
//        String[] strArray2 = datas.toArray(_gtemp);
//        view.setOnClickListener(v -> XPopup.get(context).asAttachList(strArray2, null,
//                (position, text) -> viewInteface.click(position, text))
//                .show());
//    }

    /**
     * 展示pupo窗口 无负债模式
     *
     * @param context
     * @param view
     * @param datas
     * @param viewInteface
     */
    public static void showXpupoOnView(Context context, View view, List<String> datas, ViewInteface.OnXPupoItem viewInteface) {
        String[] _gtemp = new String[datas.size()];
        String[] strArray2 = datas.toArray(_gtemp);
        new XPopup.Builder(context)
                .atView(view)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                .moveUpToKeyboard(false)
                .asAttachList(strArray2, new int[]{}, (position, text) -> viewInteface.click(position, text))
                .show();
    }

    /**
     * 底部弹出popup窗口
     *
     * @param context
     * @param datas
     * @param viewInteface
     */
    public static void showXpopupOnViewBottom(Context context, List<String> datas, ViewInteface.OnXPupoItem viewInteface) {
        String[] _gtemp = new String[datas.size()];
        String[] strArray2 = datas.toArray(_gtemp);
        new XPopup.Builder(context)
//                        .enableDrag(false)
                .maxHeight(1300)
                .asBottomList("请选择", strArray2,
                        (position, text) -> viewInteface.click(position, text))
                .show();
    }

    /**
     * 展示底部弹出框
     *
     * @param context
     * @param url
     */
    public static void showBottomView(Context context, String url) {
        new XPopup.Builder(context)
                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                .asCustom(new ZhihuCommentPopup(context, url)/*.enableDrag(false)*/)
                .show();
    }

    /**
     * 展示原位实验二级菜单弹出框
     *
     * @param context
     */
    public static void showMenuYwsyView(Context context, View view, XPopupCallback callback, CustomAttachPopup.Ywsy ywsy) {
        new XPopup.Builder(context)
                .setPopupCallback(callback)
                .atView(view)
                .hasShadowBg(false) // 去掉半透明背景
                .asCustom(new CustomAttachPopup(context, ywsy))
                .show();
    }

    /**
     * 展示原位实验二级菜单弹出框
     *
     * @param context
     */
    public static void showMenuYtmsView(Context context, View view, XPopupCallback callback, CustomAttachYtmsPopup.Ytms ytms) {
        new XPopup.Builder(context)
                .setPopupCallback(callback)
                .atView(view)
                .hasShadowBg(false) // 去掉半透明背景
                .asCustom(new CustomAttachYtmsPopup(context, ytms))
                .show();
    }

    /**
     * 展示原位实验二级菜单弹出框
     *
     * @param context
     */
    public static void showMenuQSView(Context context, View view, XPopupCallback callback, CustomAttachQSPopup.Qs qs) {
        new XPopup.Builder(context)
                .setPopupCallback(callback)
                .atView(view)
                .hasShadowBg(false) // 去掉半透明背景
                .asCustom(new CustomAttachQSPopup(context, qs))
                .show();
    }


    /**
     * 展示原位实验二级菜单弹出框
     *
     * @param context
     */
    public static void showMenukfzView(Context context, View view, XPopupCallback callback, CustomAttachkfzPopup.Kfz kfz) {
        new XPopup.Builder(context)
                .setPopupCallback(callback)
                .atView(view)
                .hasShadowBg(false) // 去掉半透明背景
                .asCustom(new CustomAttachkfzPopup(context, kfz))
                .show();
    }

    /**
     * 底部弹出popup窗口
     *
     * @param context
     * @param datas
     * @param viewInteface
     */
    public static void showCheckBoxOnViewBottom(Activity context, List<String> datas, CheckBoxBottomPopup.ICheckBoxBottomPopup viewInteface) {
        CheckBoxBottomPopup popup = new CheckBoxBottomPopup(context, datas, viewInteface);
        new XPopup.Builder(context)
                .autoDismiss(false)
                .maxHeight(400)
                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                .asCustom(popup)/*.enableDrag(false)*/
                .show();
    }
}
