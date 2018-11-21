package basecode.lsmod.top;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_bat)
    Button btnBat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_bat})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_bat:
                startActivity(new Intent(this, Activity1.class));
                break;
        }
    }
}
