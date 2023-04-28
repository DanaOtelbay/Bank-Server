package com.example.bankserverproject.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
//@Document(indexName = "accounts")

public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    //    @Field(type = FieldType.Text, name = "account_number")
    private String accountNumber;

    //    @Field(type = FieldType.Double, name = "current_balance")
    private BigDecimal currentBalance = BigDecimal.ZERO;

    //    @Field(type = FieldType.Nested, name = "customer")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

//    @JsonBackReference
////    @Field(type = FieldType.Nested, name = "transactions")
//    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List <Transaction> transactions;


    //    @Field(type = FieldType.Date, format = {}, pattern = "dd.MM.uuuu HH:mm:ss", name = "created_date_time")
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return getId() != null && Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
