package basecode.lsmod.top;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import basecode.lsmod.top.adapter.AdapterChsc;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/3/16 2:25 PM
 */
public class ActivityXlsxZhsc extends BaseActivityTitle {
    @BindView(R.id.listView)
    SwipeMenuListView listView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AdapterChsc adapterChsc;
    private List<String> datas;

    private void initData() {
        datas = new ArrayList<>();
        adapterChsc = new AdapterChsc(datas, this);
        listView.setAdapter(adapterChsc);
        initMenuView();
//        refreshLayout.finishLoadMore(); // 加载更多完成
//        refreshLayout.finishRefresh(); // 刷新完成
        refreshLayout.setOnRefreshListener(v -> {
//            pageIndex = 1;
            refreshLayout.setNoMoreData(false);
//            sendStockGetStockInout();
            loadLocalData();
            v.finishRefresh(1000/*,false*/);//传入false表示刷新失败
        });
//        refreshLayout.setOnLoadMoreListener(v -> {
//            pageIndex += 1;
//            initData();
//            v.finishLoadMore(1000/*,false*/);//传入false表示加载失败
//        });
        // 设置 Header 为 水滴 样式
        refreshLayout.setRefreshHeader(new WaterDropHeader(this));
        // 设置 Footer 为 经典模式 样式
//        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
//        refreshLayout.setEnableOverScrollDrag(false);
//        refreshLayout.setEnableLoadMore(false);
        // 加载更多
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
//            pageIndex++;
//            sendStockGetStockInout();
        });
        // 禁用加载更多
        refreshLayout.setEnableLoadMore(false);
    }

    private void loadLocalData() {
        for (int i = 0; i < 20; i++) {
            datas.add("第" + i + "条");
        }
        adapterChsc.notifyDataSetChanged();
    }

    private void initMenuView() {
        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
            // set item width
            openItem.setWidth(dp2px(90));
            // set item title
            openItem.setTitle("Open");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(dp2px(90));
            // set a icon
            deleteItem.setIcon(R.drawable.ic_flash_on);
            // add to menu
            menu.addMenuItem(deleteItem);
        };
        listView.setMenuCreator(creator);
    }

    @Override
    public int setStatusBarColor() {
        return R.color.A400red;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.acvitity_xlsx_zhsc, null);
    }

    @Override
    public String setTitleBarText() {
        return "下拉刷新SmartRefreshLayout，左滑删除SwipeMenuListView";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initData();
    }
}
