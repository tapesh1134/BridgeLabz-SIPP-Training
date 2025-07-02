package Inheritance;

public class Animal {

    String name;
    int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {

    Dog(String name, int age) {
        super(name, age);
    }

    void makeSound() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {

    Cat(String name, int age) {
        super(name, age);
    }

    void makeSound() {
        System.out.println("Meow");
    }
}

class Bird extends Animal {

    Bird(String name, int age) {
        super(name, age);
    }

    void makeSound() {
        System.out.println("Tweet");
    }
}

public class AssistedProblem {

    public static void main(String[] args) {
        Animal dog = new Dog("Buddy", 3);
        Animal cat = new Cat("Whiskers", 2);
        Animal bird = new Bird("Chirpy", 1);

        dog.makeSound();
        cat.makeSound();
        bird.makeSound();
    }
}
{
    
}
