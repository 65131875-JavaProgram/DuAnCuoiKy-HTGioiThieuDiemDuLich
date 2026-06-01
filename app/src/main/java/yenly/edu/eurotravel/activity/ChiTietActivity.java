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
    private TextView txtReviewUser1, txtReviewContent1, txtReviewUser2, txtReviewContent2, txtReviewUser3, txtReviewContent3;
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

        txtReviewUser1 = findViewById(R.id.txtReviewUser1);
        txtReviewContent1 = findViewById(R.id.txtReviewContent1);
        txtReviewUser2 = findViewById(R.id.txtReviewUser2);
        txtReviewContent2 = findViewById(R.id.txtReviewContent2);
        txtReviewUser3 = findViewById(R.id.txtReviewUser3);
        txtReviewContent3 = findViewById(R.id.txtReviewContent3);

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

                napDuLieuNộiDungĐộng(chuyenDi.getTenDiaDiem());

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPhotos.setLayoutManager(layoutManager);
        PhotoAdapter photoAdapter = new PhotoAdapter(danhSachAnh);
        rvPhotos.setAdapter(photoAdapter);

        btnTabOverview.setOnClickListener(v -> kichHoatTab(1));
        txtTabPhotos.setOnClickListener(v -> kichHoatTab(2));
        txtTabDetails.setOnClickListener(v -> kichHoatTab(3));
        txtTabReviews.setOnClickListener(v -> kichHoatTab(4));
    }

    private void kichHoatTab(int viTri) {
        txtTabOverview.setTypeface(null, Typeface.NORMAL);
        txtTabOverview.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabPhotos.setTypeface(null, Typeface.NORMAL);
        txtTabPhotos.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabDetails.setTypeface(null, Typeface.NORMAL);
        txtTabDetails.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabReviews.setTypeface(null, Typeface.NORMAL);
        txtTabReviews.setTextColor(Color.parseColor("#A0A0A0"));

        indicatorOverview.setVisibility(View.INVISIBLE);

        layoutOverviewContent.setVisibility(View.GONE);
        rvPhotos.setVisibility(View.GONE);
        layoutDetailsContent.setVisibility(View.GONE);
        layoutReviewsContent.setVisibility(View.GONE);

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

    private void napDuLieuNộiDungĐộng(String tenDiaDiem) {
        if (tenDiaDiem == null) return;
        String ten = tenDiaDiem.toLowerCase().trim();

        if (ten.contains("paris") || ten.contains("pháp")) {
            txtDetailsText.setText(
                    "Kinh đô Ánh sáng Paris không chỉ là một điểm đến trên bản đồ, mà là cả một giấc mơ xa hoa mà ai cũng khao khát được chạm tay vào một lần trong đời. Nơi đây là cái nôi của tình yêu, nghệ thuật thời trang và những tinh hoa ẩm thực đỉnh cao bậc nhất thế giới.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Tháp Eiffel: Biểu tượng kiêu hãnh vươn mình giữa nền trời. Hãy đến đây vào buổi tối để chiêm ngưỡng màn trình diễn ánh sáng lung linh.\n" +
                            "• Bảo tàng Louvre: Nơi lưu giữ hàng vạn kiệt tác nhân loại, trong đó có bức tranh nàng Mona Lisa huyền thoại.\n" +
                            "• Đại lộ Champs-Élysées & Khải Hoàn Môn: Trục đường sang trọng bậc nhất, thiên đường của các tín đồ thời trang xa si.\n" +
                            "• Đồi Montmartre: Ngôi làng nghệ sĩ cổ kính với thánh đường Sacré-Cœur trắng muốt.\n\n" +
                            "Hãy tưởng tượng một buổi chiều hoàng hôn buông xuống, bạn ngồi tại một quán cà phê vỉa hè, nhâm nhi ly rượu vang Pháp, ngắm dòng người qua lại và cảm nhận tiếng vĩ cầm du dương vang lên từ một góc phố. Paris sẽ đánh cắp trái tim bạn theo cách ngọt ngào nhất!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Hoài Nam");
            txtReviewContent1.setText("Đứng dưới chân tháp Eiffel ban đêm lộng lẫy kinh khủng khiếp! Cực kỳ đáng đi một lần trong đời.");
            txtReviewUser2.setText("⭐⭐⭐⭐ Thu Thủy");
            txtReviewContent2.setText("Bánh sừng bò ở các tiệm ven đường Paris giòn rụm, ngon hơn hẳn ăn ở Việt Nam luôn.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Hoàng Long");
            txtReviewContent3.setText("Bảo tàng Louvre siêu rộng, đi bộ rã cả chân nhưng ngắm tượng với tranh thích mê.");
        }
        else if (ten.contains("rome") || ten.contains("ý")) {
            txtDetailsText.setText(
                    "Được mệnh danh là 'Thành phố Vĩnh hằng', Rome mang trong mình dòng máu vương giả của một đế chế La Mã hùng mạnh từng thống trị thế giới. Bước đi trên những con phố đá cổ của Rome, bạn sẽ có cảm giác như đang lật mở từng trang sách lịch sử sống động bước ra từ hàng nghìn năm trước.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Đấu trường Colosseum: Kiệt tác kiến trúc cổ đại, nơi từng diễn ra những trận chiến sinh tử của các dũng sĩ giác đấu.\n" +
                            "• Đài phun nước Trevi: Công trình điêu khắc baroque lộng lẫy. Đừng quên ném một đồng xu qua vai trái để ước hẹn ngày quay trở lại Rome!\n" +
                            "• Đền Pantheon: Ngôi đền hơn 2.000 năm tuổi với mái vòm bê tông không cốt thép lớn nhất thế giới.\n" +
                            "• Tòa thánh Vatican: Quốc gia nhỏ nhất thế giới, nơi sở hữu nhà nguyện Sistine với những bức bích họa vô giá.\n\n" +
                            "Không chỉ có lịch sử, Rome còn chiêu đãi bạn bằng hương vị kem Gelato béo ngậy, những đĩa mỳ Ý Carbonara nguyên bản nồng nàn. Rome không vội được đâu, hãy đi chậm để cảm nhận vẻ đẹp vĩnh cửu này!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Quốc Bảo");
            txtReviewContent1.setText("Đấu trường Colosseum hoành tráng đến ngạt thở. Cảm giác như nghe được tiếng reo hò lịch sử.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Minh Thư");
            txtReviewContent2.setText("Nhớ ném đồng xu ở đài phun nước Trevi nha mọi người, đông nhưng mà đẹp và linh nghiệm lắm.");
            txtReviewUser3.setText("⭐⭐⭐⭐ Tuấn Anh");
            txtReviewContent3.setText("Mỳ Ý Carbonara nguyên bản ở đây béo ngậy, ăn hơi lạ miệng nhưng càng ăn càng dính.");
        }
        else if (ten.contains("venice")) {
            txtDetailsText.setText(
                    "Venice giống như một viên ngọc nổi trên mặt biển Adriatic, một thành phố kỳ diệu xây dựng trên 118 hòn đảo nhỏ và được kết nối bởi hơn 400 cây cầu. Đây là nơi duy nhất trên thế giới hoàn toàn vắng bóng tiếng động cơ ô tô, khói bụi và còi xe.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Kênh Lớn (Grand Canal): 'Đại lộ' nước sầm uất nhất Venice, hai bên bờ là những cung điện mang kiến trúc Gothic lộng lẫy.\n" +
                            "• Quảng trường San Marco: Trái tim của Venice, nơi ngự trị của Vương cung thánh đường cổ kính.\n" +
                            "• Cầu Rialto: Cây cầu đá cổ nhất và lãng mạn nhất bắc qua Kênh Lớn, điểm ngắm hoàng hôn buông xuống tuyệt mỹ.\n" +
                            "• Đảo Burano: Hòn đảo rực rỡ với những ngôi nhà sơn đủ sắc màu cầu vồng như trong một câu chuyện cổ tích.\n\n" +
                            "Trải nghiệm tuyệt vời nhất ở đây là ngồi trên chiếc thuyền Gondola truyền thống, lướt nhẹ qua những con hẻm nước nhỏ hẹp, lắng nghe người chèo thuyền hát nghêu ngao những bản tình ca Ý. Một không gian lãng mạn đến nghẹt thở!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Khánh Linh");
            txtReviewContent1.setText("Ngồi thuyền Gondola nghe anh lái thuyền hát nghêu ngao lãng mạn dã man, như phim luôn.");
            txtReviewUser2.setText("⭐⭐⭐⭐ Duy Bách");
            txtReviewContent2.setText("Thành phố không có xe cộ nên đi bộ rất chill, nhưng nhớ chuẩn bị bản đồ vì hẻm nhỏ như mê cung.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Ngọc Ánh");
            txtReviewContent3.setText("Hoàng hôn buông xuống trên quảng trường San Marco đẹp đến phát khóc.");
        }
        else if (ten.contains("london") || ten.contains("anh")) {
            txtDetailsText.setText(
                    "Nằm bên bờ sông Thames thơ mộng, thủ đô London của Vương quốc Anh là sự giao thoa hoàn hảo giữa nét cổ kính, trang nghiêm của hoàng gia lâu đời và nhịp sống sôi động, hiện đại của một siêu đô thị toàn cầu.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Tháp đồng hồ Big Ben & Cung điện Westminster: Biểu tượng vĩnh cửu của nước Anh soi bóng xuống dòng sông Thames.\n" +
                            "• Cung điện Buckingham: Nơi ở chính thức của Hoàng gia Anh. Nếu đến đúng giờ, bạn sẽ được xem lễ đổi gác của những người lính đội mũ lông gấu.\n" +
                            "• Cầu Tháp London (Tower Bridge): Cây cầu mang kiến trúc Gothic độc đáo có thể tách đôi cho tàu lớn đi qua.\n" +
                            "• Vòng quay London Eye: Chiêm ngưỡng toàn cảnh thành phố từ độ cao 135 mét giữa không trung.\n\n" +
                            "Đến London, hãy thử một lần leo lên chiếc xe buýt hai tầng màu đỏ đặc trưng, thưởng thức một bữa trà chiều thanh lịch đúng điệu quý tộc Anh và ngắm nhìn sương mù bảng lảng nhẹ buông. London sang trọng và đầy mê hoặc đang chờ bạn!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐ Thùy Trang");
            txtReviewContent1.setText("Thời tiết London đúng là hay mưa phùn thật, nhưng ngắm Big Ben mờ sương lại thấy thơ mộng.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Tiến Dũng");
            txtReviewContent2.setText("Trải nghiệm văn hóa trà chiều chuẩn Anh Quốc cực kỳ sang chảnh và thanh tao.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Bảo Trâm");
            txtReviewContent3.setText("Cung điện Buckingham siêu lộng lẫy, may mắn xem được lễ đổi gác của lính hoàng gia.");
        }
        else if (ten.contains("amsterdam") || ten.contains("hà lan")) {
            txtDetailsText.setText(
                    "Amsterdam được trìu mến gọi là 'Venice của phương Bắc' nhờ hệ thống kênh đào chằng chịt được UNESCO công nhận là di sản thế giới. Đây là thành phố của sự tự do, của những chiếc xe đạp, những ngôi nhà gạch cổ nghiêng nghiêng và những bông hoa tulip rực rỡ.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Hệ thống kênh đào Cổ: Đi du thuyền mui trần dọc theo các con kênh để ngắm nhìn những dãy nhà hẹp độc đáo từ thế kỷ 17.\n" +
                            "• Bảo tàng Van Gogh: Nơi lưu giữ bộ sưu tập tranh đồ sộ nhất của danh họa thiên tài Vincent van Gogh.\n" +
                            "• Quảng trường Dam: Trái tim của thành phố, nơi tập trung Cung điện Hoàng gia và không khí đường phố náo nhiệt.\n" +
                            "• Làng cối xay gió Zaanse Schans: Nằm ngay ngoại ô, nơi bạn có thể thấy những chiếc cối xay gió khổng lồ.\n\n" +
                            "Không khí ở Amsterdam cực kỳ trong lành và thư thái. Hãy thuê một chiếc xe đạp, thong dong đạp qua những cây cầu ngập tràn hoa tươi, bạn sẽ hiểu vì sao người dân nơi đây lại có chỉ số hạnh phúc cao đến vậy!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Văn Hùng");
            txtReviewContent1.setText("Thuê một chiếc xe đạp chạy vòng quanh các con kênh là trải nghiệm tuyệt vời nhất ở đây.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Thanh Thảo");
            txtReviewContent2.setText("Người dân cực kỳ thân thiện, chợ hoa nổi rực rỡ sắc màu chụp ảnh sống ảo cháy máy.");
            txtReviewUser3.setText("⭐⭐⭐⭐ Tấn Phát");
            txtReviewContent3.setText("Không khí trong lành, mát mẻ, thành phố yên bình và rất văn minh.");
        }
        else if (ten.contains("berlin") || ten.contains("đức")) {
            txtDetailsText.setText(
                    "Trải qua những thăng trầm khốc liệt của lịch sử hiện đại, Berlin ngày nay đã tái sinh mạnh mẽ để trở thành biểu tượng của sự tự do, sáng tạo và nghệ thuật đường phố phóng khoáng bậc nhất châu Âu.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Cổng thành Brandenburg: Biểu tượng của sự thống nhất nước Đức, một công trình kiến trúc tân cổ điển đầy uy nghiêm.\n" +
                            "• Bức tường Berlin (East Side Gallery): Đoạn tường còn sót lại dài 1,3km, giờ đây biến thành phòng triển lãm tranh ngoài trời lớn nhất thế giới.\n" +
                            "• Đảo Bảo Tàng (Museum Island): Quần thể 5 bảo tàng tầm cỡ quốc tế nằm trên sông Spree.\n" +
                            "• Tòa nhà Quốc hội Reichstag: Nổi bật với mái vòm bằng thủy tinh hiện đại.\n\n" +
                            "Berlin quyến rũ bởi sự gai góc, sâu lắng của lịch sử xen lẫn nhịp sống ngầm (underground) sôi động. Đừng quên thử món xúc xích Currywurst và một ly bia đen Đức đúng điệu khi đến đây nhé!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Minh Quang");
            txtReviewContent1.setText("Cổng thành Brandenburg sừng sững trông cực kỳ quyền lực, chụp ảnh ban ngày hay đêm đều đẹp.");
            txtReviewUser2.setText("⭐⭐⭐⭐ Đức Thắng");
            txtReviewContent2.setText("Bảo tàng Checkpoint Charlie lưu giữ lịch sử rất hay. Bạn nào mê lịch sử giống mình thì nên đi.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Thu Hà");
            txtReviewContent3.setText("Đồ ăn Đức siêu chất lượng, xúc xích Currywurst với bia đen ngon xuất sắc.");
        }
        else if (ten.contains("barcelona") || ten.contains("tây ban nha")) {
            txtDetailsText.setText(
                    "Barcelona là một bản tình ca rực rỡ của nắng vàng Địa Trung Hải, biển xanh lộng gió và những bộ óc kiến trúc 'điên rồ' nhất hành tinh. Thành phố này luôn tràn đầy năng lượng với những vũ điệu Flamenco quywyn rũ và lễ hội thâu đêm.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Vương cung thánh đường Sagrada Família: Kiệt tác vĩ đại của kiến trúc sư thiên tài Antoni Gaudí, một công trình kỳ vĩ đã xây dựng hơn 140 năm vẫn chưa hoàn thành.\n" +
                            "• Công viên Güell: Như một thế giới siêu thực với các tòa nhà hình nấm và những dải ghế khảm gốm sắc sỡ.\n" +
                            "• Con đường La Rambla: Đại lộ đi bộ sầm uất với các nghệ sĩ đường phố biểu diễn và chợ ẩm thực Boqueria.\n" +
                            "• Bãi biển Barceloneta: Nơi bạn có thể phơi mình dưới nắng ấm và tận hưởng làn nước biển mát rượi.\n\n" +
                            "Sau một ngày dài khám phá kiến trúc độc lạ, hãy tự thưởng cho mình một đĩa cơm hải sản Paella thơm nức và một ly rượu sangria mát lạnh. Barcelona chắc chắn sẽ khiến bạn không muốn về!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Hải Yến");
            txtReviewContent1.setText("Nhà thờ Sagrada Familia nhìn bên ngoài đã choáng ngợp, vào bên trong ánh sáng kính màu chiếu xiên còn đỉnh hơn.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Công Vinh");
            txtReviewContent2.setText("Biển Barceloneta siêu nhộn nhịp, vừa tắm biển vừa ăn cơm xiên hải sản Paella thì hết sảy.");
            txtReviewUser3.setText("⭐⭐⭐⭐ Kim Ngân");
            txtReviewContent3.setText("Phố đi bộ La Rambla tràn ngập năng lượng, nghệ sĩ đường phố biểu diễn vui mắt lắm.");
        }
        else if (ten.contains("prague") || ten.contains("séc")) {
            txtDetailsText.setText(
                    "Được mệnh danh là 'Thành phố Vàng' hay 'Thành phố của trăm đỉnh tháp', Prague (Praha) hiện lên như một câu chuyện cổ tích Grimm cổ kính được hiện thực hóa. Nơi đây may mắn vẹn nguyên sau những cuộc chiến tranh, giữ lại trọn vẹn nét đẹp Gothic và Baroque quý giá.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Cầu Charles (Cầu Tình): Cây cầu đá cổ từ thế kỷ 14 với 30 bức tượng thánh cổ kính. Đi bộ ở đây vào sáng sớm tinh mơ khi sương chưa tan là trải nghiệm siêu lãng mạn.\n" +
                            "• Lâu đài Prague: Quần thể lâu đài cổ rộng lớn nhất thế giới, biểu tượng quyền lực của các vị vua xứ Bohemia.\n" +
                            "• Đồng hồ Thiên văn (Prague Astronomical Clock): Chiếc đồng hồ cơ học cổ nhất vẫn còn hoạt động.\n" +
                            "• Quảng trường Phố Cổ: Không gian bao la bao quanh bởi những tòa nhà mái ngói đỏ rực rỡ.\n\n" +
                            "Prague còn nổi tiếng là nơi có bia ngon và rẻ hơn cả nước suối. Hãy gọi một chiếc bánh mì cuộn nướng lò Trdelnik ngọt ngào, thong dong dạo bước trên những con đường lát đá, bạn sẽ thấy lòng mình bình yên lạ kỳ."
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Gia Huy");
            txtReviewContent1.setText("Cây cầu đá Charles đẹp cổ kính, đi sáng sớm vắng người ngắm bình minh siêu lãng mạn.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Phương Mai");
            txtReviewContent2.setText("Đồng hồ thiên văn ở quảng trường cổ hoạt động mấy trăm năm rồi mà nhìn vẫn tinh xảo.");
            txtReviewUser3.setText("⭐⭐⭐⭐ Thanh Tú");
            txtReviewContent3.setText("Chi phí ở Prague rẻ hơn nhiều so với Tây Âu, đồ ăn ngon, bánh trdelnik ngọt thơm phức.");
        }
        else if (ten.contains("athens") || ten.contains("hy lạp")) {
            txtDetailsText.setText(
                    "Athens chính là cái nôi vĩ đại của nền văn minh phương Tây và là vùng đất của những câu chuyện thần thoại Hy Lạp kỳ vĩ. Đây là nơi bạn có thể trực tiếp chạm tay vào quá khứ, bước đi trên những khối đá cổ mà các triết gia vĩ đại như Socrates, Plato hay Aristotle từng đứng diễn thuyết.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Quần thể Acropolis: Thành trì cổ ngự trị kiêu hãnh trên đỉnh đồi đá, biểu tượng tối cao của thời kỳ hoàng kim Hy Lạp.\n" +
                            "• Đền Parthenon: Kiệt tác kiến trúc thờ nữ thần Athena với những cột đá cẩm thạch sừng sững thách thức thời gian.\n" +
                            "• Khu phố cổ Plaka: Nằm ngay dưới chân đồi Acropolis, rực rỡ với những ngôi nhà sơn trắng và giàn hoa giấy rủ bóng.\n" +
                            "• Sân vận động Panathenaic: Nơi diễn ra kỳ Thế vận hội Olympic hiện đại đầu tiên vào năm 1896.\n\n" +
                            "Hãy đến Athens để hít thở bầu không khí ngập tràn thần thoại, thưởng thức món sữa chua Hy Lạp béo ngậy kèm mật ong nguyên chất và ngắm nhìn hoàng hôn rực lửa nhuộm vàng những đống đổ nát kiêu hùng!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐ Tuấn Kiệt");
            txtReviewContent1.setText("Leo lên đồi Acropolis nhìn ngắm đền cổ Parthenon sừng sững mới thấy khâm phục người cổ đại.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Mỹ Linh");
            txtReviewContent2.setText("Khu phố cổ Plaka dưới chân đồi rất thơ mộng, nhà cửa sơn trắng xóa ngập tràn hoa giấy.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Hoàng Hiệp");
            txtReviewContent3.setText("Ẩm thực Hy Lạp thanh mát, sữa chua Hy Lạp xịn ăn kèm mật ong ngon nuốt lưỡi.");
        }
        else if (ten.contains("vienna") || ten.contains("áo")) {
            txtDetailsText.setText(
                    "Vienna (Viên) quyến rũ thực khách bằng một vẻ đẹp quý phái, thanh lịch tuyệt đối. Được mệnh danh là 'Thủ đô của Âm nhạc cổ điển', thành phố này là nơi nuôi dưỡng tài năng của những thiên tài bất hủ như Mozart, Beethoven hay Schubert.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Cung điện Schönbrunn: Cung điện mùa hè lộng lẫy của hoàng gia với khu vườn thượng uyển rộng lớn được cắt tỉa vô cùng tinh xảo.\n" +
                            "• Cung điện Hofburg: Trự sở quyền lực mùa đông, nơi bạn có thể khám phá cuộc đời của Hoàng hậu Sisi xinh đẹp.\n" +
                            "• Nhà hát Opera Quốc gia Vienna: Một trong những nhà hát opera bận rộn và danh giá nhất thế giới với kiến trúc hoành tráng.\n" +
                            "• Nhà thờ thánh Stephen: Kiệt tác Gothic với mái ngói khảm màu sắc độc đáo, trái tim kiến trúc của Vienna.\n\n" +
                            "Đến đây, bạn hãy chậm rãi trải nghiệm văn hóa quán cà phê biểu tượng của Vienna (được UNESCO công nhận), thưởng thức một ly cà phê Melange cùng một lát bánh chocolate Sacher-Torte huyền thoại trong tiếng nhạc không lời du dương nhé!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Bảo Long");
            txtReviewContent1.setText("Cung điện Schönbrunn rộng lớn và hoành tráng vô cùng, khu vườn thượng uyển cắt tỉa siêu đẹp.");
            txtReviewUser2.setText("⭐⭐⭐⭐ Hồng Nhung");
            txtReviewContent2.setText("Vừa nhâm nhi ly cà phê Vienna vừa ăn bánh ngọt Sacher-Torte trong không gian cổ điển thật tuyệt.");
            txtReviewUser3.setText("⭐⭐⭐⭐⭐ Anh Quân");
            txtReviewContent3.setText("Thành phố cực kỳ sạch sẽ, thanh bình và toát lên vẻ quý phái sang trọng.");
        }
        else if (ten.contains("florence") || ten.contains("firenze")) {
            txtDetailsText.setText(
                    "Florence chính là chiếc nôi vĩ đại sinh ra phong trào Phục hưng lừng lẫy toàn cầu, nơi sản sinh ra những bộ óc thiên tài như Leonardo da Vinci hay Michelangelo. Thành phố xinh đẹp nước Ý này giống như một bảo tàng nghệ thuật khổng lồ ngoài trời.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Nhà thờ chính tòa Duomo Florence: Nổi bật với mái vòm gạch đỏ khổng lồ do kiến trúc sư Brunelleschi xây dựng.\n" +
                            "• Cầu cổ Ponte Vecchio: Cây cầu đá bắc qua sông Arno với những cửa hàng vàng bạc, trang sức san sát nhau.\n" +
                            "• Bảo tàng Uffizi Gallery: Nơi lưu giữ những bộ sưu tập tranh Phục Hưng vô giá bậc nhất nhân loại.\n" +
                            "• Quảng trường Signoria: Nơi trưng bày các bức tượng điêu khắc cẩm thạch kiệt tác, bao gồm bản sao tượng chàng David.\n\n" +
                            "Đi bộ dọc theo sông Arno lúc hoàng hôn buông xuống, ngắm nhìn ánh mặt trời nhuộm vàng chiếc cầu cổ Ponte Vecchio sẽ là khoảnh khắc lãng mạn in sâu vào tâm trí bạn mãi mãi!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Đăng Khoa");
            txtReviewContent1.setText("Mái vòm nhà thờ Duomo to khủng khiếp, trèo lên đỉnh ngắm toàn cảnh thành phố đẹp nghẹt thở.");
            txtReviewUser2.setText("⭐⭐⭐⭐⭐ Phương Thảo");
            txtReviewContent2.setText("Thịt bò bít tết kiểu Florence (Bistecca alla Fiorentina) ở đây ngon mềm xuất sắc, rất đáng thử.");
            txtReviewUser3.setText("⭐⭐⭐⭐ Tuấn Tú");
            txtReviewContent3.setText("Nhiều bảo tàng nghệ thuật đỉnh cao, xếp hàng hơi lâu nhưng vô cùng xứng đáng.");
        }
        else if (ten.contains("santorini")) {
            txtDetailsText.setText(
                    "Santorini là hòn đảo thiên đường nổi tiếng nhất của Hy Lạp, được hình thành từ tàn tích của một ngọn núi lửa phun trào. Nơi đây hớp hồn du khách bằng sự kết hợp hoàn hảo giữa hai tông màu trắng muốt và xanh đại dương tinh khôi.\n\n" +
                            "✨ CÁC ĐỊA ĐIỂM BẮT BUỘC PHẢI GHÉ THĂM:\n" +
                            "• Ngôi làng Thơ mộng Oia: Nơi ngắm hoàng hôn buông xuống biển Aegean được bầu chọn là đẹp nhất trên thế giới.\n" +
                            "• Thị trấn Fira: Thủ phủ nhộn nhịp nằm cheo leo trên vách đá dựng đứng, tràn ngập cửa hàng và quán cafe view biển.\n" +
                            "• Bãi biển Cát Đỏ (Red Beach): Bãi biển độc đáo với vách đá và cát mang một màu đỏ rực quý hiếm.\n" +
                            "• Các nhà thờ mái vòm xanh: Biểu tượng sống ảo huyền thoại xuất hiện trên mọi tấm bưu thiếp về Hy Lạp.\n\n" +
                            "Santorini là nơi lý tưởng để sống chậm. Hãy tận hưởng một buổi sáng yên bình bên ban công lộng gió, ngắm nhìn biển xanh ngắt trải dài vô tận and thưởng thức một ly vang trắng đặc sản của đảo!"
            );
            txtReviewUser1.setText("⭐⭐⭐⭐⭐ Thúy Vy");
            txtReviewContent1.setText("Hoàng hôn ở làng Oia đúng là danh bất hư truyền. Đẹp như một bức tranh vẽ sống động.");
            txtReviewUser2.setText("⭐⭐⭐⭐ Minh Khuê");
            txtReviewContent2.setText("Đường đi bậc thang dốc khá mệt nhưng bù lại góc nào chụp ảnh lên cũng sang chảnh");
        }
    }
}