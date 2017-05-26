package com.emin.igwmp.tws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;

@Entity
@Table(schema="tws",name="take_wine_record")
public class TakeWineRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3805626209833052229L;
	
	public static final String PROP_RECORD_NUMBER = "recordNumber";
	
	public static final String PROP_ORDER_NUMBER = "orderNumber";

	private String orderNumber;//相关订单号
	
	private String recordNumber;//取酒记录号
	
	private String takeCode;//取酒码
	
	private TakeWineStatus status;//取酒状态
	
	private Integer shouldTakeMl;//应该取酒量(ml)
	
	private Integer actualTakeMl;//实际取酒量(ml)
	
	private Long createTime;//取酒记录创建时间
	
	private Long finishTime;//取酒完成时间
	
	private Long codeExpireTime;//取酒码过期时间

	private String takeInfo;//取酒信息 可存放地址
	
	@Id
	@Override
	@SequenceGenerator(name = "take_wine_record_id_seq", sequenceName = "tws.take_wine_record_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "take_wine_record_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="order_number")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Column(name="record_number")
	public String getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	@Column(name="takecode")
	public String getTakeCode() {
		return takeCode;
	}

	public void setTakeCode(String takeCode) {
		this.takeCode = takeCode;
	}

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	public TakeWineStatus getStatus() {
		return status;
	}

	public void setStatus(TakeWineStatus status) {
		this.status = status;
	}

	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name="finish_time")
	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}
	@Column(name="should_take_ml")
	public Integer getShouldTakeMl() {
		return shouldTakeMl;
	}
	public void setShouldTakeMl(Integer shouldTakeMl) {
		this.shouldTakeMl = shouldTakeMl;
	}
	@Column(name="actual_take_ml")
	public Integer getActualTakeMl() {
		return actualTakeMl;
	}
	public void setActualTakeMl(Integer actualTakeMl) {
		this.actualTakeMl = actualTakeMl;
	}
	@Column(name="code_expire_time")
	public Long getCodeExpireTime() {
		return codeExpireTime;
	}
	public void setCodeExpireTime(Long codeExpireTime) {
		this.codeExpireTime = codeExpireTime;
	}
	
	@Column(name="take_info",columnDefinition="text")
	public String getTakeInfo() {
		return takeInfo;
	}
	public void setTakeInfo(String takeInfo) {
		this.takeInfo = takeInfo;
	}
	
	 
	
}
