package dao;

import application.Employee;

import java.util.List;

public interface EmployeeDAO {

    //Создать в интерфейсе методы:
    //1. Создание(добавление) сущности Employee в таблицу
    //2. Получение конкретного объекта Employee по id
    //3. Получение списка всех объектов Employee из базы
    //4. Изменение конкретного объекта Employee в базе по id
    //5. Удаление конкретного объекта Employee из базы по id

    void           create(Employee employee);
    Employee       readById(int id);
    List<Employee> readAll();
    void updateById(int id, String last_name);
    void           deleteById(int id);
}
