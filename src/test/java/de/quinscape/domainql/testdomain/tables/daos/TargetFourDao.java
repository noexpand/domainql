/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain.tables.daos;


import de.quinscape.domainql.testdomain.tables.TargetFour;
import de.quinscape.domainql.testdomain.tables.records.TargetFourRecord;

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
public class TargetFourDao extends DAOImpl<TargetFourRecord, de.quinscape.domainql.testdomain.tables.pojos.TargetFour, String> {

    /**
     * Create a new TargetFourDao without any configuration
     */
    public TargetFourDao() {
        super(TargetFour.TARGET_FOUR, de.quinscape.domainql.testdomain.tables.pojos.TargetFour.class);
    }

    /**
     * Create a new TargetFourDao with an attached configuration
     */
    @Autowired
    public TargetFourDao(Configuration configuration) {
        super(TargetFour.TARGET_FOUR, de.quinscape.domainql.testdomain.tables.pojos.TargetFour.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(de.quinscape.domainql.testdomain.tables.pojos.TargetFour object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<de.quinscape.domainql.testdomain.tables.pojos.TargetFour> fetchById(String... values) {
        return fetch(TargetFour.TARGET_FOUR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public de.quinscape.domainql.testdomain.tables.pojos.TargetFour fetchOneById(String value) {
        return fetchOne(TargetFour.TARGET_FOUR.ID, value);
    }
}