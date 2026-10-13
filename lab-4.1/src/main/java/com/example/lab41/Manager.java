package com.example.lab41;

/**
 * Производный класс, представляющий менеджера.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class Manager extends Employee {
    private double plannedSales;
    private double actualSales;

    /**
     * Конструктор для создания объекта менеджера.
     */
    public Manager(String fullName, double salary, int experience, double plannedSales, double actualSales) {
        super(fullName, "Менеджер", salary, experience);
        this.plannedSales = plannedSales;
        this.actualSales = actualSales;
    }

    /**
     * Переопределенный метод расчета премии менеджера.
     * Премия = базовая премия + 5% от превышения плана продаж.
     *
     * @return общая премия менеджера
     */
    @Override
    public double calculateBonus() {
        double baseBonus = super.calculateBonus();
        double extraBonus = 0.0;
        if (actualSales > plannedSales) {
            extraBonus = (actualSales - plannedSales) * 0.05; // 5% от превышения плана
        }
        return baseBonus + extraBonus;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format("План продаж: %.2f руб.\nФакт продаж: %.2f руб.\nПревышение плана: %.2f руб.\n",
                plannedSales, actualSales, Math.max(0, actualSales - plannedSales));
    }
}