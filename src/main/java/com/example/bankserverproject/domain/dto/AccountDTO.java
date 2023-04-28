package com.example.bankserverproject.domain.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id; //
    private String accountNumber; // 44000055
    private BigDecimal currentBalance; // 0
//    private double currentBalance;
    //    private List<TransactionDTO> transactions = new ArrayList<>();
    private Long customerId;
    private LocalDateTime createdDate; //

}