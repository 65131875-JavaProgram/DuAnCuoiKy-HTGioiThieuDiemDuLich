package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.adapter.ChuyenDiAdapter;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChuyenDi;
    private ChuyenDiAdapter chuyenDiAdapter;
    private List<ChuyenDi> mListChuyenDi;
    private DatabaseReference mDatabase;

    private ImageButton btnMoYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChuyenDi = findViewById(R.id.rvChuyenDi);
        btnMoYeuThich = findViewById(R.id.btnMoYeuThich);

        rvChuyenDi.setLayoutManager(new LinearLayoutManager(this));
        rvChuyenDi.setHasFixedSize(true);

        mListChuyenDi = new ArrayList<>();
        chuyenDiAdapter = new ChuyenDiAdapter(mListChuyenDi);
        rvChuyenDi.setAdapter(chuyenDiAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("danh_sach_chuyen_di");
        docDuLieuTuFirebase();

        if (btnMoYeuThich != null) {
            btnMoYeuThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, YeuThichActivity.class);
                    intent.putExtra("danh_sach_goc", (java.io.Serializable) mListChuyenDi);
                    startActivity(intent);
                }
            });
        }
    }

    private void docDuLieuTuFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListChuyenDi.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChuyenDi chuyenDi = dataSnapshot.getValue(ChuyenDi.class);
                    if (chuyenDi != null) {
                        mListChuyenDi.add(chuyenDi);
                    }
                }
                chuyenDiAdapter.setDanhSachChuyenDi(mListChuyenDi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}