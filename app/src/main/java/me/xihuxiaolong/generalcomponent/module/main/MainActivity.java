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
import me.xihuxiaolong.generalcomponent.module.uishow.UIShowActivity;
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
        showMultiUiModule.setText("各种好用的组件");

        Timber.d("Activity Created");

    }

    @OnClick({ R.id.show_toolbar1_btn, R.id.show_mvp_list, R.id.show_mvp_sticky_list, R.id.show_greendao_case, R.id.show_multi_ui_module})
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
            case R.id.show_multi_ui_module:
                startActivity(new Intent(this, UIShowActivity.class));
                break;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("Activity resume");
    }
}
