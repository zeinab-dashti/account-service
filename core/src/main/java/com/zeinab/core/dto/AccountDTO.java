package com.zeinab.core.dto;

import com.zeinab.core.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {

    private long accountId;
    private String currentBalance;

    public AccountDTO(Account account) {
        this.accountId = account.getId();
        this.currentBalance = String.valueOf(account.getCurrentBalance());
    }
}
