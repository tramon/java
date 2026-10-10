package lab0;

public class Student extends Person {

    public Student(String name, int age) {
        super();
        super.name = name;
        super.age = age;
    }

    @Override
    void introduce() {
//        super.introduce(); // спочатку викликаємо версію Person
        System.out.println("I am a student and my name is " + name);
    }
}
