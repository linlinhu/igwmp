package com.emin.wxs.vo.trading;

import org.springframework.beans.BeanUtils;

import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.domain.TakeWineStatus;

public class TakeWineRecordVO {

	private String orderNumber;//相关订单号
	
	private String recordNumber;//取酒记录号
	
	private String takeCode;//取酒码
	
	private TakeWineStatus status;//取酒状态
	
	private Integer shouldTakeMl;//应该取酒量(ml)
	
	private Integer actualTakeMl;//实际取酒量(ml)
	
	private Long createTime;//取酒记录创建时间
	
	private Long finishTime;//取酒完成时间 
	
	private Long codeExpireTime;//取酒码过期时间
	
	public static TakeWineRecordVO takeWineRecordToVO(TakeWineRecord takeWineRecord){
		
		if(takeWineRecord!=null){
			TakeWineRecordVO vo = new TakeWineRecordVO();
			BeanUtils.copyProperties(takeWineRecord, vo);
			return vo;
		}
		return null;
		
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getTakeCode() {
		return takeCode;
	}

	public void setTakeCode(String takeCode) {
		this.takeCode = takeCode;
	}

	public TakeWineStatus getStatus() {
		return status;
	}

	public void setStatus(TakeWineStatus status) {
		this.status = status;
	}

	public Integer getShouldTakeMl() {
		return shouldTakeMl;
	}

	public void setShouldTakeMl(Integer shouldTakeMl) {
		this.shouldTakeMl = shouldTakeMl;
	}

	public Integer getActualTakeMl() {
		return actualTakeMl;
	}

	public void setActualTakeMl(Integer actualTakeMl) {
		this.actualTakeMl = actualTakeMl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}

	public Long getCodeExpireTime() {
		return codeExpireTime;
	}

	public void setCodeExpireTime(Long codeExpireTime) {
		this.codeExpireTime = codeExpireTime;
	}
	
	
}
