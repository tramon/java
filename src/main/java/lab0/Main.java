package lab0;

public class Main {

    public static void main() {

        Person[] persons = new Person[3];

        Person person = new Person();
        Person teacher = new Teacher("Oleg", 29);
        Person student = new Student("Max", 22);

        persons[0] = person;
        persons[1] = teacher;
        persons[2] = student;

        for (Person p: persons) {
            p.introduce();
        }

//        Person person = new Teacher("Volodymyr", 25);
//        person.introduce();
    }

}
