package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;

import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseRestController {
    
    private final ICourseServices courseServices;

    @Operation(description = "Add Course")
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return  courseServices.addCourse(course);
    }

    @Operation(description = "Retrieve all Courses")
    @GetMapping("/all")
    public List<Course> getAllCourses(){
        return courseServices.retrieveAllCourses();
    }

    @Operation(description = "Delete all Courses")
    @GetMapping("/deleteAll")
    public List<Course> deleteAllCourse(){
        return courseServices.deleteAllCourses();
    }

    @Operation(description = "Update Course ")
    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course course){
        return  courseServices.updateCourse(course);
    }

    @Operation(description = "Retrieve Course by Id")
    @GetMapping("/get/{id-course}")
    public Course getById(@PathVariable("id-course") Long numCourse){
        return courseServices.retrieveCourse(numCourse);
    }

    @Operation(description = "Retrieve Courses by Level, Type, and Support")
    @GetMapping("/search")
    public List<Course> getCoursesByLevelTypeSupport(
            @RequestParam int level,
            @RequestParam TypeCourse typeCourse,
            @RequestParam Support support) {
        return courseServices.findCoursesByLevelTypeSupport(level, typeCourse, support);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourses(
            @RequestParam int level,
            @RequestParam TypeCourse typeCourse,
            @RequestParam Support support) {

        courseServices.deleteCourses(level, typeCourse, support);
        return ResponseEntity.ok("Courses deleted successfully");
    }

    @GetMapping("/{numCourse}/revenue")
    public ResponseEntity<Float> getTotalRevenue(@PathVariable Long numCourse) {
        Float totalRevenue = courseServices.calculateTotalRevenue(numCourse);
        return ResponseEntity.ok(totalRevenue);
    }

}
