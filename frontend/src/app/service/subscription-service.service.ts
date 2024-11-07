import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private apiUrl = 'http://192.168.33.10:8089/api/subscription';

  constructor(private http: HttpClient) {}

  // Add Subscription
  addSubscription(subscription: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/add`, subscription);
  }

  // Retrieve Subscription by ID
  getSubscriptionById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get/${id}`);
  }

  // Retrieve Subscriptions by Type
  getSubscriptionsByType(type: any): Observable<Set<any>> {
    return this.http.get<Set<any>>(`${this.apiUrl}/all/${type}`);
  }

  // Update Subscription
  updateSubscription(subscription: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update`, subscription);
  }

  getSubscriptionsByDates(startDate: string, endDate: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/all/${startDate}/${endDate}`);
  }
}
