package com.kbstar.msgconsumer.repository;

import com.kbstar.msgconsumer.domain.AccountingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingLogRepository extends JpaRepository<AccountingLog, Long> {
}
