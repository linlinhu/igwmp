package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;
import java.util.Date;


@Table(schema = "prdm", name = "t_machine_video")
@Entity
public class MachineVideoVO extends BaseEntity implements UndeleteableEntity {

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
     * 机器id
     */
    @Column(name = "machine_info_id")
    private Long machineInfoId;

    /**
     * 视频url
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 音频url
     */
    @Column(name = "voice_url")
    private String voiceUrl;

    /**
     * 图片url
     */
    @Column(name = "picture_url")
    private String pictureUrl;

    /**
     * 开始日期
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束日期 一直播放视频（默认值为 3016.01.01.00.00.00）
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 播放顺序
     */
    @Column(name = "play_order")
    private Integer playOrder;

    /**
     * 视频版本 1、在线情况 平台主动发起
     * 2、离线情况 终端发起（开机自检[发送ip信息]）
     */
    @Column(name = "video_version")
    private String videoVersion;

    /**
     * 音频版本
     */
    @Column(name = "voice_version")
    private String voiceVersion;

    /**
     * 图片版本
     */
    @Column(name = "picture_version")
    private String pictureVersion;

    /**
     * 播放节点
     * 1.唤醒
     * 2.支付完成后 待取酒界面
     * 3.取酒完成
     */
    @Column(name = "play_node")
    private String playNode;

    /**
     * 0、不存在
     * 1、存在
     */
    @Column(name = "or_exit")
    private Integer orExit;

    /**
     * 播放次数
     */
    @Column(name = "play_count")
    private Integer playCount;

    /**
     * 播放时间
     */
    @Column(name = "playTime")
    private Date playTime;


    @Id
    @Override
    @SequenceGenerator(name = "machine_video_id_seq", sequenceName = "prdm.machine_video_id_seq", allocationSize = 1)
    //name是这个序列生成器的代号，sequenceName是要存入数据库的序列的名字
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_video_id_seq") //写明使用哪个序列生成器
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

    public Long getMachineInfoId() {
        return machineInfoId;
    }

    public void setMachineInfoId(Long machineInfoId) {
        this.machineInfoId = machineInfoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPlayOrder() {
        return playOrder;
    }

    public void setPlayOrder(Integer playOrder) {
        this.playOrder = playOrder;
    }

    public String getVideoVersion() {
        return videoVersion;
    }

    public void setVideoVersion(String videoVersion) {
        this.videoVersion = videoVersion;
    }

    public String getVoiceVersion() {
        return voiceVersion;
    }

    public void setVoiceVersion(String voiceVersion) {
        this.voiceVersion = voiceVersion;
    }

    public String getPictureVersion() {
        return pictureVersion;
    }

    public void setPictureVersion(String pictureVersion) {
        this.pictureVersion = pictureVersion;
    }

    public String getPlayNode() {
        return playNode;
    }

    public void setPlayNode(String playNode) {
        this.playNode = playNode;
    }

    public Integer getOrExit() {
        return orExit;
    }

    public void setOrExit(Integer orExit) {
        this.orExit = orExit;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
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
