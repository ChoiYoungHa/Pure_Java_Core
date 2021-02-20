package pure_Java_core.pure_core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class helloLombok {
    String name;
    int age;

    public static void main(String[] args) {
        helloLombok helloLombok = new helloLombok();
        helloLombok.setAge(19);
        helloLombok.setName("Hi Lombok");
        int age = helloLombok.getAge();
        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("helloLombok = " + helloLombok);
    }
}
