package com.bag.Book_Store.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithUserDetailsResponse {

    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private Long userId;
    private String username;
    private String fullName;

}
