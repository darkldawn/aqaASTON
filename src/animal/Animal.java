package src.animal;
abstract class Animal {
    private static int animalCount = 0;
    protected String name; // Поле name перемещено в абстрактный класс

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public abstract void run(int distance); // Сделано абстрактным

    public abstract void swim(int distance); // Сделано абстрактным

    @Override
    public String toString() {
        return "Животное: " + name;
    }
}

class Dog extends Animal {
    private static int dogCount = 0;
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println(name + " не может пробежать отрицательное расстояние.");
        } else if (distance > MAX_RUN_DISTANCE) {
            System.out.println(name + " не может пробежать " + distance + " м.");
        } else {
            System.out.println(name + " пробежал " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance < 0) {
            System.out.println(name + " не может проплыть отрицательное расстояние.");
        } else if (distance > MAX_SWIM_DISTANCE) {
            System.out.println(name + " не может проплыть " + distance + " м.");
        } else {
            System.out.println(name + " проплыл " + distance + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public String toString() {
        return "Собака: " + name;
    }
}

class Cat extends Animal {
    private static int catCount = 0;
    private static final int MAX_RUN_DISTANCE = 200;
    private boolean isFull;

    public Cat(String name) {
        super(name);
        catCount++;
        this.isFull = false;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println(name + " не может пробежать отрицательное расстояние.");
        } else if (distance > MAX_RUN_DISTANCE) {
            System.out.println(name + " не может пробежать " + distance + " м.");
        } else {
            System.out.println(name + " пробежал " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public boolean eat(int foodAmount, FoodBowl bowl) {
        if (bowl.getFoodAmount() >= foodAmount) {
            bowl.decreaseFood(foodAmount);
            this.isFull = true;
            return true;
        } else {
            System.out.println(name + " не хватает еды.");
            return false;
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public String toString() {
        return "Кот: " + name;
    }
}

class FoodBowl {
    private int foodAmount;

    public FoodBowl(int foodAmount) {
        this.foodAmount = Math.max(foodAmount, 0);
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void decreaseFood(int amount) {
        foodAmount = Math.max(foodAmount - amount, 0);
    }

    public void addFood(int amount) {
        foodAmount += amount;
    }
}



