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

  // @Test
  // public void addCategory_addsCategoryToStudent() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Student myStudent = new Student("Frank Leathers");
  //   myStudent.save();
  //
  //   myStudent.addCategory(myCategory);
  //   Category savedCategory = myStudent.getCategories().get(0);
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void getCategories_returnsAllCategories_List() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Student myStudent = new Student("Frank Leathers");
  //   myStudent.save();
  //
  //   myStudent.addCategory(myCategory);
  //   List savedCategories = myStudent.getCategories();
  //   assertEquals(1, savedCategories.size());
  // }
  //
  // @Test
  // public void delete_deletesAllStudentsAndListsAssociations_emptyList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Student myStudent = new Student("Frank Leathers");
  //   myStudent.save();
  //
  //   myStudent.addCategory(myCategory);
  //   myStudent.delete();
  //   assertEquals(0, myCategory.getStudents().size());
  // }
}
