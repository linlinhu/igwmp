package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;


/**
 *
 */
@Table(schema = "prdm", name = "t_machine_app_version_control")
@Entity
public class AppVersionControlVO extends BaseEntity implements UndeleteableEntity {

    /**
     * @Fields serialVersionUID : (序列化)
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "create_user")
    private String createUser;

    /**
     * 当前版本
     */
    @Column(name = "now_version")
    private String nowVersion;

    /**
     * apk 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 机器id
     */
    @Column(name = "machine_info_id")
    private String machineInfoId;


    @Id
    @Override
    @SequenceGenerator(name = "machine_control_id_seq", sequenceName = "prdm.machine_control_id_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_control_id_seq") //写明使用哪个序列生成器
    public Long getId() {
        return super.getId();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getNowVersion() {
        return nowVersion;
    }

    public void setNowVersion(String nowVersion) {
        this.nowVersion = nowVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMachineInfoId() {
        return machineInfoId;
    }

    public void setMachineInfoId(String machineInfoId) {
        this.machineInfoId = machineInfoId;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public Long getCreateTime() {
        return null;
    }

    @Override
    public void setCreateTime(Long aLong) {

    }

    @Override
    public Long getLastModifyTime() {
        return null;
    }

    @Override
    public void setLastModifyTime(Long aLong) {

    }
}
