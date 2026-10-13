
package com.example.lab4;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    /**
     * Точка входа для JavaFX-приложения. Создает главное окно (Stage).
     *
     * @param primaryStage главная подмостки (окно) для этого приложения.
     * @throws IOException если файл scene.fxml не найден или не может быть загружен.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        primaryStage.setTitle("Приложение с JavaFX меню");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Главный метод, служащий точкой входа в программу.
     * Используется для запуска в средах, которые не поддерживают JavaFX напрямую.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


