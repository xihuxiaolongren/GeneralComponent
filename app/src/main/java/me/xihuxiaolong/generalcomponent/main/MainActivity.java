package me.xihuxiaolong.generalcomponent.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.citylist.CityListActivity;
import me.xihuxiaolong.generalcomponent.shortnotelist.ShortNoteListActivity;
import me.xihuxiaolong.generalcomponent.toolbarshow.ToolbarShowActivity;
import me.xihuxiaolong.generalcomponent.doubanmovielist.DoubanMovieListActivity;
import me.xihuxiaolong.library.widget.CustomToolbar;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    CustomToolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.show_toolbar1_btn)
    FancyButton showToolbar1Btn;

    @BindView(R.id.show_mvp_list)
    FancyButton showMvpList;

    @BindView(R.id.show_mvp_sticky_list)
    FancyButton showMvpStickyList;

    @BindView(R.id.show_greendao_case)
    FancyButton showGreendaoCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showToolbar1Btn.setText("简单toolbar");
        showMvpList.setText("mvp模式列表");
        showMvpStickyList.setText("mvp模式分层列表");
        showGreendaoCase.setText("greenDao演示");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick({ R.id.show_toolbar1_btn, R.id.show_mvp_list, R.id.show_mvp_sticky_list, R.id.show_greendao_case})
    public void onButtonClick(View view) {
        switch (view.getId()){
            case R.id.show_toolbar1_btn:
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
}
