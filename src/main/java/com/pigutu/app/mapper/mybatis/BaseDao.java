package com.pigutu.app.mapper.mybatis;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Desc: mybatis base interface dao
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
public interface BaseDao<T extends BaseModel> {

    /**
     * 插入一条记录
     * @param record 记录
     * @return id值
     */
    default Long insert(T record) {
        _insert(record, this);
        return record.getId();
    }

    /**
     * 根据ID查询一条记录
     */
    default T select(Long id) {
        return _select(id, this);
    }

    /**
     * 查询所有数据
     * @return 查询结果列表，不会为 null
     */
    default List<T> selectAll() {
        return _selectAll(this);
    }

    /**
     * 使用复合条件查询单条记录
     * @param queries Map，key 为字段名，value 为字段值
     * @return 查询结果记录，可能为 null
     */
    default T selectOne(Map<String, Object> queries) {
        return _selectOne(queries, this);
    }

    /**
     * 使用 id 列表查询多条记录
     * @param ids ids
     * @return 查询结果列表，不会为 null
     */
    default List<T> selectList(List<Long> ids) {
        return ids.isEmpty() ? Collections.EMPTY_LIST : _selectByIds(ids, this);
    }

    /**
     * 使用复合条件查询多条记录
     * @param queries Map，key 为字段名，value 为字段值
     * @return 查询结果列表，不会为 null
     */
    default List<T> selectList(Map<String, Object> queries) {
        return selectList(queries, null);
    }

    /**
     * 使用复合条件并带额外条件（分页、排序）进行查询
     * @param queries Map，key 为字段名，value 为字段值
     * @param condition {@link QueryCondition}
     * @return 查询结果列表，不会为 null
     */
    default List<T> selectList(Map<String, Object> queries, QueryCondition condition) {
        return _selectList(queries, condition, this);
    }

    /**
     * count
     * @return 数量
     */
    @SuppressWarnings("unchecked")
    default int count() {
        return _count(Collections.EMPTY_MAP, this);
    }

    /**
     * 使用复合条件 count
     * @param queries Map，key 为字段名，value 为字段值
     * @return 数量
     */
    default int count(Map<String, Object> queries) {
        return _count(queries, this);
    }

    /**
     * 查询是否存在
     */
    default boolean exist(Map<String, Object> queries) {
        return count(queries) > 0;
    }

    /**
     * 按 id 更新记录
     * @param record 待更新记录
     */
    default void update(T record) {
        _update(record, this);
    }

    /**
     * 按 id 更新记录的部分字段
     * @param id id
     * @param fields Map，key 为字段名，value 为字段值
     */
    default void update(Long id, Map<String, Object> fields) {
        _updateUseMap(id, fields, this);
    }

    /**
     * 按 id 删除记录
     * @param id id
     */
    default void delete(Long id) {
        _delete(id, this);
    }

    /**
     * 按复合条件删除记录
     * @param queries Map，key 为字段名，value 为字段值
     */
    default void delete(Map<String, Object> queries) {
        _deleteBy(queries, this);
    }

