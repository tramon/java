package lab0;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name = "Person";
    int age = 60;

    void introduce() {
        List<Integer> list = new ArrayList<Integer>();
        System.out.println("I am a " + name);
    }

}
