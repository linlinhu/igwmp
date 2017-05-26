package com.emin.wxs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.emin.base.util.EminEnvUtil;
@Entity
@Table(schema="public",name="wxreply_event")
public class WeixinEventReply extends BaseEntity implements UndeleteableEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5014094624858930167L;
	public static final String REPLY_TYPE_TEXT = "text";
	public static final String REPLY_TYPE_NEWS = "news";
	public static final String PROP_EVENTKEY = "eventKey";
	public static final String PROP_WOAID = "woaId";
	private String event;
	private Long woaId;
	private String eventKey;
	private String replyType;
	private String rContent;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private List<WxArticle> articles;
	@Id
	@Override
	@SequenceGenerator(name = "wxreply_event_id_seq", sequenceName = "public.wxreply_event_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxreply_event_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="event")
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
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
    @JoinTable(schema="public",name = "wxreplye_article", 
            joinColumns = {@JoinColumn(name = "replye_id")}, 
            inverseJoinColumns = { @JoinColumn(name = "article_id") })
    @OrderBy("size")
    @Fetch(FetchMode.SELECT)
	public List<WxArticle> getArticles() {
		return articles;
	}
	public void setArticles(List<WxArticle> articles) {
		this.articles = articles;
	}
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
