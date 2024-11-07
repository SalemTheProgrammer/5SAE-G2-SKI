package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCourse") // Changez ici pour correspondre au nom de la colonne
	private Long idCourse;

	private int level;
	@Enumerated(EnumType.STRING)
	TypeCourse typeCourse;
	@Enumerated(EnumType.STRING)
	Support support;
	private Float price;
	private String name;





	@ManyToMany(mappedBy = "course") // Correspondance avec l'attribut `course` de Registration
	private List<Registration> users = new ArrayList<>();

	public static CourseBuilder builder() {
		return new CourseBuilder();
	}

	/*public static class CourseBuilder {
		private String name;
		private int level;
		private float price;
		private Support support;
		private TypeCourse typeCourse;
		private int timeSlot;

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
	}*/

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public TypeCourse getTypeCourse() {
		return typeCourse;
	}

	public void setTypeCourse(TypeCourse typeCourse) {
		this.typeCourse = typeCourse;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
