package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Data
@Table(name = "course")
public class Course implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long idCourse;


	private int level;
	@Enumerated(EnumType.STRING)
	TypeCourse typeCourse;
	@Enumerated(EnumType.STRING)
	Support support;
	private Float price;
	private String name ;
	private int timeSlot;

	@JsonIgnore
	@OneToMany(mappedBy= "course")
	Set<Registration> registrations;


	public static CourseBuilder builder() {
		return new CourseBuilder();
	}

	public static class CourseBuilder {
		private String name;
		private int level;
		private float price;
		private Support support;
		private TypeCourse typeCourse;

		public CourseBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CourseBuilder level(int level) {
			this.level = level;
			return this;
		}

		public CourseBuilder price(float price) {
			this.price = price;
			return this;
		}

		public CourseBuilder support(Support support) {
			this.support = support;
			return this;
		}

		public CourseBuilder typeCourse(TypeCourse typeCourse) {
			this.typeCourse = typeCourse;
			return this;
		}

		public Course build() {
			Course course = new Course();
			course.name = this.name;
			course.level = this.level;
			course.price = this.price;
			course.support = this.support;
			course.typeCourse = this.typeCourse;
			return course;
		}
	}
}
