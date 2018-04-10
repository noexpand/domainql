/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain.tables.records;


import de.quinscape.domainql.testdomain.tables.SourceSix;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
@Table(name = "source_six", schema = "public", indexes = {
    @Index(name = "pk_source_six", unique = true, columnList = "id ASC")
})
public class SourceSixRecord extends UpdatableRecordImpl<SourceSixRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1866781038;

    /**
     * Setter for <code>public.source_six.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.source_six.id</code>.
     */
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    @NotNull
    @Size(max = 36)
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.source_six.target_id</code>.
     */
    public void setTargetId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.source_six.target_id</code>.
     */
    @Column(name = "target_id", nullable = false, length = 36)
    @NotNull
    @Size(max = 36)
    public String getTargetId() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return SourceSix.SOURCE_SIX.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return SourceSix.SOURCE_SIX.TARGET_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getTargetId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTargetId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceSixRecord value1(String value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceSixRecord value2(String value) {
        setTargetId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceSixRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SourceSixRecord
     */
    public SourceSixRecord() {
        super(SourceSix.SOURCE_SIX);
    }

    /**
     * Create a detached, initialised SourceSixRecord
     */
    public SourceSixRecord(String id, String targetId) {
        super(SourceSix.SOURCE_SIX);

        set(0, id);
        set(1, targetId);
    }
}
