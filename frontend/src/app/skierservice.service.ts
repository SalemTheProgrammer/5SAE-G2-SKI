import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkierService {
  private apiUrl = 'http://localhost:8089/api/skier'; // Assurez-vous que cette URL est correcte

  constructor(private http: HttpClient) {}

  // Ajouter un skieur
  addSkier(skier: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/add`, skier);
  }

  // Ajouter un skieur et l'assigner à un cours
  addSkierAndAssignToCourse(skier: any, numCourse: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/addAndAssign/${numCourse}`, skier);
  }

  // Assigner un skieur à une subscription
  assignToSubscription(numSkier: number, numSub: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/assignToSub/${numSkier}/${numSub}`, {});
  }

  // Assigner un skieur à une piste
  assignToPiste(numSkier: number, numPiste: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/assignToPiste/${numSkier}/${numPiste}`, {});
  }

  // Récupérer les skieurs par type de souscription
  retrieveSkiersBySubscriptionType(typeSubscription: any): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getSkiersBySubscription?typeSubscription=${typeSubscription}`);
  }

  // Récupérer un skieur par ID
  getById(numSkier: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get/${numSkier}`);
  }

  // Supprimer un skieur par ID
  deleteById(numSkier: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${numSkier}`);
  }

  // Récupérer tous les skieurs
  getAllSkiers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/all`);
  }
}
