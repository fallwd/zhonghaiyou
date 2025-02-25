package com.hr.fire.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.HFC2Adapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.HYLogUtil;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HFCFragment2 extends Fragment {
    View rootView;
    private static HFCFragment2 fragment2;
    private static String mKey;
    private HFC2Adapter adapter;
    private IntentTransmit it;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;
    private List<CheckType> checkTypes;

    public static HFCFragment2 newInstance(String key, IntentTransmit value) {
        if (fragment2 == null) {
            fragment2 = new HFCFragment2();
        }
        mKey = key;
        Bundle args = new Bundle();
        args.putSerializable(key, value);
        fragment2.setArguments(args);
        return fragment2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // 建立当前页面的IntentTransmit，为了不影响其他页面的it
            it = new IntentTransmit();
            IntentTransmit its = (IntentTransmit) getArguments().getSerializable(mKey);
            it.srt_Date = its.srt_Date;
            it.systemId = its.systemId;
            it.companyInfoId = its.companyInfoId;
            it.id = its.id;
            it.platformId = its.platformId;
            it.type = its.type;
            it.start_time = its.start_time;
            it.end_time = its.end_time;
            it.name = its.name;
            it.number = its.number;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hfc2, container, false);
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
        // 调用接口测试
        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(it.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
        HYLogUtil.getInstance().d("设备表信息,数据查看:" + itemDataList.size() + "  " + itemDataList.toString());
        // 一级表插入数据insertItemData
        // 判断是否是基于历史数据新建，是的话，某些字段做空的处理
        if (it.name != null || it.name == "基于历史数据新建") {
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                for (int i = 0; i<itemDataList.size(); i++) {
                    ItemInfo itemInfo = new ItemInfo();
                    ItemInfo item = itemDataList.get(i);
                    //如果直接新增会导致后台id冲重复\冲突
                    itemInfo.setNo(item.getNo());
                    itemInfo.setVolume(item.getVolume());
                    itemInfo.setWeight(item.getWeight());
                    itemInfo.setGoodsWeight(item.getGoodsWeight());
                    itemInfo.setProdFactory(item.getProdFactory());
                    itemInfo.setProdDate(item.getProdDate());
                    itemInfo.setObserveDate(item.getObserveDate());
                    itemInfo.setTaskNumber(item.getTaskNumber());
                    itemInfo.setIsPass("请选择");
//                    itemInfo.setLabelNo("请编辑");
                    itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    long nowTime = new Date().getTime();
                    String d = format.format(nowTime);
                    try {
                        it.srt_Date = format.parse(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
                }
            }
            itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
        }
    }

    private void initView() {
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new HFC2Adapter(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());

        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(1).getId(), it);
        }
    }

    //动态添加条目
    public void addItemView() {
        saveData(); // 点击加号前，执行保存函数，将最新数据提交到数据库
        if (adapter != null) {
            ItemInfo itemInfo = new ItemInfo();
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                ItemInfo item = itemDataList.get(itemDataList.size() - 1);
                //如果直接新增会导致后台id冲重复\冲突
                itemInfo.setNo(item.getNo());
                itemInfo.setVolume(item.getVolume());
                itemInfo.setWeight(item.getWeight());
                itemInfo.setGoodsWeight(item.getGoodsWeight());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setObserveDate(item.getObserveDate());
                itemInfo.setTaskNumber(item.getTaskNumber());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setLabelNo(item.getLabelNo());
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));
            } else {
                //点击新增,如果没有数据,就造一条默认数据
//                itemInfo.setNo("请编辑");
//                itemInfo.setVolume("请编辑");
//                itemInfo.setWeight("请编辑");
//                itemInfo.setGoodsWeight("请编辑");
//                itemInfo.setProdFactory("请编辑");
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                long nowTime = date.getTime();
                String d = format.format(nowTime);
                try {
                    date = format.parse(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                itemInfo.setProdDate(date);
                itemInfo.setObserveDate(date);
                itemInfo.setTaskNumber("请选择");
                itemInfo.setIsPass("请选择");
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));
            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, it.companyInfoId, checkTypes.get(1).getId(), it.number, it.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }

    public void saveData() {
        int itemCount = rc_list.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(it.companyInfoId, checkTypes.get(1).getId(), it.number == null ? "" : it.number, it.srt_Date);
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
//            EditText et_9 = childAt.findViewById(R.id.et_9);
            TextView tv_10 = childAt.findViewById(R.id.tv_10);
            TextView tv_11 = childAt.findViewById(R.id.tv_11);
            EditText et_10 = childAt.findViewById(R.id.et_10);

            //这些数据需要从上层传参过来
            ItemInfo itemObj = itemDataList.get(i);
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setGoodsWeight(et_5.getText().toString());
            itemObj.setProdFactory(et_6.getText().toString());

            Date date = TimeUtil.parse(et_7.getText().toString(),"yyyy-MM");
            Date date1 = TimeUtil.parse(et_8.getText().toString(),"yyyy-MM");

            itemObj.setProdDate(date);
            itemObj.setObserveDate(date1);
            itemObj.setIsPass(tv_10.getText().toString());
            itemObj.setTaskNumber(tv_11.getText().toString());
            itemObj.setLabelNo(et_10.getText().toString());
            itemObj.setUuid(UUID.randomUUID().toString().replace("-",""));
            ServiceFactory.getYearCheckService().update(itemObj);
        }

        Toast.makeText(getActivity(), "数据保存成功", Toast.LENGTH_SHORT).show();
    }
}