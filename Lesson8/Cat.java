package Lesson8;

public class Cat extends Animal {
    private static int totalCats = 0;
    private boolean isFull = false;

    public Cat() {
        super();
        totalCats++;
    }

    public void run(int distance) {
        if (distance <= 200) {
            System.out.println("Кот пробежал " + distance + " м.");
        } else {
            System.out.println("Кот не может пробежать больше 200 м.");
        }
    }

    public void swim(int distance) {
        System.out.println("Кот не умеет плавать.");
    }

    public void eat(FoodBowl bowl, int amount) {
        if (bowl.getFoodAmount() >= amount) {
            bowl.subtractFood(amount);
            isFull = true;
            System.out.println("Кот покушал и теперь сыт.");
        } else {
            System.out.println("Коту недостаточно еды в миске.");
        }
    }

    public static int getTotalCats() {
        return totalCats;
    }

    public boolean isFull() {
        return isFull;
    }
}
