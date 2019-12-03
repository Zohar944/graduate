package com.liaozh.springboot.demo.model.sql.finance;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.data.relational.core.mapping.Table;



@Table
public class FinanceDataStrip extends Model<FinanceDataStrip> {

    /**
     * 财务数据条的ID值，用于crud
     */
    @TableId
    private int financeDataId;

    /**
     * 用户ID，用于与用户关联
     */
    private int userId;

    /**
     * 用于与日期关联
     */
    private String dateId;
    /**
     * 数据来源于哪，例：微信/支付宝
     */
    private String source;
    /**
     * 金额
     */
    private double how;
    /**
     * 产生消费的时间，精确到时分秒
     */
    private String dateTime;
    /**
     * 商家
     */
    private String project;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 显示消费类型还是收入类型
     */
    private String financeType;

    public int getFinanceDataId() {
        return financeDataId;
    }

    public void setFinanceDataId(int financeDataId) {
        this.financeDataId = financeDataId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getHow() {
        return how;
    }

    public void setHow(double how) {
        this.how = how;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    @Override
    public String toString() {
        return "FinanceDataStrip{" +
                "financeDataId=" + financeDataId +
                ", userId=" + userId +
                ", dateId=" + dateId +
                ", source='" + source + '\'' +
                ", how=" + how +
                ", dateTime='" + dateTime + '\'' +
                ", project='" + project + '\'' +
                ", remarks='" + remarks + '\'' +
                ", financeType='" + financeType + '\'' +
                '}';
    }
}
