package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;

@Entity
@Table(schema="public",name="redpack_conf")
public class RedPackConf extends BaseEntity{
	
		private static final long serialVersionUID = 4965793186951418459L;
		//裂变红包
		public static final String URL_GROUPREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
		//普通红包
		public static final String URL_REDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
		
		public static final String PROP_CODE = "code";
		
		public static final String PROP_WOA_ID = "woaId";
		
		private Long woaId;//公众号
		
		private String actCode;//活动代码
		
		private Integer type;//类型 1：普通红包 2：裂变红包
		
		private Integer totalNum;//单组红包个数
		
		private Double totalAmount;//红包总金额
		
		private Double minAmount;//最小金额
		
		private Double maxAmount;//最大金额
		
		private Integer maxRedPack;//最大组数
		
		private String actName;//活动名称
		
		private String wishing;//红包祝福语
		
		private String remark;//备注
		
		private String overText;//
		
		private String limitText;//
		
		private Boolean enable;//是否启用
		
		@Id
		@Override
		@SequenceGenerator(name = "redpack_conf_id_seq", sequenceName = "public.redpack_conf_id_seq", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "redpack_conf_id_seq")
		public Long getId() {
			return super.getId();
		}
		@Column(name="act_code")
		public String getActCode() {
			return actCode;
		}
		
		public void setActCode(String actCode) {
			this.actCode = actCode;
		}
		@Column(name="type")
		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		@Column(name="total_num")
		public Integer getTotalNum() {
			return totalNum;
		}

		public void setTotalNum(Integer totalNum) {
			this.totalNum = totalNum;
		}
		@Column(name="total_amount")
		public Double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		@Column(name="min_amount")
		public Double getMinAmount() {
			return minAmount;
		}
		
		public void setMinAmount(Double minAmount) {
			this.minAmount = minAmount;
		}
		@Column(name="max_amount")
		public Double getMaxAmount() {
			return maxAmount;
		}
		
		public void setMaxAmount(Double maxAmount) {
			this.maxAmount = maxAmount;
		}
		@Column(name="max_redpack")
		public Integer getMaxRedPack() {
			return maxRedPack;
		}

		public void setMaxRedPack(Integer maxRedPack) {
			this.maxRedPack = maxRedPack;
		}
		@Column(name="act_name")
		public String getActName() {
			return actName;
		}

		public void setActName(String actName) {
			this.actName = actName;
		}
		@Column(name="wishing")
		public String getWishing() {
			return wishing;
		}

		public void setWishing(String wishing) {
			this.wishing = wishing;
		}
		@Column(name="remark")
		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		@Column(name="over_text")
		public String getOverText() {
			return overText;
		}

		public void setOverText(String overText) {
			this.overText = overText;
		}
		@Column(name="limit_text")
		public String getLimitText() {
			return limitText;
		}

		public void setLimitText(String limitText) {
			this.limitText = limitText;
		}
		@Column(name="enable")
		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}
		@Column(name="woaid")
		public Long getWoaId() {
			return woaId;
		}
		public void setWoaId(Long woaId) {
			this.woaId = woaId;
		}
		
		
}
