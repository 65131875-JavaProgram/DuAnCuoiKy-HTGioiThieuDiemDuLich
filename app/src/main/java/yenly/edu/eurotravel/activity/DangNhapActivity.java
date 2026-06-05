package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import yenly.edu.eurotravel.R;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edtEmailDangNhap, edtMatKhauDangNhap;
    private Button btnNutDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        AnhXa();

        btnNutDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangNhap();
            }
        });
    }

    private void xuLyDangNhap() {
        String email = edtEmailDangNhap.getText().toString().trim();
        String matKhau = edtMatKhauDangNhap.getText().toString().trim();

        if (email.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ Email và Mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void AnhXa() {
        edtEmailDangNhap = findViewById(R.id.edtEmailDN);
        edtMatKhauDangNhap = findViewById(R.id.edtMatKhauDN);
        btnNutDangNhap = findViewById(R.id.btnNutDangNhap);
    }
}