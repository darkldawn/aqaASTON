package src.animal;

public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобик");
        dogBobik.run(150);
        dogBobik.swim(5);

        Cat catMurzik = new Cat("Мурзик");
        catMurzik.run(250);
        catMurzik.swim(5);

        FoodBowl bowl = new FoodBowl(30);
        Cat[] cats = {new Cat("Мурзик"), new Cat("Кузя"), new Cat("Василиса")};

        for (Cat cat : cats) {
            cat.eat(10, bowl);
            System.out.println(cat.isFull() ? cat.name + " сыт." : cat.name + " голоден.");
        }

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
    }
}
