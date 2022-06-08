package ua.lviv.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class Person {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 200, message = "Full name should be between 2 and 200 characters")
    private String fullName;

    @Min(value = 1900, message = "Year of born should be greater than 1900")
    private int yearOfBorn;

    public Person() {
    }

    public Person(String fullName, int yearOfBorn) {
        this.fullName = fullName;
        this.yearOfBorn = yearOfBorn;
    }

    public Person(int id, String fullName, int yearOfBorn) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBorn = yearOfBorn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }
}
