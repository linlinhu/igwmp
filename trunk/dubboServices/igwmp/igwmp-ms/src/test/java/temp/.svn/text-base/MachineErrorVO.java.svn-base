package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;
import java.util.Date;

@Table(schema = "prdm", name = "t_machine_error")
@Entity
public class MachineErrorVO extends BaseEntity implements UndeleteableEntity {

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
     * 售酒终_机器id
     */
    @Column(name = "machine_info_id")
    private String machineInfoId;

    /**
     *  故障对应故障字典表ID（MachineErrorDictionaryVO）
     */
    @Column(name = "error_dictionary_id")
    private String ErrorDictionaryId;

    /**
     * 故障原因描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 故障发现时间
     */
    @Column(name = "error_start_time")
    private Date errorStartTime;

    /**
     * 故障排除时间
     */
    @Column(name = "error_end_time")
    private Date errorEndTime;

    /**
     * 故障发现人
     */
    @Column(name = "error_find_people")
    private String errorFindPeople;

    /**
     * 故障排除人
     */
    @Column(name = "error_handle_people")
    private String errorHandlePeople;

    /**
     * 故障等级标准待定
     */
    @Column(name = "error_grade")
    private Integer errorGrade;

    /**
     * 故障错误字典表  (t_machine_error_dictionary)
     */
    @Column(name = "error_dic_id")
    private Long errorDicId;


    @Id
    @Override
    @SequenceGenerator(name = "machine_error_id_seq", sequenceName = "prdm.machine_error_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_error_id_seq")
    public Long getId() {
        return super.getId();
    }

    public String getErrorDictionaryId() {
        return ErrorDictionaryId;
    }

    public void setErrorDictionaryId(String errorDictionaryId) {
        ErrorDictionaryId = errorDictionaryId;
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

    public String getMachineInfoId() {
        return machineInfoId;
    }

    public void setMachineInfoId(String machineInfoId) {
        this.machineInfoId = machineInfoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getErrorStartTime() {
        return errorStartTime;
    }

    public void setErrorStartTime(Date errorStartTime) {
        this.errorStartTime = errorStartTime;
    }

    public Date getErrorEndTime() {
        return errorEndTime;
    }

    public void setErrorEndTime(Date errorEndTime) {
        this.errorEndTime = errorEndTime;
    }

    public String getErrorFindPeople() {
        return errorFindPeople;
    }

    public void setErrorFindPeople(String errorFindPeople) {
        this.errorFindPeople = errorFindPeople;
    }

    public String getErrorHandlePeople() {
        return errorHandlePeople;
    }

    public void setErrorHandlePeople(String errorHandlePeople) {
        this.errorHandlePeople = errorHandlePeople;
    }

    public Integer getErrorGrade() {
        return errorGrade;
    }

    public void setErrorGrade(Integer errorGrade) {
        this.errorGrade = errorGrade;
    }

    public Long getErrorDicId() {
        return errorDicId;
    }

    public void setErrorDicId(Long errorDicId) {
        this.errorDicId = errorDicId;
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
