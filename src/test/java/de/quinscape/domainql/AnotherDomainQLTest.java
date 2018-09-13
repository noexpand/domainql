package de.quinscape.domainql;

import de.quinscape.domainql.beans.CustomParameterProviderLogic;
import de.quinscape.domainql.beans.LogicWithAnnotated;
import de.quinscape.domainql.beans.LogicWithEnums;
import de.quinscape.domainql.beans.LogicWithEnums2;
import de.quinscape.domainql.beans.LogicWithGenerics;
import de.quinscape.domainql.beans.LogicWithMirrorInput;
import de.quinscape.domainql.beans.LogicWithWrongInjection;
import de.quinscape.domainql.beans.LogicWithWrongInjection2;
import de.quinscape.domainql.beans.NoMirroLogic;
import de.quinscape.domainql.beans.SourceTwoInput;
import de.quinscape.domainql.beans.TestLogic;
import de.quinscape.domainql.beans.TypeRepeatLogic;
import de.quinscape.domainql.config.SourceField;
import de.quinscape.domainql.config.TargetField;
import de.quinscape.domainql.testdomain.Public;
import de.quinscape.domainql.testdomain.tables.SourceOne;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;

import static de.quinscape.domainql.testdomain.Tables.*;
import static graphql.schema.GraphQLNonNull.nonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AnotherDomainQLTest
{

    private final static Logger log = LoggerFactory.getLogger(AnotherDomainQLTest.class);


    @Test
    public void testRelationRenaming()
    {
        // same config as before, but with configured names for the sides that are active

        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TestLogic()))

            .configureRelation(   SOURCE_TWO.TARGET_ID, SourceField.SCALAR, TargetField.NONE, "scalarFieldId", null)
            .configureRelation( SOURCE_THREE.TARGET_ID, SourceField.OBJECT, TargetField.NONE, "objField", null)
            .configureRelation(  SOURCE_FIVE.TARGET_ID, SourceField.NONE, TargetField.ONE, null, "oneObj")
            .configureRelation(   SOURCE_SIX.TARGET_ID, SourceField.NONE, TargetField.MANY, null, "manyObj")
            .createMirrorInputTypes(true)
            .buildGraphQLSchema();


        // SourceField.SCALAR
        {
            // just the id fields
            final GraphQLObjectType sourceTwo = (GraphQLObjectType) schema.getType("SourceTwo");
            assertThat(sourceTwo,is(notNullValue()));
            assertThat(sourceTwo.getFieldDefinitions().size(),is(2));
            assertThat(sourceTwo.getFieldDefinitions().get(1).getName(),is("scalarFieldId"));
            assertThat(sourceTwo.getFieldDefinitions().get(1).getType(),is(nonNull(Scalars.GraphQLString)));

        }


        // SourceField.OBJECT
        {
            // just the id fields
            final GraphQLObjectType sourceThree = (GraphQLObjectType) schema.getType("SourceThree");
            assertThat(sourceThree,is(notNullValue()));
            assertThat(sourceThree.getFieldDefinitions().size(),is(2));
            assertThat(sourceThree.getFieldDefinitions().get(1).getName(),is("objField"));
            assertThat(sourceThree.getFieldDefinitions().get(1).getType(),is(nonNull(schema.getType("TargetThree"))));
        }

        // TargetField.ONE
        {
            // just the id fields
            final GraphQLObjectType targetFive = (GraphQLObjectType) schema.getType("TargetFive");
            assertThat(targetFive,is(notNullValue()));
            assertThat(targetFive.getFieldDefinitions().size(),is(2));
            assertThat(targetFive.getFieldDefinitions().get(1).getName(),is("oneObj"));
            assertThat(targetFive.getFieldDefinitions().get(1).getType(),is(nonNull(schema.getType("SourceFive"))));
        }

        // TargetField.MANY
        {
            // just the id fields
            final GraphQLObjectType targetSix = (GraphQLObjectType) schema.getType("TargetSix");
            assertThat(targetSix,is(notNullValue()));
            assertThat(targetSix.getFieldDefinitions().size(),is(2));
            assertThat(targetSix.getFieldDefinitions().get(1).getName(),is("manyObj"));
            assertThat(targetSix.getFieldDefinitions().get(1).getType(),is(nonNull(new GraphQLList(schema.getType("SourceSix")))));
        }
    }


    @Test
    public void testInputMirrorCreation()
    {

        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Arrays.asList(new TestLogic(), new LogicWithMirrorInput(), new NoMirroLogic()))
            .createMirrorInputTypes(true)
            // test override
            .overrideInputTypes(SourceTwoInput.class)
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.NONE, TargetField.NONE)
            .configureRelation(   SOURCE_TWO.TARGET_ID, SourceField.SCALAR, TargetField.NONE)
            .configureRelation( SOURCE_THREE.TARGET_ID, SourceField.OBJECT, TargetField.NONE)
            .configureRelation(  SOURCE_FIVE.TARGET_ID, SourceField.NONE, TargetField.ONE)
            .configureRelation(   SOURCE_SIX.TARGET_ID, SourceField.NONE, TargetField.MANY)
            .buildGraphQLSchema();


        {

            final GraphQLInputObjectType sourceOneInput = (GraphQLInputObjectType) schema.getType("SourceOneInput");
            assertThat(sourceOneInput, is(notNullValue()));

            assertThat(sourceOneInput.getFields().size(), is(2));
            assertThat(sourceOneInput.getField("id").getType(),is(nonNull(Scalars.GraphQLString)));
            assertThat(sourceOneInput.getField("targetId").getType(),is(nonNull(Scalars.GraphQLString)));
        }

        {

            final GraphQLInputObjectType sourceOneInput = (GraphQLInputObjectType) schema.getType("SourceTwoInput");
            assertThat(sourceOneInput, is(notNullValue()));

            assertThat(sourceOneInput.getFields().size(), is(3));
            assertThat(sourceOneInput.getField("id").getType(),is(nonNull(Scalars.GraphQLString)));
            assertThat(sourceOneInput.getField("targetId").getType(),is(nonNull(Scalars.GraphQLString)));
            assertThat(sourceOneInput.getField("foo").getType(),is(Scalars.GraphQLString));
        }


        {
            final GraphQLObjectType queryType = (GraphQLObjectType) schema.getType("QueryType");
            final GraphQLFieldDefinition fieldDef = queryType.getFieldDefinition("queryWithMirrorInput");

            final GraphQLArgument arg = fieldDef.getArgument("inputOne");
            assertThat(arg, is(notNullValue()));
            assertThat(arg.getType().getName(), is("SourceOneInput"));
        }

        {
            final GraphQLObjectType queryType = (GraphQLObjectType) schema.getType("NoMirrorInput");
            assertThat(queryType, is(nullValue()));
        }

    }


    @Test(expected = DomainQLException.class)
    public void testFieldConflict()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TestLogic()))
            .configureRelation( SOURCE_FOUR.TARGET_ID, SourceField.NONE, TargetField.ONE)
            .configureRelation(SOURCE_FOUR.TARGET2_ID, SourceField.NONE, TargetField.ONE)
            .buildGraphQLSchema();

    }

    @Test
    public void testFieldConflictResolution()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TestLogic()))
            .configureRelation(  SOURCE_FOUR.TARGET_ID, SourceField.NONE, TargetField.ONE)
            .configureRelation( SOURCE_FOUR.TARGET2_ID, SourceField.NONE, TargetField.ONE, null, "source2")
            .buildGraphQLSchema();

    }



    @Test
    public void testObjectAndScalarScourceFields()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TestLogic()))

            // source variants
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.OBJECT_AND_SCALAR, TargetField.NONE)
            .buildGraphQLSchema();

        // SourceField.OBJECT_AND_SCALAR / TargetField.NONE
        {
            // just the id fields
            final GraphQLObjectType sourceOne = (GraphQLObjectType) schema.getType("SourceOne");
            assertThat(sourceOne,is(notNullValue()));
            assertThat(sourceOne.getFieldDefinitions().size(),is(3));
            assertThat(sourceOne.getFieldDefinitions().get(0).getName(),is("id"));
            assertThat(sourceOne.getFieldDefinitions().get(0).getType(),is(nonNull(Scalars.GraphQLString)));
            assertThat(sourceOne.getFieldDefinitions().get(1).getName(),is("targetId"));
            assertThat(sourceOne.getFieldDefinitions().get(1).getType(),is(nonNull(Scalars.GraphQLString)));
            assertThat(sourceOne.getFieldDefinitions().get(2).getName(),is("target"));
            assertThat(sourceOne.getFieldDefinitions().get(2).getType(),is(nonNull(schema.getType("TargetOne"))));

        }
    }


    @Test(expected =  DomainQLTypeException.class)
    public void testWrongType()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TestLogic()))
            .overrideInputTypes(SourceOne.class)

            // source variants
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.OBJECT_AND_SCALAR, TargetField.NONE)
            .buildGraphQLSchema();
    }

    @Test(expected =  DomainQLTypeException.class)
    public void testWrongTypeAsQueryInput()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new LogicWithWrongInjection()))

            // source variants
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.OBJECT_AND_SCALAR, TargetField.NONE)
            .buildGraphQLSchema();
    }

    @Test(expected =  DomainQLTypeException.class)
    public void testRecordAsQueryInput()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new LogicWithWrongInjection2()))

            // source variants
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.OBJECT_AND_SCALAR, TargetField.NONE)
            .buildGraphQLSchema();
    }

    @Test
    public void testAnnotatedFields()
    {
        final DomainQLBuilder builder = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new LogicWithAnnotated()));

        final GraphQLSchema schema = builder.buildGraphQLSchema();

        final GraphQLObjectType outputType = (GraphQLObjectType) schema.getType("AnnotatedBean");
        assertThat(outputType, is(notNullValue()));
        assertThat(outputType.getFieldDefinition("value").getType().getName(), is("Currency"));

        final GraphQLInputObjectType inputType = (GraphQLInputObjectType) schema.getType("AnnotatedBeanInput");
        assertThat(inputType, is(notNullValue()));
        assertThat(outputType.getFieldDefinition("value").getType().getName(), is("Currency"));
    }


    @Test
    public void testTypeRepeat()
    {

        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .logicBeans(Collections.singleton(new TypeRepeatLogic()))

            // source variants
            .configureRelation(   SOURCE_ONE.TARGET_ID, SourceField.NONE, TargetField.NONE)
            .configureRelation(   SOURCE_TWO.TARGET_ID, SourceField.SCALAR, TargetField.NONE)
            .configureRelation( SOURCE_THREE.TARGET_ID, SourceField.OBJECT, TargetField.NONE)
            .configureRelation(  SOURCE_FIVE.TARGET_ID, SourceField.NONE, TargetField.ONE)
            .configureRelation(   SOURCE_SIX.TARGET_ID, SourceField.NONE, TargetField.MANY)
            .configureRelation(    SOURCE_SEVEN.TARGET, SourceField.OBJECT, TargetField.NONE)

            .buildGraphQLSchema();


    }


    @Test
    public void testCustomParameterProvider()
    {

        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .objectTypes(Public.PUBLIC)
            .parameterProvider(new TestParameterProviderFactory())
            .logicBeans(Collections.singleton(new CustomParameterProviderLogic()))
            .buildGraphQLSchema();


        assertThat(schema.getType("DependencyBeanInput"), is(nullValue()));

    }


    @Test
    public void testLogicWithGenerics()
    {

        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .logicBeans(Collections.singleton(new LogicWithGenerics()))
            .buildGraphQLSchema();


        final GraphQLObjectType mutationType = schema.getMutationType();
        {


            final GraphQLFieldDefinition mutation = mutationType.getFieldDefinition(
                "mutationReturningListOfInts");

            assertThat(mutation,is(notNullValue()));

            final GraphQLList type = (GraphQLList) mutation.getType();
            assertThat(type.getWrappedType().getName(), is( "Int"));
        }
        {

            final GraphQLFieldDefinition mutation = mutationType.getFieldDefinition(
                "mutationWithIntListParam");

            assertThat(mutation,is(notNullValue()));

            final GraphQLArgument graphQLArgument = mutation.getArguments().get(0);
            final GraphQLList type = (GraphQLList) graphQLArgument.getType();
            assertThat(type.getWrappedType().getName(), is( "Int"));
        }

        {


            final GraphQLFieldDefinition mutation = mutationType.getFieldDefinition(
                "mutationReturningListOfObject");

            assertThat(mutation,is(notNullValue()));

            final GraphQLList type = (GraphQLList) mutation.getType();
            assertThat(type.getWrappedType().getName(), is( "DependencyBean"));
        }


        {

            final GraphQLFieldDefinition mutation = mutationType.getFieldDefinition(
                "mutationWithObjectListParam");

            assertThat(mutation,is(notNullValue()));

            final GraphQLArgument graphQLArgument = mutation.getArguments().get(0);
            final GraphQLList type = (GraphQLList) graphQLArgument.getType();
            assertThat(type.getWrappedType().getName(), is( "DependencyBeanInput"));
        }

    }


    @Test
    public void testLogicWithEnums()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .logicBeans(Collections.singleton(new LogicWithEnums()))
            .buildGraphQLSchema();
        final GraphQLObjectType queryType = schema.getQueryType();

        {

            GraphQLFieldDefinition query = queryType.getFieldDefinition("queryWithEnumArg");

            assertThat(query.getArguments().get(0).getType().getName(), is("MyEnum"));

            final GraphQLEnumType myEnum = (GraphQLEnumType) schema.getType("MyEnum");
            assertThat(myEnum.getValues().get(0).getName(), is("A"));
            assertThat(myEnum.getValues().get(1).getName(), is("B"));
            assertThat(myEnum.getValues().get(2).getName(), is("C"));
        }

        {

            GraphQLFieldDefinition query = queryType.getFieldDefinition("queryWithObjectArgWithEnum");

            assertThat(query.getArguments().get(0).getType().getName(), is("BeanWithEnumInput"));
            final GraphQLInputObjectType bean = (GraphQLInputObjectType) schema.getType("BeanWithEnumInput");

            assertThat(bean.getField("anotherEnum").getType().getName(), is("AnotherEnum"));

            final GraphQLEnumType myEnum = (GraphQLEnumType) schema.getType("AnotherEnum");
            assertThat(myEnum.getValues().get(0).getName(), is("X"));
            assertThat(myEnum.getValues().get(1).getName(), is("Y"));
            assertThat(myEnum.getValues().get(2).getName(), is("Z"));

        }
    }

    @Test
    public void testLogicWithEnums2()
    {
        final GraphQLSchema schema = DomainQL.newDomainQL(null)
            .logicBeans(Collections.singleton(new LogicWithEnums2()))
            .buildGraphQLSchema();
        final GraphQLObjectType mutationType = schema.getMutationType();
        {

            GraphQLFieldDefinition query = mutationType.getFieldDefinition("enumMutation");

            assertThat(query.getType().getName(), is("MyEnum"));
            final GraphQLEnumType myEnum = (GraphQLEnumType) schema.getType("MyEnum");
            assertThat(myEnum.getValues().get(0).getName(), is("A"));
            assertThat(myEnum.getValues().get(1).getName(), is("B"));
            assertThat(myEnum.getValues().get(2).getName(), is("C"));
        }

        {

            GraphQLFieldDefinition query = mutationType.getFieldDefinition("objectWithEnumMutation");

            assertThat(query.getType().getName(), is("BeanWithEnum"));

            final GraphQLObjectType bean = (GraphQLObjectType) schema.getType("BeanWithEnum");

            assertThat(bean.getFieldDefinition("anotherEnum").getType().getName(), is("AnotherEnum"));

            final GraphQLEnumType myEnum = (GraphQLEnumType) schema.getType("AnotherEnum");
            assertThat(myEnum.getValues().get(0).getName(), is("X"));
            assertThat(myEnum.getValues().get(1).getName(), is("Y"));
            assertThat(myEnum.getValues().get(2).getName(), is("Z"));
        }
    }
}