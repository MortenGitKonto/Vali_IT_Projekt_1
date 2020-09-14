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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Create new client
    public void newClientService(Client client) {
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        bankRepository.newClientRepository(client, encodedPassword);
    }

    //Create new bank account
    public void newAccountService(Account account) {
        bankRepository.newAccountRepository(account);
    }

    //Update all info of one specific client
    public void updateSqlClientNrBankService(Client client) {
        bankRepository.updateSqlClientNrBankRepository(client);
    }

    //Update all info of one specific bank account
    public void updateSqlAccountNrBankService(Account account) {
        bankRepository.updateSqlAccountNrBankRepository(account);
    }

    //Get all info of one specific bank account
    public List<Account> testOneAccountBankService(int clientId) {
        return bankRepository.testOneAccountBankRepository(clientId);
    }

    //Get all info of all clients
    public List<Client> testAllClientsBankService() {
        return bankRepository.testAllClientsBankRepository();
    }

    //Get all info of all bank accounts
    public List<Account> testAllAccountsBankService() {
        return bankRepository.testAllAccountsBankRepository();
    }

    //Get balance from specific account
    public Integer testBalance(String specificAccountNumber) {
        return bankRepository.testBalance(specificAccountNumber);
    }

    //Get currently logged in user id
    public Integer testId() {
        return userDetailsService.getIdLogIn();
    }

    //Deposit money into specific bank account
    public void sqlDepositAmountService(Account account) {
        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        Integer depositAmount = account.getAmount();
        bankRepository.sqlDepositAmountRepository(account, currentBalance, depositAmount);

        //New transaction history row when depositing money into account
        int depositAccountId = bankRepository.getToAccountId(account.getAccountNumber());
        bankRepository.newDepositTransactionRepository(depositAccountId, account.getAmount(), 0, 0);
    }

    //Withdraw money from specific bank account
    public void sqlWithdrawAmountService(Account account) {
        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        if (currentBalance >= account.getAmount()) {
            bankRepository.sqlWithdrawAmountRepository(account, currentBalance);
        } else {
            System.out.println("Not enough money in the account to make the transaction");
        }
        //New transaction history row when withdrawing money from account
        int withdrawAccountId = bankRepository.getFromAccountId(account.getAccountNumber());
        bankRepository.newWithdrawTransactionRepository(withdrawAccountId, 0, account.getAmount(), 0);
    }

    //Transfer money from one account into another
    public void sqlTransferAmountService(List<Account> transfer) {
        Integer currentBalanceAcc1 = bankRepository.selectBalanceRepository(transfer.get(0));

        if (currentBalanceAcc1 >= transfer.get(0).getAmount()) {
            sqlWithdrawAmountService(transfer.get(0));
            Integer currentBalanceAcc2 = bankRepository.selectBalanceRepository(transfer.get(1));
            Integer depositAmount = transfer.get(0).getAmount();
            bankRepository.sqlDepositAmountRepository(transfer.get(1), currentBalanceAcc2, depositAmount);

            //New transaction history row when transfering money from account into another
            int withdrawAccountId = bankRepository.getFromAccountId(transfer.get(0).getAccountNumber());
            int depositAccountId = bankRepository.getToAccountId(transfer.get(1).getAccountNumber());
            bankRepository.newTransferTransactionRepository(withdrawAccountId, depositAccountId, 0, 0, transfer.get(0).getAmount());
        }
    }
}




