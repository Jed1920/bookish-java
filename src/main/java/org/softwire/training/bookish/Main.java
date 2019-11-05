package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;

import java.sql.*;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "test";
        String user = "root";
        String password = "password";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        Statement stmt = null;
        String query = "select id, title from test.book";
        Connection connection = DriverManager.getConnection(connectionString);
        try {

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println(id + "\t" + title +"\t" + author +"\t");
            }
        } catch (SQLException e ) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        Jdbi jdbi = Jdbi.create(connectionString);
        List<Book> books = jdbi.withHandle(handle ->
             handle.createQuery("select id,title,author from test.book")
                     .mapToBean(Book.class)
                     .list());

        for (Book book : books) {
            List<Integer> book_copy = jdbi.withHandle(handle ->
                    handle.createQuery("select id from book_copy where book_id = :id")
                            .bind("id", book.getId())
                            .mapTo(Integer.class)
                            .list());
            book.setQuantity(book_copy.size());
            System.out.println(book);
        }
    }
}
