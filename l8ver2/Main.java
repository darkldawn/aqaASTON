package l8ver2;

public class Main {

    private static Shape shape;

    public static void main(String[] args) {
        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Зеленый");
        Triangle triangle = new Triangle(3, 4, 5, "Желтый", "Классический");

        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(triangle);
    }

    public static void printShapeInfo(Shape sha) {
        Main.shape = shape;
        System.out.println("Фигура: " + shape.getClass().getSimpleName());
        System.out.printf("Периметр: %.2f\\n", shape.perimeter());
        System.out.printf("Площадь: %.2f\\n", shape.area());
        System.out.println(shape.getColors());
        System.out.println();
    }
}
