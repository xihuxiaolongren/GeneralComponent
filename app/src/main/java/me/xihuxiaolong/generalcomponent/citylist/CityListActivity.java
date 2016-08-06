package me.xihuxiaolong.generalcomponent.citylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.xihuxiaolong.generalcomponent.R;

public class CityListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new CityListFragment())
                    .commit();
        }
    }
}
