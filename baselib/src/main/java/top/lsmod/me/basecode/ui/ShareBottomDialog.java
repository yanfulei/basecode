package top.lsmod.me.basecode.ui;

import android.content.Context;
import android.graphics.Bitmap;
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
    private TextView tvTitle;
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
        tvTitle = inflate.findViewById(R.id.tv_title);
        tvTitle.setVisibility(View.GONE);
        shareBottomAdapter = new ShareBottomAdapter(mContext, datas);
        listView.setAdapter(shareBottomAdapter);
        shareBottomAdapter.notifyDataSetChanged();
        EventBus.getDefault().register(this);
        return inflate;
    }

    public void setTitle(String title) {
        this.tvTitle.setVisibility(View.VISIBLE);
        this.tvTitle.setText(title);
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

        public ShareBottomAdapter(Context context, List<BottomAdapterBean> datas) {
            this.context = context;
            this.datas = datas;
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
            viewHolder.tvMsg.setText(data.getMsg());
            return view;
        }

        class ViewHolder {
            TextView tvMsg;

            ViewHolder(View view) {
                tvMsg = view.findViewById(R.id.tv_msg);
            }
        }
    }

    public static class BottomAdapterBean {
        private String msg;
        private Bitmap icon;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }
    }
}
