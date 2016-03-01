import java.util.*;
import org.sql2o.*;

public class Student {
  private int id;
  private String name;
  private Date enroll_date;

  public int GetId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Date enroll_date() {
    return enroll_date;
  }

  public Student(String name, Date date) {
    this.name = name;
    this.enroll_date = date;
  }

  public static List<Student> all() {
    String sql = "SELECT * FROM students";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO tasks (description) VALUES (:description)";
  //     con.createQuery(sql)
  //       .addParameter("description", description)
  //       .executeUpdate();
  //   }
  // }
}
