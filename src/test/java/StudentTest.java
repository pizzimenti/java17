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

  // @Test
  // public void save_assignsIdToObject() {
  //   Task myTask = new Task ("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }
  //
  // @Test
  // public void find_findsTaskInDatabase_true() {
  //   Task myTask = new Task ("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertTrue(myTask.equals(savedTask));
  // }
  //
  // @Test
  // public void addCategory_addsCategoryToTask() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //
  //   myTask.addCategory(myCategory);
  //   Category savedCategory = myTask.getCategories().get(0);
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void getCategories_returnsAllCategories_List() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //
  //   myTask.addCategory(myCategory);
  //   List savedCategories = myTask.getCategories();
  //   assertEquals(1, savedCategories.size());
  // }
  //
  // @Test
  // public void delete_deletesAllTasksAndListsAssociations_emptyList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //
  //   myTask.addCategory(myCategory);
  //   myTask.delete();
  //   assertEquals(0, myCategory.getTasks().size());
  // }
}
