package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {

    public void show(Stage stage) {

        Label title = new Label("Course Registration System");

        title.setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");

        Button btnCourse = new Button("Course Management");

        Button btnRegister = new Button("Register Course");

        Button btnWaiting = new Button("Waiting List");

        Button btnSchedule = new Button("My Schedule");

        Button btnStatistic = new Button("Statistics");

        btnCourse.setPrefWidth(250);
        btnRegister.setPrefWidth(250);
        btnWaiting.setPrefWidth(250);
        btnSchedule.setPrefWidth(250);
        btnStatistic.setPrefWidth(250);

        VBox root = new VBox(30);

        root.setPadding(new Insets(30));

        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, btnCourse, btnRegister, btnWaiting, btnSchedule, btnStatistic);

        // mở màn hình quản lý môn
        btnCourse.setOnAction(e -> {
            CourseView view = new CourseView();
            view.show(new Stage());
        });

        // đăng ký môn
        btnRegister.setOnAction(e -> {
            RegisterView view = new RegisterView();
            view.show(new Stage());
        });

        // danh sách chờ
        btnWaiting.setOnAction(e -> {
            WaitingListView view = new WaitingListView();
            view.show(new Stage());
        });

        // thời khóa biểu
        btnSchedule.setOnAction(e -> {
            ScheduleView view = new ScheduleView();
            view.show(new Stage());
        });

        // thống kê
        btnStatistic.setOnAction(e -> {
            StatisticView view = new StatisticView();
            view.show(new Stage());
        });
        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("Course Registration System");
        stage.setScene(scene);
        stage.show();
    }
}
