package org.kds.cucumbertest.data;

import org.kds.cucumbertest.dto.Account;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by KDS on 1/22/2017.
 */
@Component
public class DataRepo {

    private List<Account> accounts;

    @PostConstruct
    private void loadAccountList() {
        List<Account> accountList = Arrays.asList(
                new Account("123455",56789.00,"6754",
                "Job Villes"),
                new Account("123455",567.98,"4567",
                        "Sara Villes"),
                new Account("123455",44659.98,"2446",
                        "Paul Willomns"),
                new Account("123455",7786.46,"254",
                        "Jacon lis"),
                new Account("123455",805.67,"3454",
                        "jake joe"),
                new Account("123455",095.63,"8775",
                        "Karl Key"),
                new Account("123455",6790.78,"4466",
                        "Ollie Spuit"),
                new Account("123455",895.80,"8345",
                        "Json Jake"),
                new Account("123455",80945.8,"2457",
                        "CK"));
        accounts = accountList;
    }

    public List<Account> getAccountByAccountNumber(String acccountNumber) {
        return accounts.stream().
                filter(account -> account.getAccountNumber().equals(acccountNumber)).
                limit(1).collect(Collectors.toList());
    }

}
