package com.emin.wxs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

@Entity
@Table(schema="public",name="wxreply_msg")
public class WeixinKeyReply extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -825616354787871224L;
	public static final String REPLY_TYPE_TEXT = "text";
	public static final String REPLY_TYPE_NEWS = "news";
	public static final String PROP_CONTENT = "content";
	public static final String PROP_PREFERENCES = "preferences";
	public static final String PROP_WOAID = "woaId";
	private String msgType ="text";
	private Long woaId;
	private String content;
	private String replyType;
	private String rContent;
	private String remark;
	private Boolean preferences;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private List<WxArticle> articles;
	
	@Id
	@Override
	@SequenceGenerator(name = "wxreply_msg_id_seq", sequenceName = "public.wxreply_msg_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxreply_msg_id_seq")
	public Long getId() {
		return super.getId();
	} 
	
	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(schema="public",name = "wxreplym_article", 
            joinColumns = {@JoinColumn(name = "replym_id")}, 
            inverseJoinColumns = { @JoinColumn(name = "article_id") })
    @OrderBy("size")
    @Fetch(FetchMode.SELECT)
	public List<WxArticle> getArticles() {
		return articles;
	}
	public void setArticles(List<WxArticle> articles) {
		this.articles = articles;
	}



	public String getMsgType() {
		return msgType;
	}



	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getPreferences() {
		return preferences;
	}

	public void setPreferences(Boolean preferences) {
		this.preferences = preferences;
	}

	public Long getWoaId() {
		return woaId;
	}

	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
