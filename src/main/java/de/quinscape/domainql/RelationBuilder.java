package de.quinscape.domainql;

import de.quinscape.domainql.config.Options;
import de.quinscape.domainql.config.RelationModel;
import de.quinscape.domainql.config.SourceField;
import de.quinscape.domainql.config.TargetField;
import de.quinscape.spring.jsview.util.JSONUtil;
import jakarta.persistence.Column;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.svenson.info.JSONClassInfo;
import org.svenson.info.JSONPropertyInfo;
import org.svenson.util.IntrospectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Builds and configures a DomainQL relation configuration object.
 *
 * @see DomainQLBuilder#withRelation(RelationBuilder)
 */
public class RelationBuilder
{
    private String id;

    private Class<?> sourcePojo;

    private List<String> sourceFields;

    private Class<?> targetPojo;

    private List<String> targetFields;

    private ForeignKey<?, ?> foreignKey;

    private SourceField sourceField = SourceField.NONE;

    private TargetField targetField = TargetField.NONE;

    private String leftSideObjectName;

    private String rightSideObjectName;

    private List<String> metaTags = Collections.emptyList();


    /**
     * Configures the relation builder from the database foreign key containing the given fields. The method is meant to
     * be called with the fields autogenerated by JOOQ.
     *
     *
     *
     * @param foreignKeyFieldsArray
     * @return
     */
    public RelationBuilder withForeignKeyFields(
        TableField<?, ?>... foreignKeyFieldsArray
    )
    {
        if (sourcePojo != null)
        {
            throw new DomainQLBuilderException("Cannot set foreign key: .withPojoFields() already called");
        }

        if (foreignKeyFieldsArray == null || foreignKeyFieldsArray.length == 0)
        {
            throw new DomainQLBuilderException("Foreign key fields can't be null or empty");
        }

        final List<TableField<?, ?>> foreignKeyFields = Arrays.asList(foreignKeyFieldsArray);

        final Table<?> table = foreignKeyFields.get(0).getTable();
        for (int i = 1; i < foreignKeyFields.size(); i++)
        {
            final Table<?> otherTable = foreignKeyFields.get(i).getTable();
            if (!table.equals(otherTable))
            {
                throw new DomainQLBuilderException(
                    "All foreign key fields must belong to the same table: Encountered " +
                        table +
                        " and " +
                        otherTable
                );
            }
        }


        ForeignKey<?, ?> foreignKey = null;

        for (ForeignKey<?, ?> current : table.getReferences())
        {
            if (current.getFields().equals(foreignKeyFields))
            {
                foreignKey = current;
                break;
            }
        }

        if (foreignKey == null)
        {
            throw new DomainQLBuilderException("Could not find foreign key for fields: " + foreignKeyFields);
        }

//            final String javaTableName = table.getName().toUpperCase();
//            final String javaFieldName = javaTableName + "." + field.getName().toUpperCase();

        this.foreignKey = foreignKey;

        return this;
    }


    public RelationBuilder withPojoFields(
        Class<?> sourcePojo,
        List<String> sourceFields,
        Class<?> targetPojo,
        List<String> targetFields
    )
    {
        if (foreignKey != null)
        {
            throw new DomainQLBuilderException("Cannot set pojo fields: .withForeignKeyFields() already called");
        }

        this.sourcePojo = sourcePojo;
        this.sourceFields = sourceFields;
        this.targetPojo = targetPojo;
        this.targetFields = targetFields;

        return this;
    }


    public RelationBuilder withSourceField(SourceField sourceField)
    {
        this.sourceField = sourceField;

        return this;
    }


    public RelationBuilder withTargetField(TargetField targetField)
    {
        this.targetField = targetField;
        return this;
    }


    public RelationBuilder withLeftSideObjectName(String leftSideObjectName)
    {
        this.leftSideObjectName = leftSideObjectName;
        return this;
    }


    public RelationBuilder withRightSideObjectName(String rightSideObjectName)
    {
        this.rightSideObjectName = rightSideObjectName;
        return this;
    }


    /**
     * Configures the given meta tags for this relation
     *
     * @param metaTags  vargs of a meta tag strings
     *                  
     * @return this builder
     */
    public RelationBuilder withMetaTags(String... metaTags)
    {
        this.metaTags = Arrays.asList(metaTags);
        return this;
    }


