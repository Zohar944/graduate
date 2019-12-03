package com.liaozh.springboot.demo.service;

import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;

import java.util.List;

public interface FinanceService {
    List<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip);
    FinanceDataStrip financeDataUpdate(FinanceDataStrip financeDataStrip);
    String financeDataAdd(FinanceDataStrip weChatFinanceDataStrip);
}
