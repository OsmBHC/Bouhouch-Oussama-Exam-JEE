import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientDTO } from '../models/client-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  public getClients(): Observable<Array<ClientDTO>> {
    return this.http.get<Array<ClientDTO>>(environment.backendHost + "/clients");
  }

  public searchClients(keyword: string): Observable<Array<ClientDTO>> {
    return this.http.get<Array<ClientDTO>>(environment.backendHost + "/clients?keyword=" + keyword);
  }

  public saveClient(client: ClientDTO): Observable<ClientDTO> {
    return this.http.post<ClientDTO>(environment.backendHost + "/clients", client);
  }

  public deleteClient(id: number) {
    return this.http.delete(environment.backendHost + "/clients/" + id);
  }
}