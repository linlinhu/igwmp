package temp;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;

@Table(schema = "prdm", name = "t_machine_error_dictionary")
@Entity
public class MachineErrorDictionaryVO extends BaseEntity implements UndeleteableEntity {

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
     * 1.工控机故障
     * 2.单片机故障
     * 3.白酒机故障    4.程序故障
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 故障排除方法
     */
    @Column(name = "remove_method")
    private String removeMethod;

    /**
     * 故障原因
     */
    @Column(name = "fault_cause")
    private String faultCause;

    /**
     * 1.定位程序的类和方法
     * 2.只对技术人员暴露
     */
    @Column(name = "procedure_url")
    private String procedureUrl;

    /**
     * 故障名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 故障等级标准待定
     */
    @Column(name = "error_grade")
    private String errorGrade;

    @Id
    @Override
    @SequenceGenerator(name = "machine_error_dictionary_id_seq", sequenceName = "prdm.machine_error_dictionary_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_error_dictionary_id_seq")
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemoveMethod() {
        return removeMethod;
    }

    public void setRemoveMethod(String removeMethod) {
        this.removeMethod = removeMethod;
    }

    public String getFaultCause() {
        return faultCause;
    }

    public void setFaultCause(String faultCause) {
        this.faultCause = faultCause;
    }

    public String getProcedureUrl() {
        return procedureUrl;
    }

    public void setProcedureUrl(String procedureUrl) {
        this.procedureUrl = procedureUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getErrorGrade() {
        return errorGrade;
    }

    public void setErrorGrade(String errorGrade) {
        this.errorGrade = errorGrade;
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
