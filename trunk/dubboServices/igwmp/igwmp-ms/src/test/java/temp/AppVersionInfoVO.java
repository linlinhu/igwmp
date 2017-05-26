package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;
import java.util.Date;

@Table(schema = "prdm", name = "t_machine_app_version_info")
@Entity
public class AppVersionInfoVO extends BaseEntity implements UndeleteableEntity {

    /**
     * @Fields serialVersionUID : (序列化)
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "create_user")
    private String createUser;
    /**
     * 版本号
     */
    @Column(name = "version_code")
    private String versionCode;

    /**
     * 版本名称
     */
    @Column(name = "version_name")
    private String versionName;

    /**
     * 运行类型（监控，主程序）
     */
    @Column(name = "apk_type")
    private String apkType;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 包名
     */
    @Column(name = "packet_name")
    private String packetName;

    /**
     * apk文件名
     */
    @Column(name = "apk_file_name")
    private String apkFileName;

    /**
     * apk服务器路径
     */
    @Column(name = "url")
    private String url;

    /**
     * MD5
     */
    @Column(name = "md5")
    private String md5;

    /**
     * 升级描述信息
     */
    @Column(name = "description")
    private String description;

    /**
     * 是否全升级
     */
    @Column(name = "all_update")
    private String allUpdate;

    @Id
    @Override
    @SequenceGenerator(name = "machine_version_info_id_seq", sequenceName = "prdm.machine_version_info_id_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_version_info_id_seq") //写明使用哪个序列生成器
    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getAllUpdate() {
        return allUpdate;
    }

    public void setAllUpdate(String allUpdate) {
        this.allUpdate = allUpdate;
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
