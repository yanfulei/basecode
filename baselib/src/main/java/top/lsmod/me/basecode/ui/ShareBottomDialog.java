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
import java.util.List;

import top.lsmod.me.basecode.R;

/**
 * 自定义底部弹出框
 */
public class ShareBottomDialog extends BottomBaseDialog<ShareBottomDialog> {

    private List<String> datas;
    private ListView listView;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public ShareBottomDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareBottomDialog(Context context) {
        super(context);
    }

    public ShareBottomDialog(Context context, View animateView, List<String> datas){
        super(context, animateView);
        this.datas = datas;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_share, null);
        listView = inflate.findViewById(R.id.lv_items);
        ShareBottomAdapter shareBottomAdapter = new ShareBottomAdapter(mContext, datas);
        listView.setAdapter(shareBottomAdapter);
        shareBottomAdapter.notifyDataSetChanged();
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        listView.setOnItemClickListener(onItemClickListener);
    }

    public class ShareBottomAdapter extends BaseAdapter {

        private List<String> datas;
        private Context context;

        public ShareBottomAdapter(Context context, List<String> datas){
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
            viewHolder.tvCode.setText(datas.get(i));
            return view;
        }

        class ViewHolder {

            TextView tvCode;

            ViewHolder(View view) {
                tvCode = view.findViewById(R.id.tv_code);
            }
        }
    }
}
