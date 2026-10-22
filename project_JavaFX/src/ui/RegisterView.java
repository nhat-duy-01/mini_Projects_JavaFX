package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Course;
import service.CourseService;
import service.RegistrationService;

public class RegisterView {

    private CourseService courseService;
    private RegistrationService registrationService;
    private TextField txtStudentId;
    private ComboBox<Course> cbCourse;
    private TextArea txtResult;

    public RegisterView() {
        courseService = new CourseService();
        registrationService = new RegistrationService();
    }

    public void show(Stage stage) {
        // Tiêu đề
        Label title = new Label("Register Course");
         // Tạo ô nhập mã sinh viên
        txtStudentId = new TextField();

        txtStudentId.setPromptText("Student ID");
        // Tạo Combobox chọn môn học
        cbCourse = new ComboBox<>();

        loadCourses();
        // Tạo nút đăng ký
        Button btnRegister = new Button("Register");
        // Tạo nút hủy đăng ký
        Button btnCancel = new Button("Cancel Course");
        // Tạo vùng hiển thị kết quả
        txtResult = new TextArea();

        txtResult.setEditable(false);

        btnRegister.setOnAction(e -> {
            register();
        });

        btnCancel.setOnAction(e -> {
            cancel();
        });
        // Tạo bố cục theo chiều dọc, khoảng cách giữa các thành phần là 15
        VBox root = new VBox(15);

        root.setPadding(new Insets(20));
        // Thêm tất cả thành phần vào giao diện
        root.getChildren().addAll(title, new Label("Student"), txtStudentId, new Label("Course"), cbCourse, btnRegister, btnCancel, txtResult);

        Scene scene = new Scene(root, 450, 500);
        // tiêu đề cửa sổ
        stage.setTitle("Register Course");

        stage.setScene(scene);
        // Hiển thị cửa sổ
        stage.show();

    }

    private void loadCourses() {

        ObservableList<Course> list = FXCollections.observableArrayList(courseService.getAllCourses());

        cbCourse.setItems(list);

    }

    private void register() {
        String studentId = txtStudentId.getText();

        Course course = cbCourse.getValue();

        if (studentId.isEmpty() || course == null) {
            txtResult.setText("Please enter information");
            return;
        }

        String result = registrationService.registerCourse(studentId, course.getCourseId());
        txtResult.setText(result);
    }

    private void cancel() {
        String studentId = txtStudentId.getText();

        Course course = cbCourse.getValue();

        if (studentId.isEmpty() || course == null) {
            txtResult.setText("Please enter information");
            return;
        }

        boolean result = registrationService.cancelCourse(studentId, course.getCourseId());

        if (result) {
            txtResult.setText("Cancel success");
        } else {
            txtResult.setText("Cancel failed");
        }
    }
}
