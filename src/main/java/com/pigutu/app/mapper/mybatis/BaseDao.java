package com.pigutu.app.mapper.mybatis;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc: mybatis base interface dao
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/12
 */
public interface BaseDao<T extends BaseModel> {

    /**
     * 根据ID查询一条记录
     */
    default T select(Long id) {
        return _select(id, this);
    }

    // 不要直接使用此方法
    @SelectProvider(type = SqlProvider.class, method = "select")
    T _select(@Param("id") Long id, @Param("self") BaseDao<T> self);

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

                    // 2. TODO 其他
                    return metaInfo;
                });


        // 查询方法sql provider
        public String select(Map<String, Object> params) {
            MetaInfo metaInfo = getMetaInfo(params);
            return new SQL()
                    .SELECT(metaInfo.getSelectColumns())
                    .FROM(metaInfo.getTableName())
                    .WHERE("id = #{id}")
                    .toString();
        }

        // 获取换成的meta info
        private MetaInfo getMetaInfo(Map<String, Object> params) {
            return metaInfoCache.get(params.get("self").getClass());
        }
        // 将java的字段转化成数据库列名
        private static String convertFieldName(String field) {
            return "`" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field) + "`";
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
