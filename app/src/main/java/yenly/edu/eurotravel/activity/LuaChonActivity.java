package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import yenly.edu.eurotravel.R;

public class LuaChonActivity extends AppCompatActivity {
    private Button btnChuyenDangNhap, btnChuyenDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lua_chon);
        AnhXa();

        btnChuyenDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LuaChonActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });

        btnChuyenDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LuaChonActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AnhXa() {
        btnChuyenDangNhap = findViewById(R.id.btnChuyenDangNhap);
        btnChuyenDangKy = findViewById(R.id.btnChuyenDangKy);
    }
}