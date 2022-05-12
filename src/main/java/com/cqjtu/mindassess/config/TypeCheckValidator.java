package com.cqjtu.mindassess.config;

import com.cqjtu.mindassess.annotation.TypeCheck;
import com.cqjtu.mindassess.constans.AssessTypeCons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangzhencheng
 */
public class TypeCheckValidator implements ConstraintValidator<TypeCheck, String> {
    private String[] values;
    @Override
    public void initialize(TypeCheck typeCheck) {
        System.out.println("dsadasd");
        this.values = typeCheck.value();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("大萨达所多---------------");
        List<String> types = new ArrayList<>();
        types.add(AssessTypeCons.BODY);
        types.add(AssessTypeCons.OPPOSE);
        types.add(AssessTypeCons.ANXIOUS);
        types.addAll(Arrays.asList(values));
        types.add(AssessTypeCons.DEPRESSION);
        types.add(AssessTypeCons.INTERPERSONAL);
        types.add(AssessTypeCons.OBSESSION);
        types.add(AssessTypeCons.PSYCHOSIS);
        types.add(AssessTypeCons.STUBBORN);
        types.add(AssessTypeCons.TERROR);
        return types.contains(value);
    }
}
