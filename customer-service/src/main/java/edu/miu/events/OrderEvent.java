package edu.miu.events;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
    private String orderId;
    private String shippingCartId;
    private String customerId;
}