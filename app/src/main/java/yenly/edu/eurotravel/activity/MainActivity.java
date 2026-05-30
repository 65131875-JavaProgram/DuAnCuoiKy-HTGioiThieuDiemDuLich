package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        edtSearch = findViewById(R.id.edtSearch);
        btnMoYeuThich = findViewById(R.id.btnMoYeuThich);

        danhSachChuyenDi = new ArrayList<>();
        adapter = new ChuyenDiAdapter(danhSachChuyenDi);
        rvChuyenDi.setLayoutManager(new LinearLayoutManager(this));
        rvChuyenDi.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ChuyenDi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            danhSachChuyenDi.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tenDiaDiem = document.getString("tenDiaDiem");
                                String duration = document.getString("duration");
                                String loaiHinh = document.getString("loaiHinh");
                                String moTa = document.getString("moTa");
                                int giaTien = 0;
                                Object giaObj = document.get("giaTien");
                                if (giaObj instanceof Long) {
                                    giaTien = ((Long) giaObj).intValue();
                                } else if (giaObj instanceof String) {
                                    try {
                                        giaTien = Integer.parseInt((String) giaObj);
                                    } catch (NumberFormatException e) {
                                        giaTien = 0;
                                    }
                                }
                                int hinhAnhId = R.drawable.paris;
                                if (tenDiaDiem != null) {
                                    String tenXuly = tenDiaDiem.toLowerCase().trim();
                                    if (tenXuly.contains("paris")) hinhAnhId = R.drawable.paris;
                                    else if (tenXuly.contains("prague")) hinhAnhId = R.drawable.prague;
                                    else if (tenXuly.contains("como")) hinhAnhId = R.drawable.ho_como1;
                                    else if (tenXuly.contains("santorini")) hinhAnhId = R.drawable.santorini_sunset;
                                    else if (tenXuly.contains("rome")) hinhAnhId = R.drawable.rome;
                                    else if (tenXuly.contains("plitvice")) hinhAnhId = R.drawable.plitvice;
                                    else if (tenXuly.contains("amalfi")) hinhAnhId = R.drawable.bien_amalfi1;
                                    else if (tenXuly.contains("hallstatt")) hinhAnhId = R.drawable.hallstatt;
                                    else if (tenXuly.contains("london")) hinhAnhId = R.drawable.london;
                                    else if (tenXuly.contains("matterhorn")) hinhAnhId = R.drawable.dinh_matterhorn;
                                    else if (tenXuly.contains("barcelona")) hinhAnhId = R.drawable.barcelona;
                                    else if (tenXuly.contains("bled")) hinhAnhId = R.drawable.ho_bled_slovenia;
                                    else if (tenXuly.contains("blanc")) hinhAnhId = R.drawable.mont_blanc_khamphadisan;
                                    else if (tenXuly.contains("venice")) hinhAnhId = R.drawable.venice;
                                    else if (tenXuly.contains("algarve")) hinhAnhId = R.drawable.algarve;
                                    else if (tenXuly.contains("dolomites")) hinhAnhId = R.drawable.dolomites2;
                                    else if (tenXuly.contains("amsterdam")) hinhAnhId = R.drawable.amsterdam;
                                    else if (tenXuly.contains("geneva")) hinhAnhId = R.drawable.ho_geneva;
                                    else if (tenXuly.contains("ibiza")) hinhAnhId = R.drawable.ibiza;
                                }

                                float diemDanhGia = 5.0f;
                                ChuyenDi cd = new ChuyenDi(tenDiaDiem, duration, loaiHinh, hinhAnhId, giaTien, diemDanhGia, moTa);
                                danhSachChuyenDi.add(cd);
                            }
                            adapter.locDuLieu("");
                            Toast.makeText(MainActivity.this, "Đã hiển thị thành công " + danhSachChuyenDi.size() + " chuyến đi!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Lỗi tải dữ liệu Firestore: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        btnMoYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YeuThichActivity.class);
                intent.putExtra("danh_sach_goc", (java.io.Serializable) danhSachChuyenDi);
                startActivity(intent);
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.locDuLieu(s.toString());
            }
        });
    }
}