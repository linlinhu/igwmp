package laiebei.terminal.skm.msmanage.entity.bean;
/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/10 14:53.
 * 对外接口:
 */
public class HandlerBean {

	/**
	 * time : 140000000000
	 * type : 0
	 * version : 2.0.0.3
	 * sesscion : sgdsghfdhfgdjafgdsagfdsgwwss
	 * lenght : 200
	 */

	private long time;
	private int type;
	private String version;
	private String sesscion;
	private int lenght;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSesscion() {
		return sesscion;
	}

	public void setSesscion(String sesscion) {
		this.sesscion = sesscion;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
}
