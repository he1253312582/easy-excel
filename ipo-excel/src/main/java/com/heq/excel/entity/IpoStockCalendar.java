package com.heq.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 新股发行日历(总日历)
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IpoStockCalendar implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "stock_calendar_id", type = IdType.AUTO)
    private Integer stockCalendarId;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 1.深主板 2.中小板 3.创业板 4.沪主板 5.科创板 6.新三板
     */
    private Integer stockMarketType;

    /**
     * 网下投资者提交核查文件日期:4步截至日期，注意聚源没有时间，中午12.00截至

     */
    private Date submitCheckFileDate;

    /**
     * 初步询价日开始时间(流程6询价开始时间,9.00开始)
     */
    private Date inquiryStartTime;

    /**
     * 初步询价日结束时间(流程6询价结束时间，截至到下午3.00)
     */
    private Date inquiryEndTime;

    /**
     *  网下发行申购日开始时间(节点8，当天9.00)
     */
    private Date subStartTime;

    /**
     *  网下发行申购日结束时间((节点8，下午15.00))
     */
    private Date subEndTime;

    /**
     * 网下发行获配投资者缴款，认购资金到账截止时间(交款日截至时间,节点9)
     */
    private Date payDeadLine;

    /**
     * 本次网下发行每个配售对象的申购股数上限
     */
    private Integer applyMaxAmt;

    /**
     * 本次网下发行每个配售对象的申购股数下限
     */
    private Integer applyMinAmt;

    /**
     * 该日资产规模或资金规模:产品估值日期(4和6需要的估值日期)
     */
    private Date assetAmtCalDate;

    /**
     * 拟申购数量超过 100 万股的部分必须是
10 万股的整数倍 (弃用)
     */
    private Integer multiple;

    /**
     * 创建人
     */
    private Long createBy;

    private Date createTime;

    /**
     * 修改人
     */
    private Long updateBy;

    private Date updateTime;

    /**
     * 1 打新中 2 打新结束
     */
    private Integer stockStatus;

    /**
     * 获取市值日期(一般为询价前2日市值)
     */
    private Date assetMarketValueDate;

    /**
     * 首次信息发布日期(对应流程1公告发布日期)
     */
    private Date intentLetterPublDate;

    /**
     * 下载券商投价报告(节点5，询价前一天12.30后)
     */
    private Date tjRepotBeginTime;

    /**
     * 跟踪发行公告查看报价是否有效(节点7)
     */
    private Date checkVerifyInquiryDate;

    /**
     * 交款日前一天下午5点(节点9)
     */
    private Date makePayFileStartTime;

    /**
     * 交款日前一天下午5点(节点10)
     */
    private Date submitPayFileStartTime;

    /**
     * 交款日截至时间(节点10)
     */
    private Date submitPayFileEndTime;

    /**
     * 复合交款到账情况(节点11)
     */
    private Date checkPayResultStartTime;

    private Date checkPayResultEndTime;

    private String nodeDataJson;

    /**
     * 股票发行交易所：1 上海 2 深圳
     */
    private Integer marketType;

    /**
     * 打新需要满足市值
     */
    private Double assetMarketValue;


}