    public List<String> getMetaTags()
    {
        return metaTags;
    }


    public String getId()
    {
        return id;
    }


    /**
     * Optional unique id for the relation.
     *
     * @param id    id
     * @return this builder
     */
    public RelationBuilder withId(String id)
    {
        this.id = id;

        return this;
    }


    /**
     * Builds the configured relation configuration instance.
     *
     * @param jooqTables
     * @param fieldLookup
     *
     * @param options
     * @param usedRelationIds
     * @return relation configuration
     */
    RelationModel build(
        Map<String, TableLookup> jooqTables,
        Map<String, Field<?>> fieldLookup,
        Options options,
        Set<String> usedRelationIds
    )
    {

        // the case of both being set is already prevented in the "wither" methods
        if (foreignKey == null && this.sourcePojo == null)
        {
            throw new DomainQLBuilderException("Configuration needs either a foreign key or pojo-based fields");
        }

        if (foreignKey != null)
        {
            final Table<?> sourceTable = foreignKey.getTable();
            final Table<?> targetTable = foreignKey.getKey().getTable();
            final String sourceTableName = sourceTable.getClass().getSimpleName();
            final String targetTableName = targetTable.getClass().getSimpleName();
            
            final TableLookup sourceLookup = jooqTables.get(sourceTableName);
            if (sourceLookup == null)
            {
                throw new DomainQLBuilderException("Error looking up pojo class for domain type '" + sourceTableName + "'");
            }
            final TableLookup targetLookup = jooqTables.get(targetTableName);
            if (targetLookup == null)
            {
                throw new DomainQLBuilderException("Error looking up pojo class for domain type '" + sourceTableName + "'");
            }

            final Class<?> sourcePojo = sourceLookup.getPojoType();
            final Class<?> targetPojo = targetLookup.getPojoType();
            final List<? extends TableField<?, ?>> sourceDBFields = foreignKey.getFields();
            final List<? extends TableField<?, ?>> targetDBFields = foreignKey.getKey().getFields();

            final List<String> sourceFields = resolvePojoFields(sourcePojo, sourceDBFields);
            final List<String> targetFields = resolvePojoFields(targetPojo, targetDBFields);
            final String leftSideObjectName;
            if (this.leftSideObjectName != null)
            {
                leftSideObjectName = this.leftSideObjectName;
            }
            else if (sourceField != SourceField.NONE)
            {
                final String javaName = sourceFields.get(0);
                final String suffix = options.getForeignKeySuffix();
                if (javaName.endsWith(suffix))
                {
                    leftSideObjectName = javaName.substring(0, javaName.length() - suffix.length());
                }
                else
                {
                    leftSideObjectName = javaName;
                }
            }
            else
            {
                leftSideObjectName = null;
            }

            final String rightSideObjectName;

            if (this.rightSideObjectName != null)
            {
                rightSideObjectName = this.rightSideObjectName;
            }
            else if (targetField != TargetField.NONE)
            {
                final String otherName = IntrospectionUtil.decapitalize(sourcePojo.getSimpleName());
                rightSideObjectName = targetField == TargetField.ONE ? otherName : options.getPluralizationFunction().apply(otherName);
            }
            else
            {
                rightSideObjectName = null;
            }

            return new RelationModel(
                getRelationId(usedRelationIds, sourcePojo, leftSideObjectName != null ? leftSideObjectName : sourceFields.get(0)),
                sourceTable,
                sourcePojo,
                sourceDBFields,
                sourceFields,
                targetTable,
                targetPojo,
                targetDBFields,
                targetFields,
                sourceField,
                targetField,
                leftSideObjectName,
                rightSideObjectName,
                metaTags
            );
        }
        else
        {
            final String leftSideObjectName;
            if (this.leftSideObjectName != null)
            {
                leftSideObjectName = this.leftSideObjectName;
            }
            else if (sourceField != SourceField.NONE)
            {
                final String javaName = sourceFields.get(0);
                final String suffix = options.getForeignKeySuffix();
                if (javaName.endsWith(suffix))
                {
                    leftSideObjectName = javaName.substring(0, javaName.length() - suffix.length());
                }
                else
                {
                    leftSideObjectName = javaName;
                }
            }
            else
            {
                leftSideObjectName = null;
            }

            final String rightSideObjectName;

            if (this.rightSideObjectName != null)
            {
                rightSideObjectName = this.rightSideObjectName;
            }
            else if (targetField != TargetField.NONE)
            {
                final String otherName = IntrospectionUtil.decapitalize(sourcePojo.getSimpleName());
                rightSideObjectName = targetField == TargetField.ONE ? otherName : options.getPluralizationFunction().apply(otherName);
            }
            else
            {
                rightSideObjectName = null;
            }

            final String sourceDomainType = sourcePojo.getSimpleName();
            final String targetDomainType = targetPojo.getSimpleName();
            final Table<?> sourceTable = jooqTables.get(sourceDomainType).getTable();
            final Table<?> targetTable = jooqTables.get(targetDomainType).getTable();
            return new RelationModel(
                getRelationId(usedRelationIds, sourcePojo, leftSideObjectName != null ? leftSideObjectName : sourceFields.get(0)),
                sourceTable,
                sourcePojo,
                resolveFields(fieldLookup, sourceDomainType, sourceFields),
                sourceFields,
                targetTable,
                targetPojo,
                resolveFields(fieldLookup, targetDomainType, targetFields),
                targetFields,
                sourceField,
                targetField,
                leftSideObjectName,
                rightSideObjectName,
                metaTags
            );

        }
    }


