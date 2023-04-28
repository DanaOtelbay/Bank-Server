package com.example.bankserverproject.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private String contactNumber;
//    private Long accountId;
}