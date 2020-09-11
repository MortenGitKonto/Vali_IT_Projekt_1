package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TEE UUS ACCOUNT
    public void newAccountService(Account account) {

        bankRepository.newAccountRepository(account);


    }
    //TEE UUS KlIENT
    public void newClientService(Client client) {


        String encodedPassword = passwordEncoder.encode(client.getPassword());

        bankRepository.newClientRepository(client, encodedPassword);


    }

    //KUTSU KÕIK PANGAKONTOD VÄLJA
    public List<Account> testAllAccountsBankService() {

        List<Account> list = bankRepository.testAllAccountsBankRepository();

        return list;
    }

    //KUTSU ÜHE PANGAKONTO VÄLJA
    public List<Account>  testOneAccountBankService(int clientId) {

        List<Account> result = bankRepository.testOneAccountBankRepository(clientId);

        return result;
    }

    //KUTSU KÕIK KLIENDID VÄLJA (tabelist clients)
    public List<Client> testAllClientsBankService() {

        List<Client> clientList = bankRepository.testAllClientsBankRepository();

        return clientList;
    }


    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    public void updateSqlAccountNrBankService(Account account) {
        bankRepository.updateSqlAccountNrBankRepository(account);
    }

    public void updateSqlClientNrBankService(Client client) {

        bankRepository.updateSqlClientNrBankRepository(client);
    }


    /////DEPOSIT
    public void sqlDepositAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        Integer depositAmount = account.getAmount();
        bankRepository.sqlDepositAmountRepository(account, currentBalance, depositAmount);

        int depositAccountId = bankRepository.getToAccountId(account.getAccountNumber());
        bankRepository.newDepositTransactionRepository(depositAccountId, account.getAmount(), 0, 0);
    }


    /////WITHDRAW
    public void sqlWithdrawAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        if (currentBalance >= account.getAmount()) {
            bankRepository.sqlWithdrawAmountRepository(account, currentBalance);
        } else {
            System.out.println("Not enough money in the account to make the transaction");
        }

        int withdrawAccountId = bankRepository.getFromAccountId(account.getAccountNumber());
        bankRepository.newWithdrawTransactionRepository(withdrawAccountId, 0, account.getAmount(), 0);
    }

    /////TRANSFER

    public void sqlTransferAmountService(List<Account> transfer) {


        //Deklareerin muutuja currentBalance (konto nr1 kohta), ehk kui kontol 1 on vähemalt "amount" siis toimub ülekanne kontole 2
        Integer currentBalanceAcc1 = bankRepository.selectBalanceRepository(transfer.get(0));

        if (currentBalanceAcc1 >= transfer.get(0).getAmount()) {

            sqlWithdrawAmountService(transfer.get(0));

            //sqlDepositAmountService(transfer.get(1));
            Integer currentBalanceAcc2 = bankRepository.selectBalanceRepository(transfer.get(1));
            Integer depositAmount = transfer.get(0).getAmount();
            bankRepository.sqlDepositAmountRepository(transfer.get(1), currentBalanceAcc2, depositAmount);

            //TRANSACTION HISTORY
            int withdrawAccountId = bankRepository.getFromAccountId(transfer.get(0).getAccountNumber());

            int depositAccountId = bankRepository.getToAccountId(transfer.get(1).getAccountNumber());
            bankRepository.newTransferTransactionRepository(withdrawAccountId, depositAccountId, 0, 0, transfer.get(0).getAmount());

        }


    }

    public Integer testBalance(String specificAccountNumber) {
        Integer balance = bankRepository.testBalance(specificAccountNumber);
        return balance;
    }
}




