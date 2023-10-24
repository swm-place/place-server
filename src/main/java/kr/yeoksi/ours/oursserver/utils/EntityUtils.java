package kr.yeoksi.ours.oursserver.utils;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    private EntityUtils() {}

    public static void updateNotNullProperties(Object target, Object source) {
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());

        List<String> ignoreProperties = new ArrayList<>();

        for (PropertyDescriptor sourcePd : sourcePds) {
            Method readMethod = sourcePd.getReadMethod();
            if (readMethod != null) {
                try {
                    Object value = readMethod.invoke(source);
                    if (value == null) {
                        ignoreProperties.add(sourcePd.getName());
                    }
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[0]));
    }
}
