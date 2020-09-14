package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    //Create new client
    public void newClientRepository(Client client, String encodedPassword) {
        String sql = "INSERT INTO clients (name, username, password) VALUES (:name, :username, :password)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("name", client.getName());
        paramMap.put("username", client.getUsername());
        paramMap.put("password", encodedPassword);
        template.update(sql, paramMap);
    }

    //Create new account
    public void newAccountRepository(Account account) {
        String sql = "INSERT INTO bank_accounts (client_id, account_nr, balance) VALUES (:clientId, :accountNumber, :balance)";
        Map<String, Object> paramMap = new HashMap();
        //paramMap.put("id", account.getId());
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        template.update(sql, paramMap);
    }

    //Update all info of one specific client
    public void updateSqlClientNrBankRepository(Client client) {
        String sql = "update clients set name = :name where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", client.getId());
        paramMap.put("name", client.getName());
        template.update(sql, paramMap);
    }

    //Update all info of one specific bank account
    public void updateSqlAccountNrBankRepository(Account account) {
        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNumber, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }

    //Get all info of one specific bank account
    public List<Account> testOneAccountBankRepository(int clientId) {
        String sql = "select * from bank_accounts where client_id=:clientId";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", clientId);
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }

    //Get all info of all clients
    public List<Client> testAllClientsBankRepository() {
        String sql = "select * from clients order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Client> clientList = template.query(sql, paramMap, new ObjectRowMapper2());
        return clientList;
    }

    //Get all info of all bank accounts
    public List<Account> testAllAccountsBankRepository() {
        String sql = "select * from bank_accounts order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }

    //Get balance from specific account
    public Integer selectBalanceRepository(Account account) {
        String sqlGet = "select balance from bank_accounts where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        Integer balanceNow = template.queryForObject(sqlGet, paramMap, Integer.class);
        return balanceNow;
    }

    //Get currently logged in user id
    public Integer getClientId(String logInUsername) {
        String sql = "SELECT id FROM clients where username = :username";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", logInUsername);
        Integer id = template.queryForObject(sql, paramMap, Integer.class);
        return id;
    }

    //Deposit money into specific bank account
    public void sqlDepositAmountRepository(Account account, Integer balanceNow, Integer depositAmount) {
        String sqlDeposit = "update bank_accounts set balance= :balance where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", (balanceNow + depositAmount));
        template.update(sqlDeposit, paramMap);
    }

    //Withdraw money from specific bank account
    public void sqlWithdrawAmountRepository(Account account, Integer balanceNow) {
        String sqlWithdraw = "update bank_accounts set balance= :balance where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", (balanceNow - account.getAmount()));
        template.update(sqlWithdraw, paramMap);
    }

    //Get account ID where money is taken from
    public Integer getFromAccountId(String specificAccountNumber) {
        String sql = "SELECT id FROM bank_accounts where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", specificAccountNumber);
        Integer fromAccountId = template.queryForObject(sql, paramMap, Integer.class);
        return fromAccountId;
    }

    //Get account ID where money is put into
    public Integer getToAccountId(String specificAccountNumber) {
        String sql = "SELECT id FROM bank_accounts where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", specificAccountNumber);
        Integer toAccountId = template.queryForObject(sql, paramMap, Integer.class);
        return toAccountId;
    }

    //New transaction history row when depositing money into account
    public void newDepositTransactionRepository(int depositAccountId, int depositAmount, int withdrawalAmount, int transferAmount) {
        String sql = "INSERT INTO transaction_history (toaccount_id, transfer, withdrawal, deposit) VALUES (:toAccountId, :transfer, :withdrawal, :deposit)";
        Map<String, Object> paramMap = new HashMap();
        //paramMap.put("id", account.getId());
        paramMap.put("toAccountId", depositAccountId);
        paramMap.put("deposit", depositAmount);
        paramMap.put("transfer", transferAmount);
        paramMap.put("withdrawal", withdrawalAmount);

        template.update(sql, paramMap);
    }

    //New transaction history row when withdrawing money from account
    public void newWithdrawTransactionRepository(int withdrawAccountId, int depositAmount, int withdrawalAmount, int transferAmount) {
        String sql = "INSERT INTO transaction_history (fromaccount_id, transfer, withdrawal, deposit) VALUES (:fromAccountId, :transfer, :withdrawal, :deposit)";
        Map<String, Object> paramMap = new HashMap();
        //paramMap.put("id", account.getId());
        paramMap.put("fromAccountId", withdrawAccountId);
        paramMap.put("deposit", depositAmount);
        paramMap.put("transfer", transferAmount);
        paramMap.put("withdrawal", withdrawalAmount);
        template.update(sql, paramMap);
    }

    //New transaction history row when transfering money from account into another
    public void newTransferTransactionRepository(int withdrawAccountId, int depositAccountId, int depositAmount, int withdrawalAmount, int transferAmount) {
        String sql = "INSERT INTO transaction_history (fromaccount_id, toaccount_id, transfer, withdrawal, deposit) VALUES (:fromAccountId, :toAccountId, :transfer, :withdrawal, :deposit)";
        Map<String, Object> paramMap = new HashMap();
        //paramMap.put("id", account.getId());
        paramMap.put("fromAccountId", withdrawAccountId);
        paramMap.put("toAccountId", depositAccountId);
        paramMap.put("deposit", depositAmount);
        paramMap.put("transfer", transferAmount);
        paramMap.put("withdrawal", withdrawalAmount);
        template.update(sql, paramMap);
    }

    ////Get password HASH to check the password when logging in
    public String getPassword(String username) {
        String sql = "SELECT password FROM clients where username = :username";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", username);
        String password = template.queryForObject(sql, paramMap, String.class);
        return password;
    }

    //Check account balance
    public Integer testBalance(String specificAccountNumber) {
        String sql = "select balance from bank_accounts where account_nr = :accountNr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNr", specificAccountNumber);
        Integer balance = template.queryForObject(sql, paramMap, Integer.class);
        return balance;
    }
}
