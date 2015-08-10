
package com.ishare.mall.core.utils.mapper;

import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.core.model.product.Product;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YinLin on 2015/8/7.
 * Description: 具体的mapper接口实现
 * Version 1.0
 */
public class OrikaMapper extends ConfigurableMapper {

    private static final Logger log = LoggerFactory.getLogger(OrikaMapper.class);

	@Override
	public void configure(MapperFactory mapperFactory) {
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(Product.class, ProductDTO.class));
	    this.registerProductClassMap(mapperFactory);
    }

	/**
	 *
	 * @param mapperFactory
	 */
	private void registerProductClassMap(MapperFactory mapperFactory) {
        ClassMapBuilder<Product, ProductDTO> classMapBuilder = mapperFactory.classMap(Product.class, ProductDTO.class);
        Field[] fields = ProductDTO.class.getDeclaredFields();
        Set<String> otherDealField = new HashSet<String>();
        otherDealField.add("createByAccount");
        otherDealField.add("updateByAccount");
        otherDealField.add("brandId");
        otherDealField.add("channelId");
        otherDealField.add("typeId");
        otherDealField.add("brandName");
        otherDealField.add("channelName");
        otherDealField.add("typeName");
        for (Field field : fields) {
            if (!otherDealField.contains(field.getName())) {
                classMapBuilder.field(field.getName(), field.getName());
            }
        }
        classMapBuilder.field("createBy.account", "createByAccount");
        classMapBuilder.field("updateBy.account", "updateByAccount");
        classMapBuilder.field("brand.id", "brandId");
        classMapBuilder.field("brand.name", "brandName");
        classMapBuilder.field("channel.id", "channelId");
        classMapBuilder.field("channel.name", "channelName");
        classMapBuilder.field("type.id", "typeId");
        classMapBuilder.field("type.name", "typeName");
        mapperFactory.registerClassMap(classMapBuilder.toClassMap());
	}

	@Override
	public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
	}

    public static Logger getLog() {
        return log;
    }
}
