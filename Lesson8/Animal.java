package Lesson8;

public class Animal {
    private static int totalAnimals = 0;

    public Animal() {
        totalAnimals++;
    }

    public static int getTotalAnimals() {
        return totalAnimals;
    }

    public void run(int distance) {
        System.out.println("Животное пробежало " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println("Животное проплыло " + distance + " м.");
    }
}

