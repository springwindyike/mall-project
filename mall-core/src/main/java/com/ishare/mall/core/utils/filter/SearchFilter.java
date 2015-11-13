package com.ishare.mall.core.utils.filter;

import com.google.common.collect.Maps;
import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.utils.DateUntil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created by dongqi on 15/7/24.
 */
public class SearchFilter {
    private static final Logger log = LoggerFactory.getLogger(SearchFilter.class);
    public String fieldName;
    public Object value;
    public Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {

        if (searchParams == null) return null;

        Map<String, SearchFilter> filters = Maps.newHashMap();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (StringUtils.isBlank(String.valueOf(value))) {
                continue;
            }
            if(CommonConstant.Emputy.EMPUTY_NULL.equals(value)){
                value = null;
            }
            log.debug("key={}, value=[{}]", key, value);

            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            if (filedName.toLowerCase().contains("time")) {
                value = DateTime.parse(value.toString()).toDate();
                if (operator.equals(Operator.LTE)){
                    value = DateUntil.getTheEndOfDay((Date)value);
                }
            }
            if(filedName.toLowerCase().contains("date")){
                value = DateTime.parse(value.toString()).toDate();
                if (operator.equals(Operator.LTE)){
                    value = DateUntil.getTheEndOfDay((Date)value);
                }
            }
            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }

    public enum Operator {
        EQ, LIKE, START, END, GT, LT, GTE, LTE ,NEQ,NN//等于,like,%前， %后，大于,小于，大于等于，小于等于,not null
    }
}
