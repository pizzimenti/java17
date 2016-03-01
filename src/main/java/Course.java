import java.util.*;
import org.sql2o.*;

public class Course {
  private int id;
  private String course_name;
  private String course_number;

  public Course(String course_name, String course_number) {
    this.course_name = course_name;
    this.course_number = course_number;
  }

//getters
  public int getId() {
    return id;
  }

  public String getName() {
    return course_name;
  }

  public String course_number() {
    return course_number;
  }

  @Override
    public boolean equals(Object otherCourse){
      if (!(otherCourse instanceof Course)) {
        return false;
      } else {
        Course newCourse = (Course) otherCourse;
        return this.getName().equals(newCourse.getName()) &&
               this.getId() == newCourse.getId();
      }
    }

//database
  public static List<Course> all() {
    String sql = "SELECT * FROM courses";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO courses (course_name, course_number) VALUES (:course_name, :course_number)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("course_name", course_name)
      .addParameter("course_number", course_number)
      .executeUpdate()
      .getKey();
    }
  }

  public static Course find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM courses WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class);
    }
  }

  public void addStudent(Student student) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students_courses (student_id, course_id) VALUES (:student_id, :course_id)";
      con.createQuery(sql)
        .addParameter("student_id", student.getId())
        .addParameter("course_id", id)
        .executeUpdate();
      }
    }

  // public void addCourse(Course course) {
  //
  // }
}
