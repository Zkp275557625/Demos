package com.zkp.demos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zkp.demos.excel.ExcelActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkp
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private List<String> mTitles = new ArrayList<>();
    private MainListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        mListView = findViewById(R.id.listView);
        mTitles.add("Excel");
        mAdapter = new MainListViewAdapter(this, mTitles);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mAdapter.getItem(position)) {
                    case "Excel":
                        startActivity(new Intent(MainActivity.this, ExcelActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
