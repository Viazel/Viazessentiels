package fr.viazel.viazessentials.utils.sql;

public class DbCredentials {
    private String host, user, pass, dbname;
    private int port;

    public DbCredentials(String host, String user, String pass, String dbname, int port) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
        this.port = port;
    }
    public String toURI(){
        return "jdbc:mysql://" + host + ":" + port + "/" + dbname;
    }

    public String getDbname() {return dbname;}

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
