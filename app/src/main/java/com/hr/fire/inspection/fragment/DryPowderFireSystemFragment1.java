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
import com.hr.fire.inspection.adapter.DryPowderFireSystemAdapter1;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.TimeUtil;
import com.hr.fire.inspection.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DryPowderFireSystemFragment1 extends Fragment {

    View rootView;
    private static DryPowderFireSystemFragment1 fragment1;
    private static String mKey;
    private DryPowderFireSystemAdapter1 adapter;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private RecyclerView rc_list;
    private IntentTransmit its;
    private List<CheckType> checkTypes;

    public static DryPowderFireSystemFragment1 newInstance(String key, IntentTransmit value) {
        if (fragment1 == null) {
            fragment1 = new DryPowderFireSystemFragment1();
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
            // 建立当前页面的IntentTransmit，为了不影响其他页面的its
            its = new IntentTransmit();
            IntentTransmit it = (IntentTransmit) getArguments().getSerializable(mKey);
            its.srt_Date = it.srt_Date;
            its.systemId = it.systemId;
            its.companyInfoId = it.companyInfoId;
            its.id = it.id;
            its.platformId = it.platformId;
            its.type = it.type;
            its.start_time = it.start_time;
            its.end_time = it.end_time;
            its.name = it.name;
            its.number = it.number;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_drypower1, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    /**
     * 接口说明:
     * 1.历史中的companyInfoId  ,  systemId和在公司、平台那边传过来的都是一样的ID，使用哪一个都行
     * 2.gettableNameData  这个接口填二氧化碳灭火系统的id，就能获取到他的数据表的id，然后找到药剂瓶的id就是参数二
     * 3. 用gettableNameData返回的数据ID,  填充到getItemDataEasy第三个参数
     */
    private void initData() {
        //历史中的companyInfoId  ,  systemId和在公司、平台那边传过来的都是一样的ID，使用哪一个都行

        checkTypes = ServiceFactory.getYearCheckService().gettableNameData(its.systemId);
        if (checkTypes == null) {
            Toast.makeText(getActivity(), "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
        }
        //参数1:公司id, 参数2:检查表类型对应的id, 参数3:输入的系统位号，如果没有就填"",或者SD002,否则没数据   参数4:日期
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
        // 判断是否是基于历史数据新建，是的话，某些字段做空的处理
        if (its.name != null || its.name == "基于历史数据新建") {
            if (itemDataList != null && itemDataList.size() != 0) {
                //点击新增,有数据,就拿到最后一条数据新增,创建一个新的对象
                for (int i = 0; i<itemDataList.size(); i++) {
                    ItemInfo itemInfo = new ItemInfo();
                    ItemInfo item = itemDataList.get(i);
                    //如果直接新增会导致后台id冲重复\冲突
                    itemInfo.setTypeNo(item.getTypeNo());
                    itemInfo.setNo(item.getNo());
                    itemInfo.setVolume(item.getVolume());
                    itemInfo.setWeight(item.getWeight());
                    itemInfo.setGoodsWeight(item.getGoodsWeight());
                    itemInfo.setFillingDate(item.getFillingDate());
                    itemInfo.setProdFactory(item.getProdFactory());
                    itemInfo.setProdDate(item.getProdDate());
                    itemInfo.setTaskNumber(item.getTaskNumber());
                    itemInfo.setCodePath(item.getCodePath());
                    itemInfo.setIsPass("请选择");
//                    itemInfo.setLabelNo("请编辑");
                    itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    long nowTime = new Date().getTime();
                    String d = format.format(nowTime);
                    try {
                        its.srt_Date = format.parse(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
                }
            }
            itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
        }
    }

    private void initView() {
        if (itemDataList.size() == 0) {
            Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
        }
        rc_list = rootView.findViewById(R.id.rc_list);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setLayoutManager(layoutManager);
        adapter = new DryPowderFireSystemAdapter1(getActivity(), itemDataList);
        rc_list.setAdapter(adapter);
        //添加动画
        rc_list.setItemAnimator(new DefaultItemAnimator());
        if (checkTypes != null) {
            adapter.setCheckId(checkTypes.get(0).getId(), its);
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
                itemInfo.setTypeNo(item.getTypeNo());
                itemInfo.setNo(item.getNo());
                itemInfo.setVolume(item.getVolume());
                itemInfo.setWeight(item.getWeight());
                itemInfo.setGoodsWeight(item.getGoodsWeight());
                itemInfo.setFillingDate(item.getFillingDate());
                itemInfo.setProdFactory(item.getProdFactory());
                itemInfo.setProdDate(item.getProdDate());
                itemInfo.setTaskNumber(item.getTaskNumber());
                itemInfo.setIsPass(item.getIsPass());
                itemInfo.setLabelNo(item.getLabelNo());
                itemInfo.setCodePath(item.getCodePath());
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));

            } else {

//                型号 typeNo
//                瓶号 no
//                容积/L	 volume
//                瓶重/kg	 weight
//                药剂量/kg	  goodsWeight
//                灌装日期	 fillingDate
//                生产厂家	 prodFactory
//                生产日期	 prodDate
//                工作代号	 taskNumber
//                检查表
//                是否合格	 isPass
//                检验标签	labelNo
//                二维码 codePath


                //点击新增,如果没有数据,就造一条默认数据
//                itemInfo.setTypeNo("请编辑");
//                itemInfo.setNo("请编辑");
//                itemInfo.setVolume("请编辑");
//                itemInfo.setWeight("请编辑");
//                itemInfo.setGoodsWeight("请编辑");
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                long nowTime = date.getTime();
                String d = format.format(nowTime);
                try {
                    date = format.parse(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                itemInfo.setFillingDate(date);
//                itemInfo.setProdFactory("请编辑");
                itemInfo.setProdDate(date);
                itemInfo.setTaskNumber("请选择");
                itemInfo.setIsPass("请选择");
//                itemInfo.setLabelNo("请编辑");
                itemInfo.setUuid(UUID.randomUUID().toString().replace("-",""));

            }
            long l1 = ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, its.companyInfoId, checkTypes.get(0).getId(), its.number, its.srt_Date);
            //表示数据插入成功,再次查询,拿到最新的数据
            if (l1 == 0) {
                itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
                adapter.setNewData(itemDataList);
            } else {
                ToastUtil.show(getActivity(), "未知错误,新增失败", Toast.LENGTH_SHORT);
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public void saveData() {
        int itemCount = rc_list.getChildCount();
        //通知数据库刷新数据， 才能在调用Update();
        itemDataList = ServiceFactory.getYearCheckService().getItemDataEasy(its.companyInfoId, checkTypes.get(0).getId(), its.number == null ? "" : its.number, its.srt_Date);
        Log.d("dong", "upData==   " + itemCount + "   新的数据条数   " + itemDataList.size());
        if (itemCount == 0 || itemDataList.size() == 0 || itemDataList.size() != itemCount) {
            Toast.makeText(getActivity(), "暂无数据保存", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            LinearLayout childAt = (LinearLayout) rc_list.getChildAt(i);
            TextView tv_1 = childAt.findViewById(R.id.tv_1);
            EditText et_1 = childAt.findViewById(R.id.et_1);
            EditText et_2 = childAt.findViewById(R.id.et_2);
            EditText et_3 = childAt.findViewById(R.id.et_3);
            EditText et_4 = childAt.findViewById(R.id.et_4);
            EditText et_5 = childAt.findViewById(R.id.et_5);
            EditText et_6 = childAt.findViewById(R.id.et_6);
            EditText et_7 = childAt.findViewById(R.id.et_7);
            EditText et_8 = childAt.findViewById(R.id.et_8);
            TextView et_9 = childAt.findViewById(R.id.et_9);
            TextView et_10 = childAt.findViewById(R.id.et_10);
            EditText et_11 = childAt.findViewById(R.id.et_11);
            ImageView et_12 = childAt.findViewById(R.id.et_12);
            TextView tv_9 = childAt.findViewById(R.id.tv_9);

            ItemInfo itemObj = itemDataList.get(i);

            itemObj.setTypeNo(et_1.getText().toString());
            itemObj.setNo(et_2.getText().toString());
            itemObj.setVolume(et_3.getText().toString());
            itemObj.setWeight(et_4.getText().toString());
            itemObj.setGoodsWeight(et_5.getText().toString());
            Date date = TimeUtil.parse(et_6.getText().toString(),"yyyy-MM");
            itemObj.setFillingDate(date);
            itemObj.setProdFactory(et_7.getText().toString());
            Date date1 = TimeUtil.parse(et_8.getText().toString(),"yyyy-MM");
            itemObj.setProdDate(date1);
            itemObj.setTaskNumber(et_9.getText().toString());
            itemObj.setIsPass(et_10.getText().toString());
            itemObj.setLabelNo(et_11.getText().toString());
//            itemObj.setCodePath(et_12.getImageAlpha());
            itemObj.setUuid(UUID.randomUUID().toString().replace("-",""));

            ServiceFactory.getYearCheckService().update(itemObj);

        }
        Toast.makeText(getContext(), "数据保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter = null;
        }
    }
}
