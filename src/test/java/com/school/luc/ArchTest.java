package com.school.luc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.school.luc")
public class ArchTest {

  @com.tngtech.archunit.junit.ArchTest
  static final ArchRule rest_is_used_by_rest_only =
      noClasses()
          .that()
          .resideOutsideOfPackage("..rest..")
          .should()
          .dependOnClassesThat()
          .resideInAPackage("..rest..");
}
