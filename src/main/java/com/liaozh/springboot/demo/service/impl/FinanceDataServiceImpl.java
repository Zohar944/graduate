package com.liaozh.springboot.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liaozh.springboot.demo.mapper.user.FinanceDataStripDao;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import com.liaozh.springboot.demo.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FinanceDataServiceImpl implements FinanceService {

    @Autowired
    FinanceDataStripDao financeDataStripDao;

    /**
     *
     * @param financeDataStrip
     * @return
     */
    @Override
    public List<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip){
        System.out.println("financeDataStrip.getDateId()"+financeDataStrip.getDateId());

        List<FinanceDataStrip> listFinance = financeDataStripDao.financeDataQuery(financeDataStrip);
        for(FinanceDataStrip financeDataStrip1: listFinance) {
            System.out.println("FinanceDataStrip financeDataStrip1: listFinance"+financeDataStrip1.getDateId());
        }


        System.out.println(listFinance);

        return listFinance;
    }

    @Override
    public FinanceDataStrip financeDataUpdate(FinanceDataStrip financeDataStrip) {

        int isFinanceUpdate = financeDataStripDao.financeDataUpdate(financeDataStrip);

        if(isFinanceUpdate == 1) {
            QueryWrapper<FinanceDataStrip> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(FinanceDataStrip::getFinanceDataId, financeDataStrip.getFinanceDataId())
                    .eq(FinanceDataStrip::getUserId, financeDataStrip.getUserId());

            FinanceDataStrip queryFinance = financeDataStripDao.selectOne(queryWrapper);

            System.out.println("Serivce  财务数据修改"+queryFinance);

            return queryFinance;
        }

        return null;
    }

    @Override
    public String financeDataAdd(FinanceDataStrip weChatFinanceDataStrip) {

        int isFinanceAdd = financeDataStripDao.financeDataAdd(weChatFinanceDataStrip);
        if(isFinanceAdd == 1) {
           return "SUCCESS";
        }
        return "ERROR";

    }
}