    private String getRelationId(Set<String> usedRelationIds, Class<?> sourcePojo, String objectName)
    {
        String id = this.id;
        if (id != null)
        {
            if (usedRelationIds.contains(id))
            {
                throw new DomainQLBuilderException("Relation id '" + id + "' is already used.");
            }
        }
        else
        {
            final String baseId = sourcePojo.getSimpleName() + "-" + objectName;
            id = baseId;
            int count = 2;
            while (usedRelationIds.contains(id))
            {
                id = baseId + count++;
            }
        }
        
        usedRelationIds.add(id);
        return id;
    }


    private List<String> resolvePojoFields(Class<?> sourcePojo, List<? extends TableField<?, ?>> fields)
    {
        final JSONClassInfo classInfo = JSONUtil.getClassInfo(sourcePojo);
        if (classInfo == null)
        {
            throw new DomainQLBuilderException("Cannot find class info for " + sourcePojo);
        }

        final List<JSONPropertyInfo> propertyInfos = classInfo.getPropertyInfos();

        final List<String> pojoFieldNames = new ArrayList<>(fields.size());
        for (TableField<?, ?> field : fields)
        {
            for (JSONPropertyInfo propertyInfo : propertyInfos)
            {
                if (!DomainQL.isNormalProperty(propertyInfo))
                {
                    continue;
                }

                final Column anno = JSONUtil.findAnnotation(propertyInfo, Column.class);
                if (anno != null && anno.name().equals(field.getName()))
                {
                    pojoFieldNames.add(propertyInfo.getJsonName());
                    break;
                }
            }
        }
        return pojoFieldNames;
    }


    private List<? extends TableField<?, ?>> resolveFields(
        Map<String, Field<?>> fieldLookup,
        String sourceDomainType,
        List<String> sourceFields
    )
    {

        List<TableField<?, ?>> list = new ArrayList<>(sourceFields.size());

        for (String name : sourceFields)
        {
            final String key = DomainQLBuilder.fieldLookupKey(
                sourceDomainType,
                name
            );
            final TableField<?, ?> field = (TableField<?, ?>) fieldLookup.get(key);

            if (field == null)
            {
                throw new DomainQLBuilderException("Cannot resolve field '" + key + "': Valid keys for " + sourceDomainType + " are " + getKeysOfTable(fieldLookup, sourceDomainType) );
            }

            list.add(
                field
            );
        }

        return list;
    }


    private List<String> getKeysOfTable(Map<String, Field<?>> fieldLookup, String sourceDomainType)
    {
        final String prefix = sourceDomainType + ":";

        final List<String> keys = new ArrayList<>();

        for (String key : fieldLookup.keySet())
        {
            if (key.startsWith(prefix))
            {
                keys.add(key);
            }
        }
        return keys;
    }
}
