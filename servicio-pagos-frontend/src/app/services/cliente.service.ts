import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Tarjeta {
  id?: number;
  numero: string;
  fechaVencimiento: string;
  franquicia?: string;
  estado?: string;
  cupoTotal: number;
  cupoDisponible: number;
  cupoUtilizado?: number;
}

export interface Cliente {
  id?: number;
  identificacion: string;
  email: string;
  nombre: string;
  estado?: string;
  tarjetas: Tarjeta[];
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  private jsonHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  // 🔹 Obtener todos los clientes
  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseUrl}/clientes`);
  }

  // 🔹 Registrar un nuevo cliente con tarjetas
  registrarCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.baseUrl}/clientes`, cliente, this.jsonHeaders);
  }

  // 🔹 Editar solo el cupo total de una tarjeta
editarCupoTotal(id: number, cupoTotal: number) {
  const body = { cupo_total: cupoTotal };
  return this.http.patch(`http://localhost:8080/api/tarjetas/${id}/cupoTotal`, body);
}

  // 🔹 Eliminar lógicamente una tarjeta (estado → INACTIVO)
  eliminarTarjeta(idTarjeta: number): Observable<any> {
    return this.http.patch(`${this.baseUrl}/tarjetas/${idTarjeta}/estado`, {
      estado: 'INACTIVO'
    }, this.jsonHeaders);
  }


}