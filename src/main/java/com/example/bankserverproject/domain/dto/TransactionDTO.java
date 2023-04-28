package com.example.bankserverproject.domain.dto;

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
public class TransactionDTO {
    private Long id; //
    private Long senderAccountId;
    private Long recipientAccountId;
    private BigDecimal amount;
//    private double amount;
    private LocalDateTime transactionTime; //

    public static List<TransactionDTO> fromTransactionList(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> new TransactionDTO(transaction.getId(),
                        transaction.getSenderAccount().getId(),
                        transaction.getRecipientAccount().getId(),
                        transaction.getAmount(),
                        transaction.getCreatedDateTime()))
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
