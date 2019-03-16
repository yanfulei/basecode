package basecode.lsmod.top.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import java.util.List;

import basecode.lsmod.top.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/3/16 2:37 PM
 */
public class AdapterChsc extends BaseAdapter {

    List<String> datas;
    Activity context;

    public AdapterChsc(List<String> datas, Activity context) {
        this.datas = datas;
        this.context = context;
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
            view = context.getLayoutInflater().inflate(R.layout.item_del_new, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.ll_usb)
        LinearLayout llUsb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}