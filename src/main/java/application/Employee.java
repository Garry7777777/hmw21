package application;
import java.util.Objects;

public class Employee {

    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private City city;

    public Employee(){ }

    public void setId( int id ) {this.id = id;}
    public void setFirstName( String first_name ) {this.first_name = first_name;}
    public void setLastName( String last_name ) {this.last_name = last_name;}
    public void setGender( String gender ) {this.gender = gender;}
    public void setAge( int age ) {this.age = age;}
    public void setCity( City city ) {this.city = city;}

    public int getAge() { return age;}
    public String getGender() {return gender;}
    public String getLastName() {return last_name;}
    public String getFirstName() {return first_name;}
    public int getId() {return id;}
    public City getCity() {return city;}

    public Employee(int id, String first_name, String last_name, String gender, int age, City city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, gender, age, city);
    }

    @Override
    public String toString() {
        return "Employee:" +"id=" + id +" имя=" + first_name +" фамилия=" +
                last_name + ", " + gender + ", age=" + age +", " + city;
    }
}