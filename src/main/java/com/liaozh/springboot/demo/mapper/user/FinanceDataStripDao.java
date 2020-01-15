package com.liaozh.springboot.demo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public interface FinanceDataStripDao extends BaseMapper<FinanceDataStrip> {
    List<FinanceDataStrip> financeDataQuery(@RequestBody @Valid FinanceDataStrip financeDataStrip);
    List<FinanceDataStrip> newFinanceData(int userId);
    List<String> transactionNumber(@RequestBody @Valid int userId,String source);

    int financeDataUpdate(@RequestBody @Valid FinanceDataStrip financeDataStrip);
    int financeDataAdd(@RequestBody @Valid FinanceDataStrip financeDataStrip);
    boolean billImport(@Param("list") List<FinanceDataStrip> financeDataStrip);
}
