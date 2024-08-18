package src.shape;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5, "red", "black");
        Shape rectangle = new Rectangle(4, 6, "blue", "green");
        Shape triangle = new Triangle(3, 4, 5, "yellow", "purple");

        System.out.println("Circle: ");
        System.out.printf("Area: %.2f, Perimeter: %.2f, Fill Color: %s, Border Color: %s%n",
                ((Area) circle).area(), ((Perimeter) circle).perimeter(), circle.getFillColor(), circle.getBorderColor());

        System.out.println("Rectangle: ");
        System.out.printf("Area: %.2f, Perimeter: %.2f, Fill Color: %s, Border Color: %s%n",
                ((Area) rectangle).area(), ((Perimeter) rectangle).perimeter(), rectangle.getFillColor(), rectangle.getBorderColor());

        System.out.println("Triangle: ");
        System.out.printf("Area: %.2f, Perimeter: %.2f, Fill Color: %s, Border Color: %s%n",
                ((Area) triangle).area(), ((Perimeter) triangle).perimeter(), triangle.getFillColor(), triangle.getBorderColor());
    }
}

