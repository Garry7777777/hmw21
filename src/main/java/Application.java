import application.*;
import dao.EmployeeDAO;
import dao.EmployeeDAOimpl;

import java.sql.*;
import java.util.*;


public class Application {
    public static final String DB_URL = "jdbc:postgresql:skypro";
    public static final String user= "postgres";
    public static final String pass= "1";


    public static void main ( String[] args) throws SQLException {

        //Задание 1
        try  ( final Connection connection = DriverManager.getConnection(DB_URL,user,pass);){
            System.out.println("Соединение с СУБД выполнено.");
            var statement = connection.createStatement();
            var query = "SELECT * FROM employee e INNER JOIN city c ON e.city_id = c.city_id WHERE id = 3";
            var rs = statement.executeQuery(query);
            rs.next();
            System.out.println( (rs.getString("id") + " " +
                                 rs.getString("first_name") + " " +
                                 rs.getString("last_name" )+ " " +
                                 rs.getString("gender")+ " " +
                                 rs.getString("age")+ " " +
                                 rs.getString("city_name")));
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        }


        //Задание 2
        try ( final Connection connection = DriverManager.getConnection(DB_URL,user,pass);) {

            var employeeDAO = new EmployeeDAOimpl(connection);
            var cityLondon = new City(2, "London");
            var employee = new Employee(2, "Den", "U.", "male", 25, cityLondon);

            employeeDAO.create(employee);
            System.out.println(employeeDAO.readById( 8 ));
            employeeDAO.updateById(8, "P.");
            employeeDAO.readAll().forEach(System.out::println);

        }
    }
}
