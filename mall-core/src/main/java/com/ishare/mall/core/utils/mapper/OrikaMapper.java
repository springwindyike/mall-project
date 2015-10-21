
package com.ishare.mall.core.utils.mapper;

import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductType;
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
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(Member.class, MemberDetailDTO.class));
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(Product.class, ProductDetailDTO.class));
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(Order.class, OrderDetailDTO.class));
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(ProductType.class, ProductTypeDTO.class));
		mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(ManageUser.class, ManageUserDTO.class));
	    this.registerProductClassMap(mapperFactory);
	    this.registerMemberClassMap(mapperFactory);
	    this.registerOrderClassMap(mapperFactory);
	    this.registerProductTypeClassMap(mapperFactory);
		this.registerManageUserClassMap(mapperFactory);
    }

	/**
	 *
	 * @param mapperFactory
	 */
	private void registerProductClassMap(MapperFactory mapperFactory) {
        ClassMapBuilder<Product, ProductDetailDTO> classMapBuilder = mapperFactory.classMap(Product.class, ProductDetailDTO.class);
        Field[] fields = ProductDetailDTO.class.getDeclaredFields();
        Set<String> otherDealField = new HashSet<String>();
        otherDealField.add("createByAccount");
        otherDealField.add("updateByAccount");
        otherDealField.add("brandId");
        otherDealField.add("channelId");
        otherDealField.add("typeId");
        otherDealField.add("brandName");
        otherDealField.add("channelName");
        otherDealField.add("typeName");
        otherDealField.add("gender");
		otherDealField.add("list");
		otherDealField.add("serialVersionUID");
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

	private void registerMemberClassMap(MapperFactory mapperFactory) {
		ClassMapBuilder<Member, MemberDetailDTO>classMapBuilder = mapperFactory.classMap(Member.class, MemberDetailDTO.class);
		Field[] fields = MemberDetailDTO.class.getDeclaredFields();
		Set<String> otherDealField = new HashSet<String>();
		otherDealField.add("channelId");
		otherDealField.add("createTimeStr");
		for (Field field : fields) {
			if (!otherDealField.contains(field.getName())) {
				classMapBuilder.field(field.getName(), field.getName());
			}
		}
		classMapBuilder.field("channel.id", "channelId");
		mapperFactory.registerClassMap(classMapBuilder.toClassMap());
	}

	private void registerManageUserClassMap(MapperFactory mapperFactory) {
		ClassMapBuilder<ManageUser, ManageUserDTO>classMapBuilder = mapperFactory.classMap(ManageUser.class, ManageUserDTO.class);
		Field[] fields = ManageUserDTO.class.getDeclaredFields();
		Set<String> otherDealField = new HashSet<String>();
		otherDealField.add("channelId");
		otherDealField.add("createTimeStr");
		otherDealField.add("serialVersionUID");
		for (Field field : fields) {
			if (!otherDealField.contains(field.getName())) {
				classMapBuilder.field(field.getName(), field.getName());
			}
		}
		mapperFactory.registerClassMap(classMapBuilder.toClassMap());
	}
	
	private void registerProductTypeClassMap(MapperFactory mapperFactory) {
		ClassMapBuilder<ProductType, ProductTypeDTO>classMapBuilder = mapperFactory.classMap(ProductType.class, ProductTypeDTO.class);
		Field[] fields = ProductTypeDTO.class.getDeclaredFields();
		Set<String> otherDealField = new HashSet<String>();
		otherDealField.add("parentId");
		otherDealField.add("typeName");
		classMapBuilder.field("parent.id", "parentId");
		classMapBuilder.field("id", "id");
		classMapBuilder.field("code", "code");
		classMapBuilder.field("name", "typeName");
		classMapBuilder.field("level", "level");
		classMapBuilder.field("note", "note");
		mapperFactory.registerClassMap(classMapBuilder.toClassMap());
	}
	
	private void registerOrderClassMap(MapperFactory mapperFactory) {
		ClassMapBuilder<Order, OrderDetailDTO>classMapBuilder = mapperFactory.classMap(Order.class, OrderDetailDTO.class);
		Field[] fields = OrderDetailDTO.class.getDeclaredFields();
		Set<String> otherDealField = new HashSet<String>();
		otherDealField.add("channelId");
		otherDealField.add("orderDeliverInfoId");
		otherDealField.add("orderContactInfoId");
		otherDealField.add("serialVersionUID");
		otherDealField.add("offset");
		otherDealField.add("limit");
		otherDealField.add("pageDTO");
		otherDealField.add("recipients");
		otherDealField.add("deliver");
		otherDealField.add("contact");
		otherDealField.add("items");
		otherDealField.add("stateValue");
		otherDealField.add("log");
		otherDealField.add("createBy");

		for (Field field : fields) {
			if (!otherDealField.contains(field.getName())) {
				classMapBuilder.field(field.getName(), field.getName());
			}
		}
		classMapBuilder.field("channel.id", "channelId");
		classMapBuilder.field("orderDeliverInfo.id", "orderDeliverInfoId");
		classMapBuilder.field("orderContactInfo.id", "orderContactInfoId");
		classMapBuilder.field("createBy.account", "createBy");
		mapperFactory.registerClassMap(classMapBuilder.toClassMap());
	}
	@Override
	public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
		
	}

    public static Logger getLog() {
        return log;
    }
}
