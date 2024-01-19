package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DBConnectionProvider;
import com.example.studentlessonservlet.model.Student;
import com.example.studentlessonservlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    LessonManager lessonManager = new LessonManager();
    UserManager userManager = new UserManager();

    public void add(Student student) {
        String sql = "insert into student(name,surname,email,age,lesson_id,pic_name,user_id) values (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPicName());
            preparedStatement.setInt(7, student.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                student.setId(id);
            }

        } catch (SQLException e) {

        }
    }

    public List<Student> getAll() {
        List<Student> result = new ArrayList<>();
        String sql = "select * from student";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                int lessonId = resultSet.getInt("lesson_id");
                String picName = resultSet.getString("pic_name");
                int user_id = resultSet.getInt("user_id");
                result.add(Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonManager.getById(lessonId))
                        .picName(picName)
                        .user(userManager.getById(user_id))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        String sql = "delete from student where id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public List<Student> getByUser(User user) {
        List<Student> result = new ArrayList<>();
        String sql = "select * from student where user_id="+user.getId();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                int lessonId = resultSet.getInt("lesson_id");
                String picName = resultSet.getString("pic_name");
                int user_id = resultSet.getInt("user_id");
                result.add(Student.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonManager.getById(lessonId))
                        .picName(picName)
                        .user(userManager.getById(user_id))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Student getByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    int age = resultSet.getInt("age");
                    int lessonId = resultSet.getInt("lesson_id");
                    String picName = resultSet.getString("pic_name");
                    int userId = resultSet.getInt("user_id");

                    return Student.builder()
                            .id(id)
                            .name(name)
                            .surname(surname)
                            .email(email)
                            .age(age)
                            .lesson(lessonManager.getById(lessonId))
                            .picName(picName)
                            .user(userManager.getById(userId))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
