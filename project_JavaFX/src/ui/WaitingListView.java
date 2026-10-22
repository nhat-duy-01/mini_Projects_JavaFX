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
import model.WaitingList;
import dao.WaitingListDAOImp;

public class WaitingListView {

    private WaitingListDAOImp waitingDAO;
    private TableView<WaitingList> table;

    public WaitingListView() {
        waitingDAO = new WaitingListDAOImp();
    }

    public void show(Stage stage) {
        Label title = new Label("Waiting List");
        // Khởi tạo bảng
        table = new TableView<>();
        // Tạo các cột cho bảng
        createTable();

        loadData();
        // Tạo bố cục theo chiều dọc
        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(title, table);

        Scene scene = new Scene(root, 650, 400);

        stage.setTitle("Waiting List");

        stage.setScene(scene);

        stage.show();
    }

    private void createTable() {
        // Cột mã danh sách chờ
        TableColumn<WaitingList, Integer> id = new TableColumn<>("Waiting ID");
        id.setCellValueFactory(new PropertyValueFactory<>("waitingId"));
        // Cột mã sinh viên
        TableColumn<WaitingList, String> student = new TableColumn<>("Student ID");
        student.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        // Cột mã khóa học
        TableColumn<WaitingList, String> course = new TableColumn<>("Course ID");
        course.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        // Cột ngày đăng ký
        TableColumn<WaitingList, String> date = new TableColumn<>("Register Date");
        date.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
        // Cột mức độ ưu tiên
        TableColumn<WaitingList, Integer> priority = new TableColumn<>("Priority");
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        // Thêm tất cả các cột vào bảng
        table.getColumns().addAll(id, student, course, date, priority);
    }

    private void loadData() {

        ObservableList<WaitingList> list = FXCollections.observableArrayList(waitingDAO.getAllWaitingList());

        table.setItems(list);
    }
}
