package me.xihuxiaolong.generalcomponent.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.module.citylist.CityListActivity;
import me.xihuxiaolong.generalcomponent.module.shortnotelist.ShortNoteListActivity;
import me.xihuxiaolong.generalcomponent.module.toolbarshow.ToolbarShowActivity;
import me.xihuxiaolong.generalcomponent.module.doubanmovielist.DoubanMovieListActivity;
import me.xihuxiaolong.library.widget.CustomToolbar;
import me.xihuxiaolong.library.widget.TimePickerDialogplus;
import mehdi.sakout.fancybuttons.FancyButton;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    CustomToolbar toolbar;

    @BindView(R.id.show_toolbar1_btn)
    FancyButton showToolbar1Btn;

    @BindView(R.id.show_mvp_list)
    FancyButton showMvpList;

    @BindView(R.id.show_mvp_sticky_list)
    FancyButton showMvpStickyList;

    @BindView(R.id.show_greendao_case)
    FancyButton showGreendaoCase;

    @BindView(R.id.show_timepicker)
    FancyButton showTimepicker;

    @BindView(R.id.show_multi_ui_module)
    FancyButton showMultiUiModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showToolbar1Btn.setText("简单toolbar");
        showMvpList.setText("mvp模式列表");
        showMvpStickyList.setText("mvp模式分层列表");
        showGreendaoCase.setText("greenDao演示");
        showTimepicker.setText("timepicker");
        showMultiUiModule.setText("各种好用的组件");

        Timber.d("Activity Created");

    }

    @OnClick({ R.id.show_toolbar1_btn, R.id.show_mvp_list, R.id.show_mvp_sticky_list, R.id.show_greendao_case, R.id.show_timepicker, R.id.show_multi_ui_module})
    public void onButtonClick(View view) {
        switch (view.getId()){
            case R.id.show_toolbar1_btn:
//                throw new RuntimeException("自定义异常xxx");
                startActivity(new Intent(this, ToolbarShowActivity.class));
                break;
            case R.id.show_mvp_list:
                startActivity(new Intent(this, DoubanMovieListActivity.class));
                break;
            case R.id.show_mvp_sticky_list:
                startActivity(new Intent(this, CityListActivity.class));
                break;
            case R.id.show_greendao_case:
                startActivity(new Intent(this, ShortNoteListActivity.class));
                break;
            case R.id.show_timepicker:
                TimePickerDialogplus timePickerDialogplus = new TimePickerDialogplus();
                timePickerDialogplus.getDialog(this, DateTime.now(), DateTime.now().plusYears(33), new TimePickerDialogplus.ConfirmListener() {
                    @Override
                    public void selectedTime(DateTime dateTime) {
                        Timber.d("dateTime", dateTime.toString("yyyy年MM月dd日 HH:mm:ss"));
                    }
                });
//                TimePickerDialog timePickerDialog = new TimePickerDialog();
//                timePickerDialog.getMaterialDialog(this, DateTime.now(), DateTime.now().plusYears(33), new TimePickerDialog.ConfirmListener() {
//                    @Override
//                    public void selectedTime(DateTime dateTime) {
//                        Timber.d("dateTime", dateTime.toString("yyyy年MM月dd日 HH:mm:ss"));
//                    }
//                });
                break;
            case R.id.show_multi_ui_module:
                startActivity(new Intent(this, ShortNoteListActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("Activity resume");
    }
}
