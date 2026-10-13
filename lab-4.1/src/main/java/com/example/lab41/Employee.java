package com.example.lab41;

/**
 * Базовый класс, представляющий сотрудника организации.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class Employee {
    private String fullName;
    private String position;
    private double salary;
    private int experience;

    /**
     * Конструктор для создания объекта сотрудника.
     *
     * @param fullName   ФИО сотрудника
     * @param position   должность
     * @param salary     оклад
     * @param experience стаж работы (в годах)
     */
    public Employee(String fullName, String position, double salary, int experience) {
        this.fullName = fullName;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
    }

    /**
     * Расчитывает базовую премию: 10% от оклада * коэффициент стажа.
     * Коэффициент: более 10 лет - 2, более 15 лет - 3, иначе - 1.
     *
     * @return размер базовой премии
     */
    public double calculateBonus() {
        double coefficient = 1.0;
        if (experience > 15) {
            coefficient = 3.0;
        } else if (experience > 10) {
            coefficient = 2.0;
        }
        return (salary * 0.10) * coefficient;
    }

    /**
     * Формирует информационную строку со сведениями о сотруднике.
     *
     * @return отформатированная информация о сотруднике
     */
    public String getInfo() {
        return String.format("Сотрудник: %s\nДолжность: %s\nОклад: %.2f руб.\nСтаж: %d лет\nПремия: %.2f руб.\n",
                fullName, position, salary, experience, calculateBonus());
    }

    // Геттеры
    public String getFullName() { return fullName; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public int getExperience() { return experience; }
}