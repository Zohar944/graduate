package com.liaozh.springboot.demo.model.dto.financedto;

/**
 * 微信支付结果API DTO
 */
public class WeChatFinanceDataDto {

    private String appId; //公众号ID  微信分配的公众账号ID（企业号corpid即为此appId）
    private String mchId; //微信支付分配的商户号
    private String resultCode; // 业务结果 SUCCESS/FAIL
    private String openId; // 用户在商户appid下的唯一标识
    private String tradeType; //交易类型	JSAPI、NATIVE、APP
    private int settlementTotalFee;//应结订单金额  应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String bankType;//付款银行 银行类型，采用字符串类型的银行标识，银行类型见银行列表
    private String transactionId;//微信支付订单号
    private String timeEnd;//支付完成时间 格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
