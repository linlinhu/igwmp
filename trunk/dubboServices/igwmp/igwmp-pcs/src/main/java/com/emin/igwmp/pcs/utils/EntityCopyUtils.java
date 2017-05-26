package com.emin.igwmp.pcs.utils;

import com.emin.base.exception.EminException;
import com.emin.igwmp.pcs.exception.ExceptionCode;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/1.
 */
public class EntityCopyUtils {

    public static   Map<String ,Object> ObjectToMap(Object obj){
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;
    }

    public static void MapToObject(Map<String, Object> map, Object obj){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

        return;
    }


    public static void SimilarCopy(Object objSrc, Object objDst ) throws EminException {
        if(!objSrc.getClass().getName().equalsIgnoreCase(objDst.getClass().getName())){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"传入数据不是同样类");
        }
        BeanInfo beanInfo = null;
        BeanInfo beanInfoDst = null;
        try {

            beanInfo = Introspector.getBeanInfo(objSrc.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            beanInfoDst = Introspector.getBeanInfo(objDst.getClass());
            PropertyDescriptor[] propertyDescriptorsDst = beanInfoDst.getPropertyDescriptors();

            int propertyLen =  propertyDescriptors.length;
            for (int i = 0; i < propertyLen; i++) {
                PropertyDescriptor property= propertyDescriptors[i];
                if(property != null){
                    String key = property.getName();
                    if (key != null && !key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(objSrc);
                        if(value != null){
                            Method setter = propertyDescriptorsDst[i].getWriteMethod();
                            setter.invoke(objDst, value);
                        }

                    }
                }
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
