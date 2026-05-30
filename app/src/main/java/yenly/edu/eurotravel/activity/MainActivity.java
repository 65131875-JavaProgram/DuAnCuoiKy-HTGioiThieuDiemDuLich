package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(yenly.edu.eurotravel.R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        themChuyenDi(db, "Paris", "7 Days", "Pháp", 1500, "Kinh đô ánh sáng lãng mạn với tháp Eiffel, bảo tàng Louvre và những dòng sông thơ mộng...");
        themChuyenDi(db, "Prague", "5 Days", "Séc", 1200, "Thành phố cổ kính với những mái nhà màu mái ngói đỏ, cầu Charles và kiến trúc Gothic đặc trưng...");
        themChuyenDi(db, "Hồ Como", "4 Days", "Ý", 1400, "Hồ nước thiên nhiên lãng mạn bậc nhất nước Ý, bao quanh bởi các ngôi làng cổ kính và biệt thự sang trọng.");
        themChuyenDi(db, "Santorini", "6 Days", "Hy Lạp", 1800, "Hòn đảo thiên đường với những ngôi nhà tường trắng mái vòm xanh và hoàng hôn buông xuống biển tuyệt đẹp.");
        themChuyenDi(db, "Rome", "5 Days", "Ý", 1300, "Nơi thời gian ngưng đọng với Đấu trường La Mã cổ đại, đài phun nước Trevi và kho tàng lịch sử vĩ đại.");
        themChuyenDi(db, "Hồ Plitvice", "3 Days", "Croatia", 950, "Vườn quốc gia sở hữu chuỗi hồ nước với sắc xanh ngọc bích kỳ ảo và các thác nước hùng vĩ.");
        themChuyenDi(db, "Bờ biển Amalfi", "7 Days", "Ý", 1650, "Cung đường bờ biển ngoạn mục với những ngôi nhà rực rỡ sắc màu xếp chồng lên nhau vách đá bên đại dương.");
        themChuyenDi(db, "Hallstatt", "3 Days", "Áo", 1100, "Ngôi làng cổ tích soi bóng xuống mặt hồ yên ả, bao bọc bởi dãy núi Alps hùng vĩ phủ đầy tuyết trắng.");
        themChuyenDi(db, "London", "6 Days", "Anh", 1700, "Thủ đô sầm uất xứ sương mù với tháp đồng hồ Big Ben, vòng quay London Eye và dòng sông Thames thơ mộng.");
        themChuyenDi(db, "Đỉnh Matterhorn", "5 Days", "Thụy Sĩ", 2200, "Biểu tượng của dãy Alps Thụy Sĩ, thiên đường cho những ai đam mê trượt tiện và ngắm cảnh núi băng kỳ vĩ.");
        themChuyenDi(db, "Barcelona", "5 Days", "Tây Ban Nha", 1250, "Thành phố của kiến trúc độc lạ độc quyền bởi kiến trúc sư Gaudi, kết hợp cùng không khí biển sôi động.");
        themChuyenDi(db, "Hồ Bled", "3 Days", "Slovenia", 850, "Hồ nước đẹp như tranh vẽ với một hòn đảo nhỏ nổi lên ở giữa mang đậm nét thần thoại.");
        themChuyenDi(db, "Mont Blanc", "7 Days", "Pháp", 1950, "Nóc nhà của Tây Âu, nơi mang lại trải nghiệm cáp treo vượt mây và ngắm toàn cảnh thung lũng tuyết.");
        themChuyenDi(db, "Venice", "4 Days", "Ý", 1350, "Thành phố của những con kênh lãng mạn, nơi bạn được ngồi thuyền Gondola len lỏi qua các dãy nhà cổ.");
        themChuyenDi(db, "Algarve", "8 Days", "Bồ Đào Nha", 1450, "Vùng biển phía Nam với những vòm đá hang động tự nhiên bên bờ biển vàng, nước trong vắt nhìn thấu đáy.");
        themChuyenDi(db, "Dolomites", "6 Days", "Ý", 1600, "Dãy núi đá vôi kỳ vĩ hàng đầu thế giới, địa điểm trekking tuyệt vời với những đồng cỏ xanh mướt trải dài.");
        themChuyenDi(db, "Amsterdam", "4 Days", "Hà Lan", 1150, "Thành phố của những chiếc xe đạp, những dòng kênh đan xen và cánh đồng hoa tulip rực rỡ sắc màu.");
        themChuyenDi(db, "Hồ Geneva", "5 Days", "Thụy Sĩ", 2000, "Hồ nước ngọt lớn bậc nhất Châu Âu, nơi có vòi rồng phun nước cao ngút trời và không khí trong lành.");
        themChuyenDi(db, "Ibiza", "5 Days", "Tây Ban Nha", 1550, "Hòn đảo tiệc tùng sôi động bậc nhất thế giới, sở hữu cả những bãi biển hoang sơ tuyệt mỹ đón nắng hè.");
        themChuyenDi(db, "Cotswolds", "4 Days", "Anh", 1050, "Vùng đồng quê thanh bình với những ngôi nhà bằng đá mật ong và các khu vườn tuyệt đẹp như bước ra từ thơ ca.");

        Toast.makeText(this, "Đang tự động nạp dữ liệu... Ly hãy đợi 5 giây rồi kiểm tra trên Web nhé!", Toast.LENGTH_LONG).show();
    }
    private void themChuyenDi(FirebaseFirestore db, String ten, String duration, String loai, int gia, String mota) {
        Map<String, Object> data = new HashMap<>();
        data.put("tenDiaDiem", ten);
        data.put("duration", duration);
        data.put("loaiHinh", loai);
        data.put("giaTien", gia);
        data.put("moTa", mota);

        db.collection("ChuyenDi").add(data);
    }
}