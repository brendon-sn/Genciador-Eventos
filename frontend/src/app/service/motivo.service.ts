import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Motivo } from '../models/motivo.model';
import { Select } from '../models/select.model';

@Injectable({
  providedIn: 'root'
})
export class MotivoService {
  baseUrl: string = environment.apiUrl + '/motivo'

  constructor(private httpClient: HttpClient) { }

  listarMotivos(): Observable<Motivo[]>{
    const url = `${this.baseUrl}`
    return this.httpClient.get<Motivo[]>(url)
  }


  buscarTodos(): Observable<Select[]>{
    const url: string = `${this.baseUrl}/select`;
    return this.httpClient.get<Select[]>(url);
  }

  criarMotivo(motivo: Motivo): Observable<Motivo> {
    const url = this.baseUrl;
    return this.httpClient.post<Motivo>(url, motivo);
  }

  deletarMotivo(id: number): Observable<void>{
    const url = `${this.baseUrl}/${id}`;
    return this.httpClient.delete<void>(url);
  }
}
