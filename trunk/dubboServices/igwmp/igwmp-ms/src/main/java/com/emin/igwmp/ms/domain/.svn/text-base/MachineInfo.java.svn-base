package com.emin.igwmp.ms.domain;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;
import java.util.List;

;

@Table(schema = "ms", name = "machine_info")
@Entity
public class MachineInfo extends BaseEntity implements UndeleteableEntity {

    /**
     * @Fields serialVersionUID : (序列化)
     */
    private static final long serialVersionUID = 1L;

    /**
     * 设备资产编号（用作运维人员识别机器）
     */
    private String code;

    /**
     * sim卡编号
     */
    private String simCode;

    /**
     * 工控机编码
     */
    private String ipcCode;

    /**
     * 产品型号
     */
    private String productModel;


    /**
     * 通道数量
     */
    private int channelSum;


    /**
     * 饭店ID
     */
    private Long restaurantId;


    /**
     * 饭店名称
     */
    private String restaurantName;


    /**
     * 设备出酒  通道
     */
    private List<MachineChannel> channelList;

    /**
     * 设备状态，控制
     */
    private MachineControl machineControl;

    private Long createTime;

    private Long lastModifyTime;

    private int status;


    @Id
    @Override
    @SequenceGenerator(name = "machine_info_id_seq", sequenceName = "ms.machine_info_id_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_info_id_seq") //写明使用哪个序列生成器
    public Long getId() {
        return super.getId();
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "machine_info_id")
    public List<MachineChannel> getChannelList() {
        return channelList;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "machine_control_id")
    public MachineControl getMachineControl() {
        return machineControl;
    }

    public void setMachineControl(MachineControl machineControl) {
        this.machineControl = machineControl;
    }

    public void setChannelList(List<MachineChannel> channelList) {
        this.channelList = channelList;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "sim_code")
    public String getSimCode() {
        return simCode;
    }
    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }
    @Column(name = "ipc_code")
    public String getIpcCode() {
        return ipcCode;
    }

    public void setIpcCode(String ipcCode) {
        this.ipcCode = ipcCode;
    }
    @Column(name = "product_model")
    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }


    @Column(name = "channel_sum")
    public int getChannelSum() {
        return channelSum;
    }

    public void setChannelSum(int channelSum) {
        this.channelSum = channelSum;
    }

    @Column(name = "restaurant_id")
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    @Column(name = "restaurant_name")
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
