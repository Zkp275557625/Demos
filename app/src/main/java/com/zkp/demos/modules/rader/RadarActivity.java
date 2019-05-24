package com.zkp.demos.modules.rader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zkp.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.modules.rader
 * @time: 2019/5/24 11:14
 * @description:
 */
public class RadarActivity extends AppCompatActivity {

    private RadarView mRadarView;

    private List<String> mTitles;
    private List<Float> mValues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        mRadarView = findViewById(R.id.radarView);
        mTitles = new ArrayList<>();
        mTitles.add("KDA");
        mTitles.add("输出");
        mTitles.add("生存");
        mTitles.add("团战");
        mTitles.add("发育");
        mRadarView.setTitles(mTitles);

        mValues = new ArrayList<>();
        mValues.add(8f);
        mValues.add(9.5f);
        mValues.add(9.5f);
        mValues.add(7.5f);
        mValues.add(9.5f);
        mRadarView.setValues(mValues);

    }
}
