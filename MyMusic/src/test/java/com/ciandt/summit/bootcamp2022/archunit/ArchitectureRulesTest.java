package com.ciandt.summit.bootcamp2022.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.ciandt.summit.bootcamp2022", importOptions = {ImportOption.DoNotIncludeJars.class, ImportOption.DoNotIncludeTests.class})
public class ArchitectureRulesTest {

    @ArchTest
    static final ArchRule layerDependenciesAreRespected = layeredArchitecture()
            .layer("Controllers").definedBy("..controller..")
            .layer("Services").definedBy("..service..")
            .layer("Repositories").definedBy("..repository..")
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Services");

    @ArchTest
    ArchRule serviceRule = classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    @ArchTest
    ArchRule repositoryRule = classes()
            .that().resideInAPackage("..repository..")
            .should().onlyBeAccessed().byAnyPackage("..service..", "..repository..");

    @ArchTest
    static ArchRule repositories_classes_must_be_in_the_repository_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameEndingWith("Repository")
                    .should()
                    .resideInAnyPackage("com.ciandt.summit.bootcamp2022.repository");

    @ArchTest
    static ArchRule entities_classes_must_be_in_the_entity_package =
            ArchRuleDefinition.classes().that()
                    .areAnnotatedWith(Entity.class)
                    .should()
                    .resideInAnyPackage("com.ciandt.summit.bootcamp2022.entity");

    @ArchTest
    static ArchRule controllers_classes_must_be_in_the_controller_package =
            ArchRuleDefinition.classes().that()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .resideInAnyPackage("com.ciandt.summit.bootcamp2022.controller");


}
