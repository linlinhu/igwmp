package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;

/**
 * User: shimingliang
 * Date: 2017/3/9
 * Time: 16:33
 * <p>
 * 机器app 版本升级日志记录表
 */
@Table(schema = "prdm", name = "t_machine_app_version_info")
@Entity
public class MachineVersionLog extends BaseEntity implements UndeleteableEntity {


    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "create_user")
    private String createUser;
    /**
     * 版本号
     */
    @Column(name = "old_version_code")
    private String oldVersionCode;

    /**
     * 版本名称
     */
    @Column(name = "old_version_name")
    private String oldVersionName;

    /**
     * 版本号
     */
    @Column(name = "new_version_code")
    private String newVersionCode;

    /**
     * 版本名称
     */
    @Column(name = "new_version_name")
    private String newVersionName;

    /**
     * 机器编码 （BD3101640000173）用作识别机器
     */
    @Column(name = "machine_code")
    private String machineCode;

    /**
     * apk升级类型（监控，主程序）
     */
    @Column(name = "apk_type")
    private String apkType;

    /**
     * apk文件名
     */
    @Column(name = "apk_file_name")
    private String apkFileName;

    /**
     * 版本信息 MD5
     */
    @Column(name = "md5")
    private String md5;

    /**
     * 升级描述信息
     */
    @Column(name = "description")
    private String description;


    @Id
    @Override
    @SequenceGenerator(name = "verison_log_id_seq", sequenceName = "prdm.verison_log_id_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verison_log_id_seq") //写明使用哪个序列生成器
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

    public String getOldVersionCode() {
        return oldVersionCode;
    }

    public void setOldVersionCode(String oldVersionCode) {
        this.oldVersionCode = oldVersionCode;
    }

    public String getOldVersionName() {
        return oldVersionName;
    }

    public void setOldVersionName(String oldVersionName) {
        this.oldVersionName = oldVersionName;
    }

    public String getNewVersionCode() {
        return newVersionCode;
    }

    public void setNewVersionCode(String newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public String getNewVersionName() {
        return newVersionName;
    }

    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
