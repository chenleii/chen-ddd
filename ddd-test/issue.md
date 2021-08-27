
## 不处理 @SpyBean 注解
cucumber-spring 没有处理 TestContextManager 钩子，所以所有基于 TestContextManager 钩子的功能都无法使用。

## @DirtiesContext 注解导致报错
黄瓜测试 spring 上下文只启动一次，如果把当前上下文关闭，不会重建。

## Archunit should 不能嵌套使用。如：should().and(should().or().should())