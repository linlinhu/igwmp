package laiebei.terminal.dbm.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/23 17:58.
 * 对外接口:
 */

@Entity
public class ChannelInfo {
	@Id
	private Long id;
	private Long channel;//通道号
	private Long cabinetId;
	private int sense;//通道计量脉冲值
	private int pump;//通道脉冲间隔

	@Generated(hash = 841311950)
	public ChannelInfo(Long id, Long channel, Long cabinetId, int sense, int pump) {
					this.id = id;
					this.channel = channel;
					this.cabinetId = cabinetId;
					this.sense = sense;
					this.pump = pump;
	}

	@Generated(hash = 1609160491)
	public ChannelInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannel() {
		return channel;
	}

	public void setChannel(Long channel) {
		this.channel = channel;
	}

	public Long getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(Long cabinetId) {
		this.cabinetId = cabinetId;
	}

	public int getSense() {
		return sense;
	}

	public void setSense(int sense) {
		this.sense = sense;
	}

	public int getPump() {
		return pump;
	}

	public void setPump(int pump) {
		this.pump = pump;
	}
}
