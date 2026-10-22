CREATE DATABASE CourseRegistrationDB;

USE CourseRegistrationDB;
GO

--Các khóa học
CREATE TABLE Courses (
    courseId VARCHAR(10) PRIMARY KEY,
    courseName NVARCHAR(100) NOT NULL,
    credits INT NOT NULL CHECK (credits > 0),
    schedule NVARCHAR(100) NOT NULL,
    instructor NVARCHAR(50) NOT NULL,
    maxStudents INT NOT NULL CHECK (maxStudents > 0),
    currentStudents INT DEFAULT 0 CHECK (currentStudents >= 0)
);

--Học sinh
CREATE TABLE Students (
    studentId VARCHAR(10) PRIMARY KEY,
    fullName NVARCHAR(50) NOT NULL,
    major NVARCHAR(30) NOT NULL,
    semester INT NOT NULL CHECK (semester > 0)
);

--Đăng kí
CREATE TABLE Registrations (
    registrationId INT IDENTITY(1,1) PRIMARY KEY,
    studentId VARCHAR(10) NOT NULL,
    courseId VARCHAR(10) NOT NULL,
    registrationDate DATETIME DEFAULT GETDATE(),
    status VARCHAR(20) NOT NULL CHECK (status IN ('REGISTERED', 'WAITING', 'CANCELLED')),
    CONSTRAINT FK_Registration_Student FOREIGN KEY (studentId) REFERENCES Students(studentId),
    CONSTRAINT FK_Registration_Course FOREIGN KEY (courseId) REFERENCES Courses(courseId)
);

--Danh sách chờ đăng ký
CREATE TABLE WaitingList (
    waitingId INT IDENTITY(1,1) PRIMARY KEY,
    studentId VARCHAR(10) NOT NULL,
    courseId VARCHAR(10) NOT NULL,
    registeredDate DATETIME DEFAULT GETDATE(),
    priority INT NOT NULL,
    CONSTRAINT FK_WaitingList_Student FOREIGN KEY (studentId) REFERENCES Students(studentId),
    CONSTRAINT FK_WaitingList_Course FOREIGN KEY (courseId) REFERENCES Courses(courseId)
);

--thêm dữ liệu
INSERT INTO Students (studentId, fullName, major, semester) VALUES
('S001', 'Nguyen Van A', 'Computer Science', 3),
('S002', 'Tran Thi B', 'Information Technology', 2),
('S003', 'Le Van C', 'Software Engineering', 4);

INSERT INTO Courses (courseId, courseName, credits, schedule, instructor, maxStudents, currentStudents) VALUES
('CS101', 'Introduction to Programming', 3, 'Mon 13:00-15:00, Wed 13:00-15:00', 'Dr. Smith', 30, 0),
('CS102', 'Data Structures', 4, 'Tue 09:00-11:00, Thu 09:00-11:00', 'Prof. Johnson', 25, 0),
('CS103', 'Database Systems', 3, 'Mon 15:00-17:00, Wed 15:00-17:00', 'Dr. Williams', 20, 0),
('CS104', 'Web Development', 3, 'Tue 13:00-15:00, Thu 13:00-15:00', 'Dr. Brown', 15, 0),
('CS105', 'Software Engineering', 4, 'Fri 09:00-12:00', 'Prof. Davis', 35, 0);

Select * From Courses

Select * From Students

Select * From Registrations

Select * From WaitingList
