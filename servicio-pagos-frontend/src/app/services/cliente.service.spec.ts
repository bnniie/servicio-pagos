import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClienteService, Cliente } from './cliente.service';

describe('ClienteService', () => {
  let service: ClienteService;
  let httpMock: HttpTestingController;

  const dummyClientes: Cliente[] = [
    {
      id: 1,
      identificacion: '123456789',
      email: 'cliente1@example.com',
      nombre: 'Cliente Uno',
      estado: 'ACTIVO',
      tarjetas: []
    },
    {
      id: 2,
      identificacion: '987654321',
      email: 'cliente2@example.com',
      nombre: 'Cliente Dos',
      estado: 'ACTIVO',
      tarjetas: []
    }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClienteService]
    });
    service = TestBed.inject(ClienteService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('debería obtener todos los clientes', () => {
    service.getClientes().subscribe((clientes) => {
      expect(clientes.length).toBe(2);
      expect(clientes).toEqual(dummyClientes);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/clientes');
    expect(req.request.method).toBe('GET');
    req.flush(dummyClientes);
  });

  it('debería registrar un nuevo cliente', () => {
    const nuevoCliente: Cliente = {
      identificacion: '111222333',
      email: 'nuevo@example.com',
      nombre: 'Nuevo Cliente',
      tarjetas: []
    };

    service.registrarCliente(nuevoCliente).subscribe((response) => {
      expect(response).toEqual(nuevoCliente);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/clientes');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(nuevoCliente);
    req.flush(nuevoCliente);
  });

  it('debería editar el cupo total de una tarjeta', () => {
    const tarjetaId = 5;
    const nuevoCupo = 5000;

    service.editarCupoTotal(tarjetaId, nuevoCupo).subscribe((response) => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`http://localhost:8080/api/tarjetas/${tarjetaId}/cupoTotal`);
    expect(req.request.method).toBe('PATCH');
    expect(req.request.body).toEqual({ cupo_total: nuevoCupo });
    req.flush({ success: true });
  });

  it('debería eliminar lógicamente una tarjeta', () => {
    const tarjetaId = 10;

    service.eliminarTarjeta(tarjetaId).subscribe((response) => {
      expect(response).toEqual({ success: true });
    });

    const req = httpMock.expectOne(`http://localhost:8080/api/tarjetas/${tarjetaId}/estado`);
    expect(req.request.method).toBe('PATCH');
    expect(req.request.body).toEqual({ estado: 'INACTIVO' });
    req.flush({ success: true });
  });
});