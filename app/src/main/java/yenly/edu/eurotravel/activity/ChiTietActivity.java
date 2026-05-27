package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class ChiTietActivity extends AppCompatActivity {

    private ImageView imgChiTiet;
    private CardView btnBackCard, btnFavoriteCard;
    private TextView txtTenChiTiet, txtGiaChiTiet, txtQuocGiaChiTiet, txtMoTaChiTiet, txtSoSaoNhanXet;
    private RatingBar ratingBarHienThi;
    private Button btnSelectDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        imgChiTiet = findViewById(R.id.imgChiTiet);
        btnBackCard = findViewById(R.id.btnBackCard);
        btnFavoriteCard = findViewById(R.id.btnFavoriteCard);
        txtTenChiTiet = findViewById(R.id.txtTenChiTiet);
        txtGiaChiTiet = findViewById(R.id.txtGiaChiTiet);
        txtQuocGiaChiTiet = findViewById(R.id.txtQuocGiaChiTiet);
        txtMoTaChiTiet = findViewById(R.id.txtMoTaChiTiet);
        txtSoSaoNhanXet = findViewById(R.id.txtSoSaoNhanXet);
        ratingBarHienThi = findViewById(R.id.ratingBarHienThi);
        btnSelectDays = findViewById(R.id.btnSelectDays);

        if (getIntent().hasExtra("du_lieu_chuyen_di")) {
            ChuyenDi chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");

            if (chuyenDi != null) {
                txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
                txtGiaChiTiet.setText("$" + chuyenDi.getGiaTien());
                txtQuocGiaChiTiet.setText(chuyenDi.getLoaiHinh());
                txtMoTaChiTiet.setText(chuyenDi.getMoTa());
                imgChiTiet.setImageResource(chuyenDi.getHinhAnh());
                ratingBarHienThi.setRating(chuyenDi.getDiemDanhGia());
                txtSoSaoNhanXet.setText(chuyenDi.getDiemDanhGia() + " (147)");
            }
        }

        btnBackCard.setOnClickListener(v -> finish());

        btnFavoriteCard.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm " + txtTenChiTiet.getText() + " vào danh sách yêu thích! ❤️", Toast.LENGTH_SHORT).show();
        });

        btnSelectDays.setOnClickListener(v -> {
            Toast.makeText(this, "Đang mở lịch chọn ngày cho " + txtTenChiTiet.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}