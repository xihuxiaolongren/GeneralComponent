package me.xihuxiaolong.generalcomponent.citylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.common.api.ITXApiService;
import me.xihuxiaolong.generalcomponent.common.model.City;
import me.xihuxiaolong.generalcomponent.common.mvp.SimpleMvpLceRxPresenter;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class CityListFragmentPresenter extends SimpleMvpLceRxPresenter<CityListContract.IView, List<List<City>>> implements CityListContract.IPresenter{

    @Inject
    ITXApiService txApiService;

    HashMap<String, Integer> letters = new HashMap<>();
    ArrayList<String> customLetters = new ArrayList<>();

    @Inject
    public CityListFragmentPresenter() {}

    @Override
    public void loadCityData(){
        txApiService.getCityList(subscribe(false));
    }

    @Override
    protected void onNext(List<List<City>> data) {
        List<City> cities = data.get(1);
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(final City object1, final City object2) {
                return object1.getPinyin().get(0).compareTo(object2.getPinyin().get(0));
            }
        } );
        super.onNext(data);

        int position = 0;
        for (City city : cities) {
            String letter = city.getPinyin().get(0).substring(0, 1);
            //如果没有这个key则加入并把位置也加入
            if (!letters.containsKey(letter)) {
                letters.put(letter, position);
                customLetters.add(letter);
            }
            position++;
        }
        if (isViewAttached()) {
            getView().setQuickSideBarTipsData(customLetters);
        }

    }

    public void scrollToLetter(String letter){
        if(letters.containsKey(letter))
            getView().scrollToPosition(letters.get(letter));
    }

}
