package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import yenly.edu.eurotravel.R;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtTenNguoiDung, edtEmailDangKy, edtMatKhauDangKy, edtXacNhanMatKhau;
    private Button btnNutDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();

        btnNutDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chỗ này sau này mình sẽ gọi lệnh tạo tài khoản Firebase
            }
        });
    }

    private void AnhXa() {
        edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
        edtEmailDangKy = findViewById(R.id.edtEmailDK);
        edtMatKhauDangKy = findViewById(R.id.edtMatKhauDK);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMK);
        btnNutDangKy = findViewById(R.id.btnNutDangKy);
    }
}