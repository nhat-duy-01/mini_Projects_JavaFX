package dao;

import java.util.ArrayList;
import model.WaitingList;

public interface IWaitingListDAO {

    ArrayList<WaitingList> getAllWaitingList();

    ArrayList<WaitingList> getWaitingListByCourse(String courseId);

    WaitingList getWaitingStudent(String studentId, String courseId);

    boolean insertWaiting(WaitingList waiting);

    boolean deleteWaiting(String studentId, String courseId);

    int getNextPriority(String courseId);

}