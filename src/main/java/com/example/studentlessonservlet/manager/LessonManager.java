package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DBConnectionProvider;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    UserManager userManager = new UserManager();
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Lesson lesson) {
        String sql = "insert into lesson(name,duration,lecturerName,price,user_id) values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setDouble(5, lesson.getUser().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getLessons() {
        List<Lesson> result = new ArrayList<>();
        String sql = "select * from lesson";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getString("duration"))
                        .lecturerName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Lesson> getLessonByUser(User user) {
        List<Lesson> result = new ArrayList<>();
        String sql = "select * from lesson where user_id="+user.getId();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getString("duration"))
                        .lecturerName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Lesson getById(int id) {
        String sql = "select * from lesson  where id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getString("duration"))
                        .lecturerName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Lesson getByName(String name) {
        String sql = "SELECT * FROM lesson WHERE name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Lesson.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .duration(resultSet.getString("duration"))
                            .lecturerName(resultSet.getString("lecturerName"))
                            .price(resultSet.getDouble("price"))
                            .user(userManager.getById(resultSet.getInt("user_id")))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(int id) {
        String sql = "delete from lesson where id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
