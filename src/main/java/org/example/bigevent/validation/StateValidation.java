package org.example.bigevent.validation;

import org.example.bigevent.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//两个参数：给哪个注解提供校验规则；校验规则的数据类型
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * 注释生成快捷键：/**enter
     * @param value 将来要校验的数据
     * @param constraintValidatorContext
     * @return false校验不通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if(value == null || value.isEmpty()){
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
