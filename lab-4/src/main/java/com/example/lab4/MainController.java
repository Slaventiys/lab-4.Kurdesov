package com.example.lab4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Контроллер главного окна приложения.
 * Обеспечивает обработку событий взаимодействия пользователя с главным меню и
 * формой расчета площади боковой поверхности призмы, включая сохранение результатов в файл.
 *
 * @author Ваше Имя
 * @version 1.3
 */
public class MainController {

    @FXML
    private TextField sideAField;

    @FXML
    private TextField heightHField;

    @FXML
    private Label resultLabel;

    /** Последнее введенное пользователем значение стороны а */
    private double lastA = 0.0;

    /** Последнее введенное пользователем значение высоты h */
    private double lastH = 0.0;

    /** Переменная для хранения последнего успешного расчета площади */
    private double lastCalculatedArea = 0.0;

    /** Флаг, указывающий, был ли выполнен расчет */
    private boolean isCalculated = false;

    /**
     * Выполняет расчет площади боковой поверхности призмы.
     * Считывает сторону основания (a) и высоту (h).
     * Использует формулу: S = 3 * a * h.
     * Выводит ошибку при вводе текста или отрицательных чисел.
     */
    @FXML
    private void performCalculation() {
        try {
            double a = Double.parseDouble(sideAField.getText());
            double h = Double.parseDouble(heightHField.getText());

            if (a <= 0 || h <= 0) {
                showAlert("Ошибка геометрии", "Размеры должны быть больше нуля!", AlertType.WARNING);
                return;
            }

            lastA = a;
            lastH = h;
            lastCalculatedArea = 3 * a * h;
            isCalculated = true;

            resultLabel.setText(String.format("Результат S(бок): %.2f", lastCalculatedArea));
        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Пожалуйста, введите корректные числовые значения!", AlertType.ERROR);
        }
    }

    /**
     * Обрабатывает выбор пункта меню "Новый расчет".
     * Очищает текстовые поля ввода и сбрасывает текущий результат.
     */
    @FXML
    private void handleNewCalculation() {
        sideAField.clear();
        heightHField.clear();
        resultLabel.setText("Результат S(бок): ");
        isCalculated = false;
    }

    /**
     * Обрабатывает выбор пункта меню "Сохранить".
     * Открывает диалоговое окно выбора файла (FileChooser) и записывает
     * подробные результаты расчета в выбранный текстовый (.txt) файл.
     */
    @FXML
    private void handleSave() {
        if (!isCalculated) {
            showAlert("Сохранение", "Нет данных для сохранения! Сначала выполните расчет.", AlertType.WARNING);
            return;
        }

        // Создаем диалог сохранения файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить результат расчета");

        // Задаем фильтр, чтобы сохранялись только .txt файлы
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)", "*.txt")
        );

        // Предлагаем дефолтное имя файла
        fileChooser.setInitialFileName("расчет_призмы.txt");

        // Получаем текущее окно приложения для привязки модального диалога
        Stage stage = (Stage) resultLabel.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // Записываем данные в файл
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("=== Отчет о расчете призмы ===\n");
                writer.write(String.format("Сторона основания (a): %.2f\n", lastA));
                writer.write(String.format("Высота призмы (h): %.2f\n", lastH));
                writer.write("-----------------------------\n");
                writer.write(String.format("Площадь боковой поверхности S(бок): %.2f\n", lastCalculatedArea));

                showAlert("Успех", "Файл успешно сохранен по пути:\n" + file.getAbsolutePath(), AlertType.INFORMATION);
            } catch (IOException e) {
                showAlert("Ошибка сохранения", "Не удалось записать данные в файл: " + e.getMessage(), AlertType.ERROR);
            }
        }
    }

    /**
     * Обрабатывает выбор пункта меню "Выход".
     * Корректно завершает работу JavaFX приложения.
     */
    @FXML
    private void handleExit() {
        Platform.exit();
    }

    /**
     * Обрабатывает выбор пункта меню "О программе".
     * Отображает модальное окно с информацией о версии и назначении программы.
     */
    @FXML
    private void handleAbout() {
        String info = "Программа расчета площади боковой поверхности призмы.\n" +
                "Основание: равносторонний треугольник (a, h).\n" +
                "Версия: 1.3\nРазработано в IntelliJ IDEA.";
        showAlert("О программе", info, AlertType.INFORMATION);
    }

    /**
     * Вспомогательный метод для вывода окон с сообщениями различных типов.
     *
     * @param title   заголовок всплывающего окна
     * @param message текст сообщения внутри окна
     * @param type    тип уведомления (информация, предупреждение, ошибка)
     */
    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
