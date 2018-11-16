package top.lsmod.me.basecode.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import java.util.List;
import top.lsmod.me.basecode.R;

/**
 * Created by yanfulei on 2018/10/6
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseActivityBottomNavBar extends FragmentActivity {

    private CommonTitleBar commonTitleBar;
    private BottomNavigationBar bottomNavigationBar;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_navbar);
        initParentView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initParentView() {
        commonTitleBar = findViewById(R.id.titlebar);
        commonTitleBar.getCenterTextView().setText(setTitleBarText());
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        // 设置导航栏
        setNavBarItem(bottomNavigationBar);
        // 设置fragment
        viewPager = findViewById(R.id.viewpager);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        List<Fragment> fragments = setFragments();
        for (Fragment fragment : fragments){
            fragmentPagerAdapter.addFragment(fragment);
        }
        viewPager.setAdapter(fragmentPagerAdapter);
        // 设置底部导航栏点击事件
        setBottomNavBarClick();
    }

    /**
     * 添加底部导航栏项目
     */
    public void setNavBarItem(BottomNavigationBar bottomNavigationBar) {
        // demo
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_4k, "Home").setBadgeItem(new TextBadgeItem().setText("99+")))
                .addItem(new BottomNavigationItem(R.drawable.ic_4k, "Books"))
                .addItem(new BottomNavigationItem(R.drawable.ic_4k, "Music"))
                .addItem(new BottomNavigationItem(R.drawable.ic_4k, "Movies & TV"))
                .addItem(new BottomNavigationItem(R.drawable.ic_4k, "Games"))
//                .addItem(new BottomNavigationItem(top.lsmod.me.basecode.R.drawable.ic_4k, "Music").setActiveColor(R.color.orange).setInActiveColor(R.color.teal)).setInactiveIcon(R.drawable.ic_home_black))
//                .addItem(new BottomNavigationItem(top.lsmod.me.basecode.R.drawable.ic_4k, "Movies & TV"))
//                .addItem(new BottomNavigationItem(top.lsmod.me.basecode.R.drawable.ic_4k, "Games"))
                .initialise();
    }

    /**
     * 添加底部导航栏点击事件
     */
    public void setBottomNavBarClick(){
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    /**
     * 设置fragment
     */
    public abstract List<Fragment> setFragments();

    /**
     * 设置标题
     */
    public abstract String setTitleBarText();

    /**
     * 初始化布局
     */
    public abstract void initData();
}
