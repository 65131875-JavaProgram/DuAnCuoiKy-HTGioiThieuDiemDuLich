package yenly.edu.eurotravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<ChuyenDi.PhotoItem> mListPhotos;
    private OnPhotoClickListener mListener;

    public interface OnPhotoClickListener {
        void onPhotoClick(ChuyenDi.PhotoItem item);
    }

    public PhotoAdapter(List<ChuyenDi.PhotoItem> listPhotos, OnPhotoClickListener listener) {
        this.mListPhotos = listPhotos;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        ChuyenDi.PhotoItem item = mListPhotos.get(position);
        if (item != null) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getHinhAnhUrl())
                    .into(holder.imgPhotoItem);

            holder.txtTenDiaDanhNoiTieng.setText(item.getChuThich());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onPhotoClick(item);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListPhotos != null ? mListPhotos.size() : 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhotoItem;
        TextView txtTenDiaDanhNoiTieng;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotoItem = itemView.findViewById(R.id.imgPhotoItem);
            txtTenDiaDanhNoiTieng = itemView.findViewById(R.id.txtTenDiaDanhNoiTieng);
        }
    }
}