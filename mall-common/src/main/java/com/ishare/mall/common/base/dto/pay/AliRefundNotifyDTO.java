package com.ishare.mall.common.base.dto.pay;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by YinLin on 2015/8/27.
 * Description : 阿里退款
 * Version 1.0
 */
public class AliRefundNotifyDTO extends GenericDTO {
    private static final long serialVersionUID = -2601175208254084539L;
    private String notify_time; //通知时间
    private String notify_type; //通知类型
    private String notify_id;   //通知校验id
    private String sign_type;   //签名方式
    private String sign;        //签名
    private String batch_no;    //退款批次号
    private String success_num; //退款成功总数
    private String result_details; //退款结果明细

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getSuccess_num() {
        return success_num;
    }

    public void setSuccess_num(String success_num) {
        this.success_num = success_num;
    }

    public String getResult_details() {
        return result_details;
    }

    public void setResult_details(String result_details) {
        this.result_details = result_details;
    }
}
