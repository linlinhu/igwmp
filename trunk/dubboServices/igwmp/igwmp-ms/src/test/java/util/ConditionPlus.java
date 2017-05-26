package util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
public class ConditionPlus implements Serializable {

    private static final long serialVersionUID = 7424955449650547378L;
    private String propertyName;
    private ConditionOperator operator;
    private ConditionType type;
    private Object[] values;
    private List<ConditionPlus> plusList;

    public enum ConditionOperator {
        /**
         * like '%value%'
         * if the value contain % then use the value as the match string
         * else append the %
         * NOTE: 对应的ConditionType 必须是CHARACTER
         */
        LIKE,
        IN,
        /**
         * 等于
         */
        EQ,
        /**
         * 小于
         */
        LT,
        /**
         * 小于等于
         */
        LE,
        /**
         * 大于
         */
        GT,
        /**
         * 大于等于
         */
        GE,
        BETWEEN,
        OR,
        NE;

    }

    public enum ConditionType {
        CHARACTER,
        OTHER;
    }

    public ConditionPlus(String propertyName, ConditionOperator operator, ConditionType type, Object... values) {
        this.operator = operator;
        this.type = type;
        this.setPropertyName(propertyName);
        this.setValues(values);
    }

    public ConditionPlus(ConditionOperator operator,ConditionType type, List<ConditionPlus> plusList) {
        this.operator = operator;
        this.plusList = plusList;
        this.type = type;
    }

    public List<ConditionPlus> getPlusList() {
        return plusList;
    }

    public void setPlusList(List<ConditionPlus> plusList) {
        this.plusList = plusList;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setValues(Object[] values) {
        if (values != null) {
            this.values = Arrays.copyOf(values, values.length);
        } else {
            this.values = null;
        }

    }

    public Object[] getValues() {
        if (this.values != null) {
            return Arrays.copyOf(this.values, this.values.length);
        }
        return null;
    }

    public void setOperator(ConditionOperator operator) {
        this.operator = operator;
    }

    public ConditionOperator getOperator() {
        return operator;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public ConditionType getType() {
        return type;
    }
}
