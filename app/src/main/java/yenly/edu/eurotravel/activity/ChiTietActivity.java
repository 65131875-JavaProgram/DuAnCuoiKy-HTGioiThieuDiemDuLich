package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class ChiTietActivity extends AppCompatActivity {

    private ImageView imgChiTiet;
    private ImageButton btnBack;
    private TextView txtTenChiTiet, txtGiaChiTiet, txtMoTaChiTiet, txtSoSaoNhanXet;
    private RatingBar ratingBarHienThi;
    private Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        imgChiTiet = findViewById(R.id.imgChiTiet);
        btnBack = findViewById(R.id.btnBack);
        txtTenChiTiet = findViewById(R.id.txtTenChiTiet);
        txtGiaChiTiet = findViewById(R.id.txtGiaChiTiet);
        txtMoTaChiTiet = findViewById(R.id.txtMoTaChiTiet);
        txtSoSaoNhanXet = findViewById(R.id.txtSoSaoNhanXet);
        ratingBarHienThi = findViewById(R.id.ratingBarHienThi);
        btnFavorite = findViewById(R.id.btnFavorite);

        if (getIntent().hasExtra("du_lieu_chuyen_di")) {
            ChuyenDi chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");

            if (chuyenDi != null) {
                txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
                txtGiaChiTiet.setText("$" + chuyenDi.getGiaTien());
                txtMoTaChiTiet.setText(chuyenDi.getMoTa());
                imgChiTiet.setImageResource(chuyenDi.getHinhAnh());
                ratingBarHienThi.setRating(chuyenDi.getDiemDanhGia());
                txtSoSaoNhanXet.setText("(" + chuyenDi.getDiemDanhGia() + ") - Đánh giá từ khách hàng");
            }
        }

        btnBack.setOnClickListener(v -> finish());

        btnFavorite.setOnClickListener(v -> {
            Toast.makeText(this, "Đã lưu " + txtTenChiTiet.getText() + " vào yêu thích! ❤️", Toast.LENGTH_SHORT).show();
        });
    }
}