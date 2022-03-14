package edu.miu.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Data
public class Address {
    private String street;
    private String city;
    private String zipcode;
}
