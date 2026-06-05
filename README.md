# 🌍 EuroTravel - Ứng dụng Hướng dẫn Du lịch Châu Âu

[![Android Studio](https://img.shields.io/badge/Android%20Studio-24292E?style=for-the-badge&logo=android-studio&logoColor=3DDC84)](https://developer.android.com/studio)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Groq AI](https://img.shields.io/badge/Groq%20AI-F55036?style=for-the-badge&logo=openai&logoColor=white)](https://console.groq.com/)

**EuroTravel** là một ứng dụng di động chạy trên hệ điều hành Android, được thiết kế nhằm mang lại trải nghiệm khám phá và tìm hiểu văn hóa, địa danh du lịch Châu Âu một cách toàn diện và sống động nhất cho người dùng. Đặc biệt, ứng dụng tích hợp công nghệ Trí tuệ nhân tạo (AI) đột phá giúp giải đáp mọi thắc mắc du lịch trong tích tắc.

---

## 🚀 Tính năng nổi bật

* **Khám phá Châu Âu:** Danh sách các địa danh, thành phố, quốc gia du lịch nổi tiếng tại Châu Âu với thông tin chi tiết.
* **🤖 Thuyết Minh Viên Ảo (AI Guide):** Tính năng Chatbot thông minh tích hợp sâu mô hình ngôn ngữ lớn **Llama 3.1** (thông qua hệ thống Groq Cloud API siêu tốc).
* **Giao diện Chat Messenger:** Trải nghiệm nhắn tin thời gian thực với AI được thiết kế dạng bong bóng chat bo tròn phân cực (Trái/Phải) tương tự Facebook Messenger.
* **Lưu giữ ngữ cảnh:** Giữ nguyên lịch sử cuộc trò chuyện cũ khi người dùng đặt các câu hỏi tiếp theo, tự động cuộn màn hình thông minh xuống tin nhắn mới nhất.

---

## 🛠️ Công nghệ & Thư viện sử dụng

Dự án được xây dựng thuần túy trên nền tảng Native Android:

* **Ngôn ngữ lập trình:** Java
* **Hệ quản trị giao diện:** XML Layouts (sử dụng `ConstraintLayout`, `RelativeLayout`, `ScrollView`)
* **Kết nối mạng (Networking):** [OkHttp3](https://square.github.io/okhttp/) thực thi gửi/nhận yêu cầu mạng bất đồng bộ (Asynchronous Requests) với cấu hình Timeout tối ưu 60 giây.
* **Xử lý dữ liệu:** `org.json` (Bóc tách dữ liệu JSON phản hồi từ AI Server).
* **Đồ họa UI:** Tự động vẽ Dynamic Bubble Chat bằng mã Java thông qua lớp `GradientDrawable`.

---

## 📱 Hình ảnh minh họa (Screenshots)

| Giao diện Chatbot AI |
| :---: |
| <img src="https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=300" width="280"/> <br> *Giao diện bong bóng chat Messenger mượt mà* |

*(Mẹo: Bạn có thể thay thế link ảnh phía trên bằng ảnh chụp màn hình app thực tế của bạn bằng cách tải ảnh lên thư mục `screenshots` trong chính repo này)*

---

## ⚙️ Hướng dẫn cài đặt & Cấu hình

Để chạy thử nghiệm mã nguồn này trên máy tính của bạn, hãy làm theo các bước sau:

### 1. Yêu cầu hệ thống
* Android Studio Jellyfish (hoặc phiên bản mới hơn).
* Android SDK mã API tối thiểu 24+.
* Thiết bị Android thật hoặc Máy ảo (Emulator) có kết nối Internet.

### 2. Tải mã nguồn về máy
```bash
git clone [https://github.com/TÊN_TÀI_KHOẢN_CỦA_BẠN/EuroTravel.git](https://github.com/TÊN_TÀI_KHOẢN_CỦA_BẠN/EuroTravel.git)
