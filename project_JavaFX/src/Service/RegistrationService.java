package service;

import java.util.ArrayList;

import dao.CourseDAOImp;
import dao.RegistrationDAOImp;
import dao.WaitingListDAOImp;

import model.Course;
import model.Registration;
import model.WaitingList;

public class RegistrationService {

    private RegistrationDAOImp registrationDAO;
    private WaitingListDAOImp waitingDAO;
    private CourseDAOImp courseDAO;

    public RegistrationService() {
        registrationDAO = new RegistrationDAOImp();
        waitingDAO = new WaitingListDAOImp();
        courseDAO = new CourseDAOImp();
    }

    // đăng ký môn học
    public String registerCourse(String studentId, String courseId) {
        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            return "Course not found";
        }
        // kiểm tra đã đăng ký chưa
        Registration old = registrationDAO.getRegistration(studentId, courseId);

        if (old != null && old.getStatus().equals("REGISTERED")) {
            return "Already registered";
        }

        // kiểm tra trùng lịch
        if (checkScheduleConflict(studentId, course)) {
            return "Schedule conflict";
        }

        // kiểm tra sĩ số
        if (course.getCurrentStudents() < course.getMaxStudents()) {
            Registration r = new Registration();

            r.setStudentId(studentId);
            r.setCourseId(courseId);
            r.setStatus("REGISTERED");

            registrationDAO.insertRegistration(r);
            courseDAO.updateCurrentStudents(courseId, course.getCurrentStudents() + 1);
            return "Register success";
        } else {
            int priority = waitingDAO.getNextPriority(courseId);
            WaitingList w = new WaitingList();

            w.setStudentId(studentId);
            w.setCourseId(courseId);
            w.setPriority(priority);
            waitingDAO.insertWaiting(w);
            return "Added to waiting list";
        }
    }

    // kiểm tra trùng lịch
    private boolean checkScheduleConflict(
            String studentId,
            Course newCourse) {

        ArrayList<Registration> list = registrationDAO.getRegistrationsByStudent(studentId);

        for (Registration r : list) {
            if (r.getStatus().equals("REGISTERED")) {
                Course oldCourse = courseDAO.getCourseById(r.getCourseId());

                if (oldCourse.getSchedule().equals(newCourse.getSchedule())) {
                    return true;
                }
            }
        }
        return false;
    }

    // hủy môn học
    public boolean cancelCourse(String studentId,String courseId) {

        Registration r = registrationDAO.getRegistration(studentId,courseId);

        if (r == null) {
            return false;
        }

        registrationDAO.updateRegistrationStatus(r.getRegistrationId(),"CANCELLED");
        Course course = courseDAO.getCourseById(courseId);
        courseDAO.updateCurrentStudents(courseId,course.getCurrentStudents() - 1);

        return true;
    }
}
