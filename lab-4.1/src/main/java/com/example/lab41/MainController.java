package com.example.lab41;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 * Контроллер главного окна приложения JavaFX.
 * Осуществляет координацию ввода данных, управление видимостью компонентов
 * и вызов бизнес-логики для расчета премий сотрудников.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class MainController {

    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField nameField;
    @FXML private TextField salaryField;
    @FXML private TextField expField;

    // Менеджер элементы
    @FXML private VBox managerBox;
    @FXML private TextField planField;
    @FXML private TextField factField;

    // Разработчик элементы
    @FXML private VBox developerBox;
    @FXML private ComboBox<String> levelComboBox;
    @FXML private TextField projectsField;

    @FXML private TextArea resultArea;

    /**
     * Инициализация контроллера. Заполняет выпадающие списки начальными данными.
     */
    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList("Менеджер", "Разработчик"));
        levelComboBox.setItems(FXCollections.observableArrayList("Junior", "Middle", "Senior"));
    }

    /**
     * Управляет отображением уникальных полей в зависимости от выбранной роли сотрудника.
     */
    @FXML
    private void handleTypeSelection() {
        String selectedType = typeComboBox.getValue();
        if ("Менеджер".equals(selectedType)) {
            managerBox.setVisible(true);   managerBox.setManaged(true);
            developerBox.setVisible(false); developerBox.setManaged(false);
        } else if ("Разработчик".equals(selectedType)) {
            managerBox.setVisible(false);  managerBox.setManaged(false);
            developerBox.setVisible(true);  developerBox.setManaged(true);
        }
    }

    /**
     * Собирает введенные данные, создает соответствующий объект
     * бизнес-логики и выводит итоговую информацию о расчете премии.
     */
    @FXML
    private void performCalculation() {
        String type = typeComboBox.getValue();
        if (type == null) {
            showAlert("Ошибка", "Выберите тип сотрудника!", AlertType.WARNING);
            return;
        }

        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                showAlert("Ошибка", "Заполните ФИО сотрудника!", AlertType.WARNING);
                return;
            }

            double salary = Double.parseDouble(salaryField.getText());
            int experience = Integer.parseInt(expField.getText());

            if (salary < 0 || experience < 0) {
                showAlert("Ошибка", "Оклад и стаж не могут быть отрицательными!", AlertType.WARNING);
                return;
            }

            Employee employee;

            if ("Менеджер".equals(type)) {
                double plan = Double.parseDouble(planField.getText());
                double fact = Double.parseDouble(factField.getText());
                employee = new Manager(name, salary, experience, plan, fact);
            } else {
                String level = levelComboBox.getValue();
                if (level == null) {
                    showAlert("Ошибка", "Выберите квалификационный уровень разработчика!", AlertType.WARNING);
                    return;
                }
                int projects = Integer.parseInt(projectsField.getText());
                employee = new Developer(name, salary, experience, level, projects);
            }

            resultArea.setText(employee.getInfo());

        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Проверьте корректность числовых данных!", AlertType.ERROR);
        }
    }

    /**
     * Сбрасывает состояние всех элементов ввода интерфейса.
     */
    @FXML
    private void handleNewCalculation() {
        nameField.clear();
        salaryField.clear();
        expField.clear();
        planField.clear();
        factField.clear();
        projectsField.clear();
        typeComboBox.getSelectionModel().clearSelection();
        levelComboBox.getSelectionModel().clearSelection();
        managerBox.setVisible(false);  managerBox.setManaged(false);
        developerBox.setVisible(false); developerBox.setManaged(false);
        resultArea.clear();
    }

    /**
     * Выход из приложения.
     */
    @FXML
    private void handleExit() {
        Platform.exit();
    }

    /**
     * Вывод информации об авторе программы.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Об авторе");
        alert.setHeaderText("Информация о разработчике");
        alert.setContentText("Лабораторная работа №3\nСтудент: Ваше ФИО\nГруппа: Ваша группа");
        alert.showAndWait();
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
