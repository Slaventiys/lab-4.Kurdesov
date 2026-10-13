package com.example.lab41;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Главный класс для запуска JavaFX-приложения учета сотрудников.
 * Инициализирует основную сцену и загружает графический интерфейс из FXML.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class HelloApplication extends Application {

    /**
     * Точка входа для JavaFX-интерфейса. Создает и отображает главное окно.
     *
     * @param stage главная подмостки (окно) для этого приложения.
     * @throws IOException если файл scene.fxml не найден или поврежден.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        stage.setTitle("Учет сотрудников и расчет премий");
        stage.setScene(new Scene(root, 700, 550));
        stage.show();
    }

    /**
     * Главный метод, служащий точкой входа в программу.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        launch();
    }
}