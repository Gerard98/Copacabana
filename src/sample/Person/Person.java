package sample.Person;

import java.io.Serializable;

public class Person  implements Serializable {
    private String Name;
    private String Surname;

    Person(){
        Name = " ** ";
        Surname = " ** ";
    }

    public Person(String Name, String Surname){
        this.Name = Name;
        this.Surname = Surname;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
