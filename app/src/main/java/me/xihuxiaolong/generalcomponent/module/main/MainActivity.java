package me.xihuxiaolong.generalcomponent.module.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryToggleDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xihuxiaolong.generalcomponent.BuildConfig;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.TestLeakHelper;
import me.xihuxiaolong.generalcomponent.module.citylist.CityListActivity;
import me.xihuxiaolong.generalcomponent.module.map.MapActivity;
import me.xihuxiaolong.generalcomponent.module.shortnotelist.ShortNoteListActivity;
import me.xihuxiaolong.generalcomponent.module.toolbarshow.ToolbarShowActivity;
import me.xihuxiaolong.generalcomponent.module.doubanmovielist.DoubanMovieListActivity;
import me.xihuxiaolong.generalcomponent.module.uishow.UIShowActivity;
import me.xihuxiaolong.library.widget.CustomToolbar;
import mehdi.sakout.fancybuttons.FancyButton;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult = null;
    private Drawer result = null;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        if(BuildConfig.DEBUG)
        TestLeakHelper.getInstance(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.main);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("xihuxiaolongren").withEmail("yangxiaolong2012@gmail.com").withIcon("https://avatars1.githubusercontent.com/u/10987467?v=3&u=96c3aa1e286c55d9557a3248af085c3a29511d26&s=640").withIdentifier(100);

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
//                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("简单toolbar").withDescription("toolbar居中的一些样式").withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("mvp模式列表").withDescription("通过豆瓣api展示了基于mosby的mvc结构实现的页面").withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("mvp模式分层列表").withDescription("实现了一个常见的城市选择列表").withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName("greenDao演示").withDescription("实现了一个便签，演示通过greendao增删改查sqlite").withIdentifier(4).withSelectable(false),
                        new PrimaryDrawerItem().withName("各种好用的UI组件").withDescription("来自github&自己封装的一些组件示例").withIdentifier(5).withSelectable(false),
                        new ExpandableDrawerItem().withName("地图").withIsExpanded(true).withIcon(R.drawable.icon_map).withIdentifier(6).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("实时定位").withLevel(2).withIdentifier(2000),
                                new SecondaryDrawerItem().withName("历史轨迹").withLevel(2).withIdentifier(2001)
                        )
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(MainActivity.this, ToolbarShowActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(MainActivity.this, DoubanMovieListActivity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(MainActivity.this, CityListActivity.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(MainActivity.this, ShortNoteListActivity.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(MainActivity.this, UIShowActivity.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(MainActivity.this, UIShowActivity.class);
                            } else if (drawerItem.getIdentifier() == 2000) {
                                intent = new Intent(MainActivity.this, MapActivity.class).putExtra("status", 0);
                            } else if (drawerItem.getIdentifier() == 2001) {
                                intent = new Intent(MainActivity.this, MapActivity.class).putExtra("status", 1);
                            }

                            if (intent != null) {
                                startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
//                .withShowDrawerOnFirstLaunch(true)
                .withSelectedItem(-1)
                .build();
        result.openDrawer();
        Timber.d("Activity Created");
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
        result.openDrawer();
        Timber.d("Activity resume");
    }
}
