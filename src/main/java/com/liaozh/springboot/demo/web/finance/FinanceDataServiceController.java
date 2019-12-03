package com.liaozh.springboot.demo.web.finance;

import com.liaozh.springboot.demo.model.dto.basedto.ResponseDto;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import com.liaozh.springboot.demo.service.FinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Finance", description = "财务数据接口")
@RestController
@RequestMapping("finance")
public class FinanceDataServiceController extends Thread{

    @Autowired
    FinanceService financeService;

    @ApiOperation(value = "财务数据查询")
    @RequestMapping(value = "/financeDataQuery", method = RequestMethod.POST)
    public ResponseDto<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip){

        return new ResponseDto("200","查询成功",financeService.financeDataQuery(financeDataStrip));
    }

    @ApiOperation(value = "财务数据修改")
    @RequestMapping(value = "/financeDataUpdate", method = RequestMethod.POST)
    public ResponseDto<FinanceDataStrip> financeDataUpdate(FinanceDataStrip financeDataStrip){

        return new ResponseDto("200","修改成功",financeService.financeDataUpdate(financeDataStrip));
    }

    @ApiOperation(value = "财务数据插入")
    @RequestMapping(value = "/financeDataAdd", method = RequestMethod.POST)
    public ResponseDto<String> financeDataAdd(FinanceDataStrip weiChatFinanceDataStrip){

        return new ResponseDto("200","插入成功",financeService.financeDataAdd(weiChatFinanceDataStrip));
    }
}
