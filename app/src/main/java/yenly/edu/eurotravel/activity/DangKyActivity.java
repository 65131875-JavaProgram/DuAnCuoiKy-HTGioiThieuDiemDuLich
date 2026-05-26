package yenly.edu.eurotravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import yenly.edu.eurotravel.R;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtTenNguoiDung, edtEmailDK, edtMatKhauDK, edtXacNhanMK;
    private Button btnNutDangKy;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
        mAuth = FirebaseAuth.getInstance();

        btnNutDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangKyTaiKhoan();
            }
        });
    }

    private void xuLyDangKyTaiKhoan() {
        String ten = edtTenNguoiDung.getText().toString().trim();
        String email = edtEmailDK.getText().toString().trim();
        String matKhau = edtMatKhauDK.getText().toString().trim();
        String xacNhan = edtXacNhanMK.getText().toString().trim();

        if (ten.isEmpty() || email.isEmpty() || matKhau.isEmpty() || xacNhan.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (matKhau.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải từ 6 ký tự trở lên!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!matKhau.equals(xacNhan)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, matKhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = task.getResult().getUser().getUid();

                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://eurotravel-471ba-default-rtdb.firebaseio.com");
                            DatabaseReference databaseReference = database.getReference("USERS");

                            HashMap<String, Object> userMap = new HashMap<>();
                            userMap.put("tenNguoiDung", ten);
                            userMap.put("email", email);

                            databaseReference.child(userId).setValue(userMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> taskDb) {
                                            if (taskDb.isSuccessful()) {
                                                Toast.makeText(DangKyActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                                Toast.makeText(DangKyActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

// Dán 3 dòng này vào đây nha Ly:
                                                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                                                startActivity(intent);
                                                finish();
                                                finish();
                                            } else {
                                                Toast.makeText(DangKyActivity.this, "Lỗi lưu Database: " + taskDb.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(DangKyActivity.this, "Lỗi tạo tài khoản: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void AnhXa() {
        edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
        edtEmailDK = findViewById(R.id.edtEmailDK);
        edtMatKhauDK = findViewById(R.id.edtMatKhauDK);
        edtXacNhanMK = findViewById(R.id.edtXacNhanMK);
        btnNutDangKy = findViewById(R.id.btnNutDangKy);
    }
}