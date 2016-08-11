package me.xihuxiaolong.generalcomponent.shortnotelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.xihuxiaolong.generalcomponent.R;

public class ShortNoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new ShortNoteListFragment())
                    .commit();
        }
    }
}
