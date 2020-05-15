package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.constant.ConstantInspection;
import com.hr.fire.inspection.entity.IntentTransmit;
import com.hr.fire.inspection.fragment.HFCFragment1;
import com.hr.fire.inspection.fragment.HFCFragment2;
import com.hr.fire.inspection.fragment.HFCFragment3;
import com.hr.fire.inspection.fragment.HFCFragment4;
import com.hr.fire.inspection.fragment.HFCFragment5;
import com.hr.fire.inspection.utils.TextSpannableUtil;
import com.hr.fire.inspection.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("Registered")
public class HFCActivity extends AppCompatActivity {
    private List<String> titleList = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_finish;
    private TextView iv_save;
    private ImageView iv_add_table;
    private TextView tvInspectionPro;
    private int currentPager;
    private HFCFragment1 mHFCFragment1;
    private HFCFragment2 mHFCFragment2;
    private HFCFragment3 mHFCFragment3;
    private HFCFragment4 mHFCFragment4;
    private HFCFragment5 mHFCFragment5;
    private String f_title;
    private String sys_number;  //系统位号
    private IntentTransmit it;
    private String protect_area;  //保护区域
    private Date check_date; // 检查时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_carbon_dioxide);
        getIntentParameter();
        initView();
        initListner();
    }

    private void getIntentParameter() {
        //历史中的companyInfoId  ,  systemId和再公司、平台那边传过来的都是一样的ID，使用哪一个都行
        Intent intent = getIntent();
        long systemId = intent.getLongExtra("systemId", 0);   //系统Id
        long platform_id = intent.getLongExtra("platform_id", 0);   //系统Id
        Date srt_Date = (Date) intent.getSerializableExtra("srt_Date");  //传过来的时间
        f_title = intent.getStringExtra("f_title"); //传过来的名称
        sys_number = intent.getStringExtra("sys_number"); //传过来的名称
        it = new IntentTransmit();
        it.companyInfoId = platform_id;
        it.systemId = systemId;
        it.srt_Date = srt_Date;
        it.number = sys_number;
        protect_area = intent.getStringExtra("protect_area"); //传过来的保护区域
        check_date = srt_Date;
    }

    public void initView() {
        iv_finish = findViewById(R.id.iv_finish);
        iv_add_table = findViewById(R.id.iv_add_table);
        tvInspectionPro = findViewById(R.id.tv_inspection_pro);
        iv_save = findViewById(R.id.iv_save);
        String text = new StringBuilder().append("消防年检  >  ").append(f_title).toString();
        SpannableString showTextColor = TextSpannableUtil.showTextColor(text, "#00A779", 8, text.length());
        tvInspectionPro.setText(showTextColor);

        //显示顶部展示系统位号、保护区域、检查时间的LinearLayout
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.VISIBLE);
        // 系统位号文字显示
        TextView sys_number_text = (TextView) this.findViewById(R.id.sys_number_text);
        if (sys_number == null || sys_number == "" || sys_number.isEmpty()) {
            sys_number_text.setText("系统位号为空");
        } else {
            sys_number_text.setText(sys_number);
        }

        // 保护区域文字显示
        TextView protect_area_text = (TextView) this.findViewById(R.id.protect_area_text);
        if (protect_area == null || protect_area == "" || protect_area.isEmpty()) {
            protect_area_text.setText("保护区域为空");
        } else {
            protect_area_text.setText(protect_area);
        }

        // 检查时间文字显示
        TextView check_date_text = (TextView) this.findViewById(R.id.check_date_text);
        if (check_date == null) {
            check_date_text.setText("保护区域为空");
        } else {
            String mProdDate = (String) TimeUtil.getInstance().dataToHHmmss(check_date);
            check_date_text.setText(mProdDate);
        }


        mTabLayout = findViewById(R.id.tl_tabs);
        mViewPager = findViewById(R.id.vp_content);
        titleList.add("七氟丙烷钢瓶信息采集");
        titleList.add("氮气驱动瓶信息采集");
        titleList.add("管线管件");
        titleList.add("保护区");
        titleList.add("功能性实验");

        mHFCFragment1 = HFCFragment1.newInstance(ConstantInspection.YEARLY_ON_SITE_F1, it);
        mHFCFragment2 = HFCFragment2.newInstance(ConstantInspection.YEARLY_ON_SITE_F2, it);
        mHFCFragment3 = HFCFragment3.newInstance(ConstantInspection.YEARLY_ON_SITE_F3, it);
        mHFCFragment4 = HFCFragment4.newInstance(ConstantInspection.YEARLY_ON_SITE_F4, it);
        mHFCFragment5 = HFCFragment5.newInstance(ConstantInspection.YEARLY_ON_SITE_F5, it);

        fragments.add(mHFCFragment1);
        fragments.add(mHFCFragment2);
        fragments.add(mHFCFragment3);
        fragments.add(mHFCFragment4);
        fragments.add(mHFCFragment5);

        //设置缓存的页面数据
        mViewPager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPager = i;
                if (i == 2 || i == 3 || i == 4) {
                    iv_add_table.setVisibility(View.GONE);
                } else {
                    iv_add_table.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //每项只进入一次
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initListner() {
        iv_add_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments != null && fragments.size() != 0) {
                    Fragment fragment = fragments.get(currentPager);
                    if (fragment instanceof HFCFragment1) {
                        mHFCFragment1.addItemView();
                    } else if (fragment instanceof HFCFragment2) {
                        mHFCFragment2.addItemView();
                    } else if (fragment instanceof HFCFragment3) {
//                        mHFCFragment3.addItemView();
                    }
                }
            }
        });
        //点击保存时候调用
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upData();
            }
        });
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upData();
                finish();
            }
        });
    }


    //更新数据
    private void upData() {
        if (fragments != null && fragments.size() != 0) {
            Fragment fragment = fragments.get(currentPager);
            if (fragment instanceof HFCFragment1) {
                mHFCFragment1.saveData();
            } else if (fragment instanceof HFCFragment2) {
                mHFCFragment2.saveData();
            } else if (fragment instanceof HFCFragment3) {
                mHFCFragment3.saveData();
            } else if (fragment instanceof HFCFragment4) {
                mHFCFragment4.saveData();
            } else if (fragment instanceof HFCFragment4) {
                mHFCFragment5.saveData();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
