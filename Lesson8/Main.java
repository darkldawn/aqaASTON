import Lesson8.Animal;
import Lesson8.Cat;
import Lesson8.Dog;
import Lesson8.FoodBowl;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.run(300);
        dog1.swim(5);

        Cat cat1 = new Cat();
        cat1.run(150);
        cat1.swim(10); // Проверка, что кот не умеет плавать.

        FoodBowl bowl = new FoodBowl(10);

        Cat[] cats = { new Cat(), new Cat(), new Cat() };

        for (Cat cat : cats) {
            cat.eat(bowl, 5); // Коты кушают по 5 единиц еды.
        }

        for (Cat cat : cats) {
            System.out.println("Кот сыт: " + cat.isFull());
        }

        System.out.println("Общее количество животных: " + Animal.getTotalAnimals());
        System.out.println("Общее количество собак: " + Dog.getTotalDogs());
        System.out.println("Общее количество котов: " + Cat.getTotalCats());
    }
}
    }
}
