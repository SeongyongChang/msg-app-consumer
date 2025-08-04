package com.kbstar.msgconsumer.repository;

import com.kbstar.msgconsumer.domain.EbankingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EbankingLogRepository extends JpaRepository<EbankingLog, Long> {
}
