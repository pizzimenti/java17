import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("courses", Course.all());
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("courses", Course.all());

      int selectedCourse = Integer.parseInt(request.queryParams("course"));
      List<Student> studentsByCourse = Course.getStudents(selectedCourse);
      String courseName = Course.find(selectedCourse).getName();

      model.put("listCourseName", courseName);
      model.put("students", studentsByCourse);
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/student/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("courses", Course.all());
      model.put("template", "templates/student.vtl");

      Student student = Student.find(Integer.parseInt(request.params(":id")));
      List<Course> courseList = student.getCourses();
      model.put("student", student);
      model.put("courseList", courseList);
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/add-course", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("courses", Course.all());
      model.put("template", "templates/add-course.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/add-course", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-course.vtl");

      String courseName = request.queryParams("course");
      String courseNumber = request.queryParams("courseNumber");
      Course newCourse = new Course(courseName, courseNumber);
      newCourse.save();
      model.put("students", Student.all());
      model.put("courses", Course.all());

      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
  }
}
