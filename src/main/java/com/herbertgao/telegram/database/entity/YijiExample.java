package com.herbertgao.telegram.database.entity;

import java.util.ArrayList;
import java.util.List;

public class YijiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YijiExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCatalogIsNull() {
            addCriterion("`catalog` is null");
            return (Criteria) this;
        }

        public Criteria andCatalogIsNotNull() {
            addCriterion("`catalog` is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogEqualTo(String value) {
            addCriterion("`catalog` =", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotEqualTo(String value) {
            addCriterion("`catalog` <>", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogGreaterThan(String value) {
            addCriterion("`catalog` >", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("`catalog` >=", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLessThan(String value) {
            addCriterion("`catalog` <", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLessThanOrEqualTo(String value) {
            addCriterion("`catalog` <=", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogLike(String value) {
            addCriterion("`catalog` like", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotLike(String value) {
            addCriterion("`catalog` not like", value, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogIn(List<String> values) {
            addCriterion("`catalog` in", values, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotIn(List<String> values) {
            addCriterion("`catalog` not in", values, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogBetween(String value1, String value2) {
            addCriterion("`catalog` between", value1, value2, "catalog");
            return (Criteria) this;
        }

        public Criteria andCatalogNotBetween(String value1, String value2) {
            addCriterion("`catalog` not between", value1, value2, "catalog");
            return (Criteria) this;
        }

        public Criteria andYiIsNull() {
            addCriterion("yi is null");
            return (Criteria) this;
        }

        public Criteria andYiIsNotNull() {
            addCriterion("yi is not null");
            return (Criteria) this;
        }

        public Criteria andYiEqualTo(String value) {
            addCriterion("yi =", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotEqualTo(String value) {
            addCriterion("yi <>", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiGreaterThan(String value) {
            addCriterion("yi >", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiGreaterThanOrEqualTo(String value) {
            addCriterion("yi >=", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLessThan(String value) {
            addCriterion("yi <", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLessThanOrEqualTo(String value) {
            addCriterion("yi <=", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLike(String value) {
            addCriterion("yi like", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotLike(String value) {
            addCriterion("yi not like", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiIn(List<String> values) {
            addCriterion("yi in", values, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotIn(List<String> values) {
            addCriterion("yi not in", values, "yi");
            return (Criteria) this;
        }

        public Criteria andYiBetween(String value1, String value2) {
            addCriterion("yi between", value1, value2, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotBetween(String value1, String value2) {
            addCriterion("yi not between", value1, value2, "yi");
            return (Criteria) this;
        }

        public Criteria andJiIsNull() {
            addCriterion("ji is null");
            return (Criteria) this;
        }

        public Criteria andJiIsNotNull() {
            addCriterion("ji is not null");
            return (Criteria) this;
        }

        public Criteria andJiEqualTo(String value) {
            addCriterion("ji =", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotEqualTo(String value) {
            addCriterion("ji <>", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiGreaterThan(String value) {
            addCriterion("ji >", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiGreaterThanOrEqualTo(String value) {
            addCriterion("ji >=", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLessThan(String value) {
            addCriterion("ji <", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLessThanOrEqualTo(String value) {
            addCriterion("ji <=", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLike(String value) {
            addCriterion("ji like", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotLike(String value) {
            addCriterion("ji not like", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiIn(List<String> values) {
            addCriterion("ji in", values, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotIn(List<String> values) {
            addCriterion("ji not in", values, "ji");
            return (Criteria) this;
        }

        public Criteria andJiBetween(String value1, String value2) {
            addCriterion("ji between", value1, value2, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotBetween(String value1, String value2) {
            addCriterion("ji not between", value1, value2, "ji");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}