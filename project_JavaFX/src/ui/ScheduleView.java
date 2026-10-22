package ui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import dao.CourseDAOImp;
import dao.RegistrationDAOImp;
import model.Course;
import model.Registration;

public class ScheduleView {

    private RegistrationDAOImp registrationDAO;
    private CourseDAOImp courseDAO;
    private TableView<Course> table;
    private TextField txtStudentId;

    public ScheduleView() {
        registrationDAO = new RegistrationDAOImp();

        courseDAO = new CourseDAOImp();
    }

    public void show(Stage stage) {

        Label title = new Label("My Schedule");

        txtStudentId = new TextField();

        txtStudentId.setPromptText("Enter Student ID");

        Button btnSearch = new Button("View Schedule");

        btnSearch.setOnAction(e -> {
            loadSchedule();
        });

        table = new TableView<>();

        createTable();

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(title, txtStudentId, btnSearch, table);

        Scene scene = new Scene(root, 700, 500);

        stage.setTitle("Student Schedule");

        stage.setScene(scene);

        stage.show();

    }

    private void createTable() {
         // Cột mã khóa học
        TableColumn<Course, String> id = new TableColumn<>("Course ID");
        id.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        // Cột tên khóa học
        TableColumn<Course, String> name = new TableColumn<>("Course Name");
        name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        // Cột số tín chỉ
        TableColumn<Course, Integer> credit = new TableColumn<>("Credits");
        credit.setCellValueFactory(new PropertyValueFactory<>("credits"));
        // Cột lịch học
        TableColumn<Course, String> schedule = new TableColumn<>("Schedule");
        schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        // Cột giảng viên
        TableColumn<Course, String> instructor = new TableColumn<>("Instructor");
        instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        // Thêm các cột vào bảng
        table.getColumns().addAll(id, name, credit, schedule, instructor);
    }

    private void loadSchedule() {
        String studentId = txtStudentId.getText();
        // Danh sách các khóa học sẽ hiển thị
        ArrayList<Course> courses = new ArrayList<>();
        // Lấy danh sách đăng ký của sinh viên
        ArrayList<Registration> registrations = registrationDAO.getRegistrationsByStudent(studentId);
        // Duyệt từng bản ghi đăng ký
        for (Registration r : registrations) {
            if (r.getStatus().equals("REGISTERED")) {

                Course c = courseDAO.getCourseById(r.getCourseId());

                if (c != null) {
                    courses.add(c);
                }
            }
        }
        ObservableList<Course> data = FXCollections.observableArrayList(courses);

        table.setItems(data);
    }
}
