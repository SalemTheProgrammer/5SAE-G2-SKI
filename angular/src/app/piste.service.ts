import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PisteService {

  private apiUrl = 'http://192.168.33.10:8089/api/piste'; 
  constructor(private http: HttpClient) {}

  addPiste(piste: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/add`, piste);
  }

  // Retrieve all Pistes
  getAllPistes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/all`);
  }

  updatePiste(piste: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/update`, piste);
  }
  
  // Delete Piste by ID
  deletePiste(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

 

}
