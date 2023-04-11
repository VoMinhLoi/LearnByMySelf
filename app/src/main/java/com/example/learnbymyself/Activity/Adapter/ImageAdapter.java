package com.example.learnbymyself.Activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnbymyself.Activity.Model.Image;
import com.example.learnbymyself.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Image> imageList;

    public ImageAdapter(List<Image> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override

    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customor_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.image.setImageResource(image.getImage());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageCTM);
        }
    }
}
