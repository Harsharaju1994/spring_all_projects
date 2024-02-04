package org.in60mins.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long accountNumber;
    private String acountHolderName;
    private double balance;
}
