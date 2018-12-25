package top.lsmod.me.basecode.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.widget.base.BottomBaseDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.ShareBottomItemDelClickEvBean;

/**
 * 自定义底部弹出框
 */
public class ShareBottomDialog extends BottomBaseDialog<ShareBottomDialog> {

    private List<BottomAdapterBean> datas;
    private ListView listView;
    private ShareBottom shareBottom;

    public ShareBottomAdapter getShareBottomAdapter() {
        return shareBottomAdapter;
    }

    private ShareBottomAdapter shareBottomAdapter;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemDelClickListener(ShareBottom shareBottom) {
        this.shareBottom = shareBottom;
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    private CodeOp codeOp;

    public void setCodeOp(CodeOp codeOp) {
        this.codeOp = codeOp;
    }

    public ShareBottomDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareBottomDialog(Context context) {
        super(context);
    }

    public ShareBottomDialog(Context context, View animateView, List<BottomAdapterBean> datas) {
        super(context, animateView);
        this.datas = datas;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_share, null);
        listView = inflate.findViewById(R.id.lv_items);
        shareBottomAdapter = new ShareBottomAdapter(mContext, datas, codeOp);
        listView.setAdapter(shareBottomAdapter);
        shareBottomAdapter.notifyDataSetChanged();
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        listView.setOnItemClickListener(onItemClickListener);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onItemClick(ShareBottomItemDelClickEvBean shareBottomItemDelClickEvBean) {
        if (null == shareBottom) return;
        shareBottom.OnItemDelClick(shareBottomItemDelClickEvBean.getPosion());
    }

    public interface ShareBottom {
        void OnItemDelClick(int postion);
    }

    /**
     * 适配器类代码
     */
    public static class ShareBottomAdapter extends BaseAdapter {

        private List<BottomAdapterBean> datas;
        private Context context;
        private CodeOp codeOp;

        public ShareBottomAdapter(Context context, List<BottomAdapterBean> datas, CodeOp codeOp) {
            this.context = context;
            this.datas = datas;
            this.codeOp = codeOp;
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
                view = View.inflate(context, R.layout.item_share_item, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BottomAdapterBean data = datas.get(i);
            viewHolder.tvCode.setText(data.getCode());
            viewHolder.tvCode.setTextColor(data.isError ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.right_lab));
            viewHolder.tvCodeDel.setVisibility(data.isShowDel() ? View.VISIBLE : View.GONE);
            viewHolder.tvCodeDel.setOnClickListener(view1 -> {
                codeOp.OnCodeDel(data.getCode(), i);
            });
            return view;
        }

        class ViewHolder {

            TextView tvCode;
            TextView tvCodeDel;

            ViewHolder(View view) {
                tvCode = view.findViewById(R.id.tv_code);
                tvCodeDel = view.findViewById(R.id.tv_code_del);
            }
        }
    }

    public static class BottomAdapterBean {
        private String code;
        private boolean showDel;
        private boolean isError;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isShowDel() {
            return showDel;
        }

        public void setShowDel(boolean showDel) {
            this.showDel = showDel;
        }

        public boolean isError() {
            return isError;
        }

        public void setError(boolean error) {
            isError = error;
        }
    }

    interface CodeOp {
        void OnCodeDel(String code, int posthion);
    }
}
