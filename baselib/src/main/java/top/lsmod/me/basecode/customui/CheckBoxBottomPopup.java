package top.lsmod.me.basecode.customui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import java.util.ArrayList;
import java.util.List;

import top.lsmod.me.basecode.R;


/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: 多选框
 * @date: 2019/4/1 11:42 PM
 */
public class CheckBoxBottomPopup extends BottomPopupView {
    private ListView lv_datas;
    private Activity activity;
    private List<CheckBean> checkBeans;
    private CloundAdapter adapter;
    private ICheckBoxBottomPopup iCheckBoxBottomPopup;
    private List<String> datas;

    public CheckBoxBottomPopup(@NonNull Activity activity, List<String> datas, ICheckBoxBottomPopup iCheckBoxBottomPopup) {
        super(activity);
        this.activity = activity;
        this.iCheckBoxBottomPopup = iCheckBoxBottomPopup;
        this.datas = datas;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_checkbox_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        lv_datas = findViewById(R.id.lv_datas);
        checkBeans = new ArrayList<>();
        adapter = new CloundAdapter(checkBeans, activity);
        checkBeans.clear();
        List<CheckBean> _temp = new ArrayList<>();
        Stream.of(datas).forEach(v -> {
            CheckBean c = new CheckBean();
            c.setCheck(false);
            c.setText(v);
            _temp.add(c);
        });
        checkBeans.addAll(_temp);
        lv_datas.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {
        List<CheckBean> checked = Stream.of(adapter.getDatas()).filter(v -> v.isCheck).toList();
        iCheckBoxBottomPopup.onBatchCheck(Stream.of(checked).map(v -> v.getText()).toList());
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }

//    public void setData(List<String> data) {
//        checkBeans.clear();
//        List<CheckBean> _temp = new ArrayList<>();
//        Stream.of(data).forEach(v -> {
//            CheckBean c = new CheckBean();
//            c.setCheck(false);
//            c.setText(v);
//            _temp.add(c);
//        });
//        checkBeans.addAll(_temp);
//        adapter.notifyDataSetChanged();
//    }

    public class CloundAdapter extends BaseAdapter {

        List<CheckBean> datas;
        Activity context;

        public CloundAdapter(List<CheckBean> datas, Activity context) {
            this.datas = datas;
            this.context = context;
        }

        public List<CheckBean> getDatas() {
            return this.datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;

            if (view == null) {
                view = context.getLayoutInflater().inflate(R.layout.item_checkbox_bottom, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_check = view.findViewById(R.id.iv_check);
                viewHolder.tv_text = view.findViewById(R.id.tv_text);
                viewHolder.llallview4 = view.findViewById(R.id.ll_allview4);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            CheckBean str = datas.get(i);
            viewHolder.tv_text.setText(str.getText());
            viewHolder.iv_check.setVisibility(str.isCheck ? VISIBLE : GONE);
            viewHolder.llallview4.setOnClickListener(v -> {
                str.setCheck(!str.isCheck);
                notifyDataSetChanged();
            });
            return view;
        }

        class ViewHolder {
            TextView tv_text;
            ImageView iv_check;
            LinearLayout llallview4;
        }
    }

    public class CheckBean {
        String text;
        boolean isCheck;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }

    public interface ICheckBoxBottomPopup {
        void onBatchCheck(List<String> checked);
    }
}