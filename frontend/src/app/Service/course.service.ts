import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../Model/course';
@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = 'http://localhost:8089/course'; 

  constructor(private http: HttpClient) {}

  // Récupérer tous les cours
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/getall`);
  }

  // Ajouter un cours
  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(`${this.apiUrl}/add`, course);
  }

  // Mettre à jour un cours
  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/update`, course);
  }

 

  // Supprimer tous les cours
  deleteAllCourses(): Observable<Course[]> {
    return this.http.delete<Course[]>(`${this.apiUrl}/deleteAll`);
  }

  // Rechercher des cours par niveau, type et support
  findCourses(level: number, typeCourse: string, support: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/search?level=${level}&type=${typeCourse}&support=${support}`);
  }

  // Calculer le revenu total
  calculateTotalRevenue(idCourse: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${idCourse}/revenue`);
  }

  searchCourses(level: number, typeCourse: string, support: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/search`, {
      params: { level: level.toString(), typeCourse, support },
    });
  }

  // Méthode pour supprimer des cours
  deleteCourses(level: number, typeCourse: string, support: string): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/delete`, {
      params: { level: level.toString(), typeCourse, support },
    });
  }

  // Méthode pour assigner un cours à un utilisateur
  assignCourseToUser(idCourse: number, numRegistration: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/assign`, null, {
        params: { idCourse: idCourse.toString(), numRegistration: numRegistration.toString() },
    });
}


  // Méthode pour obtenir le revenu total d'un cours
  getTotalRevenue(numCourse: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${numCourse}/revenue`);
  }

  // Méthode pour rechercher par niveau et trier par prix
  getCoursesByLevel(level: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/by-level`, {
      params: { level },
    });
  }

  // Méthode pour rechercher par support et trier par créneau horaire
  getCoursesBySupport(support: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/by-support`, {
      params: { support },
    });
  }

  // Méthode pour rechercher par type de cours avec un prix minimum
  getCoursesByTypeAndMinPrice(typeCourse: string, minPrice: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/by-type`, {
      params: { typeCourse, minPrice: minPrice.toString() },
    });
  }
}
