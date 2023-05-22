package database;
import java.sql.*;

public class Database {
    private static Connection connection;
    private static PreparedStatement statement;

    public static boolean connection(String ip, String port, String user, String password){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/fighting", user, password);
            System.out.println("Connection to db successful");
            createTable();
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public static void createTable(){
        try{
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS public.users\n" +
                    "(\n" +
                    "    username character varying NOT NULL,\n" +
                    "    password character varying NOT NULL,\n" +
                    "    PRIMARY KEY (username)\n" +
                    ");"
            );
            statement.execute();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void clearTable(){
        try{
            statement = connection.prepareStatement("DELETE FROM public.users");
            statement.execute();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static boolean addUser(String username, String password){
        try{
            statement = connection.prepareStatement("INSERT INTO public.users (username, password) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
            System.out.println("User " + username + " added successfully");
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }

    }

    public static boolean checkUser(String username, String password){
        try {
            statement = connection.prepareStatement("SELECT count(*) FROM public.users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            resultSet.close();
            return count != 0;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }

    }

    public static void closeDB(){
        try{
            statement.close();
            connection.close();
            System.out.println("db closed");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
