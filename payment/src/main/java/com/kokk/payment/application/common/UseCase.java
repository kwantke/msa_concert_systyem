package com.kokk.payment.application.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

// UseCase 레이어(애플리케이션 계층) 빈을 명확하게 구분하기 위한 표식(스테레오타입)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface UseCase {
  @AliasFor(annotation = Component.class)
  String value() default "";
}
