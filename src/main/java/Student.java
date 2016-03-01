import java.util.*;
import org.sql2o.*;

public class Student {
  private int id;
  private String name;
  private Date enroll_date;

  public Student(String name) {
    this.name = name;
    this.enroll_date = new Date();
  }

//getters
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Date enroll_date() {
    return enroll_date;
  }

//database
  public static List<Student> all() {
    String sql = "SELECT * FROM students";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students (name, enroll_date) values (:name, :enroll_date)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("enroll_date", enroll_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static Student find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
    }
  }

  public void addCourse(Course course) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students_courses (student_id, course_id) VALUES (:student_id, :course_id)";
      con.createQuery(sql)
      .addParameter("student_id", id)
      .addParameter("course_id", course.getId())
      .executeUpdate();
    }
  }

  @Override
    public boolean equals(Object otherStudent){
      if (!(otherStudent instanceof Student)) {
        return false;
      } else {
        Student newStudent = (Student) otherStudent;
        return this.getName().equals(newStudent.getName()) &&
               this.getId() == newStudent.getId();
      }
    }
}
