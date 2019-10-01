/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain.tables;


import de.quinscape.domainql.testdomain.Indexes;
import de.quinscape.domainql.testdomain.Keys;
import de.quinscape.domainql.testdomain.Public;
import de.quinscape.domainql.testdomain.tables.records.SourceEightRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SourceEight extends TableImpl<SourceEightRecord> {

    private static final long serialVersionUID = -1975874845;

    /**
     * The reference instance of <code>public.source_eight</code>
     */
    public static final SourceEight SOURCE_EIGHT = new SourceEight();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SourceEightRecord> getRecordType() {
        return SourceEightRecord.class;
    }

    /**
     * The column <code>public.source_eight.id</code>.
     */
    public final TableField<SourceEightRecord, String> ID = createField("id", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.source_eight.target_name</code>.
     */
    public final TableField<SourceEightRecord, String> TARGET_NAME = createField("target_name", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.source_eight.target_num</code>.
     */
    public final TableField<SourceEightRecord, Integer> TARGET_NUM = createField("target_num", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.source_eight</code> table reference
     */
    public SourceEight() {
        this(DSL.name("source_eight"), null);
    }

    /**
     * Create an aliased <code>public.source_eight</code> table reference
     */
    public SourceEight(String alias) {
        this(DSL.name(alias), SOURCE_EIGHT);
    }

    /**
     * Create an aliased <code>public.source_eight</code> table reference
     */
    public SourceEight(Name alias) {
        this(alias, SOURCE_EIGHT);
    }

    private SourceEight(Name alias, Table<SourceEightRecord> aliased) {
        this(alias, aliased, null);
    }

    private SourceEight(Name alias, Table<SourceEightRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PK_SOURCE_EIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SourceEightRecord> getPrimaryKey() {
        return Keys.PK_SOURCE_EIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SourceEightRecord>> getKeys() {
        return Arrays.<UniqueKey<SourceEightRecord>>asList(Keys.PK_SOURCE_EIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<SourceEightRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SourceEightRecord, ?>>asList(Keys.SOURCE_EIGHT__FK_SOURCE_EIGHT_TARGET);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceEight as(String alias) {
        return new SourceEight(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceEight as(Name alias) {
        return new SourceEight(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SourceEight rename(String name) {
        return new SourceEight(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SourceEight rename(Name name) {
        return new SourceEight(name, null);
    }
}
