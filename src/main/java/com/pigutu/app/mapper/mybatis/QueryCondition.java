package com.pigutu.app.mapper.mybatis;

/**
 * Desc: 分页和排序等条件
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/13
 */
public class QueryCondition {

    private Integer start;
    private Integer offset;
    private OrderBy[] orderFields;

    private boolean isLimit = false;
    private boolean isOrderBy = false;

    /**
     * 设置分页条件
     */
    public QueryCondition setLimit(Integer start, Integer offset) {
        if (start == null || offset == null) {
            throw new NullPointerException("start and offset can not be null");
        }
        this.isLimit = true;
        this.start = start;
        this.offset = offset;
        return this;
    }

    /**
     * 分页的方式设置start和offset
     */
    public QueryCondition setPaging(Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageSize == null) {
            throw new NullPointerException("pageNo and pageSize can not be null");
        }
        this.isLimit = true;
        this.start = pageSize * (pageNo - 1);
        this.offset = pageSize;
        return this;
    }

    /**
     * 设置排序字段
     */
    public QueryCondition setOrderBy(OrderBy... orderFields) {
        if (orderFields == null || orderFields.length == 0) {
            throw new NullPointerException("orderFields can not be null or empty");
        }
        this.isOrderBy = true;
        this.orderFields = orderFields;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getOffset() {
        return offset;
    }

    public OrderBy[] getOrderFields() {
        return orderFields;
    }

    public boolean isLimit() {
        return isLimit;
    }

    public boolean isOrderBy() {
        return isOrderBy;
    }
}
