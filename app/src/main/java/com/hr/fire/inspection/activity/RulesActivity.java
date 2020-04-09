package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageButton;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.LawItemAdapter;

import java.util.ArrayList;


public class RulesActivity extends AppCompatActivity {

    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        String str_con = "火灾自动报警系统设计规范";
        String str_con2 = "自动喷水灭火系统设计规范";
        String str_con3 = "可燃气体探测报警系统设计规范";

        mList = new ArrayList<>();
        mList.add(str_con);
        mList.add(str_con2);
        mList.add(str_con3);
        //列表
        ListView main_list = findViewById(R.id.main_list);
        LawItemAdapter lawItemAdapter = new LawItemAdapter(this);
        lawItemAdapter.setData(mList);
        main_list.setAdapter(lawItemAdapter);

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position);
                //跳转详情
                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(id));
                bundle.putString("context",str);
                Intent intent = new Intent(RulesActivity.this, RuleContentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
