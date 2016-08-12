package me.xihuxiaolong.generalcomponent.shortnoteedit;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;

public class ShortNoteEditActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_1);
        ButterKnife.bind(this);

        ShortNoteEditFragment shortNoteEditFragment = new ShortNoteEditFragment();
        Long shortnoteId = getIntent().getLongExtra(ShortNoteEditFragment.ARGUMENT_EDIT_SHORTNOTE_ID, -1L);
        if(shortnoteId != null) {
            Bundle bundle = new Bundle();
            bundle.putLong(ShortNoteEditFragment.ARGUMENT_EDIT_SHORTNOTE_ID, shortnoteId);
            shortNoteEditFragment.setArguments(bundle);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, shortNoteEditFragment)
                    .commit();
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.shortnote_edit);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
