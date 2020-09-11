package ee.bcs.valiit.controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper2 implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultset, int i) throws SQLException {
        Client client = new Client();
        client.setName(resultset.getString("name"));
        client.setId(resultset.getInt("id"));
        return client;
    }
}