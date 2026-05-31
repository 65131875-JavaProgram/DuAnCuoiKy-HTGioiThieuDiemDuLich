package yenly.edu.eurotravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import yenly.edu.eurotravel.R;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Integer> photoList;

    public PhotoAdapter(List<Integer> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        int imageResId = photoList.get(position);
        holder.imgPhotoItem.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhotoItem;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotoItem = itemView.findViewById(R.id.imgPhotoItem);
        }
    }
}