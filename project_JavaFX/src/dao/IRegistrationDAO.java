package dao;

import java.util.ArrayList;
import model.Registration;

public interface IRegistrationDAO {

    ArrayList<Registration> getAllRegistrations();

    ArrayList<Registration> getRegistrationsByStudent(String studentId);

    Registration getRegistration(String studentId, String courseId);

    boolean insertRegistration(Registration registration);

    boolean updateRegistrationStatus(int registrationId, String status);

    boolean deleteRegistration(String studentId, String courseId);

}