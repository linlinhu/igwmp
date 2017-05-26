package com.emin.igwmp.ms.domain;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity; 

import javax.persistence.*;

/**
 * user : shimingliang
 * time : 2017/3/16
 * time : 16:43
 * des : 设备状态控制
 */
@Table(schema = "ms", name = "machine_control")
@Entity
public class MachineControl extends BaseEntity implements UndeleteableEntity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6739484973924896719L;

	/**
     * 终端机在线状态 0、不在线，1、在线
     */
    private int onlineStatus;

    private int bindStatus;

    /**
     * 设备运营类型  1、正式   2、活动  3 测试
     */
    private int runType;

    /**
     * 设备开关  1 开， 2 关
     */
    private int changer;

    /**
     *  设备与餐厅绑定时间
     */
    private Long bindTime;

    /**
     *  控制设备关机时间
     */
    private Long offTime;

    /**
     * 控制设备开机时间
     */
    private Long onTime;


    private Long createTime;

    private Long lastModifyTime;

    private int status;

    private MachineInfo machineInfo;


    @Id
    @Override
    @SequenceGenerator(name = "machine_control_seq", sequenceName = "ms.machine_control_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_control_seq") //写明使用哪个序列生成器
    public Long getId() {
        return super.getId();
    }
 
    @OneToOne(mappedBy = "machineControl")
    public MachineInfo getMachineInfo() {
        return machineInfo;
    }
 
    public void setMachineInfo(MachineInfo machineInfo) {
        this.machineInfo = machineInfo;
    }

    @Column(name = "bind_status")
    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    @Column(name = "bind_time")
    public Long getBindTime() {
        return bindTime;
    }

    public void setBindTime(Long bindTime) {
        this.bindTime = bindTime;
    }

    @Column(name = "online_status")
    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @Column(name = "run_type")
    public int getRunType() {
        return runType;
    }

    public void setRunType(int runType) {
        this.runType = runType;
    }

    @Column(name = "changer")
    public int getChanger() {
        return changer;
    }

    public void setChanger(int changer) {
        this.changer = changer;
    }
    @Column(name = "off_time")
    public Long getOffTime() {
        return offTime;
    }

    public void setOffTime(Long offTime) {
        this.offTime = offTime;
    }
    @Column(name = "on_time")
    public Long getOnTime() {
        return onTime;
    }

    public void setOnTime(Long onTime) {
        this.onTime = onTime;
    }

    @Override
    @Column(name="create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    @Column(name="lastmodify_time")
    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    @Column(name="status")
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }
}
