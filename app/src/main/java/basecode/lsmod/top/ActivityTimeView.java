package basecode.lsmod.top;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/3
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityTimeView extends BaseActivityTitle {
    @BindView(R.id.btn_riqi)
    Button btnRiqi;

    @Override
    public void initSingle() {

    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_time_view, null);
    }

    @Override
    public String setTitleBarText() {
        return "时间选择";
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_riqi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_riqi:
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(ActivityTimeView.this, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        Toast.makeText(ActivityTimeView.this, dateDesc, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("确定") //text of confirm button
                        .textCancel("取消") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(25) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1990) //min year in loop
                        .maxYear(2550) // max year in loop
                        .showDayMonthYear(true) // shows like dd mm yyyy (default is false)
                        .dateChose("2013-11-11") // date chose when init popwindow
                        .build();
                pickerPopWin.showPopWin(ActivityTimeView.this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
