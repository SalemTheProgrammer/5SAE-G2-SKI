package tn.esprit.spring.services;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;

import java.util.List;

public interface ICourseServices {

    List<Course> retrieveAllCourses();
    List<Course> deleteAllCourses();

     Float calculateTotalRevenue(Long idCourse) ;
    Course assignCourseToUser(Long idCourse, Long numRegistration) ;
    Course  addCourse(Course  course);
     List<Course> findCoursesByLevelSortedByPrice(int level) ;
  //   List<Course> findCoursesBySupportSortedByTimeSlot(String support) ;
     List<Course> findCoursesByTypeAndMinPrice(String typeCourse, double minPrice) ;
        Course updateCourse(Course course);

    Course retrieveCourse(Long idCourse);

    List<Course> findCoursesByLevelTypeSupport(int level, TypeCourse typeCourse, Support support);
    void deleteCourses(int level, TypeCourse typeCourse, Support support);

}
