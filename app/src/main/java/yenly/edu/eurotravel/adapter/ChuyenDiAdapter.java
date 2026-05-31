package yenly.edu.eurotravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.dulieu.ChuyenDi;
import yenly.edu.eurotravel.activity.ChiTietActivity;

public class ChuyenDiAdapter extends RecyclerView.Adapter<ChuyenDiAdapter.ChuyenDiViewHolder> {

    private List<ChuyenDi> danhSachGoc;
    private List<ChuyenDi> danhSachHienTai;

    public ChuyenDiAdapter(List<ChuyenDi> danhSachGoc) {
        this.danhSachGoc = danhSachGoc;
        this.danhSachHienTai = new ArrayList<>();
    }
    public void setDanhSachChuyenDi(List<ChuyenDi> list) {
        this.danhSachGoc = list;
        this.danhSachHienTai.clear();
        this.danhSachHienTai.addAll(list);
        notifyDataSetChanged();
    }

    public void locDuLieu(String chuoiTimKiem) {
        danhSachHienTai.clear();
        if (chuoiTimKiem.isEmpty()) {
            danhSachHienTai.addAll(danhSachGoc);
        } else {
            String filterPattern = chuoiTimKiem.toLowerCase().trim();
            for (ChuyenDi item : danhSachGoc) {
                if (item.getTenDiaDiem().toLowerCase().contains(filterPattern) ||
                        item.getNgonNgu().toLowerCase().contains(filterPattern)) {
                    danhSachHienTai.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChuyenDiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chuyen_di, parent, false);
        return new ChuyenDiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuyenDiViewHolder holder, int position) {
        ChuyenDi chuyenDi = danhSachHienTai.get(position);
        Context context = holder.itemView.getContext();

        holder.txtTen.setText(chuyenDi.getTenDiaDiem());
        holder.txtThoiGian.setText(chuyenDi.getSoNgayDi());
        holder.txtQuocGia.setText(chuyenDi.getNgonNgu());
        holder.txtPrice.setText("$" + chuyenDi.getGiaTien());
        holder.txtRating.setText(String.valueOf(chuyenDi.getDiemDanhGia()));

        String tenHinhAnhLocal = chuyenDi.getTenDiaDiem().toLowerCase().trim();
        int resIdImage = context.getResources().getIdentifier(tenHinhAnhLocal, "drawable", context.getPackageName());

        if (resIdImage != 0) {
            Glide.with(context)
                    .load(resIdImage)
                    .into(holder.imgHinh);
        } else {
            Glide.with(context)
                    .load(R.drawable.paris)
                    .into(holder.imgHinh);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChiTietActivity.class);
                intent.putExtra("du_lieu_chuyen_di", chuyenDi);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachHienTai != null ? danhSachHienTai.size() : 0;
    }

    public static class ChuyenDiViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtThoiGian, txtQuocGia, txtPrice, txtRating;

        public ChuyenDiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgHinhChuyenDi);
            txtTen = itemView.findViewById(R.id.txtTenDiaDiem);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGian);
            txtQuocGia = itemView.findViewById(R.id.txtQuocGia);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRating = itemView.findViewById(R.id.txtRating);
        }
    }
}