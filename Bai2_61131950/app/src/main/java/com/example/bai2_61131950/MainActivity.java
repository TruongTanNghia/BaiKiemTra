package com.example.bai2_61131950;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button them, sua, xoa ;
    EditText tacgia, baitho, idbaitho;
    DBHelper dbHelper ;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_main);
        tacgia = findViewById(R.id.tentacgia);
        baitho = findViewById(R.id.tentacgia) ;
        them = findViewById(R.id.them);
        sua = findViewById(R.id.sua);
        xoa = findViewById(R.id.xoa);
        idbaitho = findViewById(R.id.idbaitho);
        listView = findViewById(R.id.listView);
        List<Tho> listTho = dbHelper.getListTho();
        if(listTho != null ){
            ArrayList<String> list = new ArrayList<>();
            for (Tho t: listTho
            ) {
                list.add("ID: "+ t.getId() + "Tên bài: " + t.getTenBaiTho() +" Tác giả: " + t.getTenTacGia());
            }

            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
            //Gắn vào ListView
            listView.setAdapter(adapter);

        }
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tg = tacgia.getText().toString().trim() ;
                String bt = baitho.getText().toString().trim() ;
                Tho tho = new Tho();
                tho.setTenBaiTho(bt);
                tho.setTenTacGia(tg);
                int res = dbHelper.insert(tho);
                if(res > 0) {
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                }
                List<Tho> listTho = dbHelper.getListTho();

                if(listTho != null ){
                    ArrayList<String> list = new ArrayList<>();
                    for (Tho t: listTho
                         ) {
                        list.add("ID: "+ t.getId() + "Tên bài: " + t.getTenBaiTho() +" Tác giả: " + t.getTenTacGia());
                    }

                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
                    //Gắn vào ListView
                    listView.setAdapter(adapter);

                }
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idbaitho.getText().toString();
                int maChon = Integer.parseInt(id);
                AlertDialog.Builder alBuilder = new AlertDialog.Builder(MainActivity.this);
                alBuilder.setTitle("Xác nhận đề xóa");
                alBuilder.setCancelable(true);
                alBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Xoa(maChon);
                    }
                });
                alBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Hủy yêu cầu xóa!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alBuilder.create();
                alertDialog.show();
            }
        });

    }
    void Xoa(int ma)
    {
        SQLiteDatabase databaseBook = SQLiteDatabase.openOrCreateDatabase("data/data/com.example.bai2_61131950/tho.db",null);

        String[] thamSotruyen={String.valueOf(ma)};
        int kq = databaseBook.delete("baitho", "id=?",thamSotruyen);
        if(kq==0)
            Toast.makeText(this, "Không xóa được", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
}