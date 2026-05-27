package yenly.edu.eurotravel.activity;

import android.content.Intent;
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
    private ImageButton btnBack, btnMore, btnFavorite;
    private TextView txtTenChiTiet, txtPhanLoai, txtGiaChiTiet, txtMoTaChiTiet, txtSoSaoNhanXet;
    private RatingBar ratingBarHienThi, ratingBarKhachHang;
    private Button btnGuiDanhGia, btnSelectDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        imgChiTiet = findViewById(R.id.imgChiTiet);
        btnBack = findViewById(R.id.btnBack);
        btnMore = findViewById(R.id.btnMore);
        btnFavorite = findViewById(R.id.btnFavorite);
        txtTenChiTiet = findViewById(R.id.txtTenChiTiet);
        txtPhanLoai = findViewById(R.id.txtPhanLoai);
        txtGiaChiTiet = findViewById(R.id.txtGiaChiTiet);
        txtMoTaChiTiet = findViewById(R.id.txtMoTaChiTiet);
        txtSoSaoNhanXet = findViewById(R.id.txtSoSaoNhanXet);
        ratingBarHienThi = findViewById(R.id.ratingBarHienThi);
        ratingBarKhachHang = findViewById(R.id.ratingBarKhachHang);
        btnGuiDanhGia = findViewById(R.id.btnGuiDanhGia);
        btnSelectDays = findViewById(R.id.btnSelectDays);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("du_lieu_chuyen_di")) {
            ChuyenDi chuyenDi = (ChuyenDi) intent.getSerializableExtra("du_lieu_chuyen_di");

            if (chuyenDi != null) {
                txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
                imgChiTiet.setImageResource(chuyenDi.getHinhAnh());
                txtPhanLoai.setText("🇪🇺 " + chuyenDi.getLoaiHinh());
                txtGiaChiTiet.setText("$1240");
                txtMoTaChiTiet.setText("Chuyến du lịch tuyệt vời đến " + chuyenDi.getTenDiaDiem() + " với thời gian hành trình kéo dài " + chuyenDi.getThoiGian() + ". Khám phá ngay những địa điểm check-in sang xịn mịn và trải nghiệm văn hóa bản địa độc đáo.");
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChiTietActivity.this, "Đã thêm vào danh sách yêu thích! ❤️", Toast.LENGTH_SHORT).show();
            }
        });
        btnGuiDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float soSao = ratingBarKhachHang.getRating();
                Toast.makeText(ChiTietActivity.this, "Cảm ơn Ly đã đánh giá " + soSao + " sao! ⭐", Toast.LENGTH_SHORT).show();
            }
        });
    }
}