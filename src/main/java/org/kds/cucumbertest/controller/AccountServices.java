package org.kds.cucumbertest.controller;

import org.kds.cucumbertest.data.DataRepo;
import org.kds.cucumbertest.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by KDS on 1/22/2017.
 */
@RestController
public class AccountServices {

    @Autowired
    private DataRepo dataRepo;


    @GetMapping(value = "/account", headers = HttpHeaders.CONTENT_TYPE)
    public ResponseEntity<Account> getAccountDetails(@RequestParam String accountNumber) {

        Account account = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders httpHeaders = new HttpHeaders();

        List<Account> accountByAccountNumber = dataRepo.getAccountByAccountNumber(accountNumber);
        if (accountByAccountNumber.size() > 1) {
            httpStatus = HttpStatus.CONFLICT;
        }

        if (accountByAccountNumber.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        if (accountByAccountNumber.size() == 1) {
            account = accountByAccountNumber.get(0);
            httpStatus = httpStatus.OK;
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("ResponseVersion", "1.0");
        }

        return new ResponseEntity<Account>(account, httpHeaders, httpStatus);
    }
}
