package lab0;

public class Teacher extends Person {

    public Teacher(String name, int age){
        super();
        super.name = name;
        super.age = age;
    }

    @Override
    void introduce() {
//        super.introduce(); // спочатку викликаємо версію Person
        System.out.println("I am a teacher and my name is " + name);
    }
}
