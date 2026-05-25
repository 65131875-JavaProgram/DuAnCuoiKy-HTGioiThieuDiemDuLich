package yenly.edu.eurotravel;

import java.io.Serializable;
import java.util.HashMap;

public class NguoiDung implements Serializable {
    String tenNguoiDung;
    String email;
    public NguoiDung() {
    }

    public NguoiDung(String tenNguoiDung, String email) {
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public HashMap<String, String> toFirebaseObject(){
        HashMap<String, String> userObject = new HashMap<String, String>();
        userObject.put("tenNguoiDung", tenNguoiDung);
        userObject.put("email", email);
        return userObject;
    }
}