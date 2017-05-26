package laiebei.terminal.dbm.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/17 15:38.
 * 对外接口:
 */
@Entity
public class Resources {
	@Id
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String version;
	private String localUrl;
	private String remoteUrl;
	private String md5;
	private String describe;

	@Generated(hash = 29425018)
	public Resources(Long id, @NotNull String name, @NotNull String version,
									String localUrl, String remoteUrl, String md5, String describe) {
					this.id = id;
					this.name = name;
					this.version = version;
					this.localUrl = localUrl;
					this.remoteUrl = remoteUrl;
					this.md5 = md5;
					this.describe = describe;
	}

	@Generated(hash = 46520690)
	public Resources() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
