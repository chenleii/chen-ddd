package com.chen.ddd.test.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;

/**
 * ddd-client的架构约束
 *
 * @author cl
 * @date 2020/07/23
 */
@AnalyzeClasses(packages = "com.chen.ddd.client")
public class ClientArchTest {

    // service都要返回保证类型对象

}
