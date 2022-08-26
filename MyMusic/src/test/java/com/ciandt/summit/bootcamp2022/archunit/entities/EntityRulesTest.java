package com.ciandt.summit.bootcamp2022.archunit.entities;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import java.io.Serializable;

@AnalyzeClasses(packages = "com.ciandt.summit.bootcamp2022.entity", importOptions = {ImportOption.DoNotIncludeJars.class, ImportOption.DoNotIncludeTests.class})
public class EntityRulesTest {


    @ArchTest
    public static final ArchRule entities_should_be_annotation_entitiy = ArchRuleDefinition
            .classes().that().resideInAPackage("com.ciandt.summit.bootcamp2022.entity")
            .should().beAnnotatedWith(Entity.class)
            .because("entities should be annotation");

    @ArchTest
    public static final ArchRule entities_should_be_annotation_autowired_onlyIn_constructor = ArchRuleDefinition
            .classes().should().notBeAnnotatedWith(Autowired.class)
            .because("entities should be annotation autowired only in constructor");

    @ArchTest
    public static final ArchRule entities_should_implement_serializable = ArchRuleDefinition
            .classes().that().resideInAPackage("com.ciandt.summit.bootcamp2022.entity")
            .and().areAnnotatedWith(Entity.class)
            .should().implement(Serializable.class);
}
