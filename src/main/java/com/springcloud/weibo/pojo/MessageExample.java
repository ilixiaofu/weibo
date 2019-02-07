package com.springcloud.weibo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Integer value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Integer value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Integer value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Integer value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Integer> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Integer> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andForwardingCountIsNull() {
            addCriterion("forwarding_count is null");
            return (Criteria) this;
        }

        public Criteria andForwardingCountIsNotNull() {
            addCriterion("forwarding_count is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingCountEqualTo(Integer value) {
            addCriterion("forwarding_count =", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountNotEqualTo(Integer value) {
            addCriterion("forwarding_count <>", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountGreaterThan(Integer value) {
            addCriterion("forwarding_count >", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("forwarding_count >=", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountLessThan(Integer value) {
            addCriterion("forwarding_count <", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountLessThanOrEqualTo(Integer value) {
            addCriterion("forwarding_count <=", value, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountIn(List<Integer> values) {
            addCriterion("forwarding_count in", values, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountNotIn(List<Integer> values) {
            addCriterion("forwarding_count not in", values, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_count between", value1, value2, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andForwardingCountNotBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_count not between", value1, value2, "forwardingCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIsNull() {
            addCriterion("comments_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIsNotNull() {
            addCriterion("comments_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsCountEqualTo(Integer value) {
            addCriterion("comments_count =", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotEqualTo(Integer value) {
            addCriterion("comments_count <>", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountGreaterThan(Integer value) {
            addCriterion("comments_count >", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments_count >=", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountLessThan(Integer value) {
            addCriterion("comments_count <", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountLessThanOrEqualTo(Integer value) {
            addCriterion("comments_count <=", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIn(List<Integer> values) {
            addCriterion("comments_count in", values, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotIn(List<Integer> values) {
            addCriterion("comments_count not in", values, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountBetween(Integer value1, Integer value2) {
            addCriterion("comments_count between", value1, value2, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comments_count not between", value1, value2, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountIsNull() {
            addCriterion("likes_count is null");
            return (Criteria) this;
        }

        public Criteria andLikesCountIsNotNull() {
            addCriterion("likes_count is not null");
            return (Criteria) this;
        }

        public Criteria andLikesCountEqualTo(Integer value) {
            addCriterion("likes_count =", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountNotEqualTo(Integer value) {
            addCriterion("likes_count <>", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountGreaterThan(Integer value) {
            addCriterion("likes_count >", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes_count >=", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountLessThan(Integer value) {
            addCriterion("likes_count <", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountLessThanOrEqualTo(Integer value) {
            addCriterion("likes_count <=", value, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountIn(List<Integer> values) {
            addCriterion("likes_count in", values, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountNotIn(List<Integer> values) {
            addCriterion("likes_count not in", values, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountBetween(Integer value1, Integer value2) {
            addCriterion("likes_count between", value1, value2, "likesCount");
            return (Criteria) this;
        }

        public Criteria andLikesCountNotBetween(Integer value1, Integer value2) {
            addCriterion("likes_count not between", value1, value2, "likesCount");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("isDel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("isDel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(String value) {
            addCriterion("isDel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(String value) {
            addCriterion("isDel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(String value) {
            addCriterion("isDel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(String value) {
            addCriterion("isDel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(String value) {
            addCriterion("isDel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(String value) {
            addCriterion("isDel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLike(String value) {
            addCriterion("isDel like", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotLike(String value) {
            addCriterion("isDel not like", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<String> values) {
            addCriterion("isDel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<String> values) {
            addCriterion("isDel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(String value1, String value2) {
            addCriterion("isDel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(String value1, String value2) {
            addCriterion("isDel not between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIspublicIsNull() {
            addCriterion("isPublic is null");
            return (Criteria) this;
        }

        public Criteria andIspublicIsNotNull() {
            addCriterion("isPublic is not null");
            return (Criteria) this;
        }

        public Criteria andIspublicEqualTo(String value) {
            addCriterion("isPublic =", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotEqualTo(String value) {
            addCriterion("isPublic <>", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicGreaterThan(String value) {
            addCriterion("isPublic >", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicGreaterThanOrEqualTo(String value) {
            addCriterion("isPublic >=", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicLessThan(String value) {
            addCriterion("isPublic <", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicLessThanOrEqualTo(String value) {
            addCriterion("isPublic <=", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicLike(String value) {
            addCriterion("isPublic like", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotLike(String value) {
            addCriterion("isPublic not like", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicIn(List<String> values) {
            addCriterion("isPublic in", values, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotIn(List<String> values) {
            addCriterion("isPublic not in", values, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicBetween(String value1, String value2) {
            addCriterion("isPublic between", value1, value2, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotBetween(String value1, String value2) {
            addCriterion("isPublic not between", value1, value2, "ispublic");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
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