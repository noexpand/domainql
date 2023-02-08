/*
 * This file is generated by jOOQ.
 */
package de.quinscape.domainql.testdomain.tables.records;


import de.quinscape.domainql.testdomain.tables.SourceNine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;

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
        "https://www.jooq.org",
        "jOOQ version:3.17.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "source_nine",
    schema = "public"
)
public class SourceNineRecord extends UpdatableRecordImpl<SourceNineRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.source_nine.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.source_nine.id</code>.
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    @NotNull
    @Size(max = 36)
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.source_nine.target_id</code>.
     */
    public void setTargetId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.source_nine.target_id</code>.
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

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return SourceNine.SOURCE_NINE.ID;
    }

    @Override
    public Field<String> field2() {
        return SourceNine.SOURCE_NINE.TARGET_ID;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTargetId();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTargetId();
    }

    @Override
    public SourceNineRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public SourceNineRecord value2(String value) {
        setTargetId(value);
        return this;
    }

    @Override
    public SourceNineRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SourceNineRecord
     */
    public SourceNineRecord() {
        super(SourceNine.SOURCE_NINE);
    }

    /**
     * Create a detached, initialised SourceNineRecord
     */
    public SourceNineRecord(String id, String targetId) {
        super(SourceNine.SOURCE_NINE);

        setId(id);
        setTargetId(targetId);
    }

    /**
     * Create a detached, initialised SourceNineRecord
     */
    public SourceNineRecord(de.quinscape.domainql.testdomain.tables.pojos.SourceNine value) {
        super(SourceNine.SOURCE_NINE);

        if (value != null) {
            setId(value.getId());
            setTargetId(value.getTargetId());
        }
    }
}
