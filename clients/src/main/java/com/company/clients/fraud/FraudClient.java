package com.company.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Fraud")
public interface FraudClient {
    @GetMapping(value = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable(value = "customerId") Integer customerId);
}
