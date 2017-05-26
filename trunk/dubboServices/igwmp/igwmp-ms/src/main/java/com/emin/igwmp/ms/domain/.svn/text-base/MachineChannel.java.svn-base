package com.emin.igwmp.ms.domain;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;

@Table(schema = "ms", name = "machine_channel")
@Entity
public class MachineChannel extends BaseEntity implements UndeleteableEntity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2029489707352272731L;

	/**
     * 通道号
     */
    private int channelNo;

    /**
     * 机器id
     */
    private Long machineInfoId;

    /**
     * 余量  (单位：毫升 ml)
     */
    private int allowance;

    /**
     * 警戒值
     */
    private int warningValue;

    /**
     * 警告值（发送给配送人员）
     */
    private int alarmValue;

    /**
     * 酒id
     */
    private Long liquorInfoId;

    /**
     * 酒品名称
     */
    private String liquorName;

    /**
     *  通道显示排序
     */
    private int sort;

    private Long createTime;

    private Long lastModifyTime;

    private int status;


    @Id
    @Override
    @SequenceGenerator(name = "machine_channel_id_seq", sequenceName = "ms.machine_channel_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_channel_id_seq")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "channel_no")
    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }
    @Column(name = "machine_info_id")
    public Long getMachineInfoId() {
        return machineInfoId;
    }

    public void setMachineInfoId(Long machineInfoId) {
        this.machineInfoId = machineInfoId;
    }
    @Column(name = "allowance")
    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }
    @Column(name = "warning_value")
    public int getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(int warningValue) {
        this.warningValue = warningValue;
    }
    @Column(name = "alarm_value")
    public int getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(int alarmValue) {
        this.alarmValue = alarmValue;
    }

    @Column(name = "liquor_info_id")
    public Long getLiquorInfoId() {
        return liquorInfoId;
    }

    public void setLiquorInfoId(Long liquorInfoId) {
        this.liquorInfoId = liquorInfoId;
    }

    @Column(name = "liquor_name")
    public String getLiquorName() {
        return liquorName;
    }

    public void setLiquorName(String liquorName) {
        this.liquorName = liquorName;
    }
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


    @Override
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    @Column(name = "lastmodify_time")
    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }
}
