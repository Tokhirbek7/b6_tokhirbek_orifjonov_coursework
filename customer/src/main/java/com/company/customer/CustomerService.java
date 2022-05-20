package com.company.customer;

import com.company.clients.fraud.FraudCheckResponse;
import com.company.clients.fraud.FraudClient;
import com.company.clients.notification.NotificationClient;
import com.company.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
//    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer
                .builder()
                .firstname(customerRegistrationRequest.getFirstname())
                .lastname(customerRegistrationRequest.getLastname())
                .email(customerRegistrationRequest.getEmail())
                .build();

        // TODO: 5/16/2022 check if email is valid
        // TODO: 5/16/2022 check if email is not taken
        customerRepository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()){
            throw  new IllegalStateException("fraudster");
        }
        // TODO: 5/19/2022 send notification
        // TODO: 5/20/2022 Make this async. add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        "Hi welcome to microservices"
                ));

    }
}
