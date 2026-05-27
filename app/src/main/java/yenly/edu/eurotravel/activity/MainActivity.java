package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.adapter.ChuyenDiAdapter;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChuyenDi;
    private ChuyenDiAdapter adapter;
    private List<ChuyenDi> danhSachChuyenDi;
    private EditText edtSearch;
    private ImageButton btnMoYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChuyenDi = findViewById(R.id.rvChuyenDi);
        edtSearch = findViewById(R.id.edtSearch); // Gọi đúng ID edtSearch
        btnMoYeuThich = findViewById(R.id.btnMoYeuThich);

        danhSachChuyenDi = new ArrayList<>();

        // Khởi tạo danh sách 20 địa điểm khớp chính xác với tên ảnh trong drawable của Ly
        danhSachChuyenDi.add(new ChuyenDi("Paris", "7 Days", "Pháp", R.drawable.paris, 1500, 5.0f, "Kinh đô ánh sáng lãng mạn với tháp Eiffel, bảo tàng Louvre và những dòng sông thơ mộng..."));
        danhSachChuyenDi.add(new ChuyenDi("Prague", "5 Days", "Séc", R.drawable.prague, 1200, 4.5f, "Thành phố cổ kính với những mái nhà màu mái ngói đỏ, cầu Charles và kiến trúc Gothic đặc trưng..."));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Como", "4 Days", "Ý", R.drawable.ho_como1, 1400, 4.8f, "Hồ nước thiên nhiên lãng mạn bậc nhất nước Ý, bao quanh bởi các ngôi làng cổ kính và biệt thự sang trọng."));
        danhSachChuyenDi.add(new ChuyenDi("Santorini", "6 Days", "Hy Lạp", R.drawable.santorini_sunset, 1800, 4.9f, "Hòn đảo thiên đường với những ngôi nhà tường trắng mái vòm xanh và hoàng hôn buông xuống biển tuyệt đẹp."));
        danhSachChuyenDi.add(new ChuyenDi("Rome", "5 Days", "Ý", R.drawable.rome, 1300, 4.6f, "Nơi thời gian ngưng đọng với Đấu trường La Mã cổ đại, đài phun nước Trevi và kho tàng lịch sử vĩ đại."));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Plitvice", "3 Days", "Croatia", R.drawable.plitvice, 950, 4.7f, "Vườn quốc gia sở hữu chuỗi hồ nước với sắc xanh ngọc bích kỳ ảo và các thác nước hùng vĩ."));
        danhSachChuyenDi.add(new ChuyenDi("Bờ biển Amalfi", "7 Days", "Ý", R.drawable.bien_amalfi1, 1650, 4.8f, "Cung đường bờ biển ngoạn mục với những ngôi nhà rực rỡ sắc màu xếp chồng lên nhau vách đá bên đại dương."));
        danhSachChuyenDi.add(new ChuyenDi("Hallstatt", "3 Days", "Áo", R.drawable.hallstatt, 1100, 4.9f, "Ngôi làng cổ tích soi bóng xuống mặt hồ yên ả, bao bọc bởi dãy núi Alps hùng vĩ phủ đầy tuyết trắng."));
        danhSachChuyenDi.add(new ChuyenDi("London", "6 Days", "Anh", R.drawable.london, 1700, 4.5f, "Thủ đô sầm uất xứ sương mù với tháp đồng hồ Big Ben, vòng quay London Eye và dòng sông Thames thơ mộng."));
        danhSachChuyenDi.add(new ChuyenDi("Đỉnh Matterhorn", "5 Days", "Thụy Sĩ", R.drawable.dinh_matterhorn, 2200, 5.0f, "Biểu tượng của dãy Alps Thụy Sĩ, thiên đường cho những ai đam mê trượt tiện và ngắm cảnh núi băng kỳ vĩ."));
        danhSachChuyenDi.add(new ChuyenDi("Barcelona", "5 Days", "Tây Ban Nha", R.drawable.barcelona, 1250, 4.6f, "Thành phố của kiến trúc độc lạ độc quyền bởi kiến trúc sư Gaudi, kết hợp cùng không khí biển sôi động."));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Bled", "3 Days", "Slovenia", R.drawable.ho_bled_slovenia, 850, 4.7f, "Hồ nước đẹp như tranh vẽ với một hòn đảo nhỏ nổi lên ở giữa mang đậm nét thần thoại."));
        danhSachChuyenDi.add(new ChuyenDi("Mont Blanc", "7 Days", "Pháp", R.drawable.mont_blanc_khamphadisan, 1950, 4.8f, "Nóc nhà của Tây Âu, nơi mang lại trải nghiệm cáp treo vượt mây và ngắm toàn cảnh thung lũng tuyết."));
        danhSachChuyenDi.add(new ChuyenDi("Venice", "4 Days", "Ý", R.drawable.venice, 1350, 4.7f, "Thành phố của những con kênh lãng mạn, nơi bạn được ngồi thuyền Gondola len lỏi qua các dãy nhà cổ." ));
        danhSachChuyenDi.add(new ChuyenDi("Algarve", "8 Days", "Bồ Đào Nha", R.drawable.algarve, 1450, 4.6f, "Vùng biển phía Nam với những vòm đá hang động tự nhiên bên bờ biển vàng, nước trong vắt nhìn thấu đáy."));
        danhSachChuyenDi.add(new ChuyenDi("Dolomites", "6 Days", "Ý", R.drawable.dolomites2, 1600, 4.9f, "Dãy núi đá vôi kỳ vĩ hàng đầu thế giới, địa điểm trekking tuyệt vời với những đồng cỏ xanh mướt trải dài."));
        danhSachChuyenDi.add(new ChuyenDi("Amsterdam", "4 Days", "Hà Lan", R.drawable.amsterdam, 1150, 4.5f, "Thành phố của những chiếc xe đạp, những dòng kênh đan xen và cánh đồng hoa tulip rực rỡ sắc màu."));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Geneva", "5 Days", "Thụy Sĩ", R.drawable.ho_geneva, 2000, 4.7f, "Hồ nước ngọt lớn bậc nhất Châu Âu, nơi có vòi rồng phun nước cao ngút trời và không khí trong lành."));
        danhSachChuyenDi.add(new ChuyenDi("Ibiza", "5 Days", "Tây Ban Nha", R.drawable.ibiza, 1550, 4.4f, "Hòn đảo tiệc tùng sôi động bậc nhất thế giới, sở hữu cả những bãi biển hoang sơ tuyệt mỹ đón nắng hè."));
        danhSachChuyenDi.add(new ChuyenDi("Cotswolds", "4 Days", "Anh", R.drawable.london, 1050, 4.6f, "Vùng đồng quê thanh bình với những ngôi nhà bằng đá mật ong và các khu vườn tuyệt đẹp như bước ra từ thơ ca.")); // Tạm dùng ảnh london hoặc một ảnh có sẵn nếu Cotswolds chưa có ảnh riêng nha Ly

        adapter = new ChuyenDiAdapter(danhSachChuyenDi);
        rvChuyenDi.setLayoutManager(new LinearLayoutManager(this));
        rvChuyenDi.setAdapter(adapter);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.locDuLieu(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if (btnMoYeuThich != null) {
            btnMoYeuThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Đang mở danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}