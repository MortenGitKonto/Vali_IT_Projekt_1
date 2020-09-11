package ee.bcs.valiit.controller;

public class Transaction {

    private int id;
    private int transactionId;
    private int transfer;
    private int withdrawal;
    private int deposit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}

