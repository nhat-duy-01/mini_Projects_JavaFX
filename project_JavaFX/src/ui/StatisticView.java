package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.CourseStatistic;
import service.StatisticService;

public class StatisticView {

    private StatisticService service;
    private TableView<CourseStatistic> table;

    public StatisticView() {

        service = new StatisticService();
    }

    public void show(Stage stage) {

        Label title = new Label("Course Registration Statistics");
        // Tạo bảng hiển thị dữ liệu
        table = new TableView<>();
        // Tạo các cột trong bảng
        createTable();

        loadData();
        // Tạo bố cục giao diện theo chiều dọc
        VBox root = new VBox(15);
        
        root.setPadding(new Insets(20));

        root.getChildren().addAll(title, table);

        Scene scene = new Scene(root, 600, 400);
        // Đặt tiêu đề cửa sổ
        stage.setTitle("Statistics");

        stage.setScene(scene);

        stage.show();
    }

    private void createTable() {
        // Cột mã khóa học
        TableColumn<CourseStatistic, String> id = new TableColumn<>("Course ID");
        id.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        // Cột tên khóa học
        TableColumn<CourseStatistic, String> name = new TableColumn<>("Course Name");
        name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        // Cột tổng số sinh viên đăng ký
        TableColumn<CourseStatistic, Integer> total = new TableColumn<>("Total Students");
        total.setCellValueFactory(new PropertyValueFactory<>("totalStudents"));
         // Thêm các cột vào bảng
        table.getColumns().addAll(id, name, total);
    }

    private void loadData() {
        
        ObservableList<CourseStatistic> data = FXCollections.observableArrayList(service.getStatistic());

        table.setItems(data);
    }
}
