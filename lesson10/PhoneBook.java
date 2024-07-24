package lesson10;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String, String[]> phoneNumbers;

    public PhoneBook() {
        phoneNumbers = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (phoneNumbers.containsKey(lastName)) {
            // Если фамилия уже есть, добавляем номер в существующий массив
            String[] existingNumbers = phoneNumbers.get(lastName);
            String[] newNumbers = new String[existingNumbers.length + 1];
            for (int i = 0; i < existingNumbers.length; i++) {
                newNumbers[i] = existingNumbers[i];
            }
            newNumbers[existingNumbers.length] = phoneNumber;
            phoneNumbers.put(lastName, newNumbers);
        } else {
            // Если фамилии нет, создаем новый массив с одним номером
            phoneNumbers.put(lastName, new String[]{phoneNumber});
        }
    }

    public String[] get(String lastName) {
        return phoneNumbers.getOrDefault(lastName, new String[]{});
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Сидоров", "8-800-555-35-35");
        phoneBook.add("Ельцин", "8-900-123-45-67");
        phoneBook.add("Павлов", "8-911-789-02-23");

        String[] numbers = phoneBook.get("Сидоров");
        if (numbers.length > 0) {
            System.out.println("Телефонные номера для Сидорова:");
            for (String number : numbers) {
                System.out.println(number);
            }
        } else {
            System.out.println("Номер не найден.");
        }
    }
}
