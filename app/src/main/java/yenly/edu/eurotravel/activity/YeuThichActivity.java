package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.adapter.ChuyenDiAdapter;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class YeuThichActivity extends AppCompatActivity {

    private RecyclerView rvYeuThich;
    private ChuyenDiAdapter adapter;
    private List<ChuyenDi> danhSachYeuThich = new ArrayList<>();
    private CardView btnBackCardYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);

        rvYeuThich = findViewById(R.id.rvYeuThich);
        btnBackCardYeuThich = findViewById(R.id.btnBackCardYeuThich);
        List<ChuyenDi> danhSachGoc = (List<ChuyenDi>) getIntent().getSerializableExtra("danh_sach_goc");

        if (danhSachGoc != null) {
            android.content.SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);

            for (ChuyenDi cd : danhSachGoc) {
                boolean isFav = pref.getBoolean(cd.getTenDiaDiem(), false);
                if (isFav) {
                    danhSachYeuThich.add(cd);
                }
            }
        }
        rvYeuThich.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChuyenDiAdapter(danhSachYeuThich);
        rvYeuThich.setAdapter(adapter);
        adapter.locDuLieu("");
        btnBackCardYeuThich.setOnClickListener(v -> finish());
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<ChuyenDi> danhSachGoc = (List<ChuyenDi>) getIntent().getSerializableExtra("danh_sach_goc");
        if (danhSachGoc != null) {
            danhSachYeuThich.clear();
            android.content.SharedPreferences pref = getSharedPreferences("YeuThichPrefs", MODE_PRIVATE);
            for (ChuyenDi cd : danhSachGoc) {
                if (pref.getBoolean(cd.getTenDiaDiem(), false)) {
                    danhSachYeuThich.add(cd);
                }
            }
            if (adapter != null) {
                adapter.locDuLieu("");
            }
        }
    }
}