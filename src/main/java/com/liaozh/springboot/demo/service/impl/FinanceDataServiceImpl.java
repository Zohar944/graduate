package com.liaozh.springboot.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liaozh.springboot.demo.mapper.user.FinanceDataStripDao;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import com.liaozh.springboot.demo.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Service
public class FinanceDataServiceImpl implements FinanceService {

    @Autowired
    FinanceDataStripDao financeDataStripDao;

    /**
     * @param financeDataStrip
     * @return
     */
    @Override
    public List<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip) {
        List<FinanceDataStrip> listFinance = financeDataStripDao.financeDataQuery(financeDataStrip);
        return listFinance;
    }

    @Override
    public List<FinanceDataStrip> newFinanceData(int userId) {
        List<FinanceDataStrip> listFinance = financeDataStripDao.newFinanceData(userId);
        return listFinance;
    }

    @Override
    public FinanceDataStrip financeDataUpdate(FinanceDataStrip financeDataStrip) {

        int isFinanceUpdate = financeDataStripDao.financeDataUpdate(financeDataStrip);

        if (isFinanceUpdate == 1) {
            QueryWrapper<FinanceDataStrip> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FinanceDataStrip::getFinanceDataId, financeDataStrip.getFinanceDataId())
                    .eq(FinanceDataStrip::getUserId, financeDataStrip.getUserId());

            FinanceDataStrip queryFinance = financeDataStripDao.selectOne(queryWrapper);

            System.out.println("Serivce  财务数据修改" + queryFinance);

            return queryFinance;
        }

        return null;
    }

    @Override
    public String financeDataAdd(FinanceDataStrip weChatFinanceDataStrip) {

        int isFinanceAdd = financeDataStripDao.financeDataAdd(weChatFinanceDataStrip);
        if (isFinanceAdd == 1) {
            return "SUCCESS";
        }
        return "ERROR";

    }

    /**
     * 微信财务数据批量导入
     * @param financeDataStrip
     * @param userId
     * @param source
     * @return
     */
    @Override
    public String billImport(List<FinanceDataStrip> financeDataStrip, int userId, String source) {
        List<String> queryTransactionNumber = financeDataStripDao.transactionNumber(userId, source);

        for(int a = 0; a< financeDataStrip.size(); a++){ //去重
            for(String query : queryTransactionNumber) {
                if (financeDataStrip.get(a).getTransactionNumber().equals(query)){
                    financeDataStrip.remove(a);
                    if(a != 0) {
                        a--;//List是动态变化的，不像数组会占位，此时的索引因后退一位。
                    }
                }
            }
        }
//        Iterator<FinanceDataStrip> it = financeDataStrip.iterator();
//        while (it.hasNext()){
//            for (String query :queryTransactionNumber) {
//                String aa =it.next().getTransactionNumber();
//                if(aa.equals(query)){
//                    System.out.println(aa);
//                    it.remove();
//                }
//            }
//        }

        if (!financeDataStrip.isEmpty() && financeDataStrip != null) {
            if(financeDataStripDao.billImport(financeDataStrip)){
                return "SUCCESS";
            }else {
               return "ERROR";
            }
        }
        return "账单表为空或已存在数据";
    }
}
