package com.company.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Slf4j
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "notification_id_sequence" )
    Integer notificationId;
    Integer toCustomerId;
    String toCustomerEmail;
    String sender;
    String message;
    LocalDateTime sentAt;


}
