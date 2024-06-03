package org.example.bigevent.anno;

import org.example.bigevent.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//元注解 如果一个注解类型加上了 @Documented，那么它的声明会出现在由 javadoc 自动生成的文档中
@Documented
//元注解 标注的目标类型，这里指定为 FIELD，表示该注解可以用来修饰字段
@Target({ElementType.FIELD})
//元注解 指定自定义注解 @State 的生命周期，这里指定为 RUNTIME，表示自定义注解将在运行时保留
@Retention(RetentionPolicy.RUNTIME)
//指定提供校验规则的类
@Constraint(
        validatedBy = {StateValidation.class}
)
public @interface State {
    //提供校验失败后的信息
    String message() default "state的参数值只能是已发布或者草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 能获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
