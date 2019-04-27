package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import top.lsmod.me.basecode.R;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019-04-23 18:51
 */
public class BmPhotoViewActivity extends Activity {

    private String url;
    private PhotoView mPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm_photoview);

        mPhotoView = findViewById(R.id.img1);
        mPhotoView.enable();

        mPhotoView.setOnLongClickListener(v -> {
//            Toast.makeText(BmPhotoViewActivity.this, "长按了", Toast.LENGTH_SHORT).show();
            return false;
        });
        mPhotoView.setOnClickListener(view -> finish());
        url = getIntent().getStringExtra("photo_url")/* + "?x-oss-process=image/resize,m_lfit,h_100,w_100"*/;
        Glide.with(this).load(url).into((ImageView) findViewById(R.id.img1));
    }
}
