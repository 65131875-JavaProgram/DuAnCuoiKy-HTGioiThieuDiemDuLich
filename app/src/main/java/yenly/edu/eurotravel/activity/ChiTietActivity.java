package yenly.edu.eurotravel.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private TextView txtTabOverview, txtTabPhotos, txtTabDetails, txtTabReviews;
    private ImageView imgChiTiet;
    private CardView btnBackCard;
    private TextView txtTenChiTiet, txtGiaChiTiet, txtQuocGiaChiTiet, txtMoTaChiTiet, txtSoSaoNhanXet;
    private TextView txtDuration, txtWeather, txtGuide, txtSanBay, txtKhachSan, txtDetailsText;

    private ChuyenDi chuyenDi;

    private CardView btnFavoriteCard;
    private Button btnWatchVideoCard;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        AnhXa();

        chuyenDi = (ChuyenDi) getIntent().getSerializableExtra("du_lieu_chuyen_di");

        if (chuyenDi != null) {
            DoDuLieuLenGiaoDien();
        } else {
            Toast.makeText(this, "Không tìm thấy dữ liệu!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        CaiDatSuKienTab();


        if (btnFavoriteCard != null) {
            SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);

            isFavorite = pref.getBoolean(chuyenDi.getTenDiaDiem(), false);

            ImageView imgStar = (ImageView) btnFavoriteCard.getChildAt(0);
            if (imgStar != null) {
                imgStar.setColorFilter(isFavorite ? Color.RED : Color.parseColor("#2B2E43"));
            }

            btnFavoriteCard.setOnClickListener(v -> {
                isFavorite = !isFavorite;

                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean(chuyenDi.getTenDiaDiem(), isFavorite);
                editor.apply();

                if (imgStar != null) {
                    imgStar.setColorFilter(isFavorite ? Color.RED : Color.parseColor("#2B2E43"));
                }

                if (isFavorite) {
                    Toast.makeText(ChiTietActivity.this, "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiTietActivity.this, "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnWatchVideoCard != null) {
            btnWatchVideoCard.setOnClickListener(v -> {
                if (chuyenDi != null && chuyenDi.getTenDiaDiem() != null) {
                    String tuKhoa = "travel vlog" + chuyenDi.getTenDiaDiem();
                    String urlYoutube = "https://www.youtube.com/results?search_query=" + Uri.encode(tuKhoa);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlYoutube));
                    intent.setPackage("com.google.android.youtube");

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            });
        }

        btnBackCard.setOnClickListener(v -> finish());
    }

    private void DoDuLieuLenGiaoDien() {
        txtTenChiTiet.setText(chuyenDi.getTenDiaDiem());
        txtGiaChiTiet.setText("$" + chuyenDi.getGiaTien());
        txtQuocGiaChiTiet.setText(chuyenDi.getNgonNgu());
        txtSoSaoNhanXet.setText(chuyenDi.getDiemDanhGia() + " (147)");

        if (chuyenDi.getMoTaTongQuan() != null && !chuyenDi.getMoTaTongQuan().isEmpty()) {
            txtMoTaChiTiet.setText(chuyenDi.getMoTaTongQuan());
        } else {
            txtMoTaChiTiet.setText("Hãy tận hưởng một chuyến đi tuyệt vời với những trải nghiệm không thể nào quên tại " + chuyenDi.getTenDiaDiem() + ".");
        }

        Glide.with(this)
                .load(chuyenDi.getHinhAnhDaiDien())
                .placeholder(R.drawable.paris)
                .error(R.drawable.paris)
                .into(imgChiTiet);

        txtDuration.setText(chuyenDi.getSoNgayDi() != null ? chuyenDi.getSoNgayDi() : "N/A");
        txtWeather.setText(chuyenDi.getThoiTiet() != null ? chuyenDi.getThoiTiet() : "N/A");
        txtGuide.setText(chuyenDi.getNgonNgu() != null ? chuyenDi.getNgonNgu() : "N/A");
        txtSanBay.setText(chuyenDi.getThongTinSanBay() != null ? chuyenDi.getThongTinSanBay() : "Đang cập nhật");
        txtKhachSan.setText(chuyenDi.getKhachSanNoiBat() != null ? chuyenDi.getKhachSanNoiBat() : "Đang cập nhật");

        txtDetailsText.setText(taoLichSuHapDan(chuyenDi.getTenDiaDiem()));

        List<ChuyenDi.PhotoItem> listPhotos = chuyenDi.getDanhSachAnhTabPhotos();
        if (listPhotos == null) {
            listPhotos = new ArrayList<>();
        }

        rvPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PhotoAdapter photoAdapter = new PhotoAdapter(listPhotos, item -> {
            Toast.makeText(ChiTietActivity.this, "Đang xem: " + item.getChuThich(), Toast.LENGTH_SHORT).show();
        });
        rvPhotos.setAdapter(photoAdapter);
    }

    private String taoLichSuHapDan(String tenDiaDiem) {
        if (tenDiaDiem == null) return "Đang cập nhật thông tin chi tiết...";
        String name = tenDiaDiem.toLowerCase().trim();

        if (name.contains("paris")) {
            return "Kinh đô Ánh sáng Paris không chỉ là trái tim của nước Pháp mà còn là biểu tượng tối thượng của sự lãng mạn, nghệ thuật và kiến trúc vĩ đại trên toàn cầu.\n\n" +
                    "🏰 DẤU ẤN LỊCH SỬ HOÀNG GIA:\n" +
                    "Trải qua hàng ngàn năm lịch sử, từ một khu định cư cổ đại của bộ tộc Parisii bên bờ sông Seine, Paris đã vươn mình trở thành trung tâm văn hóa lớn nhất Châu Âu vào thế kỷ 12. Nơi đây từng chứng kiến cuộc Cách mạng Pháp lịch sử năm 1789 phá bỏ ngục Bastille, mở ra kỷ nguyên mới cho nhân loại. Mỗi viên gạch tại các cung điện, quảng trường đều thấm đẫm câu chuyện của các triều đại vua chúa quyền lực.\n\n" +
                    "✨ CÁC TRẢI NGHIỆM ĐỘC QUYỀN MÊ HOẶC DU KHÁCH:\n" +
                    "• Tháp Eiffel vĩ đại: Công trình bằng thép biểu tượng, nơi bạn có thể ngắm nhìn trọn vẹn toàn cảnh thành phố lung linh, huyền ảo khi màn đêm buông xuống.\n" +
                    "• Đại lộ Champs-Élysées & Khải Hoàn Môn: Con đường mua sắm sầm uất bậc nhất thế giới, nơi tôn vinh những chiến công hiển hách của vị hoàng đế Napoléon.\n" +
                    "• Bảo tàng Louvre huyền thoại: Nơi lưu giữ hàng vạn kiệt tác nghệ thuật vô giá của nhân loại, bao gồm bức tranh nàng Mona Lisa với nụ cười bí ẩn.\n\n" +
                    "Đến với Paris, hành trình tuyệt vời nhất là được tản bộ dọc bờ sông Seine thơ mộng, ghé vào một quán cà phê vỉa hè cổ kính, thưởng thức chiếc bánh sừng bò thơm nức và cảm nhận hơi thở lãng mạn len lỏi qua từng góc phố cổ!";
        }

        if (name.contains("london")) {
            return "London - Thủ đô sương mù đầy kiêu hãnh của Vương quốc Anh, là một trong những thành phố vĩ đại nhất thế giới, nơi quá khứ hoàng gia rực rỡ hòa quyện hoàn hảo với nhịp sống hiện đại siêu đô thị.\n\n" +
                    "👑 DI SẢN VÀ THẾ LỰC HOÀNG GIA:\n" +
                    "Được thành lập bởi người La Mã cổ đại với tên gọi Londinium cách đây hơn 2.000 năm, London từng là trung tâm quyền lực của Đế quốc Anh rộng lớn - nơi mặt trời không bao giờ lặn. Thành phố sở hữu một kho tàng lịch sử khổng lồ với những cung điện lộng lẫy, những nghi lễ đổi gác trang nghiêm và những câu chuyện huyền bí về hoàng tộc Anh được truyền tụng qua nhiều thế hệ.\n\n" +
                    "✨ CÁC ĐIỂM ĐẾN KHÔNG THỂ BỎ QUA:\n" +
                    "• Tháp đồng hồ Big Ben & Tòa nhà Quốc hội: Biểu tượng trường tồn theo thời gian, đứng sừng sững bên dòng sông Thames êm đềm.\n" +
                    "• Cung điện Buckingham: Nơi ở chính thức của Nữ hoàng và Đức vua Anh, mang đậm kiến trúc hoàng gia xa hoa bậc nhất.\n" +
                    "• Vòng quay khổng lồ London Eye: Trải nghiệm ngồi trên cabin trên cao để phóng tầm mắt ngắm nhìn đường chân trời tuyệt đẹp của toàn thành phố.\n\n" +
                    "Hãy dành một buổi chiều ngồi trên chiếc xe buýt hai tầng màu đỏ đặc trưng, thưởng thức một tách trà chiều chuẩn vị quý tộc Anh và đắm mình vào không gian văn hóa đẳng cấp tại đây!";
        }

        if (name.contains("santorini")) {
            return "Santorini là hòn đảo thiên đường nổi tiếng nhất của Hy Lạp và là viên ngọc quý của biển Aegean, được hình thành từ tàn tích của một trận phun trào núi lửa khủng khiếp trong quá khứ.\n\n" +
                    "💙 ĐẶC TRƯNG KIẾN TRÚC ĐỘC NHẤT:\n" +
                    "Hòn đảo hớp hồn du khách bởi lối kiến trúc Cycladic độc đáo: những ngôi nhà sơn trắng muốt như những khối đường tinh khiết, nằm cheo leo trên vách đá dựng đứng, nổi bật với những mái vòm màu xanh đại dương trùng khớp với màu nước biển Địa Trung Hải.\n\n" +
                    "✨ KỲ QUAN VÀ TRẢI NGHIỆM ĐẮT GIÁ:\n" +
                    "• Ngôi làng Thơ mộng Oia: Nơi ngắm hoàng hôn buông xuống biển được bình chọn là lãng mạn và đẹp nhất hành tinh. Khi mặt trời lặn, toàn bộ vách đá chuyển sang màu hồng cam huyền ảo.\n" +
                    "• Thủ phủ Fira: Thị trấn nhộn nhịp nằm trên đỉnh cao, tràn ngập những con hẻm nhỏ lát đá, những nhà hàng lộng gió view thẳng ra miệng núi lửa chìm dưới biển.\n" +
                    "• Bãi biển Đỏ & Biển Đen: Những bãi biển độc nhất vô nhị có cát màu đỏ rực hoặc đen tuyền do bụi tro núi lửa tạo nên.\n\n" +
                    "Santorini là nơi lý tưởng để bạn sống chậm lại, thưởng thức một ly vang trắng Assyrtiko trứ danh bên ban công, lộng gió biển và ngắm nhìn đại dương bao la vô tận!";
        }

        if (name.contains("amsterdam")) {
            return "Amsterdam – Thủ đô của Vương quốc Hà Lan, được mệnh danh là 'Venice của phương Bắc' nhờ hệ thống kênh đào chằng chịt, cổ kính đã được UNESCO công nhận là Di sản thế giới.\n\n" +
                    "🚲 LỊCH SỬ VÀ PHONG CÁCH SỐNG ĐỘC ĐÁO:\n" +
                    "Xuất phát từ một làng chài nhỏ vào thế kỷ 13, Amsterdam vươn lên thành một trong những thương cảng quan trọng nhất thế giới trong 'Kỷ nguyên Vàng' của Hà Lan. Thành phố này nổi tiếng toàn cầu bởi tinh thần tự do, phóng khoáng, và là thủ đô của văn hóa đi xe đạp với số lượng xe đạp còn nhiều hơn cả số dân!\n\n" +
                    "✨ NHỮNG TRẢI NGHIỆM ĐÁNG GIÁ KHÔNG THỂ KHÔNG THỬ:\n" +
                    "• Hệ thống kênh đào biểu tượng: Ngồi thuyền mui trần len lỏi qua các dòng kênh, ngắm nhìn những ngôi nhà gạch cao gầy có kiến trúc độc đáo từ thế kỷ 17.\n" +
                    "• Quảng trường Museumplein: Nơi tập trung những bảo tàng lớn nhất thế giới như Bảo tàng Van Gogh và Bảo tàng Rijksmuseum, lưu giữ các kiệt tác hội họa kinh điển.\n" +
                    "• Cánh đồng hoa Tulip Keukenhof (gần thành phố): Thiên đường hoa rực rỡ sắc màu, mở ra bức tranh thiên nhiên tuyệt mỹ mỗi độ xuân về.\n\n" +
                    "Một chuyến đi dạo qua những cây cầu cổ lãng mạn rực rỡ ánh đèn vào ban đêm sẽ khiến bất kỳ trái tim du khách nào cũng phải thổn thức!";
        }

        if (name.contains("barcelona")) {
            return "Barcelona – Thủ đô của xứ Catalonia, Tây Ban Nha, là một thành phố biển rực rỡ ánh nắng, tràn đầy năng lượng và là cái nôi của những công trình kiến trúc kỳ dị, vĩ đại bậc nhất nhân loại.\n\n" +
                    "🎨 THÀNH PHỐ CỦA THIÊN TÀI ANTONI GAUDÍ:\n" +
                    "Không một thành phố nào trên thế giới lại mang đậm dấu ấn cá nhân của một kiến trúc sư như Barcelona với Antoni Gaudí. Đi bộ trong thành phố, bạn sẽ ngỡ ngàng trước những tòa nhà không có đường thẳng, mô phỏng hình dáng của thiên nhiên hoang dã, tạo nên một bảo tàng kiến trúc ngoài trời khổng lồ.\n\n" +
                    "✨ ĐIỂM ĐẾN GÂY ẤN TƯỢNG MẠNH:\n" +
                    "• Vương cung thánh đường Sagrada Família: Kiệt tác thế kỷ chưa hoàn thành của Gaudí, sở hữu những cột trụ như rừng cây cổ thụ và hệ thống kính màu đón ánh sáng đẹp như cõi mơ.\n" +
                    "• Công viên Guell: Khu vườn cổ tích rực rỡ với những bức tranh khảm gốm đầy màu sắc, ngắm trọn vẹn biển Địa Trung Hải.\n" +
                    "• Đại lộ sôi động Las Ramblas: Trái tim của thành phố với các nghệ sĩ đường phố, chợ ẩm thực La Boqueria tràn ngập món ăn ngon hải sản và đùi lợn muối Iberico.\n\n" +
                    "Barcelona hứa hẹn đem đến cho bạn một kỳ nghỉ cuồng nhiệt, say đắm trong tiếng nhạc Flamenco và vị ngon của những ly rượu Sangria mát lạnh!";
        }

        if (name.contains("amalfi")) {
            return "Biển Amalfi (Amalfi Coast) – Dải bờ biển miền Nam nước Ý, được ca ngợi là một trong những cung đường ven biển ngoạn mục và quyến rũ nhất trên thế giới, được UNESCO vinh danh là kiệt tác cảnh quan thế giới.\n\n" +
                    "🍋 THIÊN ĐƯỜNG VÁCH ĐÁ ĐỊA TRUNG HẢI:\n" +
                    "Nơi đây sở hữu địa hình cực kỳ hiểm trở nhưng lại mang vẻ đẹp nghẹt thở: những thị trấn cổ kính với những ngôi nhà màu pastel rực rỡ nằm chồng chồng lớp lớp lên nhau dọc theo vách đá dựng đứng, hướng thẳng ra làn nước biển xanh trong vắt như ngọc bích.\n\n" +
                    "✨ TRẢI NGHIỆM HOÀN HẢO TẠI AMALFI:\n" +
                    "• Thị trấn Positano: Viên ngọc sáng nhất Amalfi, nơi có những bậc thang dốc uốn lượn, những giàn hoa giấy hồng rực rỡ bao phủ các lối đi nhỏ dẫn xuống bãi biển.\n" +
                    "• Thị trấn Amalfi lịch sử: Từng là một trong bốn cộng hòa hàng hải hùng mạnh của Ý, nổi bật với nhà thờ chính tòa Duomo mang phong cách kiến trúc Moorish cổ kính.\n" +
                    "• Hương vị chanh vàng bản địa: Vùng đất của những vườn chanh khổng lồ, nơi sản xuất ra loại rượu chanh Limoncello thơm lừng nổi tiếng khắp hành tinh.\n" +
                    "Cảm giác được lái xe men theo những khúc cua lộng gió, ngắm biển xanh thẳm một bên và vách đá hùng vĩ một bên chính là trải nghiệm đỉnh cao của cuộc đời!";
        }

        if (name.contains("geneva")) {
            return "Hồ Geneva (Lac Léman) – Viên ngọc xanh tuyệt mỹ nằm giữa biên giới hai nước Thụy Sĩ và Pháp, là một trong những hồ nước ngọt lớn nhất Tây Âu, nằm bình yên dưới chân dãy núi Alps hùng vĩ.\n\n" +
                    "🏔️ SỰ GIAO THOA GIỮA THIÊN NHIÊN VÀ SỰ SANG TRỌNG:\n" +
                    "Vùng hồ Geneva là biểu tượng của cuộc sống thanh bình, đẳng cấp và giàu có. Mặt hồ phẳng lặng như gương phản chiếu đỉnh núi tuyết trắng xóa, bao quanh là các thành phố quốc tế hiện đại, các thị trấn nghỉ dưỡng lãng mạn và những đồi chè xanh mướt trải dài.\n\n" +
                    "✨ NHỮNG ĐIỂM SÁNG LÀM SAY ĐẮM LÒNG NGƯỜI:\n" +
                    "• Đài phun nước Jet d'Eau: Biểu tượng của thành phố Geneva, phun cột nước khổng lồ cao tới 140m lên không trung, tạo nên cầu vồng rực rỡ dưới ánh nắng.\n" +
                    "• Lâu đài Chillon (Montreux): Lâu đài cổ kính hơn 1.000 năm tuổi nằm soi bóng ngay bên vách đá sát mặt nước hồ, trông như bước ra từ truyện cổ tích.\n" +
                    "• Thị trấn Montreux: Nơi ngập tràn hoa tươi dọc bờ hồ, nổi tiếng với khí hậu ôn hòa và là nguồn cảm hứng của rất nhiều nghệ sĩ vĩ đại.\n\n" +
                    "Hãy thử một lần ngồi trên du thuyền hơi nước cổ hoài niệm, ngắm hoàng hôn buông xuống đỉnh núi tuyết, bạn sẽ hiểu thế nào là thiên đường nơi hạ giới!";
        }

        if (name.contains("ibiza")) {
            return "Ibiza – Hòn đảo huyền thoại thuộc quần đảo Baleares của Tây Ban Nha, nổi tiếng khắp thế giới như thủ đô tiệc tùng của nhân loại nhưng đồng thời cũng ẩn chứa vẻ đẹp thiên nhiên hoang sơ thanh bình kỳ diệu.\n\n" +
                    "🌟 SỰ KẾT HỢP GIỮA CUỒNG NHIỆT VÀ BÌNH YÊN:\n" +
                    "Ibiza mang hai bộ mặt hoàn toàn đối lập nhưng đều mê hoặc. Một bên là thiên đường giải trí sôi động ban đêm với các câu lạc bộ bãi biển đẳng cấp, nơi tụ họp của các DJ hàng đầu thế giới. Một bên là khu phố cổ Dalt Vila cổ kính được UNESCO công nhận là Di sản thế giới, với những bức tường thành vững chãi bảo vệ hòn đảo khỏi cướp biển ngày xưa.\n\n" +
                    "✨ TRẢI NGHIỆM ĐỘC QUYỀN TẠI IBIZA:\n" +
                    "• Các vịnh biển bí ẩn (Calas): Như Cala Comte, Cala Salada với làn nước biển màu ngọc lục bảo trong suốt đến mức nhìn thấy đáy cát trắng.\n" +
                    "• Chợ Bohemian (Hippie Markets): Nơi lưu giữ văn hóa tự do từ thập niên 1960 với trang phục, đồ thủ công mỹ nghệ độc lạ.\n" +
                    "• Hoàng hôn tại Café del Mar: Vừa nghe nhạc chillout vừa ngắm mặt trời đỏ rực chìm dần xuống biển sâu.\n\n" +
                    "Ibiza không chỉ là một điểm đến, đó là một phong cách sống tự do phóng khoáng đỉnh cao mà ai cũng nên trải nghiệm một lần!";
        }

        if (name.contains("mont blanc") || name.contains("blanc")) {
            return "Mont Blanc (Đỉnh Núi Trắng) – Nóc nhà của Tây Âu, ngọn núi cao nhất thuộc dãy Alps sừng sững nằm giữa biên giới Pháp và Ý, là mục tiêu tối thượng của những nhà leo núi và những người yêu thiên nhiên hoang dã vĩ đại.\n\n" +
                    "❄️ SỰ HÙNG VĨ TỐI CAO CỦA THIÊN NHIÊN:\n" +
                    "Với độ cao vách núi lên tới 4.805 mét, Mont Blanc quanh năm bao phủ bởi lớp băng tuyết vĩnh cửu trắng xóa, những dòng sông băng khổng lồ uốn lượn tạo nên một cảnh quan kỳ vĩ, tráng lệ đến nghẹt thở.\n\n" +
                    "✨ NHỮNG TRẢI NGHIỆM ĐỈNH CAO:\n" +
                    "• Thung lũng Chamonix (Pháp): Thị trấn núi xinh đẹp nằm ngay dưới chân núi, trung tâm của các trò chơi mạo hiểm, trượt tiện đẳng cấp thế giới.\n" +
                    "• Cáp treo Aiguille du Midi: Hệ thống cáp treo kỷ lục đưa bạn lên độ cao 3.842m chỉ trong vài phút. Tại đây, bạn có thể trải nghiệm bước chân vào lồng kính 'Bước vào khoảng không' nhìn xuống vực sâu thẳm dưới chân.\n" +
                    "• Tàu hỏa bánh răng Montenvers: Đưa du khách đến tham quan Hang động băng (Ice Cave) được đào xuyên lòng dòng sông băng Mer de Glace rực rỡ ánh sáng xanh.\n\n" +
                    "Đứng trước sự vĩ đại của Mont Blanc, bạn sẽ cảm thấy tâm hồn mình trở nên rộng mở và hoàn toàn choáng ngợp trước bàn tay tạo hóa!";
        }

        if (name.contains("plitvice")) {
            return "Vườn quốc gia Hồ Plitvice (Croatia) – Được mệnh danh là 'Thiên đường ngọc bích' của Châu Âu, một trong những kỳ quan thiên nhiên lâu đời và đẹp nhất thế giới được UNESCO công nhận từ năm 1979.\n\n" +
                    "🌿 KÝ QUAN THÁC NƯỚC CHỐN BỒNG LAI:\n" +
                    "Plitvice hớp hồn du khách bởi một hệ thống kỳ ảo gồm 16 hồ nước liên kết với nhau bằng hàng trăm thác nước lớn nhỏ tự nhiên, đổ từ trên cao xuống các thung lũng đá vôi sâu thẳm. Làn nước ở đây có màu sắc thay đổi liên tục từ xanh lá cây, xanh ngọc bích sang màu xám bạc tùy thuộc vào lượng khoáng chất và ánh nắng mặt trời.\n\n" +
                    "✨ TRẢI NGHIỆM ĐI BỘ TRÊN NƯỚC ĐỘC ĐÁO:\n" +
                    "• Hệ thống cầu gỗ ven bờ: Bạn sẽ được tản bộ trên những lối đi bằng gỗ được thiết kế uốn lượn ngay trên sát mặt nước hồ, đi xuyên qua làn sương mờ của các dòng thác đổ ào ạt.\n" +
                    "• Thác nước Veliki Slap: Thác nước cao nhất công viên (78m), bọt tung trắng xóa giữa không gian rừng nguyên sinh xanh mướt.\n" +
                    "• Ngắm động vật hoang dã: Khu rừng nguyên sinh bao bọc quanh hồ là nơi trú ẩn của nhiều loài chim quý hiếm và thảm thực vật phong phú.\n\n" +
                    "Bước vào Plitvice giống như bạn đang lạc vào một thế giới thần tiên tách biệt hoàn toàn khỏi cuộc sống xô bồ hiện đại!";
        }

        if (name.contains("algarve")) {
            return "Algarve – Vùng đất cực Nam đầy quyến rũ của Bồ Đào Nha, nổi tiếng thế giới bởi những bãi biển cát vàng mịn màng, những vách đá vôi màu cam rực rỡ và những hang động biển kỳ vĩ được sóng đại dương đục đẽo hàng triệu năm.\n\n" +
                    "☀️ VÙNG BIỂN VÀNG TRÀN NGẬP ÁNH NẮNG:\n" +
                    "Với hơn 300 ngày nắng mỗi năm và khí hậu Địa Trung Hải ấm áp quanh năm, Algarve là điểm trốn cái lạnh mùa đông yêu thích của cả Châu Âu. Nơi đây sở hữu đường bờ biển dài ngoạn mục xen lẫn những làng chài cổ kính quét vôi trắng xóa thanh bình.\n\n" +
                    "✨ NHỮNG ĐIỂM ĐẾN ĐẮT GIÁ:\n" +
                    "• Hang động biển Benagil: Kỳ quan thiên nhiên nổi tiếng với một vòm hang khổng lồ có 'giếng trời' tự nhiên đón ánh nắng chiếu xuống bãi cát vàng bên trong lòng hang cực kỳ kỳ ảo.\n" +
                    "• Thị trấn Lagos lịch sử: Nơi khởi nguồn của những chuyến hải trình khám phá thế giới của các thủy thủ Bồ Đào Nha vĩ đại vào thế kỷ 15.\n" +
                    "• Mũi đất Ponta da Piedade: Sở hữu những cột đá vôi dựng sừng sững giữa biển xanh, tạo nên những cổng vòm tự nhiên tuyệt mỹ.\n\n" +
                    "Đến Algarve, hãy thuê một chiếc thuyền kayak nhỏ, chèo len lỏi qua các vòm đá ngầm để khám phá những bãi biển bí ẩn giấu kín trong lòng vách đá nhé!";
        }

        if (name.contains("rome")) {
            return "Rome – Thành phố vĩnh cửu của nước Ý, nơi lưu giữ những tàn tích vĩ đại vĩnh cửu của Đế chế La Mã hùng mạnh cổ đại.\n\n" +
                    "🏛️ ĐỀ CHẾ VÀ TÀN TÍCH CỔ ĐẠI:\n" +
                    "Trải qua gần 3.000 năm lịch sử, Rome là một trong những cái nôi vĩ đại nhất của văn minh phương Tây. Du khách sẽ hoàn toàn bị choáng ngợp khi đứng trước Đấu trường Colosseum huyền thoại, Điện Pantheon oai nghiêm hay khu phế tích Roman Forum cổ kính.\n\n" +
                    "✨ TRẢI NGHIỆM ĐÁNG GIÁ BẮT BUỘC:\n" +
                    "• Đài phun nước Trevi: Kiệt tác điêu khắc Baroque, nơi du khách tung đồng xu cầu nguyện quay trở lại Rome một lần nữa.\n" +
                    "• Tòa thánh Vatican: Quốc gia nhỏ nhất thế giới nằm trong lòng Rome, nơi sở hữu Vương cung thánh đường Thánh Peter xa hoa tuyệt đỉnh.\n" +
                    "• Ẩm thực Rome: Thưởng thức đĩa mì Ý Carbonara đúng điệu kết hợp một ly kem Gelato truyền thống mát lạnh.\n\n" +
                    "Mỗi bước chân tại Rome là một bước chạm vào các trang sử sách vĩ đại kinh điển của nhân loại!";
        }

        if (name.contains("venice")) {
            return "Venice – Thành phố của những con kênh lãng mạn bậc nhất thế giới, kỳ quan kiến trúc nổi trên mặt biển Địa Trung Hải độc nhất vô nhị.\n\n" +
                    "🎭 THÀNH PHỐ KHÔNG TIẾNG ĐỘNG CƠ XE:\n" +
                    "Venice được xây dựng trên 118 hòn đảo nhỏ kết nối bởi hơn 400 cây cầu cổ kính. Tại đây, hoàn toàn không có ô tô hay xe máy, mọi hoạt động di chuyển đều diễn ra trên những dòng kênh xanh biếc bằng những chiếc thuyền Gondola truyền thống kiêu sa.\n\n" +
                    "✨ TRẢI NGHIỆM ĐỘC QUYỀN MÊ HOẶC:\n" +
                    "• Quảng trường Thánh Mark: Trái tim của Venice với Vương cung thánh đường mang kiến trúc Byzantine vàng rực và Dinh Tổng trấn tráng lệ.\n" +
                    "• Cầu Rialto cổ xưa: Cây cầu đá lâu đời và đẹp nhất bắc qua dòng Kênh Lớn (Grand Canal) sầm uất tấp nập thuyền bè.\n" +
                    "• Đảo sắc màu Burano: Hòn đảo nhỏ nổi tiếng với những ngôi nhà rực rỡ đủ sắc màu cầu vồng soi bóng xuống dòng kênh xanh phẳng lặng.\n\n" +
                    "Ngồi trên chiếc thuyền Gondola nghe người lái thuyền cất tiếng hát ngân vang giữa những bức tường rêu phong lãng mạn vô cùng!";
        }

        if (name.contains("prague")) {
            return "Prague – Trái tim vàng của Châu Âu, thủ đô cổ kính của Cộng hòa Séc, được mệnh danh là thành phố của trăm đỉnh tháp vàng nguy nga.\n\n" +
                    "🏰 KHÔNG GIAN CỔ TÍCH TRUNG CỔ NGUYÊN VẸN:\n" +
                    "May mắn không bị tàn phá trong các cuộc chiến tranh lớn, Prague giữ nguyên vẹn cấu trúc đô thị thời trung cổ huyền biến. Những con đường lát đá quanh co, những quảng trường rộng lớn mang đậm phong cách Gothic và kiến trúc Baroque quyến rũ.\n\n" +
                    "✨ KÝ QUAN NỔI TIẾNG:\n" +
                    "• Cầu Charles (Cầu Tình): Cây cầu đá cổ hơn 600 tuổi bắc ngang sông Vltava thơ mộng, nơi đặt 30 bức tượng thánh cổ kính vĩ đại.\n" +
                    "• Lâu đài Prague: Quần thể lâu đài cổ rộng lớn nhất thế giới nằm kiêu hãnh trên đỉnh đồi, nhìn xuống toàn cảnh mái ngói đỏ rực.\n" +
                    "• Đồng hồ thiên văn Orloj: Kiệt tác cơ khí thế kỷ 14 tại quảng trường cổ, cứ mỗi giờ lại có màn trình diễn chuyển động độc đáo.\n\n" +
                    "Prague mang một vẻ đẹp bí ẩn, ma mị đặc biệt rực rỡ lộng lẫy khi ánh đèn đêm buông xuống phủ vàng lên những đỉnh tháp!";
        }

        if (name.contains("vienna")) {
            return "Vienna – Thủ đô âm nhạc cổ điển huyền thoại của thế giới, thành phố đáng sống nhất nước Áo mang đậm phong cách quý tộc sang trọng.\n\n" +
                    "🎵 CÁI NÔI CỦA THIÊN TÀI ÂM NHẠC:\n" +
                    "Vienna là nơi gắn liền với cuộc đời và sự nghiệp vĩ đại của các nhà soạn nhạc thiên tài như Mozart, Beethoven hay Strauss. Thành phố mang bầu không khí nghệ thuật thượng lưu đỉnh cao bao phủ khắp các nhà hát opera lộng lẫy và các đại lộ thênh thang.\n\n" +
                    "✨ KIỆT TÁC HOÀNG GIA XA HOA:\n" +
                    "• Cung điện Schönbrunn: Nơi nghỉ hè của hoàng gia Habsburg với khu vườn mê cung khổng lồ mang kiến trúc Baroque tuyệt mỹ.\n" +
                    "• Cung điện Hofburg: Trung tâm quyền lực mùa đông lộng lẫy, nơi lưu giữ kho báu hoàng gia vô giá.\n" +
                    "• Văn hóa cà phê Vienna: Thưởng thức ly cà phê Melange đậm đà cùng miếng bánh ngọt Sacher trứ danh trong không gian âm nhạc du dương.\n\n" +
                    "Vienna đem lại cho du khách một cảm giác yên bình, đẳng cấp quý tộc hoàng gia sâu sắc khó tìm thấy ở nơi nào khác!";
        }

        if (name.contains("florence")) {
            return "Florence – Cội nguồn của phong trào Văn hóa Phục Hưng vĩ đại, trung tâm nghệ thuật vĩnh cửu nằm giữa thung lũng vùng Tuscany nước Ý.\n\n" +
                    "🎨 BẢO TÀNG NGHỆ THUẬT LỘ THIÊN VĨ ĐẠI:\n" +
                    "Florence là quê hương của những vĩ nhân làm thay đổi lịch sử nghệ thuật nhân loại như Leonardo da Vinci, Michelangelo hay Dante. Thành phố nhỏ nhắn này chứa đựng một mật độ di sản và tác phẩm nghệ thuật kinh điển khổng lồ.\n\n" +
                    "✨ ĐIỂM SÁNG KHÔNG THỂ BỎ QUA:\n" +
                    "• Nhà thờ chính tòa Duomo (Santa Maria del Fiore): Kỳ quan sở hữu mái vòm bằng gạch đỏ khổng lồ lớn nhất thế giới của Brunelleschi.\n" +
                    "• Cầu Ponte Vecchio: Cây cầu đá cổ uốn lượn qua sông Arno, nơi mọc lên những cửa hàng kim hoàn lấp lánh có lịch sử hàng trăm năm.\n" +
                    "• Bảo tàng Uffizi: Nơi lưu giữ những bức họa kinh điển như 'Sự ra đời của thần Vệ Nữ' cùng vô số kiệt tác điêu khắc vô giá.\n\n" +
                    "Florence mang một vẻ đẹp lãng mạn trầm mặc cổ kính, làm say đắm bất kỳ tâm hồn yêu nghệ thuật và cái đẹp nào!";
        }

        if (name.contains("budapest")) {
            return "Budapest – Viên ngọc bích lộng lẫy bên dòng sông Danube xanh, thủ đô kiêu hãnh của đất nước Hungary cổ kính.\n\n" +
                    "🌊 SỰ GIAO THOA QUYẾN RŨ BÊN DÒNG SÔNG:\n" +
                    "Thành phố được chia làm hai phần rõ rệt bởi dòng sông Danube: vùng Buda cổ kính yên bình trên đồi cao và vùng Pest hiện đại sôi động bên bằng phẳng, tạo nên một cảnh quan đô thị đối lập hài hòa tuyệt diệu.\n\n" +
                    "✨ KÝ QUAN KIẾN TRÚC ĐỈNH CAO:\n" +
                    "• Tòa nhà Quốc hội Hungary: Kiệt tác kiến trúc Gothic khổng lồ soi bóng lung linh rực rỡ ánh vàng xuống dòng sông Danube ban đêm.\n" +
                    "• Pháo đài Fisherman: Công trình có kiến trúc như lâu đài cổ tích, nơi ngắm toàn cảnh thành phố đẹp nhất.\n" +
                    "• Nhà tắm khoáng nóng Széchenyi: Trải nghiệm thư giãn ngâm mình trong hồ khoáng nóng tự nhiên giữa cung điện Baroque xa hoa.\n\n" +
                    "Một chuyến du thuyền trên sông Danube ngắm hoàng hôn buông xuống Budapest chắc chắn sẽ ghi dấu ấn sâu đậm trong tim bạn!";
        }

        if (name.contains("berlin")) {
            return "Berlin – Thủ đô lịch sử, văn hóa và sáng tạo bùng nổ của nước Đức, biểu tượng của sự kiên cường tái sinh mạnh mẽ sau thăng trầm.\n\n" +
                    "⛓️ CHỨNG TÍCH LỊCH SỬ VÀ SỰ ĐỔI MỚI:\n" +
                    "Từng là tâm điểm của những cuộc chiến tranh thế giới và sự chia cắt thời Chiến tranh Lạnh, Berlin ngày nay đã vươn mình trở thành một trung tâm văn hóa nghệ thuật đương đại, khởi nghiệp phóng khoáng tự do hàng đầu Châu Âu.\n\n" +
                    "✨ ĐIỂM ĐẾN MANG ĐẬM DẤU ẤN:\n" +
                    "• Cổng thành Brandenburg: Biểu tượng vĩnh cửu của sự thống nhất nước Đức, công trình kiến trúc tân cổ điển oai nghiêm.\n" +
                    "• Bức tường Berlin (East Side Gallery): Đoạn tường còn sót lại được biến thành phòng tranh ngoài trời dài nhất thế giới đầy màu sắc.\n" +
                    "• Đảo Bảo Tàng (Museum Island): Quần thể 5 bảo tàng tầm cỡ quốc tế được UNESCO công nhận di sản thế giới lưu giữ cổ vật quý hiếm.\n" +
                    "• Đời sống Berlin: Tinh thần phóng khoáng tự do tràn ngập qua các quán cà phê ngập tràn graffiti nghệ thuật đường phố sôi động.\n\n" +
                    "Berlin đem lại một chiều sâu lịch sử sâu sắc kết hợp nhịp đập tương lai trẻ trung đầy cuốn hút!";
        }

        if (name.contains("munich")) {
            return "Munich – Trái tim phồn vinh của vùng Bavaria nước Đức, nơi bản sắc truyền thống lâu đời kết hợp hoàn hảo cùng sự phát triển công nghệ đỉnh cao.\n\n" +
                    "🍺 THỦ ĐÔ CỦA LỄ HỘI BIA VÀ LÂU ĐÀI CỔ TÍCH:\n" +
                    "Munich nổi tiếng toàn cầu với lễ hội bia Oktoberfest cuồng nhiệt thu hút hàng triệu du khách, và là cửa ngõ dẫn lối đến những lâu đài cổ tích lộng lẫy ẩn hiện giữa núi rừng thung lũng Alps.\n\n" +
                    "✨ TRẢI NGHIỆM ĐỈNH CAO TẠI MUNICH:\n" +
                    "• Quảng trường Marienplatz: Trung tâm thành phố với Tòa thị chính cổ kính sở hữu tháp đồng hồ Glockenspiel trình diễn độc đáo.\n" +
                    "• Lâu đài Neuschwanstein (gần thành phố): Nguyên mẫu của lâu đài Cinderella trong hoạt hình Disney nằm cheo leo trên vách núi tuyệt mỹ.\n" +
                    "• Khu vườn tiếng Anh (Englischer Garten): Công viên đô thị khổng lồ, nơi bạn có thể ngắm người dân lướt sóng trên dòng sông nhân tạo uốn lượn.\n\n" +
                    "Hãy thưởng thức một ly bia đại mạch Đức hảo hạng cùng chiếc bánh mì vòng Pretzel khổng lồ để cảm nhận sự sảng khoái đích thực!";
        }

        if (name.contains("vatican")) {
            return "Vatican – Quốc gia độc lập nhỏ nhất thế giới nằm bình yên trong lòng thủ đô Rome của Ý, trung tâm thiêng liêng tối cao của Giáo hội Công giáo.\n\n" +
                    "🇻🇦 QUỐC GIA NHỎ BÉ MANG DI SẢN KHỔNG LỒ:\n" +
                    "Dù có diện tích chưa đầy 0.5 km2, Vatican sở hữu những kho tàng kiến trúc, điêu khắc và hội họa vĩ đại nhất của nhân loại thời kỳ Phục Hưng được bảo tồn nghiêm ngặt qua nhiều thế kỷ.\n\n" +
                    "✨ KIỆT TÁC TÔN GIÁO VÀ NGHỆ THUẬT:\n" +
                    "• Vương cung thánh đường Thánh Peter: Nhà thờ lớn và lộng lẫy nhất thế giới, nơi sở hữu mái vòm cao vút và bức tượng điêu khắc Pieta bất hủ của Michelangelo.\n" +
                    "• Hệ thống Bảo tàng Vatican: Nơi lưu giữ hàng vạn hiện vật nghệ thuật cổ đại đắt giá do các Giáo hoàng sưu tập qua nhiều thời kỳ.\n" +
                    "• Nhà nguyện Sistine: Nơi chiêm ngưỡng kiệt tác tranh trần nhà 'Sáng thế ký' và 'Sự phán xét cuối cùng' vĩ đại của thiên tài Michelangelo.\n\n" +
                    "Đặt chân đến Vatican mang lại một cảm giác trang nghiêm, thành kính và choáng ngợp trước đỉnh cao sáng tạo nghệ thuật nhân loại!";
        }

        return "Chào mừng bạn đến với " + tenDiaDiem + " – một trong những mảnh đất quyến rũ, thơ mộng và mang đậm bản sắc văn hóa lâu đời nhất của Châu Âu cổ kính!\n\n" +
                "🌟 DẤU ẤN LỊCH SỬ & VĂN HÓA LÂU ĐỜI:\n" +
                "Nơi đây tự hào lưu giữ những giá trị văn hóa vô giá truyền lại qua nhiều thế kỷ, đan xen hoàn hảo giữa nét kiến trúc cổ kính tráng lệ của các công trình di sản và nhịp sống đô thị vô cùng văn minh, hiện đại. Từng viên gạch lát đường, từng quảng trường rực rỡ ánh đèn hay những con hẻm nhỏ rợp bóng hoa đều ẩn chứa một câu chuyện lịch sử hào hùng đang chờ bạn khám phá.\n\n" +
                "✨ NHỮNG TRẢI NGHIỆM ĐỘC QUYỀN KHÔNG THỂ BỎ LỠ:\n" +
                "• Tản bộ khám phá các khu phố cổ và công trình kiến trúc biểu tượng mang tính lịch sử vĩ đại.\n" +
                "• Đắm chìm trong cảnh sắc thiên nhiên tuyệt mỹ, đón ánh bình minh rực rỡ bên khung cửa hay ngắm hoàng hôn lãng mạn buông xuống.\n" +
                "• Thưởng thức nền ẩm thực bản địa tinh tế, phong phú cùng sự đón tiếp nồng hậu, hiếu khách tuyệt vời của người dân địa phương.\n\n" +
                "Hãy tạm gác lại mọi lo toan bộn bề của cuộc sống, xách ba lô lên và sẵn sàng cho một hành trình khám phá bùng nổ cảm xúc, ghi dấu những kỷ niệm không thể nào quên tại " + tenDiaDiem + "!";
    }

    private void CaiDatSuKienTab() {
        HienThiTab(1);

        txtTabOverview.setOnClickListener(v -> HienThiTab(1));
        txtTabPhotos.setOnClickListener(v -> HienThiTab(2));
        txtTabDetails.setOnClickListener(v -> HienThiTab(3));
        txtTabReviews.setOnClickListener(v -> HienThiTab(4));
    }

    private void HienThiTab(int tabIndex) {
        txtTabOverview.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabPhotos.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabDetails.setTextColor(Color.parseColor("#A0A0A0"));
        txtTabReviews.setTextColor(Color.parseColor("#A0A0A0"));

        layoutOverviewContent.setVisibility(View.GONE);
        rvPhotos.setVisibility(View.GONE);
        layoutDetailsContent.setVisibility(View.GONE);
        layoutReviewsContent.setVisibility(View.GONE);

        if (tabIndex == 1) {
            txtTabOverview.setTextColor(Color.parseColor("#1E2333"));
            layoutOverviewContent.setVisibility(View.VISIBLE);
        } else if (tabIndex == 2) {
            txtTabPhotos.setTextColor(Color.parseColor("#1E2333"));
            rvPhotos.setVisibility(View.VISIBLE);
        } else if (tabIndex == 3) {
            txtTabDetails.setTextColor(Color.parseColor("#1E2333"));
            layoutDetailsContent.setVisibility(View.VISIBLE);
        } else if (tabIndex == 4) {
            txtTabReviews.setTextColor(Color.parseColor("#1E2333"));
            layoutReviewsContent.setVisibility(View.VISIBLE);
        }
    }

    private void AnhXa() {
        rvPhotos = findViewById(R.id.rvPhotos);
        layoutOverviewContent = findViewById(R.id.layoutOverviewContent);
        layoutDetailsContent = findViewById(R.id.layoutDetailsContent);
        layoutReviewsContent = findViewById(R.id.layoutReviewsContent);

        txtTabOverview = findViewById(R.id.txtTabOverview);
        txtTabPhotos = findViewById(R.id.txtTabPhotos);
        txtTabDetails = findViewById(R.id.txtTabDetails);
        txtTabReviews = findViewById(R.id.txtTabReviews);

        imgChiTiet = findViewById(R.id.imgChiTiet);
        btnBackCard = findViewById(R.id.btnBackCard);

        txtTenChiTiet = findViewById(R.id.txtTenChiTiet);
        txtGiaChiTiet = findViewById(R.id.txtGiaChiTiet);
        txtQuocGiaChiTiet = findViewById(R.id.txtQuocGiaChiTiet);
        txtMoTaChiTiet = findViewById(R.id.txtMoTaChiTiet);
        txtSoSaoNhanXet = findViewById(R.id.txtSoSaoNhanXet);

        txtDuration = findViewById(R.id.txtDuration);
        txtWeather = findViewById(R.id.txtWeather);
        txtGuide = findViewById(R.id.txtGuide);
        txtSanBay = findViewById(R.id.txtSanBay);
        txtKhachSan = findViewById(R.id.txtKhachSan);
        txtDetailsText = findViewById(R.id.txtDetailsText);


        btnFavoriteCard = findViewById(R.id.btnFavoriteCard);
        btnWatchVideoCard = findViewById(R.id.btnSelectDays);
    }
}