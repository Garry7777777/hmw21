package dao;

import application.City;
import application.Employee;
import java.sql.*;
import java.util.*;

    public class EmployeeDAOimpl implements EmployeeDAO {

        private final Connection connection;

        public EmployeeDAOimpl(Connection connection) {
            this.connection = connection;
        }

        // Метод добавления
        @Override
        public void create(Employee employee) {
            var query = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))";

            try (var statement = connection.prepareStatement( query ))
            {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getGender());

                statement.setInt(4, employee.getAge());
                statement.setInt(5, employee.getCity().getId());

                statement.execute();
            } catch (SQLException e) {  e.printStackTrace(); }
        }

        // Метод получения объекта по id
        @Override
        public Employee readById(int id) {

            var employee = new Employee();
            var query = "SELECT * FROM employee e INNER JOIN city c ON e.city_id=c.city_id AND id = (?)";

            try (var statement = connection.prepareStatement(query))
            {
                statement.setInt(1, id);
                var resultSet = statement.executeQuery();

                    resultSet.next();
                    employee.setId(Integer.parseInt(resultSet.getString("id")));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setGender(resultSet.getString("gender"));
                    employee.setAge(Integer.parseInt(resultSet.getString("age")));
                    employee.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));

            } catch (SQLException e) { e.printStackTrace(); }
            return employee;
        }

        //Получение всех объектов из базы
        @Override
        public List<Employee> readAll() {

            List<Employee> employeeList = new ArrayList<>();
            var query ="SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id";

            try (var statement = connection.prepareStatement( query )) {
                var resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    var id = Integer.parseInt(resultSet.getString("id"));
                    var first_name = resultSet.getString("first_name");
                    var last_name = resultSet.getString("last_name");
                    var gender = resultSet.getString("gender");
                    var age = Integer.parseInt(resultSet.getString("age"));
                    var city = new City(resultSet.getInt("city_id"), resultSet.getString("city_name"));

                    employeeList.add(new Employee(id, first_name, last_name, gender, age, city));
                }
            } catch (SQLException e) { e.printStackTrace(); }
            return employeeList;
        }


        //Метод обновления данных в базе по id
        @Override
        public void updateById(int id, String last_name) {
            var query = "UPDATE employee SET last_name =(?) WHERE id=(?)";

            try (PreparedStatement statement = connection.prepareStatement( query )) {

                statement.setString(1, last_name);
                statement.setInt(2, id);
                statement.execute();

            } catch (SQLException e) { e.printStackTrace(); }
        }

        // Метод удаления данных из базы по id
        @Override
        public void deleteById(int id) {
            var query ="DELETE FROM employee WHERE id=(?)";

            try (PreparedStatement statement = connection.prepareStatement( query )) {

                statement.setInt(1, id);
                statement.executeQuery();

            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

