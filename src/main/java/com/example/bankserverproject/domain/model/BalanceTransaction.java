package com.example.bankserverproject.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "balanceTransaction")
public class BalanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //    @Field(type = FieldType.Double)
    private BigDecimal amount;
//    private double amount;

    //    @Field(type = FieldType.Nested)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id", referencedColumnName = "id")
    private Account senderAccount;

    private String action;

    //    @Field(type = FieldType.Date, format = {}, pattern = "dd.MM.uuuu HH:mm:ss", name = "created_date_time")
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    public BalanceTransaction(Account senderAccount, BigDecimal amount, String action) {
        this.senderAccount = senderAccount;
        this.amount = amount;
        this.action = action;
//        this.createdDateTime = now;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceTransaction that = (BalanceTransaction) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
