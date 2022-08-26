package com.ciandt.summit.bootcamp2022.archunit.repositories;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@AnalyzeClasses(packages = "com.ciandt.summit.bootcamp2022.repository", importOptions = {ImportOption.DoNotIncludeJars.class, ImportOption.DoNotIncludeTests.class})
public class RepositoryRulesTest {

    @ArchTest
    public static final ArchRule repositories_should_be_annotation_restcontroller = ArchRuleDefinition
            .classes().that().resideInAPackage("com.ciandt.summit.bootcamp2022.repository")
            .should().beAnnotatedWith(Repository.class)
            .because("repositories should be annotation");


    @ArchTest
    public static final ArchRule repositories_should_be_annotation_autowired_only_in_constructor = ArchRuleDefinition
            .classes().should().notBeAnnotatedWith(Autowired.class)
            .because("repositories should be annotation autowired only in constructor");

    @ArchTest
    public static final ArchRule repositories_should_be_interfaces = ArchRuleDefinition
            .classes().that().areAnnotatedWith(Repository.class)
            .should().beInterfaces();
}
