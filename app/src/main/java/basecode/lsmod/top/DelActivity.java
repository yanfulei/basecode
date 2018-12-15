package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import basecode.lsmod.top.adapter.AdapterDel;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Created by yanfulei on 2018/12/9
 * Email yanfulei1990@gmail.com
 */
public class DelActivity extends BaseActivityTitle {
    @BindView(R.id.lv_test_del)
    ListView lvTestDel;
    private List<String> datas;
    private AdapterDel adapterDel;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_del, null);
    }

    @Override
    public String setTitleBarText() {
        return "侧滑删除";
    }

    @Override
    public boolean showRightTitleButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        datas = new ArrayList<>();
        adapterDel = new AdapterDel(datas, this);
        lvTestDel.setAdapter(adapterDel);
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
        adapterDel.notifyDataSetChanged();
    }
}
