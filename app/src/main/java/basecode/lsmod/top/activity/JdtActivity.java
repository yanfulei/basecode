package basecode.lsmod.top.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import basecode.lsmod.top.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.customui.stepview.HorizontalStepView;
import top.lsmod.me.basecode.customui.stepview.bean.StepBean;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019-05-31 18:11
 */
public class JdtActivity extends BaseActivityTitle {
    @BindView(R.id.step_view)
    HorizontalStepView stepView;

    @Override
    public String setStatusBarColor() {
        return "#3F51B5";
    }

    @Override
    public View setContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_jdt, null);
        return view;
    }

    @Override
    public String setTitleBarText() {
        return "进度条stepView";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("接单", 1);
        StepBean stepBean1 = new StepBean("打包", 1);
        StepBean stepBean2 = new StepBean("出发", 1);
        StepBean stepBean3 = new StepBean("送单", 0);
        StepBean stepBean4 = new StepBean("完成", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);
        stepView
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
//                .setOnItemClicked(position -> ToastUtils.showSnackbar(JdtActivity.this, getLlAllView(), "" + position, ToastUtils.SUCCESS));
    }
}
