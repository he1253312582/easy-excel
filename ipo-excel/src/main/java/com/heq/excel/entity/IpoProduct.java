package com.heq.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IpoProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 托管机构-已经放弃维护，改用托管机构代码
     */
    private String custodianInstitution;

    /**
     * 深圳账户
     */
    private String accountSz;

    /**
     * 上海账户
     */
    private String accountSh;

    /**
     * 开户姓名
     */
    private String accountName;

    /**
     * 银行账户
     */
    private String bankAccount;

    /**
     * 开户银行
     */
    private String accountBank;

    /**
     * 市场类型：1.科创板/创业板；2.新三板
     */
    private String allowStockMarkets;

    /**
     * 深市沪市类型：1.沪市单边；2.深市单边；3.沪深双边
     */
    private Integer allowStockType;

    /**
     * 机构id
     */
    private String tenantId;

    /**
     * 托管机构代码
     */
    private String trustCode;

    /**
     * 交易券商代码
     */
    private String tradeCode;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 产品状态信息  -1 删除 0暂停 1正常
     */
    private Integer productStatus;

    /**
     * 产品简称
     */
    private String productShortName;

    /**
     * 深交所编号(配售对象编码)
     */
    private String stockExchangeSzBh;

    /**
     * 席位号(深市）
     */
    private String seatNumberSz;

    /**
     * 协会编码
     */
    private String societyCode;

    /**
     * 配售对象
     */
    private String rationObject;


}
