package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    private final Map<String, Integer> accounts = new HashMap();

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private BankService bankService;


    //Create new client
    @PostMapping("sqlNewClient")
    public void newClient(@RequestBody Client client) {
        bankService.newClientService(client);
    }

    //Create new account
    @PostMapping("sqlNewAccount")
    public void newAccount(@RequestBody Account account) {
        bankService.newAccountService(account);
    }

    //Update all info of one specific client
    @PutMapping("sqlUpdateClientInfo")
    public void updateSqlClientNr(@RequestBody Client client) {
        bankService.updateSqlClientNrBankService(client);
    }

    //Update all info of one specific bank account
    @PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr(@RequestBody Account account) {
        bankService.updateSqlAccountNrBankService(account);
    }

    //Get all info of one specific bank account
    @GetMapping("testOneAccount/{a}")
    public List<Account> testOneAccount(@PathVariable("a") int clientId) {
        List<Account> result = bankService.testOneAccountBankService(clientId);
        return result;
    }

    //Get all info of all clients
    @GetMapping("sqltestAllClients")
    public List<Client> testAllClients() {
        return bankService.testAllClientsBankService();
    }

    //Get all info of all bank accounts
    @GetMapping("sqltestAllAccounts")
    public List<Account> testAllAccounts() {
        return bankService.testAllAccountsBankService();
    }

    //Get balance from specific account
    @GetMapping("sqltestBalance/{a}")
    public Integer testBalance(@PathVariable("a") String specificAccountNumber) {
        return bankService.testBalance(specificAccountNumber);
    }

    //Get currently logged in user id
    @GetMapping("sqltestId")
    public Integer testId() {
        return bankService.testId();
    }

    //Deposit money into specific bank account
    @PutMapping("/sqlDepositIntoAccount")
    public void sqlDepositAmount(@RequestBody Account account) {
        bankService.sqlDepositAmountService(account);
    }

    //Withdraw money from specific bank account
    @PutMapping("/sqlWithdrawFromAccount")
    public void sqlWithdrawAmount(@RequestBody Account account) {
        bankService.sqlWithdrawAmountService(account);
    }

    //Transfer money from one account into another
    @PutMapping("/sqlTransferFromAccountToAccount")
    public void sqlTransfer(@RequestBody List<Account> transfer) {
        bankService.sqlTransferAmountService(transfer);
    }
}

