import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Cliente, ClienteService, Tarjeta } from './services/cliente.service';
import { CommonModule } from '@angular/common';


@Component({
    standalone: true,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
 imports: [CommonModule, ReactiveFormsModule, FormsModule]
})
export class AppComponent implements OnInit {

  clientes: Cliente[] = [];
  formulario: FormGroup;
tarjetaEnEdicionId: number | null = null;
nuevoCupoTotal: number = 0;

  constructor(
    private clienteService: ClienteService,
    private fb: FormBuilder,
    
  ) {
    this.formulario = this.fb.group({
      identificacion: ['', Validators.required],
      nombreCompleto: ['', Validators.required],
      correoElectronico: ['', [Validators.required, Validators.email]],
      numero: ['', Validators.required],
      fechaVencimiento: ['', Validators.required],
      cupoTotal: [0, Validators.required],
      cupoDisponible: [0, Validators.required],
    });
  }

  formularioSubmitted = false;

  ngOnInit(): void {
    this.cargarClientes();
  }

cargarClientes(): void {
  this.clienteService.getClientes().subscribe({
    next: data => {
      console.log('📦 Clientes cargados:', data); // 👈 verifica aquí
      this.clientes = data;
    },
    error: err => console.error('❌ Error al cargar clientes:', err)
  });
}
  registrarCliente(): void {
    const form = this.formulario.value;

    const cliente: Cliente = {
      identificacion: form.identificacion,
      nombre: form.nombreCompleto,
      email: form.correoElectronico,
      tarjetas: [{
        numero: form.numero,
        fechaVencimiento: form.fechaVencimiento,
        cupoTotal: form.cupoTotal,
        cupoDisponible: form.cupoDisponible
      }]
    };

    this.clienteService.registrarCliente(cliente).subscribe({
      next: () => {
        this.formulario.reset();
        this.cargarClientes();
      },
      error: err => console.error('Error registrando cliente', err)
    });
  }

editarCupo(tarjeta: Tarjeta): void {
  this.tarjetaEnEdicionId = tarjeta.id!;
  this.nuevoCupoTotal = tarjeta.cupoTotal;
}

guardarCupoAlEnter(tarjeta: Tarjeta): void {
  this.clienteService.editarCupoTotal(tarjeta.id!, this.nuevoCupoTotal).subscribe({
    next: () => {
      this.tarjetaEnEdicionId = null;
      this.cargarClientes(); // recarga la tabla
    },
    error: err => console.error('❌ Error al actualizar cupo', err)
  });
}

eliminarTarjeta(tarjeta: Tarjeta): void {
  if (confirm('¿Estás seguro de eliminar esta tarjeta?')) {
    this.clienteService.eliminarTarjeta(tarjeta.id!).subscribe({
      next: () => this.cargarClientes(),
      error: err => console.error('Error eliminando tarjeta', err)
    });
  }
}

}