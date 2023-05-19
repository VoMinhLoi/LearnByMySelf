package com.example.learnbymyself.Activity.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.learnbymyself.Activity.Model.Clothe;
import com.example.learnbymyself.R;

import java.util.List;

public class ClothesAdapter extends BaseAdapter {
    private Activity activity;
    private List<Clothe> clotheList;
    public ClothesAdapter(Activity activity) {
        this.activity = activity;
    }
    public ClothesAdapter(List<Clothe> aoQuanList) {
        this.clotheList = aoQuanList;
    }
    public ClothesAdapter(Activity activity, List<Clothe> aoQuanList) {
        this.activity = activity;
        this.clotheList = aoQuanList;
    }

    @Override
    public int getCount() {
        return clotheList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        ImageView imageView;
        TextView nameTV, priceTV;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.customor_clothe,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.customor_clothe_img);
            viewHolder.nameTV = view.findViewById(R.id.customor_clothe_nameTV);
            viewHolder.priceTV = view.findViewById(R.id.customor_clothe_priceTV);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Clothe clothe = clotheList.get(i);
        Glide.with(viewHolder.imageView.getContext()).load(clothe.getImage()).into(viewHolder.imageView);
        viewHolder.nameTV.setText(clothe.getClothesName());
        viewHolder.priceTV.setText(clothe.getPrice());
        return view;
    }
}
