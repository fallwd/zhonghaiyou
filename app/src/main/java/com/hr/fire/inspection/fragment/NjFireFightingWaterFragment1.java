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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.NjFireFightingWaterAdapter1;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NjFireFightingWaterFragment1 extends Fragment {
    View rootView;
    private static NjFireFightingWaterFragment1 fragment1;
    private static String mKey;
    private NjFireFightingWaterAdapter1 adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView table_tbody_id;
    private IntentTransmit it;
    private List<CheckType> checkTypes;

    public static NjFireFightingWaterFragment1 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new NjFireFightingWaterFragment1();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            it = (IntentTransmit) getArguments().getSerializable(mKey);
            Log.e("dong", "f1传参====" + it.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.acitivty_nj_fire_fighting_water1, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        List<CheckType> arr = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        long checkTypeId = arr.get(0).getId();


        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData


    }

    private void initView() {

        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        table_tbody_id = rootView.findViewById(R.id.table_tbody_id);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        table_tbody_id.setLayoutManager(layoutManager);
        adapter = new NjFireFightingWaterAdapter1(getActivity(), itemDataList);
        table_tbody_id.setAdapter(adapter);
        //添加动画
        table_tbody_id.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(0).getId(), it);
        }
    }

    //动态添加条目
    public void addItemView() {
        if (adapter != null) {
            ItemInfo itemInfo = new ItemInfo();
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                ItemInfo item = itemDataList.get(itemDataList.size() - 1);
                //如果直接新增会导致后台id冲重复\冲突

                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setNo(item.getNo());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setDeviceType(item.getDeviceType());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setTaskNumber(item.getTaskNumber());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setCodePath(item.getCodePath());
//
            } else {
                //点击新增,如果没有数据,就造一条默认数据
                itemInfo.setTypeNo("请编辑");
                itemInfo.setNo("请编辑");
                itemInfo.setProdFactory("请编辑");
                itemInfo.setDeviceType("请编辑");
                Date date = new Date();
                itemInfo.setProdDate(date);
                itemInfo.setTaskNumber("请编辑");
                itemInfo.setIsPass("请选择");



//            <!--序号--> t
//            <!--类型--> e typeNo
//            <!--编号/位置--> e  no
//            <!--生产厂家--> e  prodFactory
//            <!--规格型号--> e  deviceType
//            <!--生产日期--> e  prodDate
//            <!--工作代号--> t  taskNumber
//            <!--检查表--> t
//            <!--是否合格--> t  isPass
//            <!--二维码--> i codePath

            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(0).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }


    public void upData() {
        int itemCount = table_tbody_id.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(0).getId(), it.number == null ? "" : it.number, it.srt_Date);

        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) table_tbody_id.getChildAt(i);
            TextView et_1 = childAt.findViewById(R.id.et_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            TextView et_7 = childAt.findViewById(R.id.et_7);
            TextView et_8 = childAt.findViewById(R.id.et_8);
            TextView et_9 = childAt.findViewById(R.id.et_9);
            ImageView et_10 = childAt.findViewById(R.id.et_10);



            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setTypeNo(et_2.getText().toString());
            itemObj.setNo(et_3.getText().toString());
            itemObj.setProdFactory(et_4.getText().toString());
            itemObj.setDeviceType(et_5.getText().toString());
            Date date = TimeUtil.getInstance().hhmmssTodata(et_6.getText().toString());
            itemObj.setProdDate(date);
            itemObj.setTaskNumber(et_7.getText().toString());
            itemObj.setIsPass(et_9.getText().toString());
            ServiceFactory.getYearCheckService().update(itemObj);
            Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}