    // 不要直接使用此方法
    @InsertProvider(type = SqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    void _insert(@Param("record") T record, @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "select")
    T _select(@Param("id") Long id, @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "selectAll")
    List<T> _selectAll(@Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "selectQueries")
    T _selectOne(@Param("queries") Map<String, Object> queries,
                 @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "selectByIds")
    List<T> _selectByIds(@Param("ids") List<Long> ids,
                         @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "selectQueries")
    List<T> _selectList(@Param("queries") Map<String, Object> queries,
                        @Param("condition") QueryCondition condition,
                        @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "countQueries")
    int _count(@Param("queries") Map<String, Object> queries,
               @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @UpdateProvider(type = SqlProvider.class, method = "update")
    void _update(@Param("record") T record, @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @UpdateProvider(type = SqlProvider.class, method = "updateOptional")
    void _updateUseMap(@Param("id") Long id,
                       @Param("fields") Map<String, Object> fields,
                       @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @DeleteProvider(type = SqlProvider.class, method = "delete")
    void _delete(@Param("id") Long id, @Param("self") BaseDao<T> self);

    // 不要直接使用此方法
    @DeleteProvider(type = SqlProvider.class, method = "deleteQueries")
    void _deleteBy(@Param("queries") Map<String, Object> queries, @Param("self") BaseDao<T> self);


    /**
     * sql提供类
     */
    class SqlProvider {

        /**
         * {@link MetaInfo} 的缓存
         */
        private static LoadingCache<Class<?>, MetaInfo> metaInfoCache = Caffeine.newBuilder()
                .build(key -> {
                    DBMeta dbMeta = AnnotationUtils.findAnnotation(key, DBMeta.class);
                    Class daoInterface = (Class) key.getGenericInterfaces()[0];
                    Class modelClass = (Class) ((ParameterizedType) daoInterface.getGenericInterfaces()[0]).getActualTypeArguments()[0];

                    String tableName;
                    if (dbMeta != null && !"".equals(dbMeta.table())) {
                        tableName = dbMeta.table();
                    } else {
                        tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelClass.getSimpleName()) + "s";
                    }

                    // 是否继承BaseModel
                    boolean isExtendBase = BaseModel.class.isAssignableFrom(modelClass);

                    MetaInfo metaInfo = new MetaInfo();
                    metaInfo.setTableName(tableName);

                    // 获取fields
                    metaInfo.setFields(Arrays.stream(modelClass.getDeclaredFields())
                            .filter(field -> !Modifier.isStatic(field.getModifiers()) && field.getAnnotation(Transient.class) == null)
                            .map(Field::getName)
                            .collect(Collectors.toList())
                    );
                    //
                    List<String> columns = metaInfo.getFields().stream()
                            .map(SqlProvider::convertFieldName)
                            .collect(Collectors.toList());


                    // 1. select columns
                    List<String> selectColumns = Lists.newArrayList();
                    if (isExtendBase) {
                        selectColumns.add(0, "`id`");
                    }
                    selectColumns.addAll(columns);
                    if (isExtendBase) {
                        // selectColumns.add("`created_at`");
                        // selectColumns.add("`updated_at`");
                    }
                    metaInfo.setSelectColumns(Iterables.toArray(selectColumns, String.class));

                    // 2. insert columns
                    List<String> insertColumns = Lists.newArrayList(columns);
                    if (isExtendBase) {
                        // insertColumns.add("`created_at`");
                        // insertColumns.add("`updated_at`");
                    }
                    metaInfo.setInsertColumns(Iterables.toArray(insertColumns, String.class));

                    // 3. insert values, @Param全部转化成record
                    // 对应created_at和updated_at转化成now()
                    List<String> insertValues = metaInfo.getFields().stream().map(
                            fieldName -> "#{record." + fieldName + "}"
                    ).collect(Collectors.toList());
                    if (isExtendBase) {
                        // insertValues.add("now()");
                        // insertValues.add("now()");
                    }
                    metaInfo.setInsertValues(Iterables.toArray(insertValues, String.class));

                    // 4. update sets
                    List<String> updateSets = metaInfo.getFields().stream().map(fieldName -> {
                        String column = convertFieldName(fieldName);
                        return column + " = #{record." + fieldName + "}";
                    }).collect(Collectors.toList());
                    if (isExtendBase) {
                        // updateSets.add("updated_at = now()");
                    }
                    metaInfo.setUpdateSets(Iterables.toArray(updateSets, String.class));
                    return metaInfo;
                });

        // 获取换成的meta info
        private MetaInfo getMetaInfo(Map<String, Object> params) {
            return metaInfoCache.get(params.get("self").getClass());
        }
        // 将java的字段转化成数据库列名
        private static String convertFieldName(String field) {
            return "`" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field) + "`";
        }
        // 转换查询参数
        private String buildWhereClause(String key, Object v) {
            if (v == null) {
                return convertFieldName(key) + " is null";
            }
            return convertFieldName(key) + " = #{queries." + key + "}";
        }

        // 增加方法sql Provider
        public String insert(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .INSERT_INTO(metaInfo.getTableName())
                    .INTO_COLUMNS(metaInfo.getInsertColumns())
                    .INTO_VALUES(metaInfo.getInsertValues())
                    .toString();
        }

        // 查询方法sql provider
        public String select(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .SELECT(metaInfo.getSelectColumns())
                    .FROM(metaInfo.getTableName())
                    .WHERE("id = #{id}")
                    .toString();
        }

        // 查询所有
        public String selectAll(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .SELECT(metaInfo.getSelectColumns())
                    .FROM(metaInfo.getTableName())
                    .toString();
        }

        // 根据IDs查询
        public String selectByIds(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            int idsLength = ((List) params.get("ids")).size();
            // 拼接ids
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, idsLength)
                    .mapToObj(it -> "#{ids[" + it + "]}")
                    .forEach(it -> sb.append(it).append(","));
            sb.deleteCharAt(sb.length() - 1);
            return new SQL()
                    .SELECT(metaInfo.getSelectColumns())
                    .FROM(metaInfo.getTableName())
                    .WHERE("id in (" + sb.toString() + ")")
                    .toString();
        }

        // 根据条件查询
        @SuppressWarnings("unchecked")
        public String selectQueries(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            Map<String, Object> queries = (Map<String, Object>) params.get("queries");
            QueryCondition condition = params.containsKey("condition")
                    ? (QueryCondition) params.get("condition")
                    : null;
            StringBuilder sqlBuilder = new SQL() {{
                SELECT(metaInfo.getSelectColumns());
                FROM(metaInfo.getTableName());
                queries.forEach((k, v) -> WHERE(buildWhereClause(k, v)));
                // 排序条件
                if (condition != null && condition.isOrderBy()) {
                    for (OrderBy orderField : condition.getOrderFields()) {
                        ORDER_BY(convertFieldName(orderField.getField()) + (orderField.isDesc() ? " desc" : " asc"));
                    }
                }
            }}.usingAppender(new StringBuilder());
            // 分页条件
            if (condition != null && condition.isLimit()) {
                sqlBuilder.append(" limit #{condition.start}, #{condition.offset}");
            }
            return sqlBuilder.toString();
        }

        // 查询数量
        @SuppressWarnings("unchecked")
        public String countQueries(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            Map<String, Object> queries = (Map<String, Object>) params.get("queries");
            return new SQL() {{
                SELECT("count(1)");
                FROM(metaInfo.getTableName());
                queries.forEach((k, v) -> WHERE(buildWhereClause(k, v)));
            }}.toString();
        }

        // 更新
        public String update(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .UPDATE(metaInfo.getTableName())
                    .SET(metaInfo.getUpdateSets())
                    .WHERE("id = #{record.id}")
                    .toString();
        }

        // 指定字段进行更新
        public String updateOptional(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            @SuppressWarnings("unchecked")
            Map<String, Object> fields = (Map<String, Object>) params.get("fields");
            return new SQL() {{
                UPDATE(metaInfo.getTableName());
                fields.forEach((k, v) -> SET(convertFieldName(k) + " = #{fields." + k + "}"));
                // SET("`updated_at` = now()");
                WHERE("id = #{id}");
            }}.toString();
        }

        // 删除一条
        public String delete(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .DELETE_FROM(metaInfo.getTableName())
                    .WHERE("id = #{id}")
                    .toString();
        }

        // 根据条件删除
        @SuppressWarnings("unchecked")
        public String deleteQueries(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            Map<String, Object> queries = (Map<String, Object>) params.get("queries");
            return new SQL() {{
                DELETE_FROM(metaInfo.getTableName());
                queries.forEach((k, v) -> WHERE(buildWhereClause(k, v)));
            }}.toString();
        }
    }

    /**
     * T 类型的基本信息
     */
    class MetaInfo {
        private String tableName;
        private List<String> fields;
        private String[] selectColumns;
        private String[] insertColumns;
        private String[] insertValues;
        private String[] updateSets;

        String getTableName() {
            return tableName;
        }

        void setTableName(String tableName) {
            this.tableName = tableName;
        }

        List<String> getFields() {
            return fields;
        }

        void setFields(List<String> fields) {
            this.fields = fields;
        }

        String[] getSelectColumns() {
            return selectColumns;
        }

        void setSelectColumns(String[] selectColumns) {
            this.selectColumns = selectColumns;
        }

        String[] getInsertColumns() {
            return insertColumns;
        }

        void setInsertColumns(String[] insertColumns) {
            this.insertColumns = insertColumns;
        }

        String[] getInsertValues() {
            return insertValues;
        }

        void setInsertValues(String[] insertValues) {
            this.insertValues = insertValues;
        }

        String[] getUpdateSets() {
            return updateSets;
        }

        void setUpdateSets(String[] updateSets) {
            this.updateSets = updateSets;
        }
    }
}
