import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/Model/course';
import { CourseService } from 'src/app/Service/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  courses: Course[] = [];
  selectedCourse: Course | null = null;
  numRegistration = 52; // Id d'inscription par défaut

  constructor(private courseService: CourseService) {}
   
  isEditing: boolean = false; // Indique si un cours est en cours de modification

  ngOnInit(): void {
    this.getCourses();
  }

  getCourses(): void {
   

    this.courseService.getAllCourses().subscribe(
      (data) => {
          this.courses = data;
      },
      (error: HttpErrorResponse) => {
          console.error('Erreur lors de la récupération des cours :', error);
      }
  );
  }
  


  deleteAllCourses(): void {
    this.courseService.deleteAllCourses().subscribe((data) => {
      this.courses = data;
    });
  }

  // Sélectionner un cours pour l'édition
  selectCourse(course: Course) {
    this.selectedCourse = { ...course }; // Créer une copie pour l'édition
  }




  // Méthode pour sélectionner un cours à modifier
  editCourse(course: Course): void {
    this.selectedCourse = { ...course }; // Crée une copie pour éviter de modifier directement l'objet d'origine
    this.isEditing = true; // Active le mode édition
  }

  updateCourse() {
    if (this.selectedCourse) {
      this.courseService.updateCourse(this.selectedCourse).subscribe(
        response => {
          console.log('Cours mis à jour avec succès', response);
          // Réinitialisez ou faites d'autres actions nécessaires après la mise à jour
          this.selectedCourse = null; // Optionnel : réinitialisez la sélection
        },
        error => {
          console.error('Erreur lors de la mise à jour du cours:', error);
          alert('Erreur lors de la mise à jour du cours: ' + (error.error.message || 'Erreur inconnue.'));
        }
      );
    } else {
      alert('Aucun cours sélectionné pour la mise à jour.');
    }
  }
  
  

  // Méthode pour supprimer un cours
  deleteCourse(idCourse: number): void {
    this.courseService.deleteCourses(0, '', '').subscribe(
      response => {
        console.log('Cours supprimé:', response);
        this.getCourses(); // Recharge la liste après la suppression
      },
      error => {
        console.error('Erreur lors de la suppression du cours:', error);
      }
    );
  }

  assignCourseToUser(idCourse: number): void {
    const registrationId = 52; // Utilisez le registrationId par défaut ici
    this.courseService.assignCourseToUser(idCourse, registrationId).subscribe(
        response => {
            console.log('Response from server:', response);
            if (response === 'Course assigned to user successfully') {
                console.log('Course assigned successfully');
                // Mettez à jour l'interface utilisateur si nécessaire
            } else {
                console.error('Unexpected response:', response);
            }
        },
        error => {
            console.error('Erreur lors de l\'assignation du cours: ', error);
        }
    );
}


  

  // Méthode pour annuler la sélection du cours
  cancelEdit(): void {
    this.isEditing = false; // Désactive le mode édition
    this.selectedCourse = null; // Réinitialisez si nécessaire
  }
 

}
