package edu.miu.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Data
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private String email;
}
