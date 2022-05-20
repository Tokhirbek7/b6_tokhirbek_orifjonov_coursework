package com.company.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    public Boolean isFraudulent(Integer customerId){
            fraudCheckHistoryRepository.save(
                    FraudCheckHistory
                            .builder()
                            .customerId(customerId)
                            .isFraudster(false)
                            .createdAt(now())
                            .build()
            );
            return false;
    }

}
