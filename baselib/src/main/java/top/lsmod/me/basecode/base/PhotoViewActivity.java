package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.customui.PhotoView.PhotoView;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019-04-23 18:51
 */
public class PhotoViewActivity extends Activity {

    private String[] urls;
    private ViewPager viewPager;
    public static String photo_urls = "photo_urls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm_photoview);
        // 获取图片集合
        urls = getIntent().getStringExtra(photo_urls).split(",")/* + "?x-oss-process=image/resize,m_lfit,h_100,w_100"*/;
        viewPager = findViewById(R.id.pager);
        viewPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(PhotoViewActivity.this);
                view.enable();
//                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(PhotoViewActivity.this).load(urls[position]).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
