package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.adapter.PhotoAdapter;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class ChiTietActivity extends AppCompatActivity {

    private RecyclerView rvPhotos;
    private LinearLayout layoutOverviewContent, layoutDetailsContent, layoutReviewsContent;

    private ImageView imgChiTiet;
    private CardView btnBackCard, btnFavoriteCard;
    private TextView txtTenChiTiet, txtGiaChiTiet, txtQuocGiaChiTiet, txtMoTaChiTiet, txtSoSaoNhanXet;
    private TextView txtDuration, txtWeather, txtGuide, txtSanBay, txtKhachSan, txtDetailsText;
    private RatingBar ratingBarHienThi;
    private Button btnSelectDays;

    private LinearLayout btnTabOverview;
    private TextView txtTabOverview, txtTabPhotos, txtTabDetails, txtTabReviews;
    private View indicatorOverview;

    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        // Ánh xạ View
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

        rvPhotos = findViewById(R.id.rvPhotos);
        layoutOverviewContent = findViewById(R.id.layoutOverviewContent);
        layoutDetailsContent = findViewById(R.id.layoutDetailsContent);
        layoutReviewsContent = findViewById(R.id.layoutReviewsContent);
        txtDetailsText = findViewById(R.id.txtDetailsText);

        txtDuration = findViewById(R.id.txtDuration);
        txtWeather = findViewById(R.id.txtWeather);
        txtGuide = findViewById(R.id.txtGuide);
        txtSanBay = findViewById(R.id.txtSanBay);
        txtKhachSan = findViewById(R.id.txtKhachSan);

        List<ChuyenDi.PhotoItem> danhSachAnh = new ArrayList<>();

        if (getIntent().hasExtra("du_lieu_chuyen_di")) {
            ChuyenDi chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");
            if (chuyenDi != null) {
                txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
                txtGiaChiTiet.setText("$" + chuyenDi.getGiaTien());
                txtQuocGiaChiTiet.setText(chuyenDi.getNgonNgu());
                txtMoTaChiTiet.setText(chuyenDi.getMoTaTongQuan());
                btnSelectDays.setText("🎥 Xem Video Review Thực Tế");

                txtDuration.setText(chuyenDi.getSoNgayDi());
                txtWeather.setText(chuyenDi.getThoiTiet());
                txtGuide.setText(chuyenDi.getNgonNgu());
                txtSanBay.setText(chuyenDi.getThongTinSanBay());
                txtKhachSan.setText(chuyenDi.getKhachSanNoiBat());

                // Gắn nội dung "sâu deep" cho mục Details
                napDuLieuChiTietLichSu(chuyenDi.getTenDiaDiem());

                if (chuyenDi.getDanhSachAnhTabPhotos() != null) {
                    danhSachAnh.addAll(chuyenDi.getDanhSachAnhTabPhotos());
                }
                android.content.SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);
                isFavorite = pref.getBoolean(chuyenDi.getTenDiaDiem(), false);
                btnFavoriteCard.setCardBackgroundColor(isFavorite ?
                        Color.parseColor("#FFD2D2") : Color.parseColor("#FFFFFF"));

                String tenHinhAnhLocal = chuyenDi.getTenDiaDiem().toLowerCase().trim();
                int resIdImage = getResources().getIdentifier(tenHinhAnhLocal, "drawable", getPackageName());

                if (resIdImage != 0) {
                    Glide.with(this).load(resIdImage).into(imgChiTiet);
                } else {
                    Glide.with(this).load(R.drawable.paris).into(imgChiTiet);
                }

                ratingBarHienThi.setRating(chuyenDi.getDiemDanhGia());
                txtSoSaoNhanXet.setText(chuyenDi.getDiemDanhGia() + " (147)");
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
                    btnFavoriteCard.setCardBackgroundColor(Color.parseColor("#FFD2D2"));
                    Toast.makeText(this, "Đã thêm vào yêu thích! ❤️", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putBoolean(chuyenDi.getTenDiaDiem(), false);
                    btnFavoriteCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(this, "Đã xóa khỏi yêu thích! 💔", Toast.LENGTH_SHORT).show();
                }
                editor.apply();
            }
        });

        btnSelectDays.setOnClickListener(v -> {
            String tenDiaDiem = txtTenChiTiet.getText().toString();
            String duongLinkYoutube = "https://www.youtube.com/results?search_query=" + tenDiaDiem + " travel vlog";
            Intent intentYoutube = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(duongLinkYoutube));
            startActivity(intentYoutube);
        });

        // Set layout cho tab Photos
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPhotos.setLayoutManager(layoutManager);
        PhotoAdapter photoAdapter = new PhotoAdapter(danhSachAnh);
        rvPhotos.setAdapter(photoAdapter);

        // Bắt sự kiện nhấn 4 Tab chuyển đổi nội dung
        btnTabOverview.setOnClickListener(v -> kichHoatTab(1));
        txtTabPhotos.setOnClickListener(v -> kichHoatTab(2));
        txtTabDetails.setOnClickListener(v -> kichHoatTab(3));
        txtTabReviews.setOnClickListener(v -> kichHoatTab(4));
    }

    // Hàm chuyển Tab dùng chung cực mượt
    private void kichHoatTab(int viTri) {
        // Trả tất cả về mặc định (xám mờ)
        txtTabOverview.setTypeface(null, Typeface.NORMAL);
        txtTabOverview.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabPhotos.setTypeface(null, Typeface.NORMAL);
        txtTabPhotos.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabDetails.setTypeface(null, Typeface.NORMAL);
        txtTabDetails.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabReviews.setTypeface(null, Typeface.NORMAL);
        txtTabReviews.setTextColor(Color.parseColor("#A0A0A0"));

        indicatorOverview.setVisibility(View.INVISIBLE);

        // Ẩn tất cả nội dung
        layoutOverviewContent.setVisibility(View.GONE);
        rvPhotos.setVisibility(View.GONE);
        layoutDetailsContent.setVisibility(View.GONE);
        layoutReviewsContent.setVisibility(View.GONE);

        // Hiện Tab tương ứng
        if (viTri == 1) {
            txtTabOverview.setTypeface(null, Typeface.BOLD);
            txtTabOverview.setTextColor(Color.parseColor("#1E2333"));
            indicatorOverview.setVisibility(View.VISIBLE);
            layoutOverviewContent.setVisibility(View.VISIBLE);
        } else if (viTri == 2) {
            txtTabPhotos.setTypeface(null, Typeface.BOLD);
            txtTabPhotos.setTextColor(Color.parseColor("#1E2333"));
            rvPhotos.setVisibility(View.VISIBLE);
        } else if (viTri == 3) {
            txtTabDetails.setTypeface(null, Typeface.BOLD);
            txtTabDetails.setTextColor(Color.parseColor("#1E2333"));
            layoutDetailsContent.setVisibility(View.VISIBLE);
        } else if (viTri == 4) {
            txtTabReviews.setTypeface(null, Typeface.BOLD);
            txtTabReviews.setTextColor(Color.parseColor("#1E2333"));
            layoutReviewsContent.setVisibility(View.VISIBLE);
        }
    }
    private void napDuLieuChiTietLichSu(String tenDiaDiem) {
        if (tenDiaDiem == null) return;
        String ten = tenDiaDiem.toLowerCase().trim();
        String noiDung = "";

        if (ten.contains("rome") || ten.contains("ý")) {
            noiDung = "Thành phố vĩnh hằng Rome là minh chứng sống động cho một đế chế từng thống trị cả châu Âu. Đặt chân đến đây, bạn không chỉ đang đi du lịch, mà là đang bước lên một cỗ máy thời gian quay ngược về hơn 2.500 năm trước.\n\nTừng viên gạch ở Đấu trường La Mã Colosseum, từng cột đá đổ nát tại Roman Forum hay đài phun nước Trevi tráng lệ đều mang trong mình hơi thở hùng tráng của lịch sử. Dạo bước trên những con phố lát đá rêu phong, bạn sẽ thấy sự hòa quyện tuyệt vời giữa vẻ đẹp cổ kính và nhịp sống hiện đại sôi động.\n\nCuối cùng, hãy nuông chiều bản thân bằng một đĩa mỳ Carbonara chuẩn vị, nhâm nhi ly Gelato mát lạnh dưới ánh hoàng hôn và tận hưởng trọn vẹn triết lý 'La Dolce Vita' (Cuộc sống ngọt ngào) của người dân nơi đây.";
        } else if (ten.contains("paris") || ten.contains("pháp")) {
            noiDung = "Kinh đô ánh sáng Paris chưa bao giờ làm du khách thất vọng bởi vẻ đẹp lãng mạn, hào nhoáng và đậm chất thơ. Nơi đây từ lâu đã được mệnh danh là cái nôi của nghệ thuật, thời trang và những tinh hoa ẩm thực bậc nhất thế giới.\n\nBức tranh Paris hiện lên hoàn hảo từ ngọn tháp Eiffel sừng sững kiêu hãnh vươn lên nền trời, đến dòng sông Seine lững lờ trôi ôm trọn lấy thành phố. Bạn có thể dành hàng giờ chìm đắm trong Bảo tàng Louvre để chiêm ngưỡng nụ cười bí ẩn của nàng Mona Lisa, hay dạo quanh đồi Montmartre - nơi từng là nguồn cảm hứng bất tận cho Picasso.\n\nParis sẽ quyến rũ bạn bằng những chiếc bánh croissant giòn rụm, những ly rượu vang hảo hạng và một nhịp sống thanh lịch đến khó quên.";
        } else if (ten.contains("venice")) {
            noiDung = "Venice - Thành phố nổi trứ danh của nước Ý, là một kiệt tác kiến trúc độc nhất vô nhị. Một thành phố hoàn toàn vắng bóng tiếng động cơ ô tô và khói bụi.\n\nĐến với Venice, giao thông chính là những chiếc thuyền Gondola truyền thống lướt nhẹ nhàng trên các con kênh chằng chịt, luồn lách qua những cây cầu hàng trăm năm tuổi. Quảng trường San Marco tráng lệ với lối kiến trúc Gothic xen lẫn âm hưởng Phục Hưng sẽ khiến bạn choángợp ngay từ cái nhìn đầu tiên.\n\nĐiều tuyệt vời nhất ở Venice có lẽ là cảm giác được 'lạc đường' qua những con hẻm nhỏ hẹp tựa mê cung. Hãy kết thúc ngày dài bằng cách thưởng thức hải sản tươi rói bên bờ kênh thơ mộng.";
        } else if (ten.contains("london") || ten.contains("anh")) {
            noiDung = "Thủ đô London của xứ sở sương mù mang trong mình vẻ đẹp giao thoa hoàn hảo giữa truyền thống hoàng gia lâu đời và nhịp sống hiện đại bậc nhất thế giới.\n\nĐến đây, bạn sẽ được chiêm ngưỡng tháp đồng hồ Big Ben kiêu hãnh, vòng quay London Eye khổng lồ bên bờ sông Thames lộng gió. Đừng quên dạo bước qua những con phố tấp nập với chiếc xe buýt 2 tầng màu đỏ đặc trưng và những bốt điện thoại cổ kính.\n\nTrải nghiệm văn hóa trà chiều thanh tao hay ghé thăm Cung điện Buckingham tráng lệ sẽ làm cho chuyến đi của bạn trở nên đẳng cấp và đáng nhớ hơn bao giờ hết.";
        } else if (ten.contains("amsterdam") || ten.contains("hà lan")) {
            noiDung = "Amsterdam - thủ đô của Hà Lan, được mệnh danh là 'Venice của phương Bắc' với hệ thống kênh đào tuyệt đẹp đã được UNESCO công nhận là di sản thế giới.\n\nThành phố này nổi tiếng với sự tự do, phóng khoáng và nhịp sống yên bình. Bạn có thể thuê một chiếc xe đạp chạy dọc theo những con phố rợp bóng cây, ngắm nhìn những dãy nhà cổ kính với kiến trúc hẹp độc đáo và những chiếc cối xay gió xa xa.\n\nMột buổi chiều dạo quanh chợ hoa nổi rực rỡ sắc tulip, hay nhâm nhi ly bia thủ công chắc chắn sẽ làm say lòng bất cứ du khách nào.";
        } else {
            noiDung = "Một điểm đến tuyệt vời đang chờ đón bạn khám phá! Nơi đây không chỉ sở hữu cảnh quan thiên nhiên đẹp say đắm lòng người mà còn mang trong mình những câu chuyện lịch sử văn hóa lâu đời.\n\nHãy chuẩn bị cho mình một hành trang đầy đủ, một chiếc máy ảnh sạc đầy pin để ghi lại trọn vẹn những khoảnh khắc tuyệt diệu nhất. Chắc chắn bạn sẽ có những trải nghiệm không thể nào quên khi đặt chân tới vùng đất xinh đẹp này.";
        }

        txtDetailsText.setText(noiDung);
    }
}