package main.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity //определение, что этот класс является сущностью БД
public class Person {

    @Getter
    @Setter
    @Id //для генерации Id в БД
    @GeneratedValue(strategy = GenerationType.AUTO) //Автоматическая генерация Id
    private int id;
    @Getter //аннотации ломбок обычного метода get()
    @Setter //аннотации ломбок обычного метода set()
    private String surname;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String patronymic;
    @Getter
    @Setter
    private String birthday;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String phoneNumber;

    //переопределение методов класса Object equals() и hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getSurname()
                .equals(person.getSurname()) && getName()
                .equals(person.getName()) && getPatronymic()
                .equals(person.getPatronymic()) && getBirthday()
                .equals(person.getBirthday()) && getEmail()
                .equals(person.getEmail()) && getPhoneNumber()
                .equals(person.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(),
                getName(), getPatronymic(),
                getBirthday(), getEmail(),
                getPhoneNumber());
    }
}
