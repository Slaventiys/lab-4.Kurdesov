package com.example.lab41;

/**
 * Производный класс, представляющий разработчика.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class Developer extends Employee {
    private String level; // junior, middle, senior
    private int completedProjects;

    /**
     * Конструктор для создания объекта разработчика.
     */
    public Developer(String fullName, double salary, int experience, String level, int completedProjects) {
        super(fullName, "Разработчик (" + level + ")", salary, experience);
        this.level = level.toLowerCase();
        this.completedProjects = completedProjects;
    }

    /**
     * Переопределенный метод расчета премии разработчика.
     * Формула: (базовая премия + проектов * 300) * повышающий коэффициент уровня.
     * Коэффициенты: junior - 1.5, middle - 3.0, senior - 5.0.
     *
     * @return общая премия разработчика
     */
    @Override
    public double calculateBonus() {
        double baseBonus = super.calculateBonus();
        double levelCoefficient = 1.0;

        switch (level) {
            case "junior": levelCoefficient = 1.5; break;
            case "middle": levelCoefficient = 3.0; break;
            case "senior": levelCoefficient = 5.0; break;
        }

        return (baseBonus + (completedProjects * 300)) * levelCoefficient;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format("Уровень: %s\nЗавершено проектов: %d\n", level, completedProjects);
    }
}
