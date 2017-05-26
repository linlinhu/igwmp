package laiebei.terminal.dbm.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/7.
 */
@Entity
public class Session {
    @Id
    private String ipcCode;
    @NotNull
    private String session;
    private Long creatTime;
    private Long updateTime;

    @Generated(hash = 1824974714)
    public Session(String ipcCode, @NotNull String session, Long creatTime,
            Long updateTime) {
        this.ipcCode = ipcCode;
        this.session = session;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1317889643)
    public Session() {
    }

    public String getIpcCode() {
        return ipcCode;
    }

    public void setIpcCode(String ipcCode) {
        this.ipcCode = ipcCode;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
