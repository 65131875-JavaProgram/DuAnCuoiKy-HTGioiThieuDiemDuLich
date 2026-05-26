package yenly.edu.eurotravel.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import yenly.edu.eurotravel.R;
import yenly.edu.eurotravel.adapter.ChuyenDiAdapter;
import yenly.edu.eurotravel.dulieu.ChuyenDi;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChuyenDi;
    private ChuyenDiAdapter adapter;
    private List<ChuyenDi> danhSachChuyenDi;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChuyenDi = findViewById(R.id.rvChuyenDi);
        searchView = findViewById(R.id.searchViewChuyenDi);

        danhSachChuyenDi = new ArrayList<>();

        danhSachChuyenDi.add(new ChuyenDi("Paris, Pháp", "Mùa xuân 2023 - 5 day", "thành phố", R.drawable.paris));
        danhSachChuyenDi.add(new ChuyenDi("Dãy Alps, Thụy Sĩ", "Mùa đông 2022 - 7 day", "núi", R.drawable.dayalps));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Como, Ý", "Mùa hè 2021 - 4 day", "hồ", R.drawable.ho_como1));
        danhSachChuyenDi.add(new ChuyenDi("Santorini, Hy Lạp", "Mùa hè 2023 - 6 day", "biển", R.drawable.santorini_sunset));
        danhSachChuyenDi.add(new ChuyenDi("Rome, Ý", "Mùa thu 2022 - 5 day", "thành phố", R.drawable.rome));

        danhSachChuyenDi.add(new ChuyenDi("Hồ Plitvice, Croatia", "Mùa hè 2019 - 3 day", "hồ", R.drawable.plitvice));
        danhSachChuyenDi.add(new ChuyenDi("Bờ biển Amalfi, Ý", "Mùa hè 2020 - 7 day", "biển", R.drawable.bien_amalfi1));
        danhSachChuyenDi.add(new ChuyenDi("Hallstatt, Áo", "Mùa đông 2021 - 3 day", "hồ", R.drawable.hallstatt));
        danhSachChuyenDi.add(new ChuyenDi("London, Anh", "Mùa thu 2023 - 6 day", "thành phố", R.drawable.london));
        danhSachChuyenDi.add(new ChuyenDi("Đỉnh Matterhorn, Thụy Sĩ", "Mùa đông 2020 - 5 day", "núi", R.drawable.dinh_matterhorn));

        danhSachChuyenDi.add(new ChuyenDi("Barcelona, Tây Ban Nha", "Mùa hè 2022 - 5 day", "biển", R.drawable.barcelona));
        danhSachChuyenDi.add(new ChuyenDi("Prague, Séc", "Mùa xuân 2018 - 4 day", "thành phố", R.drawable.prague));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Bled, Slovenia", "Mùa thu 2021 - 3 day", "hồ", R.drawable.ho_bled_slovenia));
        danhSachChuyenDi.add(new ChuyenDi("Mont Blanc, Pháp", "Mùa đông 2023 - 7 day", "núi", R.drawable.mont_blanc_khamphadisan));
        danhSachChuyenDi.add(new ChuyenDi("Venice, Ý", "Mùa xuân 2019 - 4 day", "thành phố", R.drawable.venice));

        danhSachChuyenDi.add(new ChuyenDi("Algarve, Bồ Đào Nha", "Mùa hè 2023 - 8 day", "biển", R.drawable.algarve));
        danhSachChuyenDi.add(new ChuyenDi("Dolomites, Ý", "Mùa thu 2020 - 6 day", "núi", R.drawable.dolomites2));
        danhSachChuyenDi.add(new ChuyenDi("Amsterdam, Hà Lan", "Mùa xuân 2022 - 4 day", "thành phố", R.drawable.amsterdam));
        danhSachChuyenDi.add(new ChuyenDi("Hồ Geneva, Thụy Sĩ", "Mùa hè 2018 - 5 day", "hồ", R.drawable.ho_geneva));
        danhSachChuyenDi.add(new ChuyenDi("Ibiza, Tây Ban Nha", "Mùa hè 2021 - 5 day", "biển", R.drawable.ibiza));
        adapter = new ChuyenDiAdapter(danhSachChuyenDi);
        rvChuyenDi.setLayoutManager(new LinearLayoutManager(this));
        rvChuyenDi.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.locDuLieu(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.locDuLieu(newText);
                return false;
            }
        });
    }
}