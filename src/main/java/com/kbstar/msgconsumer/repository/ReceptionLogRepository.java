package com.kbstar.msgconsumer.repository;

import com.kbstar.msgconsumer.domain.ReceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionLogRepository extends JpaRepository<ReceptionLog, Long> {
}
