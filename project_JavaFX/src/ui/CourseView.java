package ui;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Course;
import service.CourseService;

public class CourseView {

    private CourseService service;
    private TableView<Course> table;
    private TextField txtId;
    private TextField txtName;
    private TextField txtCredit;
    private TextField txtSchedule;
    private TextField txtInstructor;
    private TextField txtMax;

    public CourseView() {
        service = new CourseService();
    }
    
    public void show(Stage stage) {
        // Tiêu đề
        Label title = new Label("Course Management");
        // Tạo bảng
        table = new TableView<>();
        // Tạo các cột cho bảng
        createTable();
        loadData();

        txtId = new TextField();
        txtName = new TextField();
        txtCredit = new TextField();
        txtSchedule = new TextField();
        txtInstructor = new TextField();
        txtMax = new TextField();

        txtId.setPromptText("Course ID");
        txtName.setPromptText("Course Name");
        txtCredit.setPromptText("Credits");
        txtSchedule.setPromptText("Schedule");
        txtInstructor.setPromptText("Instructor");
        txtMax.setPromptText("Max Students");

        GridPane form = new GridPane();
        // Khoảng cách giữa các hàng và cột
        form.setHgap(10);
        form.setVgap(10);
        // Khoảng cách với mép cửa sổ
        form.setPadding(new Insets(10));

        form.add(new Label("ID"), 0, 0);
        form.add(txtId, 1, 0);

        form.add(new Label("Name"), 0, 1);
        form.add(txtName, 1, 1);

        form.add(new Label("Credits"), 0, 2);
        form.add(txtCredit, 1, 2);

        form.add(new Label("Schedule"), 0, 3);
        form.add(txtSchedule, 1, 3);

        form.add(new Label("Instructor"), 0, 4);
        form.add(txtInstructor, 1, 4);

        form.add(new Label("Max"), 0, 5);
        form.add(txtMax, 1, 5);
        // Tạo các nút chức năng
        Button btnAdd = new Button("Add");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Button btnRefresh = new Button("Refresh");

        btnAdd.setOnAction(e -> addCourse());

        btnUpdate.setOnAction(e -> updateCourse());

        btnDelete.setOnAction(e -> deleteCourse());

        btnRefresh.setOnAction(e -> loadData());
        // Tạo bố cục theo chiều dọc
        VBox root = new VBox(20);

        root.setPadding(new Insets(15));

        root.getChildren().addAll(title, table, form, btnAdd, btnUpdate, btnDelete, btnRefresh);

        Scene scene = new Scene(root, 700, 650);

        stage.setTitle("Course Management");

        stage.setScene(scene);

        stage.show();

    }

    private void createTable() {
        // Cột mã khóa học
        TableColumn<Course, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        // Cột tên khóa học
        TableColumn<Course, String> name = new TableColumn<>("Name");
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
        // Cột số lượng sinh viên tối đa
        TableColumn<Course, Integer> max = new TableColumn<>("Max");
        max.setCellValueFactory(new PropertyValueFactory<>("maxStudents"));
        // Thêm các cột vào bảng
        table.getColumns().addAll(id, name, credit, schedule, instructor, max);

        table.setOnMouseClicked(e -> {
            Course c = table.getSelectionModel().getSelectedItem();
            if (c != null) {
                // Hiển thị thông tin lên các ô nhập
                txtId.setText(c.getCourseId());

                txtName.setText(c.getCourseName());

                txtCredit.setText(String.valueOf(c.getCredits()));

                txtSchedule.setText(c.getSchedule());

                txtInstructor.setText(c.getInstructor());

                txtMax.setText(String.valueOf(c.getMaxStudents()));
            }
        });
    }

    private void loadData() {

        ObservableList<Course> data = FXCollections.observableArrayList(service.getAllCourses());
        table.setItems(data);
    }

    private void addCourse() {
        Course c = new Course();
        // Lấy dữ liệu từ các ô nhập
        c.setCourseId(txtId.getText());

        c.setCourseName(txtName.getText());

        c.setCredits(Integer.parseInt(txtCredit.getText()));

        c.setSchedule(txtSchedule.getText());

        c.setInstructor(txtInstructor.getText());

        c.setMaxStudents(Integer.parseInt(txtMax.getText()));

        c.setCurrentStudents(0);

        service.addCourse(c);

        loadData();
    }

    private void updateCourse() {
        Course c = new Course();

        c.setCourseId(txtId.getText());

        c.setCourseName(txtName.getText());

        c.setCredits(Integer.parseInt(txtCredit.getText()));

        c.setSchedule(txtSchedule.getText());

        c.setInstructor(txtInstructor.getText());

        c.setMaxStudents(Integer.parseInt(txtMax.getText()));

        service.updateCourse(c);

        loadData();

    }
    // Xóa khóa học
    private void deleteCourse() {
        // Lấy mã khóa học cần xóa
        String id = txtId.getText();
        // Gọi Service xóa khóa học
        service.deleteCourse(id);

        loadData();

    }

}
