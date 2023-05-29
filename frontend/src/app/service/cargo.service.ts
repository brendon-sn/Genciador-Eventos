import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Select } from '../models/select.model';

@Injectable({
  providedIn: 'root'
})
export class CargoService {
  baseUrl: string = environment.apiUrl + '/cargo'

  constructor(private httpClient: HttpClient) { }

  buscarTodos(): Observable<Select[]>{
    const url: string = `${this.baseUrl}`;
    return this.httpClient.get<Select[]>(url);
  }
}
