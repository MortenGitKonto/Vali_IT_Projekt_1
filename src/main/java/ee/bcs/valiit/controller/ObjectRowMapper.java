package ee.bcs.valiit.controller;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class ObjectRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultset, int i) throws SQLException {
        Account account = new Account();
        account.setAmount(resultset.getInt("balance"));
        account.setAccountNumber(resultset.getString("account_nr"));
        account.setClientId(resultset.getInt("client_id"));
        account.setId(resultset.getInt("id"));
        return account;
    }
}
