package com.hr.fire.inspection.entity;

import android.util.Log;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.ItemInfoDao;

@Entity(
        nameInDb = "t_item_info"
)
public class ItemInfo   {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String uuid;

    private Long checkTypeId;

    @ToOne(joinProperty = "checkTypeId")
    private CheckType checkType; //检查类型

    private Long companyInfoId;
    @ToOne(joinProperty = "companyInfoId")
    private CompanyInfo companyInfo;//公司选择信息

    private String typeNo;//型号

    private String deviceType;//设备类型 规格型号

    private String agentsType;//介质类型

    private Date fillingDate;//灌装日期

    private String no; //编号 瓶号 位号

    private String level; // 级别

    private String volume; //容积

    private String weight; //瓶重

    private String goodsWeight;//物品重量 药剂重量

    private String pressure;//压力

    private String prodFactory;//生产厂家

    private Date prodDate; //生产日期

    private String typeConformity;//型号符合性

    private String positionConformity;//位置符合性

    private String appearance;//外观是否良好

    private String isPressure;//压力/重量是否合格

    private String check;//自检是否良好

    private String slience;//消音是否良好

    private String reset;//复位功能是否良好

    private String powerAlarmFunction;//电源线故障报警是否正常

    private String alarmFunction;//报警功能是否正常

    private String effectiveness;//有效性

    private String responseTime;//响应时间

    private String description;//隐患描述

    private String setAlarm25;//设置高报值25LEL

    private String setAlarm50;//设置高报值50LEL

    private String testAlarm25;//测试高报值25LEL

    private String testAlarm50;//测试高报值50LEL

    private Date observeDate;//检测日期/试验日期

    private String taskNumber;//工作代号

    private String isPass;//合格

    private String labelNo;//标签号

    private String imageUrl; //图片路径

    private String videoUrl; //视频路径

    private String codePath;//二维码路径

    private String SystemNumber;//系统位号

    private String ProtectArea;//保护区域

    private Date checkDate;//检查日期

    @ToMany(referencedJoinProperty = "targetId")
    private List<YearCheckResult> checkResultList;


    @Override
    public String toString() {
        return "ItemInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", checkTypeId=" + checkTypeId +
                ", checkType=" + checkType +
                ", companyInfoId=" + companyInfoId +
                ", companyInfo=" + companyInfo +
                ", typeNo='" + typeNo + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", agentsType='" + agentsType + '\'' +
                ", fillingDate=" + fillingDate +
                ", no='" + no + '\'' +
                ", level='" + level + '\'' +
                ", volume='" + volume + '\'' +
                ", weight='" + weight + '\'' +
                ", goodsWeight='" + goodsWeight + '\'' +
                ", pressure='" + pressure + '\'' +
                ", prodFactory='" + prodFactory + '\'' +
                ", prodDate=" + prodDate +
                ", typeConformity='" + typeConformity + '\'' +
                ", positionConformity='" + positionConformity + '\'' +
                ", appearance='" + appearance + '\'' +
                ", isPressure='" + isPressure + '\'' +
                ", check='" + check + '\'' +
                ", slience='" + slience + '\'' +
                ", reset='" + reset + '\'' +
                ", powerAlarmFunction='" + powerAlarmFunction + '\'' +
                ", alarmFunction='" + alarmFunction + '\'' +
                ", effectiveness='" + effectiveness + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", description='" + description + '\'' +
                ", setAlarm25='" + setAlarm25 + '\'' +
                ", setAlarm50='" + setAlarm50 + '\'' +
                ", testAlarm25='" + testAlarm25 + '\'' +
                ", testAlarm50='" + testAlarm50 + '\'' +
                ", observeDate=" + observeDate +
                ", taskNumber='" + taskNumber + '\'' +
                ", isPass='" + isPass + '\'' +
                ", labelNo='" + labelNo + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", codePath='" + codePath + '\'' +
                ", SystemNumber='" + SystemNumber + '\'' +
                ", ProtectArea='" + ProtectArea + '\'' +
                ", checkDate=" + checkDate +
                ", checkResultList=" + checkResultList +
                '}';
    }

