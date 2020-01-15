package com.liaozh.springboot.demo.web.finance;

import com.liaozh.springboot.demo.model.dto.basedto.ResponseDto;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import com.liaozh.springboot.demo.service.FinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Api(tags = "Finance", description = "财务数据接口")
@RestController
@RequestMapping("finance")
public class FinanceDataServiceController extends Thread {
    private static Logger logger = LoggerFactory.getLogger(FinanceDataServiceController.class);

    @Autowired
    FinanceService financeService;

    //全部查询
    @ApiOperation(value = "财务数据查询")
    @RequestMapping(value = "/financeDataQuery", method = RequestMethod.POST)
    public ResponseDto<FinanceDataStrip> financeDataQuery(FinanceDataStrip financeDataStrip) {

        return new ResponseDto("200", "查询成功", financeService.financeDataQuery(financeDataStrip));
    }

    //最新数据查询
    @CrossOrigin
    @ApiOperation(value = "最新数据查询")
    @RequestMapping(value = "/newFinanceData", method = RequestMethod.POST)
    public ResponseDto<FinanceDataStrip> newFinanceData(int userId) {
        return new ResponseDto("200", "查询成功", financeService.newFinanceData(userId));
    }

    @ApiOperation(value = "财务数据修改")
    @RequestMapping(value = "/financeDataUpdate", method = RequestMethod.POST)
    public ResponseDto<FinanceDataStrip> financeDataUpdate(FinanceDataStrip financeDataStrip) {

        return new ResponseDto("200", "修改成功", financeService.financeDataUpdate(financeDataStrip));
    }

//    @ApiOperation(value = "财务数据插入")
//    @RequestMapping(value = "/financeDataAdd", method = RequestMethod.POST)
//    public ResponseDto<String> financeDataAdd(FinanceDataStrip weiChatFinanceDataStrip) {
//
//        return new ResponseDto("200", "插入成功", financeService.financeDataAdd(weiChatFinanceDataStrip));
//    }

    /**
     * 处理源文件，将数据存储道List<FinanceDataStrip>中
     *
     * @param csvPath
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "账单导入")
    @RequestMapping(value = "/billImport", method = RequestMethod.POST)
    public ResponseDto<String> billImport(String csvPath, int userId, String source) { //参数看需要

        if (!csvPath.isEmpty() && csvPath != null && !source.isEmpty() && source != null && userId != 0) {
            List<FinanceDataStrip> financeDataStripList = new LinkedList<>();

            FileInputStream fileInputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(csvPath); //获取输入流文件
                inputStreamReader = new InputStreamReader(fileInputStream);//
                bufferedReader = new BufferedReader(inputStreamReader);//
                CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);//

                /**
                 *  CSVRecord record : parser.getRecords() 得到的是列 record.get(a)得到第a列的数据
                 */
                int a = 0;
                for (CSVRecord record : parser.getRecords()) {
                    FinanceDataStrip financeDataStrip = new FinanceDataStrip();
                    financeDataStrip.setUserId(userId);
                    financeDataStrip.setSource(source);
                    a++;
                    if (record.size() > 9 && a > 17) { //因为微信账单record.size()>9，a>17才是自己需要的数据
                        for (int b = 0; b < record.size(); b++) {
                            if (b == 0) {//格式化日期
                                financeDataStrip.setDateTime(record.get(b));//时间
                                financeDataStrip.setDateId(record.get(b).substring(0, 10));
                                continue;
                            }
                            if (b == 1) {
                                financeDataStrip.setRemarks(record.get(b) + "__" + record.get(3));
                                continue;//消费说明
                            }
                            if (b == 2) {
                                String getRecord = record.get(b).replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
                                financeDataStrip.setProject(getRecord);
                                continue;//商家
                            }
                            if (b == 4) {
                                financeDataStrip.setFinanceType(record.get(b));
                                continue;//消费类型
                            }
                            if (b == 5) {
                                String REGEX = "[^(0-9).]";
                                financeDataStrip.setHow(Double.parseDouble(Pattern.compile(REGEX).matcher(record.get(b)).replaceAll("").trim()));//金钱
                            }
                            if (b == 8) {
                                financeDataStrip.setTransactionNumber(record.get(b));//交易编号
                            }
                        }
                        financeDataStripList.add(financeDataStrip);
                    }

                }

            } catch (IOException e) {
                logger.error("解析CSV内容失败" + e.getMessage(), e);
            } finally {
                //关闭流
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String isData = financeService.billImport(financeDataStripList, userId, source);
            if (isData == "SUCCESS") {
                return new ResponseDto("200", "上传服务器成功", financeService.billImport(financeDataStripList, userId, source));
            }else if(isData == "ERROR") {
                return new ResponseDto("204","上传服务器成功","数据库操作失败");
            }else {
                return new ResponseDto("204","连接服务器成功",isData);
            }
        } else {
            return new ResponseDto("204", "账单文件错误", null);
        }

    }

    @ApiOperation(value = "账单导出")
    @RequestMapping(value = "/billExport", method = RequestMethod.POST)
    public ResponseDto<String> billExport(FinanceDataStrip financeDataStrip, String path) {
        return null;
    }
}
