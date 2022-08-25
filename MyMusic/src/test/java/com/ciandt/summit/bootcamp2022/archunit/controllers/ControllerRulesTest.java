package com.ciandt.summit.bootcamp2022.archunit.controllers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.ciandt.summit.bootcamp2022.controller", importOptions = {ImportOption.DoNotIncludeJars.class, ImportOption.DoNotIncludeTests.class})
public class ControllerRulesTest {

    @ArchTest
    public static final ArchRule controllers_should_be_annotation_restcontroller = ArchRuleDefinition
            .classes().that().resideInAPackage("com.ciandt.summit.bootcamp2022.controller")
            .should().beAnnotatedWith(RestController.class)
            .because("controllers should be annotation");

    @ArchTest
    public static final ArchRule controllers_should_be_annotation_autowired_only_in_constructor = ArchRuleDefinition
            .classes().should().notBeAnnotatedWith(Autowired.class)
            .because("controllers should be annotation autowired only in constructor");

}

