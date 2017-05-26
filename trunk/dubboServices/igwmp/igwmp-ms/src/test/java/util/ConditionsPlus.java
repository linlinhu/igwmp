package util;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.PropertyFilter;
import com.emin.base.dao.PropertyFilter.MatchType;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;

import java.util.ArrayList;
import java.util.List;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
public class ConditionsPlus {

    public static PreFilter convertToPropertyFilter(ConditionPlus condition) {
        if (condition == null) {
            throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
        }
        MatchType matchType = null;
        PreFilter preFilterOr = null;
        String propertyName = condition.getPropertyName();
        if (checkValid(condition)) {
            switch (condition.getOperator()) {
                case BETWEEN:
                    matchType = MatchType.BETWEEN;
                    break;
                case LIKE:
                    String value = (String) condition.getValues()[0];
                    if (value.lastIndexOf("%") == 0
                            || value.lastIndexOf("%") == value.length() - 1) {
                        matchType = MatchType.ELIKE;
                    } else {
                        matchType = MatchType.LIKE;
                    }
                    break;
                case EQ:
                    if (condition.getValues() == null
                            || condition.getValues()[0] == null) {
                        matchType = MatchType.ISNULL;
                    } else {
                        matchType = MatchType.EQ;
                    }
                    break;
                case LT:
                    matchType = MatchType.LT;
                    break;
                case LE:
                    matchType = MatchType.LE;
                    break;
                case GT:
                    matchType = MatchType.GT;
                    break;
                case GE:
                    matchType = MatchType.GE;
                    break;
                case IN:
                    matchType = MatchType.IN;
                    break;
                case NE:
                    if (condition.getValues() == null || condition.getValues().length == 0 || condition.getValues()[0] == null) {
                        matchType = MatchType.ISNOTNULL;
                    } else {
                        matchType = MatchType.NE;
                    }
                    break;
                case OR:
                    List<ConditionPlus> plusList = condition.getPlusList();
                    List<PreFilter> filterList = new ArrayList<>();
                    for (ConditionPlus plus : plusList) {
                        switch (plus.getOperator()) {
                            case LIKE:
                                filterList.add(PreFilters.like(plus.getPropertyName(), "%" + plus.getValues()[0].toString() + "%"));
                                break;
                            case EQ:
                                filterList.add(PreFilters.eq(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case IN:
                                filterList.add(PreFilters.in(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case LT:
                                filterList.add(PreFilters.lt(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case LE:
                                filterList.add(PreFilters.le(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case GT:
                                filterList.add(PreFilters.gt(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case GE:
                                filterList.add(PreFilters.ge(plus.getPropertyName(), plus.getValues()[0].toString()));
                                break;
                            case BETWEEN:
                                filterList.add(PreFilters.between(plus.getPropertyName(), plus.getValues()[0].toString(), plus.getValues()[1]));
                                break;
                        }
                    }
                    preFilterOr = PreFilters.or(filterList.toArray(new PreFilter[]{}));
                    break;
            }

            if(preFilterOr != null){
                return preFilterOr;
            }
            return new PropertyFilter(propertyName, matchType, condition.getValues());
        }

        return null;
    }

    private static boolean checkValid(ConditionPlus condition) {
        boolean isValid = true;
        switch (condition.getType()) {
            case CHARACTER:
                if (condition.getOperator() == ConditionPlus.ConditionOperator.BETWEEN) {
                    isValid = false;
                }
                break;
            case OTHER:
                if (condition.getOperator() == ConditionPlus.ConditionOperator.LIKE) {
                    isValid = false;
                }
                break;
        }
        return isValid;
    }
}
