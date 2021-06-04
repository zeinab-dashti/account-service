package com.zeinab.account.client;


import com.zeinab.account.exception.ApiRestTemplateErrorHandler;
import com.zeinab.core.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Component
public class TransactionClient {

    @Autowired
    private Environment env;

    public TransactionDTO[] getTransactionsByAccountId(long accountId) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ApiRestTemplateErrorHandler());
        String requestURL = env.getProperty("transaction.service.url") + String.format("/%s", accountId);
        return Objects
                .requireNonNull(
                        restTemplate.getForEntity(
                                requestURL,
                                TransactionDTO[].class
                        ).getBody()
                );
    }
}
