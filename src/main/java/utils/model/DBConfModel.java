package utils.model;

public class DBConfModel {
    public String host = null;
    public Integer port = null;
    public String database = null;
    public String user = null;
    public String password = null;

    public DBConfModel(String host, Integer port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }
}
