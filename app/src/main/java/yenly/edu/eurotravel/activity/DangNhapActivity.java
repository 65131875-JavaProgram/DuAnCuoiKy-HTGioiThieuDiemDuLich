package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                // Chỗ này sau này mình sẽ viết code kết nối Firebase Auth nhé Ly
            }
        });
    }
    private void AnhXa() {
        edtEmailDangNhap = findViewById(R.id.edtEmailDN);
        edtMatKhauDangNhap = findViewById(R.id.edtMatKhauDN);
        btnNutDangNhap = findViewById(R.id.btnNutDangNhap);
    }
}