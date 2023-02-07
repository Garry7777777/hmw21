package application;

import java.util.Objects;

public class City {

    private final int id;
    private final String city_name;

    public City( int id, String city_name ) {
        this.id = id;
        this.city_name = city_name;
    }

    public int getId() {return id;}
    public String getCityName() {return city_name;}



    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city_name);
    }

    @Override
    public String toString() { return "City:" + "id=" + id +", city_name=" + city_name ;
    }
}