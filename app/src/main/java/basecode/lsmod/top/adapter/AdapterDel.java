package basecode.lsmod.top.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import basecode.lsmod.top.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanfulei on 2018/12/9
 * Email yanfulei1990@gmail.com
 */
public class AdapterDel extends BaseAdapter {

    List<String> datas;
    Activity context;

    public AdapterDel(List<String> datas, Activity context) {
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
            view = context.getLayoutInflater().inflate(R.layout.item_del, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.delete_button)
        RelativeLayout deleteButton;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}