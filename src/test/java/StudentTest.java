import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Student newStudent = new Student("Frank Leathers");
    newStudent.save();
    Student savedStudent = Student.all().get(0);
    assertTrue(savedStudent.equals(newStudent));
  }

  @Test
  public void save_assignsIdToObject() {
    Student myStudent = new Student ("Frank Leathers");
    myStudent.save();
    Student savedStudent = Student.all().get(0);
    assertEquals(myStudent.getId(), savedStudent.getId());
  }

  @Test
  public void find_findsStudentInDatabase_true() {
    Student myStudent = new Student ("Frank Leathers");
    myStudent.save();
    Student savedStudent = Student.find(myStudent.getId());
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
  public void addCourse_addsCourseToStudent() {
    Course myCourse = new Course("Intro to Java", "Java101");
    myCourse.save();

    Student myStudent = new Student("Frank Leathers");
    myStudent.save();

    myStudent.addCourse(myCourse);
    Course savedCourse = myStudent.getCourses().get(0);
    assertTrue(myCourse.equals(savedCourse));
  }
}
