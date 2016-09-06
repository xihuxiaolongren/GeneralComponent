package me.xihuxiaolong.generalcomponent.module.toolbarshow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.library.widget.CustomToolbar;

public class ToolbarShowActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_left_text)
    CustomToolbar toolbarLeftText;
    @BindView(R.id.toolbar_left_icon)
    CustomToolbar toolbarLeftIcon;
    @BindView(R.id.toolbar1)
    CustomToolbar toolbar1;
    @BindView(R.id.toolbar2)
    CustomToolbar toolbar2;
    @BindView(R.id.toolbar3)
    CustomToolbar toolbar3;
    @BindView(R.id.toolbar4)
    CustomToolbar toolbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_show);
        ButterKnife.bind(this);
        Slidr.attach(this);

        toolbarLeftText.setLeftFirstAreaClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击效果", Toast.LENGTH_SHORT).show();
            }
        });

        toolbarLeftIcon.setLeftFirstAreaClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击效果", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar1.setBackVisble(true, this);
        toolbar2.setBackVisble(true, this);
        toolbar3.setBackVisble(true, this);
        toolbar4.setBackVisble(true, this);


//        toolbar1.init(true, this, "toolbar演示-右角标-文字", "功能", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "功能演示", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        toolbar2.init(true, this, "toolbar演示-右角标-图片", R.drawable.icon_search, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "搜索功能演示", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
