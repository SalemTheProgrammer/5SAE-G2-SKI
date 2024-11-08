import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Instructor } from './instructor.model';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {
  private baseUrl = 'http://localhost:8089/api/instructor'; // Ajustez l'URL de votre API

  constructor(private http: HttpClient) { }

  addInstructor(instructor: Instructor): Observable<Instructor> {
    return this.http.post<Instructor>(`${this.baseUrl}/add`, instructor);
  }

  updateInstructor(instructor: Instructor): Observable<Instructor> {
    return this.http.put<Instructor>(`${this.baseUrl}/update`, instructor);
  }

  getInstructors(): Observable<Instructor[]> {
    return this.http.get<Instructor[]>(`${this.baseUrl}/all`);
  }

  deleteInstructor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
