package com.chen.ddd.test.archunit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.jddd.core.annotation.AggregateRoot;
import org.jddd.core.annotation.Entity;
import org.jddd.core.annotation.Service;
import org.jddd.core.annotation.ValueObject;

import static com.tngtech.archunit.lang.conditions.ArchConditions.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * arch文档: https://www.archunit.org/userguide/html/000_Index.html
 * <p>
 * 参考arch-junit5示例：https://github.com/TNG/ArchUnit-Examples/tree/main/example-junit5
 *
 * @author cl
 * @date 2020/07/21
 */
@AnalyzeClasses(packages = "com.chen.ddd.core")
public class GeneralArchTest {

    @ArchTest
    public static final ArchRule domain_should_not_access_application =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAPackage("..application..");

    @ArchTest
    public static final ArchRule domain_should_not_access_port =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAPackage("..port..");

    @ArchTest
    public static final ArchRule port_should_not_access_domain_service_entity =
            noClasses().that().resideInAPackage("..port..")
                    .should().dependOnClassesThat().areAnnotatedWith(Service.class)
                    .orShould().dependOnClassesThat().areAnnotatedWith(AggregateRoot.class)
                    .orShould().dependOnClassesThat().areAnnotatedWith(Entity.class);

    @ArchTest
    public static final ArchRule port_should_not_access_application =
            noClasses().that().resideInAPackage("..port..")
                    .should().dependOnClassesThat().resideInAPackage("..application..");

    /**
     * https://www.javacodegeeks.com/2019/12/arch-unit.html
     */
    static ArchCondition<JavaClass> noPublicSettersCondition =
            new ArchCondition<JavaClass>("class has no public setters") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    for (JavaMethod javaMethod : item.getMethods()) {
                        if (javaMethod.getName().startsWith("set") &&
                                javaMethod.getModifiers().contains(JavaModifier.PUBLIC)) {
                            String message = String.format(
                                    "Public method %s is not allowed begin with setter", javaMethod.getName());
                            events.add(SimpleConditionEvent.violated(item, message));
                        }
                    }
                }
            };

    @ArchTest
    public static final ArchRule value_object_should_not_have_setters = classes().that()
            .areAnnotatedWith(ValueObject.class)
            .should(noPublicSettersCondition);

}
