package me.xihuxiaolong.generalcomponent.doubanmovielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.xihuxiaolong.generalcomponent.R;

public class DoubanMovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new DoubanMovieListFragment())
                    .commit();
        }
    }
}
