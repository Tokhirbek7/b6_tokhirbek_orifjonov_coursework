package com.company.customer;

import lombok.Data;

@Data
public class CustomerRegistrationRequest {
    String firstname;
    String lastname;
    String email;
}
