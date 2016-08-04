package me.xihuxiaolong.generalcomponent.citylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.model.City;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/8.
 */
public class CityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private ArrayList<City> items = new ArrayList<City>();

    public CityListAdapter() {
//        setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CityViewHolder) holder).cityTv.setText(getItem(position).getFullname());
    }

    public void add(City object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, City object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends City> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(City... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public City getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getPinyin().get(0).charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_city, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextView)holder.itemView).setText(getItem(position).getPinyin().get(0).substring(0, 1));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
