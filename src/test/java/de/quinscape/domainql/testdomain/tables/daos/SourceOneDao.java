/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain.tables.daos;


import de.quinscape.domainql.testdomain.tables.SourceOne;
import de.quinscape.domainql.testdomain.tables.records.SourceOneRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
@Repository
public class SourceOneDao extends DAOImpl<SourceOneRecord, de.quinscape.domainql.testdomain.tables.pojos.SourceOne, String> {

    /**
     * Create a new SourceOneDao without any configuration
     */
    public SourceOneDao() {
        super(SourceOne.SOURCE_ONE, de.quinscape.domainql.testdomain.tables.pojos.SourceOne.class);
    }

    /**
     * Create a new SourceOneDao with an attached configuration
     */
    @Autowired
    public SourceOneDao(Configuration configuration) {
        super(SourceOne.SOURCE_ONE, de.quinscape.domainql.testdomain.tables.pojos.SourceOne.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(de.quinscape.domainql.testdomain.tables.pojos.SourceOne object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<de.quinscape.domainql.testdomain.tables.pojos.SourceOne> fetchById(String... values) {
        return fetch(SourceOne.SOURCE_ONE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public de.quinscape.domainql.testdomain.tables.pojos.SourceOne fetchOneById(String value) {
        return fetchOne(SourceOne.SOURCE_ONE.ID, value);
    }

    /**
     * Fetch records that have <code>target_id IN (values)</code>
     */
    public List<de.quinscape.domainql.testdomain.tables.pojos.SourceOne> fetchByTargetId(String... values) {
        return fetch(SourceOne.SOURCE_ONE.TARGET_ID, values);
    }
}
