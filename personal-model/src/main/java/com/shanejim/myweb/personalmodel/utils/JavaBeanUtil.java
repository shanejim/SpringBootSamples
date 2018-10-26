package com.shanejim.myweb.personalmodel.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 16:15
 **/
public class JavaBeanUtil {
    private static Logger logger = LoggerFactory.getLogger(JavaBeanUtil.class);

    /**
     * 实体类转化成string string的map
     *
     * @param obj           实体类
     * @param excludeFields 排除的字段名
     * @return
     */
    public static Map<String, String> convertBeanToStringMap(Object obj, String[] excludeFields) {
        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性 和 过滤排除的字段
                if (!ArrayUtils.contains(excludeFields, key) && !key.equals("class")) {
                    // 得到property对应的getter方法             
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    if (value != null) {
                        map.put(key, value.toString());
                    } else {
                        map.put(key, null);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("convertBeanToStringMap Error {}", e);
        }
        return map;
    }

}
