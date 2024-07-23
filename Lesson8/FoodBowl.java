package Lesson8;

public class FoodBowl {
    private int foodAmount;

    public FoodBowl(int initialFood) {
        this.foodAmount = Math.max(0, initialFood);
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        foodAmount += amount;
    }

    public void subtractFood(int amount) {
        foodAmount = Math.max(0, foodAmount - amount);
    }
}
