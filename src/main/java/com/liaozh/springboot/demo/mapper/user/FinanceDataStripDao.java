package com.liaozh.springboot.demo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaozh.springboot.demo.model.sql.finance.FinanceDataStrip;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;

@Service
public interface FinanceDataStripDao extends BaseMapper<FinanceDataStrip> {
    List<FinanceDataStrip> financeDataQuery(@RequestBody @Valid FinanceDataStrip financeDataStrip);
    List<FinanceDataStrip> newFinanceData(int userId);
    int financeDataUpdate(@RequestBody @Valid FinanceDataStrip financeDataStrip);
    int financeDataAdd(@RequestBody @Valid FinanceDataStrip financeDataStrip);
}
