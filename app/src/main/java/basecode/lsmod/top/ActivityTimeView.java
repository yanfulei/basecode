package basecode.lsmod.top;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.customui.pickerview.popwindow.DatePickerPopWin;
import top.lsmod.me.basecode.customui.pickerview.popwindow.TimePickerPopWin;
import top.lsmod.me.basecode.utils.BDateUtils;

/**
 * Author:yanfulei
 * Date:2018/12/3
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityTimeView extends BaseActivityTitle {
    @BindView(R.id.btn_riqi)
    Button btnRiqi;

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

    @OnClick({R.id.btn_riqi, R.id.btn_shijian})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_riqi:
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(ActivityTimeView.this, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        // 转换为long
                        long time = BDateUtils.stringToLong(dateDesc, BDateUtils.YYYY_MM_DD);
                        Toast.makeText(ActivityTimeView.this, "String 日期：" + dateDesc + "时间戳：" + time, Toast.LENGTH_SHORT).show();
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
            case R.id.btn_shijian:
                TimePickerPopWin timePickerPopWin = new TimePickerPopWin.Builder(ActivityTimeView.this, new TimePickerPopWin.OnTimePickListener() {
                    @Override
                    public void onTimePickCompleted(int hour, int minute, String AM_PM, String time) {
                        Toast.makeText(ActivityTimeView.this, time, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("确定")
                        .textCancel("取消")
                        .btnTextSize(16)
                        .viewTextSize(25)
                        .colorCancel(Color.parseColor("#999999"))
                        .colorConfirm(Color.parseColor("#009900"))
                        .build();
                timePickerPopWin.showPopWin(ActivityTimeView.this);
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
