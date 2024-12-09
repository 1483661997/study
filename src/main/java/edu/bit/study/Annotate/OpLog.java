package edu.bit.study.Annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    /**
     * 业务对象名称，如订单、库存、价格
     *
     * @return
     */
    public String opItem();



}
