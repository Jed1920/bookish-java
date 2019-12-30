package org.softwire.training.bookish.models.database.Services;

import org.jdbi.v3.core.Jdbi;

public abstract class DatabaseService {
    protected final Jdbi jdbi;

    protected DatabaseService(){
        String hostname = "localhost";
        String database = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbi = Jdbi.create(connectionString);
    }
}
