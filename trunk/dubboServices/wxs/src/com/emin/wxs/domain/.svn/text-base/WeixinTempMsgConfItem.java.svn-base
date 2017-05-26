package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
@Entity
@Table(schema="public",name="wxtmpmsg_conf_item")
public class WeixinTempMsgConfItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -981662157102986708L;
	public static final String PROP_CONFID = "confId";
	private Long confId;
	private String keynote;
	private String value;
	private String color;
	private String remark;	
	private WeixinTempMsgConf conf;
	@Id
	@Override
	@SequenceGenerator(name = "wxtmpmsg_conf_item_id_seq", sequenceName = "public.wxtmpmsg_conf_item_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxtmpmsg_conf_item_id_seq")
	public Long getId() {
		return super.getId();
	}
	
	@Column(name="conf_id",insertable=false,updatable=false)
	public Long getConfId() {
		return confId;
	}
	
	public void setConfId(Long confId) {
		this.confId = confId;
	}
	@Column(name="keynote")
	public String getKeynote() {
		return keynote;
	}

	public void setKeynote(String keynote) {
		this.keynote = keynote;
	}
	@Column(name="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="color")
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@ManyToOne
	@JoinColumn(name="conf_id")
	public WeixinTempMsgConf getConf() {
		return conf;
	}

	public void setConf(WeixinTempMsgConf conf) {
		this.conf = conf;
	}
	
}
