package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;


import com.hr.fire.inspection.adapter.XJGasColumnAdapter;
import com.hr.fire.inspection.adapter.XJGasContentAdapter;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.impl.YCCamera;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.utils.CamaraUtil;
import com.hr.fire.inspection.utils.FileRoute;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.ToastUtil;
import com.hr.fire.inspection.view.tableview.HListViewScrollView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 巡检气体灭火系统
public class XJGasFireSystem extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_finish;
    private TextView tv_inspection_pro;
    private TextView iv_save;
    private TextView tv_xh_title;
    private ImageView iv_add_table;
    private HListViewScrollView chs_datagroup;
    private RecyclerView rl_firstColumn;
    private RecyclerView rl_content;
    private long systemId;
    private long companyInfoId;
    private String str_title;
    private String duty;
    private String check_name;
    private String check_date;
    Date parse_check_date = null;
    private String srt_date;
    private List<InspectionResult> inspectionResults;
    private InspectionServiceImpl service;
    private XJGasColumnAdapter firstColumnApapter;
    private XJGasContentAdapter contentApapter;
    private TextView tvInspectionPro;
    private File fileNew = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final int TAKE_PHOTO = 1;//拍照
    private int imgPostion = -1;
    private Context mContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xj_gas_fire_system);
        mContent = getApplicationContext();
        getIntentData();
        initData();
        initView();
        initAdapter();
        setSlide();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        systemId = intent.getLongExtra("systemId", -1);
        companyInfoId = intent.getLongExtra("platform_id", -1);
        str_title = intent.getStringExtra("str_title"); //系统名称 :高压二氧化碳灭火系统
        duty = intent.getStringExtra("duty");  // 专业
        check_name = intent.getStringExtra("check_name"); // 检查人
        check_date = intent.getStringExtra("check_date"); //用户选择的时间
        //测试用, 因为前面传过来的时间格式有问题
        check_date = "2020-04-23 18:21";

        try {
            //这个解析方式是没有问题的 ,需要保证前面传入的数据是 2020-04-23 18:21 格式
            parse_check_date = sdf.parse(check_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        srt_date = intent.getStringExtra("srt_Date");   //检查日期,用户没选择,就是表示是新建
    }

    private void initData() {
        service = new InspectionServiceImpl();
        inspectionResults = service.getInspectionData(companyInfoId, systemId, parse_check_date);
        Log.d("dong", "inspectionData == " + inspectionResults.size());
        if (inspectionResults != null && inspectionResults.size() != 0) {
            Log.d("dong", "inspectionData == -----   " + inspectionResults.get(0).toString());
        }
    }

    private void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        tv_inspection_pro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        tv_xh_title = findViewById(R.id.tv_xh_title);
        iv_add_table = findViewById(R.id.iv_add_table);
        chs_datagroup = findViewById(R.id.chs_datagroup); //横向滑动的vire
        rl_firstColumn = findViewById(R.id.rl_firstColumn);   //第一列
        rl_content = findViewById(R.id.rl_content);     //内容
        iv_finish.setOnClickListener(this);
        iv_add_table.setOnClickListener(this);
        iv_save.setOnClickListener(this);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        String text = new StringBuilder().append("消防巡检>气体灭火系统").toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 0, text.length());
        tvInspectionPro.setText(showTextColor);
    }

    @SuppressLint("WrongConstant")
    private void initAdapter() {
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_firstColumn.setLayoutManager(mLayoutManager);
        firstColumnApapter = new XJGasColumnAdapter(this, inspectionResults);
        rl_firstColumn.setAdapter(firstColumnApapter);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        rl_content.setLayoutManager(mLayoutManager2);
        contentApapter = new XJGasContentAdapter(this, inspectionResults);
        rl_content.setAdapter(contentApapter);

        contentApapter.setmYCCamera(new YCCamera() {
            @Override
            public void startCamera(int postion) {
                imgPostion = postion;
               openSysCamera(mContent);
            }
        });
        //刷新序号列表
        contentApapter.setDeleteRefresh(new XJGasContentAdapter.RemoveXH() {
            @Override
            public void deleteRefresh(int postion) {
                firstColumnApapter.notifyDataSetChanged();
            }
        });
    }

    //设置同步滑动
    private void setSlide() {
        rl_firstColumn.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_content.scrollBy(dx, dy);
                }
            }
        });
        rl_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rl_firstColumn.scrollBy(dx, dy);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.iv_add_table:
                addItemView();
                break;
            case R.id.iv_save:
                saveToUpdara();
                break;
        }
    }

    /**
     * 一  点击增加:分两种情况
     * 1.是没有数据的时候,  默认给用户增加一条数据,插入到数据库
     * 2.有数据的时候,  默认给用户增加与最后一条数据一样的数据
     * 二:增加数据成功后,刷新适配器adapter
     */
    private void addItemView() {
        if (rl_firstColumn != null && rl_content != null && inspectionResults != null) {
            InspectionResult result = new InspectionResult();
            if (inspectionResults.size() != 0) {
                Log.d("dong", "默认有数据==之后吧");
                //有数据的时候,拿到最后一条数据进行填充
                InspectionResult item = inspectionResults.get(inspectionResults.size() - 1);
                result.setProfession(item.getProfession());
                result.setCheckPerson(item.getCheckPerson());
                result.setCheckDate(item.getCheckDate());
                result.setDescription(item.getDescription());
                result.setImgPath(item.getImgPath());
                result.setParam1(item.getParam1());
                result.setParam2(item.getParam2());
                result.setParam3(item.getParam3());
                result.setParam4(item.getParam4());
                result.setParam5(item.getParam5());
                result.setParam6(item.getParam6());
                result.setParam7(item.getParam7());
                result.setParam8(item.getParam8());
                result.setParam9(item.getParam9());
                result.setParam10(item.getParam10());
                result.setParam11(item.getParam11());
                result.setParam12(item.getParam12());
                result.setParam13(item.getParam13());
                result.setParam14(item.getParam14());
                result.setParam15(item.getParam15());
                result.setParam16(item.getParam16());
                result.setParam17(item.getParam17());
                result.setParam18(item.getParam18());
                result.setParam19(item.getParam19());
                result.setParam20(item.getParam20());
                result.setParam21(item.getParam21());
                result.setParam22(item.getParam22());
                result.setParam23(item.getParam23());
                result.setParam24(item.getParam24());
                result.setParam25(item.getParam25());
                result.setParam26(item.getParam26());
            } else {
                result.setProfession(duty);
                result.setCheckPerson(check_name);
                result.setCheckDate(parse_check_date);
                result.setDescription("暂无");
                result.setImgPath("暂无图片");
                result.setParam1("请填写");
                result.setParam2("请填写");
                result.setParam3("是");
                result.setParam4("是");
                result.setParam5("是");
                result.setParam6("是");
                result.setParam7("是");
                result.setParam8("否");
                result.setParam9("否");
                result.setParam10("是");
                result.setParam11("是");
                result.setParam12("是");
                result.setParam13("是");
                result.setParam14("是");
                result.setParam15("否");
                result.setParam16("否");
                result.setParam17("否");
                result.setParam18("是");
                result.setParam19("是");
                result.setParam20("是");
                result.setParam21("是");
                result.setParam22("是");
                result.setParam23("否");
                result.setParam24("否");
                result.setParam25("否");
                result.setParam26("请输入");
            }
            long l = service.insertInspectionData(result, companyInfoId, systemId, parse_check_date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l == 0) {
                inspectionResults = service.getInspectionData(companyInfoId, systemId, parse_check_date);
                Log.d("dong", "inspectionResults=  " + inspectionResults.size() + "  " + inspectionResults.get(0).getParam2());
                firstColumnApapter.setNewData(inspectionResults);
                contentApapter.setNewData(inspectionResults);
            } else {
                ToastUtil.show(this, "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 流程说明:
     * 我们在点击加号的时候, 就从数据量库中插入了数据,
     * 我们在Updara的时候先查询,如果没有没有数据就说明无数据更新  , 如果有就进行更新,
     */
    private void saveToUpdara() {
        if (inspectionResults == null || inspectionResults.size() == 0) {
            Toast.makeText(this, "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        int itemCount = rl_content.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rl_content.getChildAt(i);

            EditText et_gas1 = childAt.findViewById(R.id.et_gas1);
            EditText et_gas2 = childAt.findViewById(R.id.et_gas2);
            EditText et_fire10 = childAt.findViewById(R.id.et_fire10);
            TextView tv_gas1 = childAt.findViewById(R.id.tv_gas1);
            TextView tv_gas2 = childAt.findViewById(R.id.tv_gas2);
            TextView tv_gas3 = childAt.findViewById(R.id.tv_gas3);
            TextView tv_gas4 = childAt.findViewById(R.id.tv_gas4);
            TextView tv_gas5 = childAt.findViewById(R.id.tv_gas5);
            TextView tv_gas6 = childAt.findViewById(R.id.tv_gas6);
            TextView tv_gas7 = childAt.findViewById(R.id.tv_gas7);
            TextView tv_gas8 = childAt.findViewById(R.id.tv_gas8);
            TextView tv_gas9 = childAt.findViewById(R.id.tv_gas9);
            TextView tv_gas10 = childAt.findViewById(R.id.tv_gas10);
            TextView tv_gas11 = childAt.findViewById(R.id.tv_gas11);
            TextView tv_gas12 = childAt.findViewById(R.id.tv_gas12);
            TextView tv_gas13 = childAt.findViewById(R.id.tv_gas13);
            TextView tv_gas14 = childAt.findViewById(R.id.tv_gas14);
            TextView tv_gas15 = childAt.findViewById(R.id.tv_gas15);
            TextView tv_gas16 = childAt.findViewById(R.id.tv_gas16);
            TextView tv_gas17 = childAt.findViewById(R.id.tv_gas17);
            TextView tv_gas18 = childAt.findViewById(R.id.tv_gas18);
            TextView tv_gas19 = childAt.findViewById(R.id.tv_gas19);
            TextView tv_gas20 = childAt.findViewById(R.id.tv_gas20);
            TextView tv_gas21 = childAt.findViewById(R.id.tv_gas21);
            TextView tv_gas22 = childAt.findViewById(R.id.tv_gas22);
            TextView tv_gas23 = childAt.findViewById(R.id.tv_gas23);
            TextView tv_gas24 = childAt.findViewById(R.id.tv_gas24);
            TextView tv_gas25 = childAt.findViewById(R.id.tv_gas25);

            InspectionResult itemObj = inspectionResults.get(i);
            itemObj.setProfession(itemObj.getProfession());
            itemObj.setCheckPerson(itemObj.getCheckPerson());
            itemObj.setCheckDate(itemObj.getCheckDate());
            itemObj.setDescription(et_fire10.getText().toString());
            itemObj.setParam1(et_gas1.getText().toString());
            itemObj.setParam2(et_gas2.getText().toString());
            itemObj.setParam3(tv_gas3.getText().toString());
            itemObj.setParam4(tv_gas4.getText().toString());
            itemObj.setParam5(tv_gas5.getText().toString());
            itemObj.setParam6(tv_gas6.getText().toString());
            itemObj.setParam7(tv_gas7.getText().toString());
            itemObj.setParam8(tv_gas8.getText().toString());
            itemObj.setParam9(tv_gas9.getText().toString());
            itemObj.setParam10(tv_gas10.getText().toString());
            itemObj.setParam11(tv_gas11.getText().toString());
            itemObj.setParam12(tv_gas12.getText().toString());
            itemObj.setParam13(tv_gas13.getText().toString());
            itemObj.setParam14(tv_gas14.getText().toString());
            itemObj.setParam15(tv_gas15.getText().toString());
            itemObj.setParam16(tv_gas16.getText().toString());
            itemObj.setParam17(tv_gas17.getText().toString());
            itemObj.setParam18(tv_gas18.getText().toString());
            itemObj.setParam19(tv_gas19.getText().toString());
            itemObj.setParam20(tv_gas20.getText().toString());
            itemObj.setParam21(tv_gas21.getText().toString());
            itemObj.setParam22(tv_gas22.getText().toString());
            itemObj.setParam23(tv_gas23.getText().toString());
            itemObj.setParam24(tv_gas24.getText().toString());
            itemObj.setParam25(tv_gas25.getText().toString());

            service.update(itemObj);
        }
        Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
    }
    /**
     * 打开系统相机
     */
    public void openSysCamera(Context mContent)  {

        // intent用来启动系统自带的Camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            fileNew = new FileRoute(mContent).createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUriOri = null;
        if (fileNew != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(fileNew);
            } else {
                imgUriOri = FileProvider.getUriForFile(mContent, mContent.getApplicationContext().getPackageName() + ".fileProvider", fileNew);
            }
            // 将系统Camera的拍摄结果写入到文件
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, FileRoute.CAMERA_RESULT_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FileRoute.CAMERA_RESULT_CODE:
                //这里目前需要适配
                if (fileNew != null && imgPostion != -1 && contentApapter != null) {
                    inspectionResults.get(imgPostion).setImgPath(fileNew.getAbsolutePath());
                    // contentApapter.notifyItemChanged(imgPostion);
                    contentApapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
