package com.kbstar.msgconsumer.service;

import com.kbstar.msgconsumer.domain.AccountingLog;
import com.kbstar.msgconsumer.domain.EbankingLog;
import com.kbstar.msgconsumer.domain.ReceptionLog;
import com.kbstar.msgconsumer.repository.AccountingLogRepository;
import com.kbstar.msgconsumer.repository.EbankingLogRepository;
import com.kbstar.msgconsumer.repository.ReceptionLogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final AccountingLogRepository accountingLogRepository;
    private final ReceptionLogRepository receptionLogRepository;
    private final EbankingLogRepository ebankingLogRepository;

    public KafkaConsumerService(AccountingLogRepository accountingLogRepository,
                                ReceptionLogRepository receptionLogRepository,
                                EbankingLogRepository ebankingLogRepository) {
        this.accountingLogRepository = accountingLogRepository;
        this.receptionLogRepository = receptionLogRepository;
        this.ebankingLogRepository = ebankingLogRepository;
    }

    @KafkaListener(topics = "THKLD01", groupId = "thkld01-group")
    public void consume(String message) {
        log.info("Consumed raw message: {}", message);
        String decodedMessage = decodeCustomEncoding(message);
        log.info("Decoded message: {}", decodedMessage);

        try {
            // Assuming the original message (or decoded message) still contains these keywords for type determination
            if (message.contains("ACCOUNTING")) {
                accountingLogRepository.save(new AccountingLog(null, decodedMessage));
                log.info("Saved to AccountingLog: {}", decodedMessage);
            } else if (message.contains("RECEPTION")) {
                receptionLogRepository.save(new ReceptionLog(null, decodedMessage));
                log.info("Saved to ReceptionLog: {}", decodedMessage);
            } else if (message.contains("EBANKING")) {
                ebankingLogRepository.save(new EbankingLog(null, decodedMessage));
                log.info("Saved to EbankingLog: {}", decodedMessage);
            } else {
                log.warn("Message type not recognized, not saving: {}", decodedMessage);
            }
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", decodedMessage, e);
        }
    }

    /**
     * Decodes a string where Korean characters are encoded as 'C' followed by 4 hexadecimal digits.
     * Example: Cadf8Cb8f9Cd68cCc0accCcf54Cb4dc -> 그룹회사코드
     *
     * @param encodedString The string containing custom 'Cxxxx' encoding.
     * @return The decoded string.
     */
    private String decodeCustomEncoding(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) {
            return encodedString;
        }

        StringBuilder decoded = new StringBuilder();
        int i = 0;
        while (i < encodedString.length()) {
            if (encodedString.charAt(i) == 'C' && i + 4 < encodedString.length()) {
                try {
                    String hex = encodedString.substring(i + 1, i + 5);
                    int charCode = Integer.parseInt(hex, 16);
                    decoded.append((char) charCode);
                    i += 5; // Skip 'C' and 4 hex digits
                } catch (NumberFormatException e) {
                    // Not a valid hex sequence after 'C', treat 'C' as a literal character
                    decoded.append(encodedString.charAt(i));
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    // Not enough characters after 'C', treat 'C' as a literal character
                    decoded.append(encodedString.charAt(i));
                    i++;
                }
            } else {
                decoded.append(encodedString.charAt(i));
                i++;
            }
        }
        return decoded.toString();
    }
}