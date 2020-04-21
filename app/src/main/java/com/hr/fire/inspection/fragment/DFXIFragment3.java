package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.CarBon3Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;

import java.util.List;

public class DFXIFragment3 extends Fragment {
    View rootView;
    private static DFXIFragment3 fragment3;
    private static String mKey;
    private IntentTransmit its;
    private CarBon3Adapter adapter;
    private RecyclerView rc_list;
    private List<YearCheck> checkDataEasy;
    private List<YearCheckResult> yearCheckResults;

    public static DFXIFragment3 newInstance(String key, IntentTransmit value) {
        if (fragment3 == null) {
            fragment3 = new DFXIFragment3();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment3.setArguments(args);
        return fragment3;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            its = (IntentTransmit) getArguments().getSerializable(mKey);
            Log.e("dong", "获取检查表传参数据" + its.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_carbon3, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    private void initData() {
        //根据二氧化碳系统的ID,拿到二氧化碳下面的检查表数据
        List<CheckType> checkTypes = ServiceFactory.getYearCheckService().gettableNameData(its.systemId);
        Log.e("dong", "tab列表checkTypes======" + checkTypes);
        //在二氧化碳的子表数据中,拿到管线管件对应的id, 通过id去查询管线管件需要检查的内容
        checkDataEasy = ServiceFactory.getYearCheckService().getCheckDataEasy(checkTypes.get(2).getId());
        //获取用户需要填写的数据,如果没有数据,就需要插入的默认数据（流程4）。如果有数据就
        yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(2).getId(), its.number, its.srt_Date);
        if (yearCheckResults == null || yearCheckResults.size() == 0) {
            for (int i = 0; i < checkDataEasy.size(); i++) {
//                Log.d("dong", "第一次加载数据 = ");
                //3.进入系统就给用户默认插入两条数据, 用户点击保存时,就Updata数据库
                YearCheckResult ycr = new YearCheckResult();
                ycr.setIsPass(" -- ");
                ycr.setImageUrl("暂无");  //可以在iv7中获取
                ycr.setDescription("无描述");
                ycr.setSystemNumber(its.number);
                ycr.setProtectArea(" "); // 保护位号
                ycr.setCheckDate(its.srt_Date);  //检查日期
                ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, 0, checkDataEasy.get(i).getId(), its.companyInfoId,
                        checkTypes.get(2).getId(), its.number, its.srt_Date);
                yearCheckResults = ServiceFactory.getYearCheckService().getCheckResultDataEasy(0, its.companyInfoId, checkTypes.get(2).getId(), its.number, its.srt_Date);
            }
        }
        initView();
    }

    private void initView() {

        rc_list = rootView.findViewById(R.id.rc_list3);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new CarBon3Adapter(getActivity(), checkDataEasy, yearCheckResults);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
    }

    public void upData() {
        int childCount = rc_list.getChildCount();
//                Log.d("dong", "childCount==- " + childCount + "    数据条目 " + yearCheckResults.size());
        //两边的数据条数是一样的.
        if (yearCheckResults.size() == childCount) {
            for (int i = 0; i < childCount; i++) {
                LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
                TextView tv6 = childAt.findViewById(R.id.tv6);
                //图片参数
                TextView tv7 = childAt.findViewById(R.id.tv7);
                ImageView iv7 = childAt.findViewById(R.id.iv7);
                EditText ev8 = childAt.findViewById(R.id.ev8);

                YearCheckResult yearCheckResult = yearCheckResults.get(i);
                yearCheckResult.setIsPass(tv6.getText().toString().isEmpty() ? " -- " : tv6.getText().toString());
                yearCheckResult.setImageUrl("暂无图片链接");  //可以在iv7中获取
                yearCheckResult.setDescription(ev8.getText().toString().isEmpty() ? "无隐患描述" : ev8.getText().toString());
                yearCheckResult.setSystemNumber(its.number);
                yearCheckResult.setProtectArea(" "); // 保护位号
                yearCheckResult.setCheckDate(its.srt_Date);  //检查日期
                ServiceFactory.getYearCheckService().update(yearCheckResult);
            }
        }
        Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
