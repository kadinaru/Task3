module com.example.task7 {
    requires javafx.controls;  // Зависимость от JavaFX Controls для элементов управления (например, кнопок)
    requires javafx.graphics;  // Зависимость от JavaFX Graphics для анимации, сцен и т.д.
    requires javafx.fxml;     // Зависимость от FXML для использования FXML файлов
    requires java.desktop;// Зависимость от java.desktop для работы с аудио
    requires javafx.media;

    opens com.example.task7 to javafx.fxml;  // Открытие пакета для FXML
    exports com.example.task7;  // Экспорт пакета для использования в других модулях
}
