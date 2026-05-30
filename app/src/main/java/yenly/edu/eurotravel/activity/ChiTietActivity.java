package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout btnTabOverview;
    private TextView txtTabOverview, txtTabPhotos, txtTabDetails, txtTabReviews;
    private View indicatorOverview;
    private LinearLayout layoutOverviewContent;
    private boolean isFavorite = false;


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

        btnTabOverview = findViewById(R.id.btnTabOverview);
        txtTabOverview = findViewById(R.id.txtTabOverview);
        txtTabPhotos = findViewById(R.id.txtTabPhotos);
        txtTabDetails = findViewById(R.id.txtTabDetails);
        txtTabReviews = findViewById(R.id.txtTabReviews);
        indicatorOverview = findViewById(R.id.indicatorOverview);
        layoutOverviewContent = findViewById(R.id.layoutOverviewContent);

        if (getIntent().hasExtra("du_lieu_chuyen_di")) {
            ChuyenDi chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");

            if (chuyenDi != null) {
                txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
                txtGiaChiTiet.setText("$" + chuyenDi.getGiaTien());
                txtQuocGiaChiTiet.setText(chuyenDi.getLoaiHinh());
                txtMoTaChiTiet.setText(chuyenDi.getMoTa());
                btnSelectDays.setText("🎥 Xem Video Review Thực Tế");
                android.content.SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);
                isFavorite = pref.getBoolean(chuyenDi.getTenDiaDiem(), false);
                if (isFavorite) {
                    btnFavoriteCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFD2D2")); // Đổi sang nền hồng nhạt nếu đã thích
                } else {
                    btnFavoriteCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF")); // Nền trắng mặc định
                }
                imgChiTiet.setImageResource(chuyenDi.getHinhAnh());
                ratingBarHienThi.setRating(chuyenDi.getDiemDanhGia());
                txtSoSaoNhanXet.setText(chuyenDi.getDiemDanhGia() + " (147)");
                txtMoTaChiTiet.setText(chuyenDi.getMoTa());
            }
        }
        btnBackCard.setOnClickListener(v -> finish());

        btnFavoriteCard.setOnClickListener(v -> {
            ChuyenDi chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");
            if (chuyenDi != null) {
                android.content.SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);
                android.content.SharedPreferences.Editor editor = pref.edit();
                isFavorite = !isFavorite;

                if (isFavorite) {
                    editor.putBoolean(chuyenDi.getTenDiaDiem(), true);
                    btnFavoriteCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFD2D2"));
                    Toast.makeText(this, "Đã thêm " + chuyenDi.getTenDiaDiem() + " vào danh sách yêu thích! ❤️", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putBoolean(chuyenDi.getTenDiaDiem(), false);
                    btnFavoriteCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"));
                    Toast.makeText(this, "Đã xóa " + chuyenDi.getTenDiaDiem() + " khỏi danh sách yêu thích! 💔", Toast.LENGTH_SHORT).show();
                }
                editor.apply();
            }
        });

        btnSelectDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDiaDiem = txtTenChiTiet.getText().toString();
                String duongLinkYoutube = "https://www.youtube.com/results?search_query=" + tenDiaDiem + " travel guide 4k";
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(duongLinkYoutube));
                startActivity(intentYoutube);
            }
        });

        btnTabOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTabOverview.setTypeface(null, android.graphics.Typeface.BOLD);
                txtTabOverview.setTextColor(android.graphics.Color.parseColor("#1E2333"));
                indicatorOverview.setVisibility(View.VISIBLE);

                txtTabPhotos.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabPhotos.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtTabDetails.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabDetails.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtTabReviews.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabReviews.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                layoutOverviewContent.setVisibility(View.VISIBLE);
            }

        });

        txtTabPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTabPhotos.setTypeface(null, android.graphics.Typeface.BOLD);
                txtTabPhotos.setTextColor(android.graphics.Color.parseColor("#1E2333"));

                txtTabOverview.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabOverview.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                indicatorOverview.setVisibility(View.INVISIBLE);
                txtTabDetails.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabDetails.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtTabReviews.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabReviews.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtMoTaChiTiet.setVisibility(View.GONE);
                layoutOverviewContent.setVisibility(View.GONE);
            }
        });

        txtTabDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTabDetails.setTypeface(null, android.graphics.Typeface.BOLD);
                txtTabDetails.setTextColor(android.graphics.Color.parseColor("#1E2333"));

                txtTabOverview.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabOverview.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                indicatorOverview.setVisibility(View.INVISIBLE);
                txtTabPhotos.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabPhotos.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtTabReviews.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabReviews.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));

                txtMoTaChiTiet.setVisibility(View.GONE);
                layoutOverviewContent.setVisibility(View.GONE);
            }
        });
        txtTabReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTabReviews.setTypeface(null, android.graphics.Typeface.BOLD);
                txtTabReviews.setTextColor(android.graphics.Color.parseColor("#1E2333"));

                txtTabOverview.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabOverview.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                indicatorOverview.setVisibility(View.INVISIBLE);
                txtTabPhotos.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabPhotos.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));
                txtTabDetails.setTypeface(null, android.graphics.Typeface.NORMAL);
                txtTabDetails.setTextColor(android.graphics.Color.parseColor("#A0A0A0"));

                txtMoTaChiTiet.setVisibility(View.GONE);
                layoutOverviewContent.setVisibility(View.GONE);
            }
        });
    }
}