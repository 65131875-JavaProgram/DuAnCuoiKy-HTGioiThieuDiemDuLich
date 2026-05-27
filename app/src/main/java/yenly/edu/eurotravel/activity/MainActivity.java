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

        danhSachChuyenDi.add(new ChuyenDi(
                "Paris",
                "7 Days",
                "Pháp",
                R.drawable.paris,
                1500,
                5.0f,
                "Kinh đô ánh sáng lãng mạn với tháp Eiffel, bảo tàng Louvre và những dòng sông thơ mộng..."
        ));

        danhSachChuyenDi.add(new ChuyenDi(
                "Prague",
                "5 Days",
                "Séc",
                R.drawable.prague,
                1200,
                4.5f,
                "Thành phố cổ kính với những mái nhà màu mái ngói đỏ, cầu Charles và kiến trúc Gothic đặc trưng..."
        ));

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