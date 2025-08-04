package com.kbstar.msgconsumer.controller;

import com.kbstar.msgconsumer.domain.AccountingLog;
import com.kbstar.msgconsumer.domain.EbankingLog;
import com.kbstar.msgconsumer.domain.ReceptionLog;
import com.kbstar.msgconsumer.repository.AccountingLogRepository;
import com.kbstar.msgconsumer.repository.EbankingLogRepository;
import com.kbstar.msgconsumer.repository.ReceptionLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final AccountingLogRepository accountingLogRepository;
    private final ReceptionLogRepository receptionLogRepository;
    private final EbankingLogRepository ebankingLogRepository;

    public LogController(AccountingLogRepository accountingLogRepository,
                         ReceptionLogRepository receptionLogRepository,
                         EbankingLogRepository ebankingLogRepository) {
        this.accountingLogRepository = accountingLogRepository;
        this.receptionLogRepository = receptionLogRepository;
        this.ebankingLogRepository = ebankingLogRepository;
    }

    @GetMapping("/accounting")
    public List<AccountingLog> getAllAccountingLogs() {
        return accountingLogRepository.findAll();
    }

    @GetMapping("/reception")
    public List<ReceptionLog> getAllReceptionLogs() {
        return receptionLogRepository.findAll();
    }

    @GetMapping("/ebanking")
    public List<EbankingLog> getAllEbankingLogs() {
        return ebankingLogRepository.findAll();
    }
}
