
public class MatrixSum {

    public static void main(String[] args) {
        String[][] matrix = {
                {"1", "6", "3", "4"},
                {"3", "6", "7", "8"},
                {"9", "10", "12", "12"},
                {"32", "13", "15", "16"}
        };

        try {
            int sum = calculateMatrixSum(matrix);
            System.out.println("Сумма элементов матрицы: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка: Матрица не соответствует размеру 4x4. " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка: Некорректные данные в ячейке [" + e.getRow() + "][" + e.getCol() + "].");
        }
    }

    public static int calculateMatrixSum(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != 4 || matrix[0].length != 4) {
            throw new MyArraySizeException("Матрица не соответствует размеру 4x4.");
        }

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    sum += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Некорректные данные в ячейке [" + i + "][" + j + "].", i, j);
                }
            }
        }

        return sum;
    }
}

class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    private int row;
    private int col;

    public MyArrayDataException(String message, int row, int col) {
        super(message);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}