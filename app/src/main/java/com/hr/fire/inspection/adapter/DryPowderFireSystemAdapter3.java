package com.hr.fire.inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.PhotoUploadActivity;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.impl.YCCCameraForVideo;
import com.hr.fire.inspection.utils.PhotoView;
import com.hr.fire.inspection.view.tableview.HrPopup;
import com.hr.fire.inspection.impl.YCCamera;

import java.util.List;

public class DryPowderFireSystemAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<YearCheckResult> ycr;
    private Context mContext;
    private List<YearCheck> mData;


    public DryPowderFireSystemAdapter3(Context mContext, List<YearCheck> mData, List<YearCheckResult> yearCheckResults) {
        this.mContext = mContext;
        this.mData = mData;
        this.ycr = yearCheckResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hfc3_form_input, parent, false);
        DryPowderFireSystemAdapter3.ViewHolder holder = new DryPowderFireSystemAdapter3.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final DryPowderFireSystemAdapter3.ViewHolder vh = (DryPowderFireSystemAdapter3.ViewHolder) holder;
        if (mData != null && mData.size() != 0) {
            YearCheck yearCheck = mData.get(position);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            vh.ll_item.setLayoutParams(layoutParams);
            vh.tv1.setText(new StringBuffer().append(position + 1));

            vh.tv2.setText(yearCheck.getProject());
            vh.tv3.setText(yearCheck.getContent());
            vh.tv4.setText(yearCheck.getRequirement());
            vh.tv5.setText(yearCheck.getStandard());

            vh.tv6.setText(ycr.get(position).getIsPass());
            //            vh.ev8.setText(ycr.get(position).getDescription());
            if (ycr.get(position).getDescription() == null) {
                vh.ev8.setHint("无描述");
            } else {
                vh.ev8.setText(ycr.get(position).getDescription());
            }

            //在左侧添加图片
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.goods_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            vh.tv6.setCompoundDrawables(null, null, drawable, null);
            Drawable drawable1 = mContext.getResources().getDrawable(R.drawable.listview_border_margin);
            vh.tv6.setBackground(drawable1);

            vh.tv7.setVisibility(View.GONE);
            vh.rl7.setVisibility(View.VISIBLE);
            vh.tv8.setVisibility(View.GONE);
            vh.ev8.setVisibility(View.VISIBLE);

            vh.tv6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("dong","点击的状post== " + position);

                    //如果点击的是最后一个条目, 那么控件的高度需要增加  否则弹框会被挡住
                    showPopWind(vh.tv6);
                }
            });

            String imageUrl = ycr.get(position).getImageUrl();
            if (imageUrl != null && imageUrl.endsWith(".jpg")) {
                Uri uri = Uri.parse(imageUrl);
                vh.iv7.setImageURI(uri);
            } else {
                vh.iv7.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            String videoUrl = ycr.get(position).getVideoUrl();
            if (videoUrl != null && videoUrl.endsWith(".mp4")) {
                vh.iv7.setImageDrawable(mContext.getDrawable(R.mipmap.video_icon));
            } else {
                vh.iv7.setImageDrawable(mContext.getDrawable(R.mipmap.scene_photos_icon));
            }

            vh.iv7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PhotoView().showPopWindPic2(mContext, position, mYCCamera, doOpenCameraForVideo, ycr);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_item;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv6;
        TextView tv7;
        RelativeLayout rl7;
        ImageView iv7;
        TextView tv8;
        EditText ev8;
        View vw;

        ViewHolder(View view) {
            super(view);
            ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            tv5 = (TextView) view.findViewById(R.id.tv5);
            tv6 = (TextView) view.findViewById(R.id.tv6);
            tv7 = (TextView) view.findViewById(R.id.tv7);
            rl7 = (RelativeLayout) view.findViewById(R.id.rl7);
            iv7 = (ImageView) view.findViewById(R.id.iv7);
            tv8 = (TextView) view.findViewById(R.id.tv8);
            ev8 = (EditText) view.findViewById(R.id.ev8);
        }
    }

    private HrPopup hrPopup;

    private void showPopWind(final TextView tv6) {
        View PopupRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods, null);
        if (hrPopup == null) {
            hrPopup = new HrPopup((Activity) mContext);
        }
        RelativeLayout rl_yes = PopupRootView.findViewById(R.id.rl_yes);
        RelativeLayout rl_no = PopupRootView.findViewById(R.id.rl_no);
        RelativeLayout rl_other = PopupRootView.findViewById(R.id.rl_other);
        hrPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        hrPopup.setBackgroundDrawable(new BitmapDrawable());
        hrPopup.setFocusable(true);
        hrPopup.setOutsideTouchable(true);
        hrPopup.setContentView(PopupRootView);
        hrPopup.showAsDropDown(tv6);

        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("是");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("否");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
        rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText("---");
                if (hrPopup.isShowing()) {
                    hrPopup.dismiss();
                }
            }
        });
    }

    private YCCamera mYCCamera;

    //接口回调, 将点击事件传递到activity中,打开相机
    public void setmYCCamera(YCCamera y) {
        this.mYCCamera = y;
    }

    private YCCCameraForVideo doOpenCameraForVideo;
    //接口回调, 将点击事件传递到activity中,打开相机录像
    public void setdoOpenCameraForVideo(YCCCameraForVideo y) {
        this.doOpenCameraForVideo = y;
    }
}
