package com.liaozh.springboot.demo.service;

import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;

import java.util.LinkedList;
import java.util.List;

public interface FinanceService {
    List<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip);
    List<FinanceDataStrip> newFinanceData(int userId);
    FinanceDataStrip financeDataUpdate(FinanceDataStrip financeDataStrip);
    String financeDataAdd(FinanceDataStrip weChatFinanceDataStrip);
    String billImport(List<FinanceDataStrip> financeDataStrip,int userId,String source);
}
