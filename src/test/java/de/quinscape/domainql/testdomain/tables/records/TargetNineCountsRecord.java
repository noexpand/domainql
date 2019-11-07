/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain.tables.records;


import de.quinscape.domainql.testdomain.tables.TargetNineCounts;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


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
@Entity
@Table(name = "target_nine_counts", schema = "public")
public class TargetNineCountsRecord extends TableRecordImpl<TargetNineCountsRecord> implements Record2<String, Long> {

    private static final long serialVersionUID = -1466683652;

    /**
     * Setter for <code>public.target_nine_counts.target_id</code>.
     */
    public void setTargetId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.target_nine_counts.target_id</code>.
     */
    @Column(name = "target_id", length = 36)
    @Size(max = 36)
    public String getTargetId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.target_nine_counts.count</code>.
     */
    public void setCount(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.target_nine_counts.count</code>.
     */
    @Column(name = "count", precision = 64)
    public Long getCount() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TargetNineCounts.TARGET_NINE_COUNTS.TARGET_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return TargetNineCounts.TARGET_NINE_COUNTS.COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getTargetId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getTargetId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TargetNineCountsRecord value1(String value) {
        setTargetId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TargetNineCountsRecord value2(Long value) {
        setCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TargetNineCountsRecord values(String value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TargetNineCountsRecord
     */
    public TargetNineCountsRecord() {
        super(TargetNineCounts.TARGET_NINE_COUNTS);
    }

    /**
     * Create a detached, initialised TargetNineCountsRecord
     */
    public TargetNineCountsRecord(String targetId, Long count) {
        super(TargetNineCounts.TARGET_NINE_COUNTS);

        set(0, targetId);
        set(1, count);
    }
}