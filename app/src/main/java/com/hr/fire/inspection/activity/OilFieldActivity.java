package com.hr.fire.inspection.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CompanyAdapter;
import com.hr.fire.inspection.adapter.OilFieldAdapter;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TextSpannableUtil;

import java.util.ArrayList;
import java.util.List;

public class OilFieldActivity extends AppCompatActivity implements View.OnClickListener {
    List<CompanyInfo> dataList;
    private ArrayList<String> list;
    private String infocontcompanyName;
    private ImageView insert_btn;
    private String f_title;
    private String duty;
    private String check_name;
    private String check_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_field);

        //获取Bundle的信息
        Bundle b1 = getIntent().getExtras();
        infocontcompanyName = b1.getString("company_name");
        Log.i("aaaa", "infocontcompanyName = " + infocontcompanyName);

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        f_title = b1.getString("f_title");
        if(f_title.equals("xunjian")){
            getIntentInfo();
        }

        insert_btn = (ImageView) this.findViewById(R.id.insert_btn);
        ImageView iv_finish = (ImageView) this.findViewById(R.id.iv_finish);
        TextView tv_inspection_pro = (TextView) this.findViewById(R.id.tv_inspection_pro);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (infocontcompanyName != null) {
            String srt = new StringBuffer().append("检查所属  >  ").append(infocontcompanyName).append("  >  ").append("请选择油田").toString();
            SpannableString textColor = TextSpannableUtil.showTextColor(srt, "#00A779", srt.length() - 5, srt.length());
            tv_inspection_pro.setText(textColor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(infocontcompanyName);
        Log.i("aaaa", "dataList = " + dataList);


        list = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            CompanyInfo CompanyListItem = dataList.get(i);
            String companyName = CompanyListItem.getOilfieldName();
            if (companyName != null && companyName != "") {
                list.add(companyName);
            }
        }

        ListView oil_list_item = findViewById(R.id.oil_list_item);
        OilFieldAdapter oilFieldAdapter = new OilFieldAdapter(this, this);
        oilFieldAdapter.setData(list);
        oil_list_item.setAdapter(oilFieldAdapter);

        // 监听点击事件
        oil_list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String oil_name = list.get(position);
                Intent intent = new Intent(OilFieldActivity.this, PlatformActivity.class);
                // 跳转时传入下一页面 公司名称 油田名称
                intent.putExtra("company_name", infocontcompanyName);
                intent.putExtra("oil_name", oil_name);
                intent.putExtra("f_title", f_title);
                // 同上  若为消防巡检  则需要传入参数
                if(f_title.equals("xunjian")){
                    intent.putExtra("duty", duty);
                    intent.putExtra("check_name",check_name);
                    intent.putExtra("check_date",check_date);
                }
                startActivity(intent);
            }
        });
        insert_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                AlertDialog.Builder insert_builder = new AlertDialog.Builder(OilFieldActivity.this);
//                insert_builder.setTitle("将要前往添加页面，确认离开?");
//                insert_builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                insert_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(OilFieldActivity.this, OilInsertActivity.class);
//                        intent.putExtra("company_name", infocontcompanyName);
//                        intent.putExtra("f_title", f_title);
//                        startActivity(intent);
//
//                    }
//                });
//                insert_builder.show();
                Intent intent = new Intent(OilFieldActivity.this, OilInsertActivity.class);
                intent.putExtra("company_name", infocontcompanyName);
                intent.putExtra("f_title", f_title);
                startActivity(intent);

            }
        });
    }

    private void getIntentInfo() {
        Bundle b = getIntent().getExtras();
        f_title = b.getString("f_title");
        duty = b.getString("duty");
        check_name = b.getString("check_name");
        check_date = b.getString("check_date");
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.del_btn:   //lv条目中 iv_del
                final int position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转

                //点击删除按钮之后，给出dialog提示
                AlertDialog.Builder builder = new AlertDialog.Builder(OilFieldActivity.this);
                builder.setTitle("确认删除?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oilValue = list.get(position);
                        String type = "oilfield";
                        long ret = ServiceFactory.getCompanyInfoService().deleteData(oilValue, type);
                        if (ret == 0) {
                            Toast.makeText(OilFieldActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                            dataList = ServiceFactory.getCompanyInfoService().getOilfieldList(infocontcompanyName);
                            list = new ArrayList<>();

                            for (int i = 0; i < dataList.size(); i++) {
                                CompanyInfo CompanyListItem = dataList.get(i);
                                String companyName = CompanyListItem.getOilfieldName();
                                if (companyName != null && companyName != "") {
                                    list.add(companyName);
                                }
                            }

                            ListView oil_list_item = findViewById(R.id.oil_list_item);

                            OilFieldAdapter oilFieldAdapter = new OilFieldAdapter(OilFieldActivity.this, OilFieldActivity.this);
                            oilFieldAdapter.setData(list);
                            oil_list_item.setAdapter(oilFieldAdapter);
                        } else {
                            Toast.makeText(OilFieldActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.edit_btn:   //lv条目中 iv_del
                final int edit_position = (int) v.getTag(); //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转
                String NameItem = list.get(edit_position);
                Intent intent = new Intent(OilFieldActivity.this, OilOperationActivity.class);
                intent.putExtra("company_name", infocontcompanyName);
                intent.putExtra("oil_name", NameItem);
                startActivity(intent);
                break;

        }
    }
}