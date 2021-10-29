package com.otoil.ot_1_1_1.client.utils;


import java.util.function.BiConsumer;
import java.util.function.Function;

import ru.ot.gwt.utils.client.BeanProperty;


public class BeanPropertyUtils
{
    public static <B, P> BeanProperty<B, P> createBeanProperty(
        final Function<B, P> getter, final BiConsumer<B, P> setter)
    {
        return new BeanProperty<B, P>()
        {
            @Override
            public void set(B bean, P value)
            {
                setter.accept(bean, value);
            }

            @Override
            public P get(B bean)
            {
                return getter.apply(bean);
            }
        };
    }
}
