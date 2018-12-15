package top.lsmod.me.basecode.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.wang.avi.AVLoadingIndicatorView;

import top.lsmod.me.basecode.R;

/**
 * Created by yanfulei on 2018/10/6
 * Email yanfulei1990@gmail.com
 */
public class LoadingDialog extends BaseDialog<LoadingDialog> {
    private Context context;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    //该方法用来出来数据初始化代码
    @Override
    public View onCreateView() {
        widthScale(0.25f);
        //填充弹窗布局
        View inflate = View.inflate(context, R.layout.dialog_loading, null);
        // 加载框
        avLoadingIndicatorView = inflate.findViewById(R.id.av_loading);
        //用来放整个图片的控件
//        iv_ad = (ImageView) inflate.findViewById(R.id.iv_ad);
        //放在透明部分和错号上的隐形控件，用来点击使弹窗消失
//        back = (ImageView) inflate.findViewById(R.id.ad_back);
        //用来加载网络图片，填充iv_ad控件，注意要添加网络权限，和Picasso的依赖和混淆
//        Picasso.with(context)
//                .load("https://img-blog.csdn.net/20170906094014301?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzY2MjE5OTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast")
//                .into(iv_ad);

        return inflate;
    }

    //该方法用来处理逻辑代码
    @Override
    public void setUiBeforShow() {
        avLoadingIndicatorView.show();
//        //点击弹窗相应位置，处理相关逻辑。
//        iv_ad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"哈哈",Toast.LENGTH_SHORT).show();
//                //处理完逻辑关闭弹框的代码
//                dismiss();
//            }
//        });
//        //点×关闭弹框的代码
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //关闭弹框的代码
//                dismiss();
//            }
//        });
    }
}
