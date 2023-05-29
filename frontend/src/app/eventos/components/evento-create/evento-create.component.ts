import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Select } from 'src/app/models/select.model';
import { MotivoService } from 'src/app/service/motivo.service';
import { UsuarioService } from 'src/app/usuarios/service/usuario.service';
import { Evento } from '../../models/evento.model';
import { EventoService } from '../../services/evento.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-evento-create',
  templateUrl: './evento-create.component.html',
  styleUrls: ['./evento-create.component.scss']
})
export class EventoCreateComponent implements OnInit {

  constructor(
    private usuarioSvc: UsuarioService,
    private motivoSvc: MotivoService,
    private eventoSvc: EventoService,
    private messageSvc: MessageService,
    private router: Router
  ) { }

  usuariosAtivos: Select[] = [];
  usuariosEvento: Select[] = [];
  motivos: Select[];
  minDateValue: Date;
  form: FormGroup;
  formBuilder: FormBuilder = new FormBuilder;
  dadosEvento: Evento;

  ngOnInit(): void {
    this.criarFormulario();
    this.minDateValue = new Date();
    this.motivoSvc.buscarTodos().subscribe(element => this.motivos = element);
    this.usuarioSvc.buscarUsuariosPorStatus(true).subscribe(element => {
      element.forEach(usuario => { this.usuariosAtivos.push({ value: usuario.id, label: usuario.nome }) });
    });
  }

  criarFormulario(): void {
    this.form = this.formBuilder.group({
      id: [''],
      nome: ['', Validators.required, Validators.minLength(5)],
      dataEvento: ['', Validators.required],
      justificativa: [''],
      valor: ['', Validators.required, Validators.minLength(2)],
      motivo: ['', Validators.required],
      situacao: [''],
      usuario: [''],
    })
  }

  criarEvento(): void {
    this.dadosEvento = this.form.getRawValue();
    if (this.usuariosEvento.length > 0) {
      this.dadosEvento.motivo = { value: this.form.get('motivo').value }
      this.dadosEvento.situacao = { value: 1 }
      this.dadosEvento.usuario = this.usuariosEvento;
      this.eventoSvc.criarEvento(this.dadosEvento).subscribe(() => console.log("teste"));
    }
    else if(!this.form.valid){
      this.messageSvc.add({severity: 'error', summary:'Não foi possível criar', detail:'Existem campos inválidos'});
    }
    else{
      this.messageSvc.add({severity:'error', summary:'Não foi possível criar', detail:'Selecione ao menos um patrocinador'});
    }
  }
}