    /** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 547138643)
private transient ItemInfoDao myDao;

@Generated(hash = 180279690)
public ItemInfo(Long id, String uuid, Long checkTypeId, Long companyInfoId,
        String typeNo, String deviceType, String agentsType, Date fillingDate,
        String no, String level, String volume, String weight,
        String goodsWeight, String pressure, String prodFactory, Date prodDate,
        String typeConformity, String positionConformity, String appearance,
        String isPressure, String check, String slience, String reset,
        String powerAlarmFunction, String alarmFunction, String effectiveness,
        String responseTime, String description, String setAlarm25,
        String setAlarm50, String testAlarm25, String testAlarm50,
        Date observeDate, String taskNumber, String isPass, String labelNo,
        String imageUrl, String videoUrl, String codePath, String SystemNumber,
        String ProtectArea, Date checkDate) {
    this.id = id;
    this.uuid = uuid;
    this.checkTypeId = checkTypeId;
    this.companyInfoId = companyInfoId;
    this.typeNo = typeNo;
    this.deviceType = deviceType;
    this.agentsType = agentsType;
    this.fillingDate = fillingDate;
    this.no = no;
    this.level = level;
    this.volume = volume;
    this.weight = weight;
    this.goodsWeight = goodsWeight;
    this.pressure = pressure;
    this.prodFactory = prodFactory;
    this.prodDate = prodDate;
    this.typeConformity = typeConformity;
    this.positionConformity = positionConformity;
    this.appearance = appearance;
    this.isPressure = isPressure;
    this.check = check;
    this.slience = slience;
    this.reset = reset;
    this.powerAlarmFunction = powerAlarmFunction;
    this.alarmFunction = alarmFunction;
    this.effectiveness = effectiveness;
    this.responseTime = responseTime;
    this.description = description;
    this.setAlarm25 = setAlarm25;
    this.setAlarm50 = setAlarm50;
    this.testAlarm25 = testAlarm25;
    this.testAlarm50 = testAlarm50;
    this.observeDate = observeDate;
    this.taskNumber = taskNumber;
    this.isPass = isPass;
    this.labelNo = labelNo;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
    this.codePath = codePath;
    this.SystemNumber = SystemNumber;
    this.ProtectArea = ProtectArea;
    this.checkDate = checkDate;
}

@Generated(hash = 1334958530)
public ItemInfo() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getUuid() {
    return this.uuid;
}

public void setUuid(String uuid) {
    this.uuid = uuid;
}

public Long getCheckTypeId() {
    return this.checkTypeId;
}

public void setCheckTypeId(Long checkTypeId) {
    this.checkTypeId = checkTypeId;
}

public Long getCompanyInfoId() {
    return this.companyInfoId;
}

public void setCompanyInfoId(Long companyInfoId) {
    this.companyInfoId = companyInfoId;
}

public String getTypeNo() {
    return this.typeNo;
}

public void setTypeNo(String typeNo) {
    this.typeNo = typeNo;
}

public String getDeviceType() {
    return this.deviceType;
}

public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
}

public String getAgentsType() {
    return this.agentsType;
}

public void setAgentsType(String agentsType) {
    this.agentsType = agentsType;
}

public Date getFillingDate() {
    return this.fillingDate;
}

public void setFillingDate(Date fillingDate) {
    this.fillingDate = fillingDate;
}

public String getNo() {
    return this.no;
}

public void setNo(String no) {
    this.no = no;
}

public String getLevel() {
    return this.level;
}

public void setLevel(String level) {
    this.level = level;
}

public String getVolume() {
    return this.volume;
}

public void setVolume(String volume) {
    this.volume = volume;
}

public String getWeight() {
    return this.weight;
}

public void setWeight(String weight) {
    this.weight = weight;
}

public String getGoodsWeight() {
    return this.goodsWeight;
}

public void setGoodsWeight(String goodsWeight) {
    this.goodsWeight = goodsWeight;
}

public String getPressure() {
    return this.pressure;
}

public void setPressure(String pressure) {
    this.pressure = pressure;
}

public String getProdFactory() {
    return this.prodFactory;
}

public void setProdFactory(String prodFactory) {
    this.prodFactory = prodFactory;
}

public Date getProdDate() {
    return this.prodDate;
}

public void setProdDate(Date prodDate) {
    this.prodDate = prodDate;
}

public String getTypeConformity() {
    return this.typeConformity;
}

public void setTypeConformity(String typeConformity) {
    this.typeConformity = typeConformity;
}

public String getPositionConformity() {
    return this.positionConformity;
}

public void setPositionConformity(String positionConformity) {
    this.positionConformity = positionConformity;
}

public String getAppearance() {
    return this.appearance;
}

public void setAppearance(String appearance) {
    this.appearance = appearance;
}

public String getIsPressure() {
    return this.isPressure;
}

public void setIsPressure(String isPressure) {
    this.isPressure = isPressure;
}

public String getCheck() {
    return this.check;
}

public void setCheck(String check) {
    this.check = check;
}

public String getSlience() {
    return this.slience;
}

public void setSlience(String slience) {
    this.slience = slience;
}

public String getReset() {
    return this.reset;
}

public void setReset(String reset) {
    this.reset = reset;
}

public String getPowerAlarmFunction() {
    return this.powerAlarmFunction;
}

public void setPowerAlarmFunction(String powerAlarmFunction) {
    this.powerAlarmFunction = powerAlarmFunction;
}

public String getAlarmFunction() {
    return this.alarmFunction;
}

public void setAlarmFunction(String alarmFunction) {
    this.alarmFunction = alarmFunction;
}

public String getEffectiveness() {
    return this.effectiveness;
}

public void setEffectiveness(String effectiveness) {
    this.effectiveness = effectiveness;
}

public String getResponseTime() {
    return this.responseTime;
}

public void setResponseTime(String responseTime) {
    this.responseTime = responseTime;
}

public String getDescription() {
    return this.description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getSetAlarm25() {
    return this.setAlarm25;
}

public void setSetAlarm25(String setAlarm25) {
    this.setAlarm25 = setAlarm25;
}

public String getSetAlarm50() {
    return this.setAlarm50;
}

public void setSetAlarm50(String setAlarm50) {
    this.setAlarm50 = setAlarm50;
}

public String getTestAlarm25() {
    return this.testAlarm25;
}

public void setTestAlarm25(String testAlarm25) {
    this.testAlarm25 = testAlarm25;
}

public String getTestAlarm50() {
    return this.testAlarm50;
}

public void setTestAlarm50(String testAlarm50) {
    this.testAlarm50 = testAlarm50;
}

public Date getObserveDate() {
    return this.observeDate;
}

public void setObserveDate(Date observeDate) {
    this.observeDate = observeDate;
}

public String getTaskNumber() {
    return this.taskNumber;
}

public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
}

public String getIsPass() {
    return this.isPass;
}

public void setIsPass(String isPass) {
    this.isPass = isPass;
}

public String getLabelNo() {
    return this.labelNo;
}

public void setLabelNo(String labelNo) {
    this.labelNo = labelNo;
}

public String getImageUrl() {
    return this.imageUrl;
}

public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
}

public String getVideoUrl() {
    return this.videoUrl;
}

public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
}

public String getCodePath() {
    return this.codePath;
}

public void setCodePath(String codePath) {
    this.codePath = codePath;
}

public String getSystemNumber() {
    return this.SystemNumber;
}

public void setSystemNumber(String SystemNumber) {
    this.SystemNumber = SystemNumber;
}

public String getProtectArea() {
    return this.ProtectArea;
}

public void setProtectArea(String ProtectArea) {
    this.ProtectArea = ProtectArea;
}

public Date getCheckDate() {
    return this.checkDate;
}

public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
}

@Generated(hash = 1822795957)
private transient Long checkType__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 863167469)
public CheckType getCheckType() {
    Long __key = this.checkTypeId;
    if (checkType__resolvedKey == null
            || !checkType__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CheckTypeDao targetDao = daoSession.getCheckTypeDao();
        CheckType checkTypeNew = targetDao.load(__key);
        synchronized (this) {
            checkType = checkTypeNew;
            checkType__resolvedKey = __key;
        }
    }
    return checkType;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 599256709)
public void setCheckType(CheckType checkType) {
    synchronized (this) {
        this.checkType = checkType;
        checkTypeId = checkType == null ? null : checkType.getId();
        checkType__resolvedKey = checkTypeId;
    }
}

@Generated(hash = 702142230)
private transient Long companyInfo__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1193346340)
public CompanyInfo getCompanyInfo() {
    Long __key = this.companyInfoId;
    if (companyInfo__resolvedKey == null
            || !companyInfo__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CompanyInfoDao targetDao = daoSession.getCompanyInfoDao();
        CompanyInfo companyInfoNew = targetDao.load(__key);
        synchronized (this) {
            companyInfo = companyInfoNew;
            companyInfo__resolvedKey = __key;
        }
    }
    return companyInfo;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 777983097)
public void setCompanyInfo(CompanyInfo companyInfo) {
    synchronized (this) {
        this.companyInfo = companyInfo;
        companyInfoId = companyInfo == null ? null : companyInfo.getId();
        companyInfo__resolvedKey = companyInfoId;
    }
}

/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 1741583958)
public List<YearCheckResult> getCheckResultList() {
    if (checkResultList == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        YearCheckResultDao targetDao = daoSession.getYearCheckResultDao();
        List<YearCheckResult> checkResultListNew = targetDao
                ._queryItemInfo_CheckResultList(id);
        synchronized (this) {
            if (checkResultList == null) {
                checkResultList = checkResultListNew;
            }
        }
    }
    return checkResultList;
}

/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 1934777524)
public synchronized void resetCheckResultList() {
    checkResultList = null;
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 747586935)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getItemInfoDao() : null;
}

    public String toEnCodeString() {
        StringBuilder sb = new StringBuilder();

        if (this.SystemNumber != null || this.SystemNumber != "") {
            sb.append("系统位号：").append(this.SystemNumber).append("\n");
        }
        if (this.ProtectArea != null || this.SystemNumber != "") {
            sb.append("保护区域：").append(this.ProtectArea).append("\n");
        }

        if (this.checkDate != null || this.SystemNumber != "") {
            sb.append("检查时间：").append(DateFormatUtils.format(this.checkDate,"yyyy-MM-dd")).append("\n");
        }

        if (this.typeNo != null) {
            sb.append("型号：").append(this.typeNo).append("\n");
        }

        if (this.deviceType != null) {
            sb.append("设备类型/规格型号：").append(this.deviceType).append("\n");
        }

        if (this.agentsType != null) {
            sb.append("介质类型：").append(this.agentsType).append("\n");
        }

        if (this.fillingDate != null) {
            sb.append("灌装日期：").append(DateFormatUtils.format(this.fillingDate,"yyyy-MM")).append("\n");
        }

        if (this.no != null) {
            sb.append("编号/瓶号/位号：").append(this.no).append("\n");
        }


        if (this.level != null) {
            sb.append("等级：").append(this.level).append("\n");
        }

        if (this.volume != null) {
            sb.append("容积：").append(this.volume).append("\n");
        }

        if (this.weight != null) {
            sb.append("瓶重：").append(this.weight).append("\n");
        }

        if (this.goodsWeight != null) {
            sb.append("物品重量/药剂重量：").append(this.goodsWeight).append("\n");
        }

        if (this.pressure != null) {
            sb.append("压力：").append(this.pressure).append("\n");
        }

        if (this.prodFactory != null) {
            sb.append("生产厂家：").append(this.prodFactory).append("\n");
        }

        if (this.prodDate != null) {
            sb.append("生产日期：").append(DateFormatUtils.format(this.prodDate,"yyyy-MM")).append("\n");
        }

        if (this.typeConformity != null) {
            sb.append("型号符合性：").append(this.typeConformity).append("\n");
        }

        if (this.positionConformity != null) {
            sb.append("位置符合性：").append(this.positionConformity).append("\n");
        }

        if (this.appearance != null) {
            sb.append("外观是否良好：").append(this.appearance).append("\n");
        }

        if (this.isPressure != null) {
            sb.append("压力/重量是否合格：").append(this.isPressure).append("\n");
        }

        if (this.check != null) {
            sb.append("自检是否良好：").append(this.check).append("\n");
        }

        if (this.slience != null) {
            sb.append("消音是否良好：").append(this.slience).append("\n");
        }

        if (this.reset != null) {
            sb.append("复位功能是否良好：").append(this.reset).append("\n");
        }

        if (this.powerAlarmFunction != null) {
            sb.append("电源线故障报警是否正常：").append(this.powerAlarmFunction).append("\n");
        }

        if (this.alarmFunction != null) {
            sb.append("报警功能是否正常：").append(this.alarmFunction).append("\n");
        }

        if (this.effectiveness != null) {
            sb.append("有效性：").append(this.effectiveness).append("\n");
        }

        if (this.responseTime != null) {
            sb.append("响应时间：").append(this.responseTime).append("\n");
        }

        if (this.description != null) {
            sb.append("隐患描述：").append(this.description).append("\n");
        }

        if (this.setAlarm25 != null) {
            sb.append("设置高报值25LEL：").append(this.setAlarm25).append("\n");
        }

        if (this.setAlarm50 != null) {
            sb.append("设置高报值50LEL：").append(this.setAlarm50).append("\n");
        }

        if (this.testAlarm25 != null) {
            sb.append("测试高报值25LEL：").append(this.testAlarm25).append("\n");
        }

        if (this.testAlarm50 != null) {
            sb.append("测试高报值50LEL：").append(this.testAlarm50).append("\n");
        }

        if (this.observeDate != null) {
            sb.append("检测日期/试验日期/水压实验日期：").append(DateFormatUtils.format(this.observeDate,"yyyy-MM")).append("\n");
        }

        if (this.taskNumber != null) {
            sb.append("工作代号：").append(this.taskNumber).append("\n");
        }

        if (this.isPass != null) {
            sb.append("是否合格：").append(this.isPass).append("\n");
        }

        if (this.labelNo != null) {
            sb.append("标签号：").append(this.labelNo).append("\n");
        }

        return sb.toString();

    }

}
