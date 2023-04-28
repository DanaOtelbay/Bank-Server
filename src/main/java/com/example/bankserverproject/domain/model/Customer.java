package com.example.bankserverproject.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Field(type = FieldType.Text)
    private String name;

    //    @Field(type = FieldType.Text)
    private String surname;

    //    @Field(type = FieldType.Text)
    private String contactNumber;

//    @Field(type = FieldType.Nested)
//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

