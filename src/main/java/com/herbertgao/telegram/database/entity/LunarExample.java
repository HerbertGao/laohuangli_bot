package com.herbertgao.telegram.database.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LunarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LunarExample() {
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

        public Criteria andGregoriandatetimeIsNull() {
            addCriterion("GregorianDateTime is null");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeIsNotNull() {
            addCriterion("GregorianDateTime is not null");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeEqualTo(LocalDate value) {
            addCriterion("GregorianDateTime =", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeNotEqualTo(LocalDate value) {
            addCriterion("GregorianDateTime <>", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeGreaterThan(LocalDate value) {
            addCriterion("GregorianDateTime >", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeGreaterThanOrEqualTo(LocalDate value) {
            addCriterion("GregorianDateTime >=", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeLessThan(LocalDate value) {
            addCriterion("GregorianDateTime <", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeLessThanOrEqualTo(LocalDate value) {
            addCriterion("GregorianDateTime <=", value, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeIn(List<LocalDate> values) {
            addCriterion("GregorianDateTime in", values, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeNotIn(List<LocalDate> values) {
            addCriterion("GregorianDateTime not in", values, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeBetween(LocalDate value1, LocalDate value2) {
            addCriterion("GregorianDateTime between", value1, value2, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andGregoriandatetimeNotBetween(LocalDate value1, LocalDate value2) {
            addCriterion("GregorianDateTime not between", value1, value2, "gregoriandatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeIsNull() {
            addCriterion("LunarDateTime is null");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeIsNotNull() {
            addCriterion("LunarDateTime is not null");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeEqualTo(String value) {
            addCriterion("LunarDateTime =", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeNotEqualTo(String value) {
            addCriterion("LunarDateTime <>", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeGreaterThan(String value) {
            addCriterion("LunarDateTime >", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("LunarDateTime >=", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeLessThan(String value) {
            addCriterion("LunarDateTime <", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeLessThanOrEqualTo(String value) {
            addCriterion("LunarDateTime <=", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeLike(String value) {
            addCriterion("LunarDateTime like", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeNotLike(String value) {
            addCriterion("LunarDateTime not like", value, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeIn(List<String> values) {
            addCriterion("LunarDateTime in", values, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeNotIn(List<String> values) {
            addCriterion("LunarDateTime not in", values, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeBetween(String value1, String value2) {
            addCriterion("LunarDateTime between", value1, value2, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunardatetimeNotBetween(String value1, String value2) {
            addCriterion("LunarDateTime not between", value1, value2, "lunardatetime");
            return (Criteria) this;
        }

        public Criteria andLunarshowIsNull() {
            addCriterion("LunarShow is null");
            return (Criteria) this;
        }

        public Criteria andLunarshowIsNotNull() {
            addCriterion("LunarShow is not null");
            return (Criteria) this;
        }

        public Criteria andLunarshowEqualTo(String value) {
            addCriterion("LunarShow =", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowNotEqualTo(String value) {
            addCriterion("LunarShow <>", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowGreaterThan(String value) {
            addCriterion("LunarShow >", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowGreaterThanOrEqualTo(String value) {
            addCriterion("LunarShow >=", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowLessThan(String value) {
            addCriterion("LunarShow <", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowLessThanOrEqualTo(String value) {
            addCriterion("LunarShow <=", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowLike(String value) {
            addCriterion("LunarShow like", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowNotLike(String value) {
            addCriterion("LunarShow not like", value, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowIn(List<String> values) {
            addCriterion("LunarShow in", values, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowNotIn(List<String> values) {
            addCriterion("LunarShow not in", values, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowBetween(String value1, String value2) {
            addCriterion("LunarShow between", value1, value2, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andLunarshowNotBetween(String value1, String value2) {
            addCriterion("LunarShow not between", value1, value2, "lunarshow");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaIsNull() {
            addCriterion("IsJieJia is null");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaIsNotNull() {
            addCriterion("IsJieJia is not null");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaEqualTo(Short value) {
            addCriterion("IsJieJia =", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaNotEqualTo(Short value) {
            addCriterion("IsJieJia <>", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaGreaterThan(Short value) {
            addCriterion("IsJieJia >", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaGreaterThanOrEqualTo(Short value) {
            addCriterion("IsJieJia >=", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaLessThan(Short value) {
            addCriterion("IsJieJia <", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaLessThanOrEqualTo(Short value) {
            addCriterion("IsJieJia <=", value, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaIn(List<Short> values) {
            addCriterion("IsJieJia in", values, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaNotIn(List<Short> values) {
            addCriterion("IsJieJia not in", values, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaBetween(Short value1, Short value2) {
            addCriterion("IsJieJia between", value1, value2, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andIsjiejiaNotBetween(Short value1, Short value2) {
            addCriterion("IsJieJia not between", value1, value2, "isjiejia");
            return (Criteria) this;
        }

        public Criteria andLjieIsNull() {
            addCriterion("LJie is null");
            return (Criteria) this;
        }

        public Criteria andLjieIsNotNull() {
            addCriterion("LJie is not null");
            return (Criteria) this;
        }

        public Criteria andLjieEqualTo(String value) {
            addCriterion("LJie =", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieNotEqualTo(String value) {
            addCriterion("LJie <>", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieGreaterThan(String value) {
            addCriterion("LJie >", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieGreaterThanOrEqualTo(String value) {
            addCriterion("LJie >=", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieLessThan(String value) {
            addCriterion("LJie <", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieLessThanOrEqualTo(String value) {
            addCriterion("LJie <=", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieLike(String value) {
            addCriterion("LJie like", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieNotLike(String value) {
            addCriterion("LJie not like", value, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieIn(List<String> values) {
            addCriterion("LJie in", values, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieNotIn(List<String> values) {
            addCriterion("LJie not in", values, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieBetween(String value1, String value2) {
            addCriterion("LJie between", value1, value2, "ljie");
            return (Criteria) this;
        }

        public Criteria andLjieNotBetween(String value1, String value2) {
            addCriterion("LJie not between", value1, value2, "ljie");
            return (Criteria) this;
        }

        public Criteria andGjieIsNull() {
            addCriterion("GJie is null");
            return (Criteria) this;
        }

        public Criteria andGjieIsNotNull() {
            addCriterion("GJie is not null");
            return (Criteria) this;
        }

        public Criteria andGjieEqualTo(String value) {
            addCriterion("GJie =", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieNotEqualTo(String value) {
            addCriterion("GJie <>", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieGreaterThan(String value) {
            addCriterion("GJie >", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieGreaterThanOrEqualTo(String value) {
            addCriterion("GJie >=", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieLessThan(String value) {
            addCriterion("GJie <", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieLessThanOrEqualTo(String value) {
            addCriterion("GJie <=", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieLike(String value) {
            addCriterion("GJie like", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieNotLike(String value) {
            addCriterion("GJie not like", value, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieIn(List<String> values) {
            addCriterion("GJie in", values, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieNotIn(List<String> values) {
            addCriterion("GJie not in", values, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieBetween(String value1, String value2) {
            addCriterion("GJie between", value1, value2, "gjie");
            return (Criteria) this;
        }

        public Criteria andGjieNotBetween(String value1, String value2) {
            addCriterion("GJie not between", value1, value2, "gjie");
            return (Criteria) this;
        }

        public Criteria andYiIsNull() {
            addCriterion("Yi is null");
            return (Criteria) this;
        }

        public Criteria andYiIsNotNull() {
            addCriterion("Yi is not null");
            return (Criteria) this;
        }

        public Criteria andYiEqualTo(String value) {
            addCriterion("Yi =", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotEqualTo(String value) {
            addCriterion("Yi <>", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiGreaterThan(String value) {
            addCriterion("Yi >", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiGreaterThanOrEqualTo(String value) {
            addCriterion("Yi >=", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLessThan(String value) {
            addCriterion("Yi <", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLessThanOrEqualTo(String value) {
            addCriterion("Yi <=", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiLike(String value) {
            addCriterion("Yi like", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotLike(String value) {
            addCriterion("Yi not like", value, "yi");
            return (Criteria) this;
        }

        public Criteria andYiIn(List<String> values) {
            addCriterion("Yi in", values, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotIn(List<String> values) {
            addCriterion("Yi not in", values, "yi");
            return (Criteria) this;
        }

        public Criteria andYiBetween(String value1, String value2) {
            addCriterion("Yi between", value1, value2, "yi");
            return (Criteria) this;
        }

        public Criteria andYiNotBetween(String value1, String value2) {
            addCriterion("Yi not between", value1, value2, "yi");
            return (Criteria) this;
        }

        public Criteria andJiIsNull() {
            addCriterion("Ji is null");
            return (Criteria) this;
        }

        public Criteria andJiIsNotNull() {
            addCriterion("Ji is not null");
            return (Criteria) this;
        }

        public Criteria andJiEqualTo(String value) {
            addCriterion("Ji =", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotEqualTo(String value) {
            addCriterion("Ji <>", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiGreaterThan(String value) {
            addCriterion("Ji >", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiGreaterThanOrEqualTo(String value) {
            addCriterion("Ji >=", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLessThan(String value) {
            addCriterion("Ji <", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLessThanOrEqualTo(String value) {
            addCriterion("Ji <=", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiLike(String value) {
            addCriterion("Ji like", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotLike(String value) {
            addCriterion("Ji not like", value, "ji");
            return (Criteria) this;
        }

        public Criteria andJiIn(List<String> values) {
            addCriterion("Ji in", values, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotIn(List<String> values) {
            addCriterion("Ji not in", values, "ji");
            return (Criteria) this;
        }

        public Criteria andJiBetween(String value1, String value2) {
            addCriterion("Ji between", value1, value2, "ji");
            return (Criteria) this;
        }

        public Criteria andJiNotBetween(String value1, String value2) {
            addCriterion("Ji not between", value1, value2, "ji");
            return (Criteria) this;
        }

        public Criteria andShenweiIsNull() {
            addCriterion("ShenWei is null");
            return (Criteria) this;
        }

        public Criteria andShenweiIsNotNull() {
            addCriterion("ShenWei is not null");
            return (Criteria) this;
        }

        public Criteria andShenweiEqualTo(String value) {
            addCriterion("ShenWei =", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiNotEqualTo(String value) {
            addCriterion("ShenWei <>", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiGreaterThan(String value) {
            addCriterion("ShenWei >", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiGreaterThanOrEqualTo(String value) {
            addCriterion("ShenWei >=", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiLessThan(String value) {
            addCriterion("ShenWei <", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiLessThanOrEqualTo(String value) {
            addCriterion("ShenWei <=", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiLike(String value) {
            addCriterion("ShenWei like", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiNotLike(String value) {
            addCriterion("ShenWei not like", value, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiIn(List<String> values) {
            addCriterion("ShenWei in", values, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiNotIn(List<String> values) {
            addCriterion("ShenWei not in", values, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiBetween(String value1, String value2) {
            addCriterion("ShenWei between", value1, value2, "shenwei");
            return (Criteria) this;
        }

        public Criteria andShenweiNotBetween(String value1, String value2) {
            addCriterion("ShenWei not between", value1, value2, "shenwei");
            return (Criteria) this;
        }

        public Criteria andTaishenIsNull() {
            addCriterion("Taishen is null");
            return (Criteria) this;
        }

        public Criteria andTaishenIsNotNull() {
            addCriterion("Taishen is not null");
            return (Criteria) this;
        }

        public Criteria andTaishenEqualTo(String value) {
            addCriterion("Taishen =", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenNotEqualTo(String value) {
            addCriterion("Taishen <>", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenGreaterThan(String value) {
            addCriterion("Taishen >", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenGreaterThanOrEqualTo(String value) {
            addCriterion("Taishen >=", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenLessThan(String value) {
            addCriterion("Taishen <", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenLessThanOrEqualTo(String value) {
            addCriterion("Taishen <=", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenLike(String value) {
            addCriterion("Taishen like", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenNotLike(String value) {
            addCriterion("Taishen not like", value, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenIn(List<String> values) {
            addCriterion("Taishen in", values, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenNotIn(List<String> values) {
            addCriterion("Taishen not in", values, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenBetween(String value1, String value2) {
            addCriterion("Taishen between", value1, value2, "taishen");
            return (Criteria) this;
        }

        public Criteria andTaishenNotBetween(String value1, String value2) {
            addCriterion("Taishen not between", value1, value2, "taishen");
            return (Criteria) this;
        }

        public Criteria andChongIsNull() {
            addCriterion("Chong is null");
            return (Criteria) this;
        }

        public Criteria andChongIsNotNull() {
            addCriterion("Chong is not null");
            return (Criteria) this;
        }

        public Criteria andChongEqualTo(String value) {
            addCriterion("Chong =", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongNotEqualTo(String value) {
            addCriterion("Chong <>", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongGreaterThan(String value) {
            addCriterion("Chong >", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongGreaterThanOrEqualTo(String value) {
            addCriterion("Chong >=", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongLessThan(String value) {
            addCriterion("Chong <", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongLessThanOrEqualTo(String value) {
            addCriterion("Chong <=", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongLike(String value) {
            addCriterion("Chong like", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongNotLike(String value) {
            addCriterion("Chong not like", value, "chong");
            return (Criteria) this;
        }

        public Criteria andChongIn(List<String> values) {
            addCriterion("Chong in", values, "chong");
            return (Criteria) this;
        }

        public Criteria andChongNotIn(List<String> values) {
            addCriterion("Chong not in", values, "chong");
            return (Criteria) this;
        }

        public Criteria andChongBetween(String value1, String value2) {
            addCriterion("Chong between", value1, value2, "chong");
            return (Criteria) this;
        }

        public Criteria andChongNotBetween(String value1, String value2) {
            addCriterion("Chong not between", value1, value2, "chong");
            return (Criteria) this;
        }

        public Criteria andSuishaIsNull() {
            addCriterion("SuiSha is null");
            return (Criteria) this;
        }

        public Criteria andSuishaIsNotNull() {
            addCriterion("SuiSha is not null");
            return (Criteria) this;
        }

        public Criteria andSuishaEqualTo(String value) {
            addCriterion("SuiSha =", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaNotEqualTo(String value) {
            addCriterion("SuiSha <>", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaGreaterThan(String value) {
            addCriterion("SuiSha >", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaGreaterThanOrEqualTo(String value) {
            addCriterion("SuiSha >=", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaLessThan(String value) {
            addCriterion("SuiSha <", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaLessThanOrEqualTo(String value) {
            addCriterion("SuiSha <=", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaLike(String value) {
            addCriterion("SuiSha like", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaNotLike(String value) {
            addCriterion("SuiSha not like", value, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaIn(List<String> values) {
            addCriterion("SuiSha in", values, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaNotIn(List<String> values) {
            addCriterion("SuiSha not in", values, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaBetween(String value1, String value2) {
            addCriterion("SuiSha between", value1, value2, "suisha");
            return (Criteria) this;
        }

        public Criteria andSuishaNotBetween(String value1, String value2) {
            addCriterion("SuiSha not between", value1, value2, "suisha");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziIsNull() {
            addCriterion("WuxingJiazi is null");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziIsNotNull() {
            addCriterion("WuxingJiazi is not null");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziEqualTo(String value) {
            addCriterion("WuxingJiazi =", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziNotEqualTo(String value) {
            addCriterion("WuxingJiazi <>", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziGreaterThan(String value) {
            addCriterion("WuxingJiazi >", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziGreaterThanOrEqualTo(String value) {
            addCriterion("WuxingJiazi >=", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziLessThan(String value) {
            addCriterion("WuxingJiazi <", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziLessThanOrEqualTo(String value) {
            addCriterion("WuxingJiazi <=", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziLike(String value) {
            addCriterion("WuxingJiazi like", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziNotLike(String value) {
            addCriterion("WuxingJiazi not like", value, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziIn(List<String> values) {
            addCriterion("WuxingJiazi in", values, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziNotIn(List<String> values) {
            addCriterion("WuxingJiazi not in", values, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziBetween(String value1, String value2) {
            addCriterion("WuxingJiazi between", value1, value2, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingjiaziNotBetween(String value1, String value2) {
            addCriterion("WuxingJiazi not between", value1, value2, "wuxingjiazi");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearIsNull() {
            addCriterion("WuxingNaYear is null");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearIsNotNull() {
            addCriterion("WuxingNaYear is not null");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearEqualTo(String value) {
            addCriterion("WuxingNaYear =", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearNotEqualTo(String value) {
            addCriterion("WuxingNaYear <>", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearGreaterThan(String value) {
            addCriterion("WuxingNaYear >", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearGreaterThanOrEqualTo(String value) {
            addCriterion("WuxingNaYear >=", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearLessThan(String value) {
            addCriterion("WuxingNaYear <", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearLessThanOrEqualTo(String value) {
            addCriterion("WuxingNaYear <=", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearLike(String value) {
            addCriterion("WuxingNaYear like", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearNotLike(String value) {
            addCriterion("WuxingNaYear not like", value, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearIn(List<String> values) {
            addCriterion("WuxingNaYear in", values, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearNotIn(List<String> values) {
            addCriterion("WuxingNaYear not in", values, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearBetween(String value1, String value2) {
            addCriterion("WuxingNaYear between", value1, value2, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnayearNotBetween(String value1, String value2) {
            addCriterion("WuxingNaYear not between", value1, value2, "wuxingnayear");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthIsNull() {
            addCriterion("WuxingNaMonth is null");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthIsNotNull() {
            addCriterion("WuxingNaMonth is not null");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthEqualTo(String value) {
            addCriterion("WuxingNaMonth =", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthNotEqualTo(String value) {
            addCriterion("WuxingNaMonth <>", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthGreaterThan(String value) {
            addCriterion("WuxingNaMonth >", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthGreaterThanOrEqualTo(String value) {
            addCriterion("WuxingNaMonth >=", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthLessThan(String value) {
            addCriterion("WuxingNaMonth <", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthLessThanOrEqualTo(String value) {
            addCriterion("WuxingNaMonth <=", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthLike(String value) {
            addCriterion("WuxingNaMonth like", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthNotLike(String value) {
            addCriterion("WuxingNaMonth not like", value, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthIn(List<String> values) {
            addCriterion("WuxingNaMonth in", values, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthNotIn(List<String> values) {
            addCriterion("WuxingNaMonth not in", values, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthBetween(String value1, String value2) {
            addCriterion("WuxingNaMonth between", value1, value2, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnamonthNotBetween(String value1, String value2) {
            addCriterion("WuxingNaMonth not between", value1, value2, "wuxingnamonth");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayIsNull() {
            addCriterion("WuxingNaDay is null");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayIsNotNull() {
            addCriterion("WuxingNaDay is not null");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayEqualTo(String value) {
            addCriterion("WuxingNaDay =", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayNotEqualTo(String value) {
            addCriterion("WuxingNaDay <>", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayGreaterThan(String value) {
            addCriterion("WuxingNaDay >", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayGreaterThanOrEqualTo(String value) {
            addCriterion("WuxingNaDay >=", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayLessThan(String value) {
            addCriterion("WuxingNaDay <", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayLessThanOrEqualTo(String value) {
            addCriterion("WuxingNaDay <=", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayLike(String value) {
            addCriterion("WuxingNaDay like", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayNotLike(String value) {
            addCriterion("WuxingNaDay not like", value, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayIn(List<String> values) {
            addCriterion("WuxingNaDay in", values, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayNotIn(List<String> values) {
            addCriterion("WuxingNaDay not in", values, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayBetween(String value1, String value2) {
            addCriterion("WuxingNaDay between", value1, value2, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andWuxingnadayNotBetween(String value1, String value2) {
            addCriterion("WuxingNaDay not between", value1, value2, "wuxingnaday");
            return (Criteria) this;
        }

        public Criteria andMoonnameIsNull() {
            addCriterion("MoonName is null");
            return (Criteria) this;
        }

        public Criteria andMoonnameIsNotNull() {
            addCriterion("MoonName is not null");
            return (Criteria) this;
        }

        public Criteria andMoonnameEqualTo(String value) {
            addCriterion("MoonName =", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameNotEqualTo(String value) {
            addCriterion("MoonName <>", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameGreaterThan(String value) {
            addCriterion("MoonName >", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameGreaterThanOrEqualTo(String value) {
            addCriterion("MoonName >=", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameLessThan(String value) {
            addCriterion("MoonName <", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameLessThanOrEqualTo(String value) {
            addCriterion("MoonName <=", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameLike(String value) {
            addCriterion("MoonName like", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameNotLike(String value) {
            addCriterion("MoonName not like", value, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameIn(List<String> values) {
            addCriterion("MoonName in", values, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameNotIn(List<String> values) {
            addCriterion("MoonName not in", values, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameBetween(String value1, String value2) {
            addCriterion("MoonName between", value1, value2, "moonname");
            return (Criteria) this;
        }

        public Criteria andMoonnameNotBetween(String value1, String value2) {
            addCriterion("MoonName not between", value1, value2, "moonname");
            return (Criteria) this;
        }

        public Criteria andXingeastIsNull() {
            addCriterion("XingEast is null");
            return (Criteria) this;
        }

        public Criteria andXingeastIsNotNull() {
            addCriterion("XingEast is not null");
            return (Criteria) this;
        }

        public Criteria andXingeastEqualTo(String value) {
            addCriterion("XingEast =", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastNotEqualTo(String value) {
            addCriterion("XingEast <>", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastGreaterThan(String value) {
            addCriterion("XingEast >", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastGreaterThanOrEqualTo(String value) {
            addCriterion("XingEast >=", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastLessThan(String value) {
            addCriterion("XingEast <", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastLessThanOrEqualTo(String value) {
            addCriterion("XingEast <=", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastLike(String value) {
            addCriterion("XingEast like", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastNotLike(String value) {
            addCriterion("XingEast not like", value, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastIn(List<String> values) {
            addCriterion("XingEast in", values, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastNotIn(List<String> values) {
            addCriterion("XingEast not in", values, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastBetween(String value1, String value2) {
            addCriterion("XingEast between", value1, value2, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingeastNotBetween(String value1, String value2) {
            addCriterion("XingEast not between", value1, value2, "xingeast");
            return (Criteria) this;
        }

        public Criteria andXingwestIsNull() {
            addCriterion("XingWest is null");
            return (Criteria) this;
        }

        public Criteria andXingwestIsNotNull() {
            addCriterion("XingWest is not null");
            return (Criteria) this;
        }

        public Criteria andXingwestEqualTo(String value) {
            addCriterion("XingWest =", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestNotEqualTo(String value) {
            addCriterion("XingWest <>", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestGreaterThan(String value) {
            addCriterion("XingWest >", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestGreaterThanOrEqualTo(String value) {
            addCriterion("XingWest >=", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestLessThan(String value) {
            addCriterion("XingWest <", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestLessThanOrEqualTo(String value) {
            addCriterion("XingWest <=", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestLike(String value) {
            addCriterion("XingWest like", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestNotLike(String value) {
            addCriterion("XingWest not like", value, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestIn(List<String> values) {
            addCriterion("XingWest in", values, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestNotIn(List<String> values) {
            addCriterion("XingWest not in", values, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestBetween(String value1, String value2) {
            addCriterion("XingWest between", value1, value2, "xingwest");
            return (Criteria) this;
        }

        public Criteria andXingwestNotBetween(String value1, String value2) {
            addCriterion("XingWest not between", value1, value2, "xingwest");
            return (Criteria) this;
        }

        public Criteria andPengzuIsNull() {
            addCriterion("PengZu is null");
            return (Criteria) this;
        }

        public Criteria andPengzuIsNotNull() {
            addCriterion("PengZu is not null");
            return (Criteria) this;
        }

        public Criteria andPengzuEqualTo(String value) {
            addCriterion("PengZu =", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuNotEqualTo(String value) {
            addCriterion("PengZu <>", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuGreaterThan(String value) {
            addCriterion("PengZu >", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuGreaterThanOrEqualTo(String value) {
            addCriterion("PengZu >=", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuLessThan(String value) {
            addCriterion("PengZu <", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuLessThanOrEqualTo(String value) {
            addCriterion("PengZu <=", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuLike(String value) {
            addCriterion("PengZu like", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuNotLike(String value) {
            addCriterion("PengZu not like", value, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuIn(List<String> values) {
            addCriterion("PengZu in", values, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuNotIn(List<String> values) {
            addCriterion("PengZu not in", values, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuBetween(String value1, String value2) {
            addCriterion("PengZu between", value1, value2, "pengzu");
            return (Criteria) this;
        }

        public Criteria andPengzuNotBetween(String value1, String value2) {
            addCriterion("PengZu not between", value1, value2, "pengzu");
            return (Criteria) this;
        }

        public Criteria andJianshenIsNull() {
            addCriterion("JianShen is null");
            return (Criteria) this;
        }

        public Criteria andJianshenIsNotNull() {
            addCriterion("JianShen is not null");
            return (Criteria) this;
        }

        public Criteria andJianshenEqualTo(String value) {
            addCriterion("JianShen =", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenNotEqualTo(String value) {
            addCriterion("JianShen <>", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenGreaterThan(String value) {
            addCriterion("JianShen >", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenGreaterThanOrEqualTo(String value) {
            addCriterion("JianShen >=", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenLessThan(String value) {
            addCriterion("JianShen <", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenLessThanOrEqualTo(String value) {
            addCriterion("JianShen <=", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenLike(String value) {
            addCriterion("JianShen like", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenNotLike(String value) {
            addCriterion("JianShen not like", value, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenIn(List<String> values) {
            addCriterion("JianShen in", values, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenNotIn(List<String> values) {
            addCriterion("JianShen not in", values, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenBetween(String value1, String value2) {
            addCriterion("JianShen between", value1, value2, "jianshen");
            return (Criteria) this;
        }

        public Criteria andJianshenNotBetween(String value1, String value2) {
            addCriterion("JianShen not between", value1, value2, "jianshen");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearIsNull() {
            addCriterion("TianGanDiZhiYear is null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearIsNotNull() {
            addCriterion("TianGanDiZhiYear is not null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearEqualTo(String value) {
            addCriterion("TianGanDiZhiYear =", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearNotEqualTo(String value) {
            addCriterion("TianGanDiZhiYear <>", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearGreaterThan(String value) {
            addCriterion("TianGanDiZhiYear >", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearGreaterThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiYear >=", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearLessThan(String value) {
            addCriterion("TianGanDiZhiYear <", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearLessThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiYear <=", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearLike(String value) {
            addCriterion("TianGanDiZhiYear like", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearNotLike(String value) {
            addCriterion("TianGanDiZhiYear not like", value, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearIn(List<String> values) {
            addCriterion("TianGanDiZhiYear in", values, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearNotIn(List<String> values) {
            addCriterion("TianGanDiZhiYear not in", values, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiYear between", value1, value2, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhiyearNotBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiYear not between", value1, value2, "tiangandizhiyear");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthIsNull() {
            addCriterion("TianGanDiZhiMonth is null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthIsNotNull() {
            addCriterion("TianGanDiZhiMonth is not null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthEqualTo(String value) {
            addCriterion("TianGanDiZhiMonth =", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthNotEqualTo(String value) {
            addCriterion("TianGanDiZhiMonth <>", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthGreaterThan(String value) {
            addCriterion("TianGanDiZhiMonth >", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthGreaterThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiMonth >=", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthLessThan(String value) {
            addCriterion("TianGanDiZhiMonth <", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthLessThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiMonth <=", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthLike(String value) {
            addCriterion("TianGanDiZhiMonth like", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthNotLike(String value) {
            addCriterion("TianGanDiZhiMonth not like", value, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthIn(List<String> values) {
            addCriterion("TianGanDiZhiMonth in", values, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthNotIn(List<String> values) {
            addCriterion("TianGanDiZhiMonth not in", values, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiMonth between", value1, value2, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhimonthNotBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiMonth not between", value1, value2, "tiangandizhimonth");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayIsNull() {
            addCriterion("TianGanDiZhiDay is null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayIsNotNull() {
            addCriterion("TianGanDiZhiDay is not null");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayEqualTo(String value) {
            addCriterion("TianGanDiZhiDay =", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayNotEqualTo(String value) {
            addCriterion("TianGanDiZhiDay <>", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayGreaterThan(String value) {
            addCriterion("TianGanDiZhiDay >", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayGreaterThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiDay >=", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayLessThan(String value) {
            addCriterion("TianGanDiZhiDay <", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayLessThanOrEqualTo(String value) {
            addCriterion("TianGanDiZhiDay <=", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayLike(String value) {
            addCriterion("TianGanDiZhiDay like", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayNotLike(String value) {
            addCriterion("TianGanDiZhiDay not like", value, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayIn(List<String> values) {
            addCriterion("TianGanDiZhiDay in", values, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayNotIn(List<String> values) {
            addCriterion("TianGanDiZhiDay not in", values, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiDay between", value1, value2, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andTiangandizhidayNotBetween(String value1, String value2) {
            addCriterion("TianGanDiZhiDay not between", value1, value2, "tiangandizhiday");
            return (Criteria) this;
        }

        public Criteria andLmonthnameIsNull() {
            addCriterion("LMonthName is null");
            return (Criteria) this;
        }

        public Criteria andLmonthnameIsNotNull() {
            addCriterion("LMonthName is not null");
            return (Criteria) this;
        }

        public Criteria andLmonthnameEqualTo(String value) {
            addCriterion("LMonthName =", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameNotEqualTo(String value) {
            addCriterion("LMonthName <>", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameGreaterThan(String value) {
            addCriterion("LMonthName >", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameGreaterThanOrEqualTo(String value) {
            addCriterion("LMonthName >=", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameLessThan(String value) {
            addCriterion("LMonthName <", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameLessThanOrEqualTo(String value) {
            addCriterion("LMonthName <=", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameLike(String value) {
            addCriterion("LMonthName like", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameNotLike(String value) {
            addCriterion("LMonthName not like", value, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameIn(List<String> values) {
            addCriterion("LMonthName in", values, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameNotIn(List<String> values) {
            addCriterion("LMonthName not in", values, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameBetween(String value1, String value2) {
            addCriterion("LMonthName between", value1, value2, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLmonthnameNotBetween(String value1, String value2) {
            addCriterion("LMonthName not between", value1, value2, "lmonthname");
            return (Criteria) this;
        }

        public Criteria andLyearIsNull() {
            addCriterion("LYear is null");
            return (Criteria) this;
        }

        public Criteria andLyearIsNotNull() {
            addCriterion("LYear is not null");
            return (Criteria) this;
        }

        public Criteria andLyearEqualTo(String value) {
            addCriterion("LYear =", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearNotEqualTo(String value) {
            addCriterion("LYear <>", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearGreaterThan(String value) {
            addCriterion("LYear >", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearGreaterThanOrEqualTo(String value) {
            addCriterion("LYear >=", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearLessThan(String value) {
            addCriterion("LYear <", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearLessThanOrEqualTo(String value) {
            addCriterion("LYear <=", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearLike(String value) {
            addCriterion("LYear like", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearNotLike(String value) {
            addCriterion("LYear not like", value, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearIn(List<String> values) {
            addCriterion("LYear in", values, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearNotIn(List<String> values) {
            addCriterion("LYear not in", values, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearBetween(String value1, String value2) {
            addCriterion("LYear between", value1, value2, "lyear");
            return (Criteria) this;
        }

        public Criteria andLyearNotBetween(String value1, String value2) {
            addCriterion("LYear not between", value1, value2, "lyear");
            return (Criteria) this;
        }

        public Criteria andLmonthIsNull() {
            addCriterion("LMonth is null");
            return (Criteria) this;
        }

        public Criteria andLmonthIsNotNull() {
            addCriterion("LMonth is not null");
            return (Criteria) this;
        }

        public Criteria andLmonthEqualTo(String value) {
            addCriterion("LMonth =", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthNotEqualTo(String value) {
            addCriterion("LMonth <>", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthGreaterThan(String value) {
            addCriterion("LMonth >", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthGreaterThanOrEqualTo(String value) {
            addCriterion("LMonth >=", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthLessThan(String value) {
            addCriterion("LMonth <", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthLessThanOrEqualTo(String value) {
            addCriterion("LMonth <=", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthLike(String value) {
            addCriterion("LMonth like", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthNotLike(String value) {
            addCriterion("LMonth not like", value, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthIn(List<String> values) {
            addCriterion("LMonth in", values, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthNotIn(List<String> values) {
            addCriterion("LMonth not in", values, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthBetween(String value1, String value2) {
            addCriterion("LMonth between", value1, value2, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLmonthNotBetween(String value1, String value2) {
            addCriterion("LMonth not between", value1, value2, "lmonth");
            return (Criteria) this;
        }

        public Criteria andLdayIsNull() {
            addCriterion("LDay is null");
            return (Criteria) this;
        }

        public Criteria andLdayIsNotNull() {
            addCriterion("LDay is not null");
            return (Criteria) this;
        }

        public Criteria andLdayEqualTo(String value) {
            addCriterion("LDay =", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayNotEqualTo(String value) {
            addCriterion("LDay <>", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayGreaterThan(String value) {
            addCriterion("LDay >", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayGreaterThanOrEqualTo(String value) {
            addCriterion("LDay >=", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayLessThan(String value) {
            addCriterion("LDay <", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayLessThanOrEqualTo(String value) {
            addCriterion("LDay <=", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayLike(String value) {
            addCriterion("LDay like", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayNotLike(String value) {
            addCriterion("LDay not like", value, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayIn(List<String> values) {
            addCriterion("LDay in", values, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayNotIn(List<String> values) {
            addCriterion("LDay not in", values, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayBetween(String value1, String value2) {
            addCriterion("LDay between", value1, value2, "lday");
            return (Criteria) this;
        }

        public Criteria andLdayNotBetween(String value1, String value2) {
            addCriterion("LDay not between", value1, value2, "lday");
            return (Criteria) this;
        }

        public Criteria andSolartermnameIsNull() {
            addCriterion("SolarTermName is null");
            return (Criteria) this;
        }

        public Criteria andSolartermnameIsNotNull() {
            addCriterion("SolarTermName is not null");
            return (Criteria) this;
        }

        public Criteria andSolartermnameEqualTo(String value) {
            addCriterion("SolarTermName =", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameNotEqualTo(String value) {
            addCriterion("SolarTermName <>", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameGreaterThan(String value) {
            addCriterion("SolarTermName >", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameGreaterThanOrEqualTo(String value) {
            addCriterion("SolarTermName >=", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameLessThan(String value) {
            addCriterion("SolarTermName <", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameLessThanOrEqualTo(String value) {
            addCriterion("SolarTermName <=", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameLike(String value) {
            addCriterion("SolarTermName like", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameNotLike(String value) {
            addCriterion("SolarTermName not like", value, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameIn(List<String> values) {
            addCriterion("SolarTermName in", values, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameNotIn(List<String> values) {
            addCriterion("SolarTermName not in", values, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameBetween(String value1, String value2) {
            addCriterion("SolarTermName between", value1, value2, "solartermname");
            return (Criteria) this;
        }

        public Criteria andSolartermnameNotBetween(String value1, String value2) {
            addCriterion("SolarTermName not between", value1, value2, "solartermname");
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