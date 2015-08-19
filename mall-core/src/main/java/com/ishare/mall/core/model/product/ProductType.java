package com.ishare.mall.core.model.product;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_TYPE_NAME;

/**
 * Created by YinLin on 2015/7/31.
 * Description : 商品类别表，自连接表，code 001000100001这种形式来表示商品类型，使得商品可以快速的被查找
 * Version 1.0
 */
@Entity(name = TABLE_PRODUCT_TYPE_NAME)
public class ProductType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**类别名称**/
    @Column(length = 36, nullable = false, name = "type_name")
    private String name;
    /**类别代号**/
    @Column(length = 20, nullable = false, name = "type_code")
    private String code;
    /**类别描述**/
    @Column(length = 36, nullable = false, name = "type_note")
    private String note;
    /**父节点**/
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name ="type_parent_id")
    private ProductType parent;
    /**所有子节点**/
    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, mappedBy="parent")
    private Set<ProductType> childTypes = Sets.newConcurrentHashSet();
    /**所有商品**/
    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    private Set<Product> products = Sets.newConcurrentHashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProductType getParent() {
        return parent;
    }

    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    public Set<ProductType> getChildTypes() {
        return childTypes;
    }

    public void setChildTypes(Set<ProductType> childTypes) {
        this.childTypes = childTypes;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", code=" + code
				+ ", note=" + note + "]";
	}
    
}
