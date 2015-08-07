package com.ishare.mall.core.utils.mapper;

import ma.glasnost.orika.MapperFacade;

import java.util.List;

/**
 * Created by YinLin on 2015/8/7.
 * Description: JSON mapper
 * Version 1.0
 */
public final class MapperUtils {

    private static MapperFacade orikaMapper;

    private static MapperFacade getOrikaMapper() {
        if (orikaMapper == null) {
            orikaMapper = new OrikaMapper();
        }
        return orikaMapper;
    }

    public static List<?> mapAsList(Iterable<?> source,
                                    Class<?> destinationClass) {
        return getOrikaMapper().mapAsList(source, destinationClass);
    }

    public static Object map(Object source, Class<?> destinationClass) {
        return getOrikaMapper().map(source, destinationClass);
    }
}
