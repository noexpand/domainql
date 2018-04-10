/*
 * This file is generated by jOOQ.
*/
package de.quinscape.domainql.testdomain;


import de.quinscape.domainql.testdomain.tables.SourceFive;
import de.quinscape.domainql.testdomain.tables.SourceFour;
import de.quinscape.domainql.testdomain.tables.SourceOne;
import de.quinscape.domainql.testdomain.tables.SourceSix;
import de.quinscape.domainql.testdomain.tables.SourceThree;
import de.quinscape.domainql.testdomain.tables.SourceTwo;
import de.quinscape.domainql.testdomain.tables.TargetFive;
import de.quinscape.domainql.testdomain.tables.TargetFour;
import de.quinscape.domainql.testdomain.tables.TargetOne;
import de.quinscape.domainql.testdomain.tables.TargetSix;
import de.quinscape.domainql.testdomain.tables.TargetThree;
import de.quinscape.domainql.testdomain.tables.TargetTwo;
import de.quinscape.domainql.testdomain.tables.records.SourceFiveRecord;
import de.quinscape.domainql.testdomain.tables.records.SourceFourRecord;
import de.quinscape.domainql.testdomain.tables.records.SourceOneRecord;
import de.quinscape.domainql.testdomain.tables.records.SourceSixRecord;
import de.quinscape.domainql.testdomain.tables.records.SourceThreeRecord;
import de.quinscape.domainql.testdomain.tables.records.SourceTwoRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetFiveRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetFourRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetOneRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetSixRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetThreeRecord;
import de.quinscape.domainql.testdomain.tables.records.TargetTwoRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<SourceFiveRecord> PK_SOURCE_FIVE = UniqueKeys0.PK_SOURCE_FIVE;
    public static final UniqueKey<SourceFourRecord> PK_SOURCE_FOUR = UniqueKeys0.PK_SOURCE_FOUR;
    public static final UniqueKey<SourceOneRecord> PK_SOURCE_ONE = UniqueKeys0.PK_SOURCE_ONE;
    public static final UniqueKey<SourceSixRecord> PK_SOURCE_SIX = UniqueKeys0.PK_SOURCE_SIX;
    public static final UniqueKey<SourceThreeRecord> PK_SOURCE_THREE = UniqueKeys0.PK_SOURCE_THREE;
    public static final UniqueKey<SourceTwoRecord> PK_SOURCE_TWO = UniqueKeys0.PK_SOURCE_TWO;
    public static final UniqueKey<TargetFiveRecord> PK_TARGET_FIVE = UniqueKeys0.PK_TARGET_FIVE;
    public static final UniqueKey<TargetFourRecord> PK_TARGET_FOUR = UniqueKeys0.PK_TARGET_FOUR;
    public static final UniqueKey<TargetOneRecord> PK_TARGET_ONE = UniqueKeys0.PK_TARGET_ONE;
    public static final UniqueKey<TargetSixRecord> PK_TARGET_SIX = UniqueKeys0.PK_TARGET_SIX;
    public static final UniqueKey<TargetThreeRecord> PK_TARGET_THREE = UniqueKeys0.PK_TARGET_THREE;
    public static final UniqueKey<TargetTwoRecord> PK_TARGET_TWO = UniqueKeys0.PK_TARGET_TWO;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<SourceFiveRecord, TargetFiveRecord> SOURCE_FIVE__FK_SOURCE_FIVE_TARGET_ID = ForeignKeys0.SOURCE_FIVE__FK_SOURCE_FIVE_TARGET_ID;
    public static final ForeignKey<SourceFourRecord, TargetFourRecord> SOURCE_FOUR__FK_SOURCE_FOUR_TARGET_ID = ForeignKeys0.SOURCE_FOUR__FK_SOURCE_FOUR_TARGET_ID;
    public static final ForeignKey<SourceFourRecord, TargetFourRecord> SOURCE_FOUR__FK_SOURCE_FOUR_TARGET2_ID = ForeignKeys0.SOURCE_FOUR__FK_SOURCE_FOUR_TARGET2_ID;
    public static final ForeignKey<SourceOneRecord, TargetOneRecord> SOURCE_ONE__FK_SOURCE_ONE_TARGET_ID = ForeignKeys0.SOURCE_ONE__FK_SOURCE_ONE_TARGET_ID;
    public static final ForeignKey<SourceSixRecord, TargetSixRecord> SOURCE_SIX__FK_SOURCE_SIX_TARGET_ID = ForeignKeys0.SOURCE_SIX__FK_SOURCE_SIX_TARGET_ID;
    public static final ForeignKey<SourceThreeRecord, TargetThreeRecord> SOURCE_THREE__FK_SOURCE_THREE_TARGET_ID = ForeignKeys0.SOURCE_THREE__FK_SOURCE_THREE_TARGET_ID;
    public static final ForeignKey<SourceTwoRecord, TargetTwoRecord> SOURCE_TWO__FK_SOURCE_TWO_TARGET_ID = ForeignKeys0.SOURCE_TWO__FK_SOURCE_TWO_TARGET_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<SourceFiveRecord> PK_SOURCE_FIVE = Internal.createUniqueKey(SourceFive.SOURCE_FIVE, "pk_source_five", SourceFive.SOURCE_FIVE.ID);
        public static final UniqueKey<SourceFourRecord> PK_SOURCE_FOUR = Internal.createUniqueKey(SourceFour.SOURCE_FOUR, "pk_source_four", SourceFour.SOURCE_FOUR.ID);
        public static final UniqueKey<SourceOneRecord> PK_SOURCE_ONE = Internal.createUniqueKey(SourceOne.SOURCE_ONE, "pk_source_one", SourceOne.SOURCE_ONE.ID);
        public static final UniqueKey<SourceSixRecord> PK_SOURCE_SIX = Internal.createUniqueKey(SourceSix.SOURCE_SIX, "pk_source_six", SourceSix.SOURCE_SIX.ID);
        public static final UniqueKey<SourceThreeRecord> PK_SOURCE_THREE = Internal.createUniqueKey(SourceThree.SOURCE_THREE, "pk_source_three", SourceThree.SOURCE_THREE.ID);
        public static final UniqueKey<SourceTwoRecord> PK_SOURCE_TWO = Internal.createUniqueKey(SourceTwo.SOURCE_TWO, "pk_source_two", SourceTwo.SOURCE_TWO.ID);
        public static final UniqueKey<TargetFiveRecord> PK_TARGET_FIVE = Internal.createUniqueKey(TargetFive.TARGET_FIVE, "pk_target_five", TargetFive.TARGET_FIVE.ID);
        public static final UniqueKey<TargetFourRecord> PK_TARGET_FOUR = Internal.createUniqueKey(TargetFour.TARGET_FOUR, "pk_target_four", TargetFour.TARGET_FOUR.ID);
        public static final UniqueKey<TargetOneRecord> PK_TARGET_ONE = Internal.createUniqueKey(TargetOne.TARGET_ONE, "pk_target_one", TargetOne.TARGET_ONE.ID);
        public static final UniqueKey<TargetSixRecord> PK_TARGET_SIX = Internal.createUniqueKey(TargetSix.TARGET_SIX, "pk_target_six", TargetSix.TARGET_SIX.ID);
        public static final UniqueKey<TargetThreeRecord> PK_TARGET_THREE = Internal.createUniqueKey(TargetThree.TARGET_THREE, "pk_target_three", TargetThree.TARGET_THREE.ID);
        public static final UniqueKey<TargetTwoRecord> PK_TARGET_TWO = Internal.createUniqueKey(TargetTwo.TARGET_TWO, "pk_target_two", TargetTwo.TARGET_TWO.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<SourceFiveRecord, TargetFiveRecord> SOURCE_FIVE__FK_SOURCE_FIVE_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_FIVE, SourceFive.SOURCE_FIVE, "source_five__fk_source_five_target_id", SourceFive.SOURCE_FIVE.TARGET_ID);
        public static final ForeignKey<SourceFourRecord, TargetFourRecord> SOURCE_FOUR__FK_SOURCE_FOUR_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_FOUR, SourceFour.SOURCE_FOUR, "source_four__fk_source_four_target_id", SourceFour.SOURCE_FOUR.TARGET_ID);
        public static final ForeignKey<SourceFourRecord, TargetFourRecord> SOURCE_FOUR__FK_SOURCE_FOUR_TARGET2_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_FOUR, SourceFour.SOURCE_FOUR, "source_four__fk_source_four_target2_id", SourceFour.SOURCE_FOUR.TARGET2_ID);
        public static final ForeignKey<SourceOneRecord, TargetOneRecord> SOURCE_ONE__FK_SOURCE_ONE_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_ONE, SourceOne.SOURCE_ONE, "source_one__fk_source_one_target_id", SourceOne.SOURCE_ONE.TARGET_ID);
        public static final ForeignKey<SourceSixRecord, TargetSixRecord> SOURCE_SIX__FK_SOURCE_SIX_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_SIX, SourceSix.SOURCE_SIX, "source_six__fk_source_six_target_id", SourceSix.SOURCE_SIX.TARGET_ID);
        public static final ForeignKey<SourceThreeRecord, TargetThreeRecord> SOURCE_THREE__FK_SOURCE_THREE_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_THREE, SourceThree.SOURCE_THREE, "source_three__fk_source_three_target_id", SourceThree.SOURCE_THREE.TARGET_ID);
        public static final ForeignKey<SourceTwoRecord, TargetTwoRecord> SOURCE_TWO__FK_SOURCE_TWO_TARGET_ID = Internal.createForeignKey(de.quinscape.domainql.testdomain.Keys.PK_TARGET_TWO, SourceTwo.SOURCE_TWO, "source_two__fk_source_two_target_id", SourceTwo.SOURCE_TWO.TARGET_ID);
    }
}
