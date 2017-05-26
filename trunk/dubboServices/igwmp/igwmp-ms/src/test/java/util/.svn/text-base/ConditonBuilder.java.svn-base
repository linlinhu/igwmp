package util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * user : shimingliang
 * time : 2017/4/1
 * des :
 */
public class ConditonBuilder {


    private static String buildSqlCondition(ConditionPlus condition) {
        StringBuffer stringBuffer = new StringBuffer();
        Object value = "";
        if (condition.getValues() != null && condition.getValues().length > 0) {
            value = condition.getValues()[0];
        }
        switch (condition.getOperator()) {
            case BETWEEN:
                stringBuffer.append(" and " + condition.getPropertyName() + " BETWEEN " + condition.getValues()[0] + " and " + condition.getValues()[1]);
                break;
            case LIKE:
                String value1 = (String) condition.getValues()[0];
                if (value1.lastIndexOf("%") == 0
                        || value1.lastIndexOf("%") == value1.length() - 1) {
                    stringBuffer.append(" and " + condition.getPropertyName() + " like '" + value +"'");
                } else {
                    stringBuffer.append(" and " + condition.getPropertyName() + " like '%" + value + "%'");
                }
                break;
            case EQ:
                if (condition.getValues() == null
                        || condition.getValues()[0] == null) {
                    stringBuffer.append(" and " + condition.getPropertyName() + " is null");
                } else {
                    stringBuffer.append(" and " + condition.getPropertyName() + "=" + value);
                }
                break;
            case LT:
                stringBuffer.append(" and " + condition.getPropertyName() + "<" + value);
                break;
            case LE:
                stringBuffer.append(" and " + condition.getPropertyName() + "<=" + value);
                break;
            case GT:
                stringBuffer.append(" and " + condition.getPropertyName() + ">" + value);
                break;
            case GE:
                stringBuffer.append(" and " + condition.getPropertyName() + ">=" + value);
                break;
            case IN:
                stringBuffer.append(" and " + condition.getPropertyName() + " in(" + String.format("%s,%s,%s", value) + ")");
                break;
            case NE:
                if (condition.getValues() == null || condition.getValues().length == 0 || condition.getValues()[0] == null) {
                    stringBuffer.append(" and " + condition.getPropertyName() + " is not null");
                } else {
                    stringBuffer.append(" and " + condition.getPropertyName() + " != " + value);
                }
                break;
            case OR:
                if (condition.getPlusList() != null && condition.getPlusList().size() > 0) {
                    String r = sqlCondition(condition.getPlusList());
                    stringBuffer.append(" and (" + r.replaceAll("and", "or") + ") ");
                }
                break;
        }

        return stringBuffer.toString();
    }

    /**
     * 把查询条件拼接成可执行的sql
     *
     * @param condition
     * @return
     */
    public static String sqlCondition(List<ConditionPlus> condition) {

        if (condition == null || condition.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (ConditionPlus conditionPlus : condition) {
            if (!StringUtils.isBlank(buildSqlCondition(conditionPlus))) {
                stringBuffer.append(buildSqlCondition(conditionPlus));
            }
        }
        String c = "";
        if (stringBuffer.length() > 0) {
            c = stringBuffer.toString().substring(4);
        }
        return c;
    }
}
