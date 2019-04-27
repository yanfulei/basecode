package top.lsmod.me.basecode.customui.textfieldboxes;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: selected 点击事件
 * @date: 2019/3/26 5:45 PM
 */
public interface TfbChangeWatcher {
    // 作为select类型
    interface OnSelectType {
        // 作为select点击事件
        void onClick();
    }

    // 作为输入框类型
    interface OnInputType {
        void onInput(String text);
    }
}
