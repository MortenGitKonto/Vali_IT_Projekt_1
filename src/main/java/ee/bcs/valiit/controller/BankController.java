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
    //////////////////////////////////////////////////////////////////////////////
    @Autowired
    private BankService bankService;
    ////////////////////////////////////////////////////////////////////////////////////

    //Get balance from one account
    @GetMapping("sqltestBalance/{a}")
    public Integer testBalance(@PathVariable("a") String specificAccountNumber) {
       Integer vastus = bankService.testBalance(specificAccountNumber);
        return vastus;
    }

    /*@PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr() {
        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", 222);
        paramMap.put("accountNr", "ACCOUNT2");
        paramMap.put("balance", 220000);
        paramMap.put("id", 2);
        template.update(sql, paramMap);
    }*/

    @GetMapping("test")
    public String test() {
        String x="Morten";
        return x;
    }

    //KUTSU KÕIK PANGAKONTOD VÄLJA (tabelist bank_accounts)

    @GetMapping("sqltestAllAccounts")
    public List<Account> testAllAccounts() {
        List<Account> kontodeList = bankService.testAllAccountsBankService();
        return kontodeList;
    }
//
    @GetMapping("testOneAccount/{a}")
    public List<Account> testOneAccount(@PathVariable("a") int clientId) {
        List<Account> result = bankService.testOneAccountBankService(clientId);
        return result;
    }

    //KUTSU KÕIK KLIENDID VÄLJA (tabelist clients)

    @GetMapping("sqltestAllClients")
    public List<Client> testAllClients() {
        List<Client> clientList = bankService.testAllClientsBankService();
        return clientList;
    }

    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    @PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr(@RequestBody Account account) {
        bankService.updateSqlAccountNrBankService(account);
    }

    //MUUDA ÜHE KLIENDI KÕIKI ANDMEID
    @PutMapping("sqlUpdateClientInfo")
    public void updateSqlClientNr(@RequestBody Client client) {
        bankService.updateSqlClientNrBankService(client);
    }

    /*@PostMapping("sqlNewRow")
    public void newRow() {
        String sql = "INSERT INTO bank_accounts (id, client_id, account_nr, balance) VALUES (:id, :clientId, :accountNr, :balance)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 2);
        paramMap.put("clientId", 202);
        paramMap.put("accountNr", "ACCOUNT2");
        paramMap.put("balance", 20000);
        template.update(sql, paramMap);
    }*/

    //TEE UUS ACCOUNT (tabelisse bank_accounts)
    @PostMapping("sqlNewAccount")
    public void newAccount(@RequestBody Account account) {

        bankService.newAccountService(account);

    }
    //TEE UUS KLIENT (tabelisse clients)
    @PostMapping("sqlNewClient")
    public void newClient(@RequestBody Client client) {

        bankService.newClientService(client);

    }


        /*String sql = "update bank_accounts set column1 = :column1, column2=:column2;
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", "ACCOUNT1");
        paramMap.put("account_nr", "ACC3");
        paramMap.put("account_nr", "ACC3");
        paramMap.put("id", 1);
        template.update(sql, paramMap);
           }*/

    /*@PutMapping("sqlUpdateBalance")
    public void updateSqlBalance() {
        String sql = "update bank_accounts set balance = :balance where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("balance", 4949);
        paramMap.put("id", 1);
        template.update(sql, paramMap);
    }*/

/*
    public Integer getBalance(String x) {
        return accounts.get(x);
    }

    public void depositMoney(String x, Integer y) {
        accounts.put(x, accounts.get(x) + y);
    }

    public void withdrawMoney(String x, Integer y) {
        accounts.put(x, accounts.get(x) - y);
    }

    public void transferMoney(String x, String y, Integer z) {
        accounts.put(x, accounts.get(x) + z);
        accounts.put(y, accounts.get(y) - z);
    }
*/
    //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    //Loo accounte listi
    /*@PostMapping("/addbankaccount/{a}/{b}")
    public void addAccount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        createAccount(x, y);
    }*/

    /*@PostMapping("/addbankaccount")
    public void addAccount(@RequestBody Account reqaccount) {
        accounts.put(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }*/


    //22222222222222222222222222222222222222222222222222222222222222222222
    //Vaata kontoseisu
    /*@GetMapping("/allaccounts/{a}")
    public Integer AmountinAccount(@PathVariable("a") String x) {
        return getBalance(x);
    }*/


//33333333333333333333333333333333333333333333333333333333333333333333333
    /*//Lisa raha...
    @PutMapping("/depositIntoAccount/{a}/{b}")
    public void depositAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        depositMoney(x, y);
    }*/

    /*//Lisa raha...
    @PutMapping("/depositIntoAccount")
    public void depositAmount(@RequestBody Account reqaccount) {
        depositMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }*/


    /////DEPOSIT
    @PutMapping("/sqlDepositIntoAccount")
    public void sqlDepositAmount(@RequestBody Account account) {

        bankService.sqlDepositAmountService(account);

    }

    /////WITHDRAW

    @PutMapping("/sqlWithdrawFromAccount")
    public void sqlWithdrawAmount(@RequestBody Account account) {
        bankService.sqlWithdrawAmountService(account);
    }

    ///TRANSFER

    @PutMapping("/sqlTransferFromAccountToAccount")
    public void sqlTransfer(@RequestBody List<Account> transfer) {

        bankService.sqlTransferAmountService(transfer);

    }

///TRANSFER PIKALT LAHTIKIRJUTATUD - TÖÖTAB KA
    /*@PutMapping("/sqlTransferFromAccountToAccount")
    public void sqlTransfer(@RequestBody TransferMoneyRequest transfer) {

        String sqlGet = "select balance from bank_accounts where account_nr= :accountNr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNr", transfer.getAccountNumber());
        //Balance 1
        Integer balanceAcc1 = template.queryForObject(sqlGet, paramMap, Integer.class);

        String sqlGet2 = "select balance from bank_accounts where account_nr= :accountNr2";
        paramMap.put("accountNr2", transfer.getAccountNumber2());
        //Balance 2
        Integer balanceAcc2 = template.queryForObject(sqlGet2, paramMap, Integer.class);

        String sqlFromAcc = "update bank_accounts set balance= :balance where account_nr= :accountNr";
        paramMap.put("balance", (balanceAcc1 - transfer.getAmount()));
        template.update(sqlFromAcc, paramMap);

        String sqlToAcc = "update bank_accounts set balance= :balance2 where account_nr= :accountNr2";
        paramMap.put("balance2", (balanceAcc2 + transfer.getAmount()));
        template.update(sqlToAcc, paramMap);
    }*/

    //4444444444444444444444444444444444444444444444444444444444444444444444
    //Võta raha...
    /*@PutMapping("/withdrawFromAccount/{a}/{b}")
    public void withdrawAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        withdrawMoney(x, y);
    }*/

    /*@PutMapping("/withdrawFromAccount")
    public void withdrawAmount(@RequestBody Account reqaccount) {
        withdrawMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }*/

    //5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555

    //Transfeer
    /*@PutMapping("/transferMoney/{a}/{b}/{c}")
    public void transferMoney(@PathVariable("a") String x, @PathVariable("b") String y, @PathVariable("c") Integer z) {
        transferMoney(x, y, z);
    }*/

    /*@PutMapping("/transferMoney")
    public void transferMoney(@RequestBody TransferMoneyRequest transferRequest) {
        transferMoney(transferRequest.getAccountNumber(), transferRequest.getAccountNumber2(), transferRequest.getAmount());
    }*/

}

