import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreditDTO } from '../models/credit-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CreditService {

  constructor(private http: HttpClient) { }

  public getCredits(page: number, size: number): Observable<CreditDTO[]> {
    return this.http.get<CreditDTO[]>(environment.backendHost + "/credits?page=" + page + "&size=" + size);
  }

  public getCreditById(id: number): Observable<CreditDTO> {
    return this.http.get<CreditDTO>(environment.backendHost + "/credits/" + id);
  }

  public createCredit(credit: CreditDTO): Observable<CreditDTO> {
    return this.http.post<CreditDTO>(environment.backendHost + "/credits", credit);
  }

  public updateCredit(id: number, credit: CreditDTO): Observable<CreditDTO> {
    return this.http.put<CreditDTO>(environment.backendHost + "/credits/" + id, credit);
  }

  public deleteCredit(id: number) {
    return this.http.delete(environment.backendHost + "/credits/" + id);
  }

  public getCreditsByClientId(clientId: number): Observable<CreditDTO[]> {
    return this.http.get<CreditDTO[]>(environment.backendHost + "/credits/client/" + clientId);
  }
}