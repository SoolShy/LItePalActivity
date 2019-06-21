package com.example.litepalactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button creatData,addData,selectData,updateData,deleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatData = findViewById(R.id.creat_data);
        addData = findViewById(R.id.add_data);
        selectData = findViewById(R.id.select_data);
        updateData = findViewById(R.id.update_data);
        deleteData = findViewById(R.id.delete_data);
        creatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("十年");
                book.setAuthor("陈奕迅");
                book.setPages(500);
                book.setPrice(18.67);
                book.save();
            }
        });
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(10.00);
                book.setPages(400);
                book.updateAll("name = ? and author = ?","十年","陈奕迅");
                /*数据恢复默认值
                 book.setToDefault("pages");
                 book.updateAll();*/

            }
        });
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "price <= ?","10");
            }
        });
        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book: books){
                    Log.d("MainActivity",""+book.getName());
                    Log.d("MainActivity",""+book.getAuthor());
                    Log.d("MainActivity",""+book.getPages());
                    Log.d("MainActivity",""+book.getPrice());
                }
            }
        });
    }
}
