package me.xihuxiaolong.generalcomponent.module.uishow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.library.utils.DialogUtil;
import me.xihuxiaolong.library.widget.VerticalTextview;

public class UITextShowFragment extends Fragment {

    @BindView(R.id.autofitTextView)
    AutofitTextView autofitTextView;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.verticalTextView)
    VerticalTextview verticalTextView;
    @BindView(R.id.hTextView)
    HTextView hTextView;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.normalTextView)
    TextView normalTextView;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.expand_text_view)
    ExpandableTextView expandTextView;

    private ArrayList<String> titleList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_text_show, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        titleList.add("你是天上最受宠的一架钢琴");
        titleList.add("我是丑人脸上的鼻涕");
        titleList.add("你发出完美的声音");
        titleList.add("我被默默揩去");
        titleList.add("你冷酷外表下藏着诗情画意");
        titleList.add("我已经够胖还吃东西");
        titleList.add("你踏着七彩祥云离去");
        titleList.add("我被留在这里");
        verticalTextView.setTextList(titleList);
        verticalTextView.setText(26, 5, Color.RED);//设置属性
        verticalTextView.setTextStillTime(3000);//设置停留时长间隔
        verticalTextView.setAnimTime(300);//设置进入和退出的时间间隔
        verticalTextView.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "点击了 : " + titleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                autofitTextView.setText(charSequence);
                normalTextView.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final String[] items = {"scale", "evaporate", "fall", "sparkle", "line", "anvil", "typer", "rainbow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, items);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                switch (items[position]) {
                    case "scale":
                        hTextView.setAnimateType(HTextViewType.SCALE);
                        break;
                    case "evaporate":
                        hTextView.setAnimateType(HTextViewType.EVAPORATE);
                        break;
                    case "fall":
                        hTextView.setAnimateType(HTextViewType.FALL);
                        break;
                    case "sparkle":
                        hTextView.setAnimateType(HTextViewType.SPARKLE);
                        break;
                    case "line":
                        hTextView.setAnimateType(HTextViewType.LINE);
                        break;
                    case "anvil":
                        hTextView.setAnimateType(HTextViewType.ANVIL);
                        break;
                    case "typer":
                        hTextView.setAnimateType(HTextViewType.TYPER);
                        break;
                    case "rainbow":
                        hTextView.setAnimateType(HTextViewType.RAINBOW);
                        break;
                }
                updateCounter();
            }
        });
        spinner.setAdapter(adapter);

        expandTextView.setText(getString(R.string.dummy_text1));
        return view;
    }

    String[] sentences = new String[]{"What is design?", "Design", "Design is not just", "what it looks like", "and feels like.", "Design", "is how it works.", "- Steve Jobs", "Older people", "sit down and ask,", "'What is it?'", "but the boy asks,", "'What can I do with it?'.", "- Steve Jobs", "Swift", "Objective-C", "iPhone", "iPad", "Mac Mini", "MacBook Pro", "Mac Pro", "爱老婆", "老婆和女儿"};
    private int mCounter = 10;

    private void updateCounter() {
        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        hTextView.animateText(sentences[mCounter]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_explain:
                DialogUtil.showDialog(getContext(), "image UI 说明", Html.fromHtml(getString(R.string.about_button)));
                return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        verticalTextView.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        verticalTextView.stopAutoScroll();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_button_menu, menu);
    }

}
