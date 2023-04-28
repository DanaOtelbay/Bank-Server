package com.example.bankserverproject.domain.dto;

import com.example.bankserverproject.constants.ACTION;
import com.example.bankserverproject.domain.model.BalanceTransaction;
import com.example.bankserverproject.domain.model.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTransactionDTO {
    private Long id; //
    private Long senderAccountId;
    private BigDecimal amount;
    //    private double amount;
    private String action;
    private LocalDateTime transactionTime; //

    public static List<BalanceTransactionDTO> fromTransactionList(List<BalanceTransaction> balanceTransactions) {
        return balanceTransactions.stream()
                .map(balanceTransaction -> new BalanceTransactionDTO(balanceTransaction.getId(),
                        balanceTransaction.getSenderAccount().getId(),
                        balanceTransaction.getAmount(),
                        balanceTransaction.getAction(),
                        balanceTransaction.getCreatedDateTime()))
                .collect(Collectors.toList());
    }
    public static TransactionDTO fromTransaction(Transaction transaction) {
        TransactionDTO transactionDto = new TransactionDTO();
        transactionDto.setId(transaction.getId());
//        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setSenderAccountId(transaction.getSenderAccount().getId());
        transactionDto.setRecipientAccountId(transaction.getRecipientAccount().getId());
        return transactionDto;
    }
}
