# 🌍 EuroTravel - Ứng dụng Giới Thiệu Địa Điểm Du Lịch Châu Âu

[![Android Studio](https://img.shields.io/badge/Android%20Studio-24292E?style=for-the-badge&logo=android-studio&logoColor=3DDC84)](https://developer.android.com/studio)
[![Groq AI](https://img.shields.io/badge/Groq%20AI-F55036?style=for-the-badge&logo=openai&logoColor=white)](https://console.groq.com/)

**EuroTravel** là một ứng dụng di động chạy trên hệ điều hành Android, được thiết kế nhằm mang lại trải nghiệm khám phá và tìm hiểu văn hóa, địa danh du lịch Châu Âu một cách toàn diện và sống động nhất cho người dùng. Đặc biệt, ứng dụng tích hợp công nghệ Trí tuệ nhân tạo (AI) đột phá giúp giải đáp mọi thắc mắc du lịch trong tích tắc.

---

## 🚀 Các Tính Năng Cốt Lõi

### 🔐 1. Đăng Nhập & Đăng Ký (Bảo mật)
* **Tính năng:** Khởi động với màn hình chào chuyển cảnh mượt mà  cho phép người dùng đăng ký tài khoản mới hoặc đăng nhập để đồng bộ dữ liệu cá nhân.
* **Điểm nổi bật:** Tích hợp hệ thống mã hóa xác thực của **Firebase Authentication** và lưu trữ thông tin tài khoản tự động lên **Firebase Realtime Database** theo thời gian thực.

### 🏔️ 2. Khám Phá Địa Danh (Tab Explore)
* **Tính năng:** Màn hình chính hiển thị danh sách các chuyến đi và danh lam thắng cảnh Châu Âu hot nhất hiện nay. Người dùng có thể tìm kiếm nhanh điểm đến thông qua thanh tìm kiếm thông minh.
* **Điểm nổi bật:** Bộ lọc thời gian thực (`TextWatcher`) hoạt động siêu mượt mà, lọc dữ liệu ngay lập tức khi gõ chữ. Danh sách thẻ bài hiển thị ảnh chất lượng cao thông qua thư viện tối ưu bộ nhớ `Glide`.

### 📄 3. Chi Tiết Hành Trình (Multi-Tab layout)
* **Tính năng:** Trang hiển thị thông tin chuyên sâu của từng địa điểm với cấu trúc giao diện trượt đa tab (Overview, Photos, Details, Reviews). Cung cấp từ giá vé, số ngày đi, cho đến thông tin thời tiết, ngôn ngữ, sân bay và khách sạn.
* **Điểm nổi bật:** Trình xem bộ sưu tập ảnh danh thắng kết hợp mô tả trực quan giúp người dùng có góc nhìn toàn diện nhất về điểm đến trước khi xách balo lên và đi.

### 🤖 4. Trợ Lý Thuyết Minh Ảo (Groq AI Agent)
* **Tính năng:** Không gian trò chuyện riêng tư giúp người dùng đặt câu hỏi, dịch thuật, lên lịch trình chi tiết từng ngày hoặc tìm hiểu sâu về văn hóa lịch sử các nước Châu Âu.
* **Điểm nổi bật:** Kết nối trực tiếp với siêu mô hình **Llama 3.1** qua Groq Cloud API cho tốc độ phản hồi cực nhanh. Giao diện bong bóng chat trực quan, tự động ẩn bàn phím và tự động cuộn xuống tin nhắn mới nhất.

### ❤️ 5. Chuyến Đi Yêu Thích (Favorite Layout)
* **Tính năng:** Nơi lưu trữ riêng biệt các hành trình hoặc quốc gia mà người dùng đã bấm "thả tim" để tiện theo dõi lại sau này.
* **Điểm nổi bật:** Danh sách hiển thị vuông vức, quản lý trạng thái lưu trữ gọn nhẹ thông qua mã nguồn Android Native tối ưu, không gây nặng máy hay lag ứng dụng.
---

## 🛠️ Kiến Trúc Công Nghệ & Thư Viện Sử Dụng

* **Ngôn ngữ phát triển:** Java (Android SDK Native).
* **Giao diện người dùng (UI/UX):** `NestedScrollView`, `CardView`, `RecyclerView`, `RelativeLayout`, `LinearLayout`.
* **Thành phần kết nối Backend:** `firebase-auth`, `firebase-database`.
* **Thư viện tương tác và nạp dữ liệu:**
  * **Glide:** Tối ưu hóa bộ nhớ đệm, tự động kiểm tra tài nguyên hệ thống hoặc nạp ảnh từ URL đường truyền Internet.
  * **OkHttp3:** Xử lý các yêu cầu mạng API HTTP tuần tự và bất đồng bộ.
  * **JSON Org:** Phân tích dữ liệu cấu trúc phản hồi từ máy chủ AI..

---

## 📱 Hình Ảnh Minh Họa Giao Diện
### 🔐 1. Phân Hệ Khởi Động & Xác Thực
<img width="296" height="661" alt="image" src="https://github.com/user-attachments/assets/ca459891-2d81-46d4-8f06-96c72411e0ad" /><img width="293" height="659" alt="image" src="https://github.com/user-attachments/assets/30481bf0-9014-4cf2-941b-3a765ae00990" /><img width="295" height="658" alt="image" src="https://github.com/user-attachments/assets/02c9c4a0-591f-4475-9465-084717001732" /><img width="353" height="791" alt="image" src="https://github.com/user-attachments/assets/1a843a1f-4ce7-4b14-8665-4285273c1f9b" />

### 🏔️ 2. Trung Tâm Khám Phá & Chi Tiết Lịch Trình
<img width="350" height="789" alt="image" src="https://github.com/user-attachments/assets/c6a1922c-b1fc-4bc4-bbe9-f669f7dcef34" /><img width="354" height="786" alt="image" src="https://github.com/user-attachments/assets/17269619-1f4f-402a-b7e7-dac57f1aebff" /><img width="352" height="787" alt="image" src="https://github.com/user-attachments/assets/b727c93f-1eef-40fa-b4dd-bc409b398fe2" /><img width="351" height="788" alt="image" src="https://github.com/user-attachments/assets/54a0ede6-4665-4386-a4a9-7cdc703ff2ed" /><img width="352" height="789" alt="image" src="https://github.com/user-attachments/assets/9d896ba4-51d5-4059-bd1b-79bb35eaefb5" />

### 🤖 3. Phân Hệ Trợ Lý Ảo Groq AI
        <img width="350" height="789" alt="image" src="https://github.com/user-attachments/assets/6890451f-9a4f-4c23-b0b2-c1d2dc4302b4" /><img width="353" height="790" alt="image" src="https://github.com/user-attachments/assets/fca5c023-0654-4a36-8c53-3f02bb0b8218" />

### 📐 4. Phân hệ địa điểm yêu thích cá nhân
                   <img width="348" height="787" alt="image" src="https://github.com/user-attachments/assets/9a3a830d-b801-4767-bbef-02d9c2bb1e6d" />











