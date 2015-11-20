package com.ishare.mall.core.model.supplier;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SUPPLIER_TYPE_NAME;

/**
 * Created by YinLin on 2015/11/18.
 * Description : 供应商供货类型
 * Version 1.0
 */

@Entity
@Table(name = TABLE_SUPPLIER_TYPE_NAME)
public class SupplierType extends BaseEntity {

    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//自增ID

    @Column(name = "supplier_product_type_id", length = 20, nullable = false)
    private Integer typeId;//商品类型ID

    @Column(name = "supplier_product_type_name", length = 128, nullable = false)
    private String typeName;//经营商品类型名字 类型修改时需要一并修改供应商类型

    @Column(name = "supplier_product_royalty", length = 128, nullable = false)
    private Float royalty;//提点

    @Column(name = "supplier_sort", length = 10, nullable = false)
    private Integer sort;//排序

    @Column(name = "supplier_id", length = 20, nullable = false)
    private Integer supplierId;//供应商

    @Column(name = "supplier_name", length = 128, nullable = false)
    private String supplierName;//供应商名字

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRoyalty() {
        return royalty;
    }

    public void setRoyalty(Float royalty) {
        this.royalty = royalty;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
