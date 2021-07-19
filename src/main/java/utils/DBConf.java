package utils;

public class DBConf {
    public String host = null;
    public Integer port = null;
    public String database = null;
    public String user = null;
    public String password = null;

    public DBConf(String host, Integer port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }
}
