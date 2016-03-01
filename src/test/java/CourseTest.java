import org.junit.*;
import static org.junit.Assert.*;

public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Course.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Course newCourse = new Course("Intro to Java", "Java100");
    newCourse.save();
    Course savedCourse = Course.all().get(0);
    assertTrue(savedCourse.equals(newCourse));
  }

  @Test
  public void save_assignsIdToObject() {
    Course myCourse = new Course ("Intro to Java", "Java100");
    myCourse.save();
    Course savedCourse = Course.all().get(0);
    assertEquals(myCourse.getId(), savedCourse.getId());
  }

  @Test
  public void find_findsCourseInDatabase_true() {
    Course myCourse = new Course ("Intro to Java", "Java100");
    myCourse.save();
    Course savedCourse = Course.find(myCourse.getId());
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void addStudent_addsStudentToCourse() {
    Course myCourse = new Course("Intro to Java", "Java100");
    myCourse.save();

    Student myStudent = new Student("Frank Leathers");
    myStudent.save();

    myCourse.addStudent(myStudent);
    Student savedStudent = myCourse.getStudents().get(0);
    assertTrue(myStudent.equals(savedStudent));
  }
}
