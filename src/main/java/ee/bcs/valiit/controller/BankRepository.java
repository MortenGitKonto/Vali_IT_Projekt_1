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

    //UUS KLIENT
    public void newClientRepository(Client client, String encodedPassword) {
        String sql = "INSERT INTO clients (name, username, password) VALUES (:name, :username, :password)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("name", client.getName());
        paramMap.put("username", client.getUsername());
        paramMap.put("password", encodedPassword);
        template.update(sql, paramMap);
    }

    //UUS KONTO
    public void newAccountRepository(Account account) {
        String sql = "INSERT INTO bank_accounts (client_id, account_nr, balance) VALUES (:clientId, :accountNumber, :balance)";
        Map<String, Object> paramMap = new HashMap();
        //paramMap.put("id", account.getId());
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        template.update(sql, paramMap);
    }

    //UUS DEPOSIT TRANSACTION HISTORY SISSEKANNE
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

    //UUS WITHDRAW TRANSACTION HISTORY SISSEKANNE
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

    //UUS TRANSFER TRANSACTION HISTORY SISSEKANNE

    // SIIA JÄIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIN, VT KUIDAS TABELISSE VEERUD TEKITADA MIS PUUDU. VT KAS JSON TÖÖTAB
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

    //VAATA KÕIKI KONTOSID
    public List<Account> testAllAccountsBankRepository() {

        String sql = "select * from bank_accounts order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }
//TÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖTAB
    //VAATA ÜHT KONTOT 2
    public List<Account> testOneAccountBankRepository(int clientId) {

        String sql = "select * from bank_accounts where client_id=:clientId";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", clientId);
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }



    //VAATA KÕIKI KLIENTE
    public List<Client> testAllClientsBankRepository() {

        String sql = "select * from clients order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Client> clientList = template.query(sql, paramMap, new ObjectRowMapper2());
        return clientList;
    }

    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    public void updateSqlAccountNrBankRepository(Account account) {

        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNumber, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }

    public void updateSqlClientNrBankRepository(Client client) {

        String sql = "update clients set name = :name where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", client.getId());
        paramMap.put("name", client.getName());
        template.update(sql, paramMap);
    }

    /////DEPOSIT JA WITHDRAW (MÕLEMAD VAJAVAD KAHT ETAPPI)

    public Integer selectBalanceRepository(Account account) {

        String sqlGet = "select balance from bank_accounts where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        Integer balanceNow = template.queryForObject(sqlGet, paramMap, Integer.class);
        return balanceNow;
    }

    public void sqlDepositAmountRepository(Account account, Integer balanceNow, Integer depositAmount) {
        String sqlDeposit = "update bank_accounts set balance= :balance where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", (balanceNow + depositAmount));

        template.update(sqlDeposit, paramMap);
    }
    public void sqlWithdrawAmountRepository(Account account, Integer balanceNow) {

        String sqlWithdraw = "update bank_accounts set balance= :balance where account_nr = :accountNumber";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", (balanceNow - account.getAmount()));
        template.update(sqlWithdraw, paramMap);
    }


////account ID kui võetakse raha
    public Integer getFromAccountId (String specificAccountNumber) {
        String sql = "SELECT id FROM bank_accounts where account_nr = :accountNumber";

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", specificAccountNumber);

        Integer fromAccountId = template.queryForObject(sql, paramMap, Integer.class);

        return fromAccountId;
    }
////account ID kui pannakse juurde raha
    public Integer getToAccountId (String specificAccountNumber) {
        String sql = "SELECT id FROM bank_accounts where account_nr = :accountNumber";

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNumber", specificAccountNumber);

        Integer toAccountId = template.queryForObject(sql, paramMap, Integer.class);

        return toAccountId;
    }

    ////Get parooli HASH, et saaks parooli vastavust tsekkida
    public String getPassword (String username) {
        String sql = "SELECT password FROM clients where username = :username";

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", username);

        String password = template.queryForObject(sql, paramMap, String.class);

        return password;
    }






    public Integer testBalance(String specificAccountNumber) {
        String sql = "select balance from bank_accounts where account_nr = :accountNr";

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accountNr", specificAccountNumber);

        Integer balance = template.queryForObject(sql, paramMap, Integer.class);
        return balance;
    }

    /*public Integer getWithrawId(String withrawAccountNumber) {

        String sql = "SELECT id FROM account where account_nr = :accountNumber";



    }*/
}
    /*public void updateSqlAccountNrBankRepository(Account account) {

        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNumber, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }*/