package Database.DatabaseStartup;

import Database.*;

public class MainTest {
    public static void main(String[] args) {
        MysqlCon mysqlCon = new MysqlCon();
        mysqlCon.getConnection();
    }
}
