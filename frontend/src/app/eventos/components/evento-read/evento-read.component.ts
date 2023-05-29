import { Component, OnInit } from '@angular/core';
import { Evento } from '../../models/evento.model';
import { EventoListagem } from '../../models/eventoListagem.model';
import { EventoService } from '../../services/evento.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-evento-read',
  templateUrl: './evento-read.component.html',
  styleUrls: ['./evento-read.component.scss']
})
export class EventoReadComponent implements OnInit {

  eventos: EventoListagem[] = [];
  eventoCompleto: Evento;
  listaSemEventoSelecionado: EventoListagem[] = [];
  eventoTroca: EventoListagem;
  mostrarEvento: boolean = false;
  isEventoAtivo: boolean = true;


  constructor(
    private eventoService: EventoService,
    private confirmationService: ConfirmationService
  ) { }

  buscaForm: FormGroup;
  formBuilder: FormBuilder = new FormBuilder();

  br: any;

  ngOnInit(): void {
    this.eventoService.filter({ 'situacao': 'Em Espera' }).subscribe(element => this.eventos = element);
    this.criarForms();
    this.localizacaoCalendario();
  }

  localizacaoCalendario(): void {
    this.br = {
      firstDayOfWeek: 0,
      dayNames: ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
      dayNamesShort: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
      dayNamesMin: ["D", "S", "T", "Q", "Q", "S", "S"],
      monthNames: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
      monthNamesShort: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"],
      today: 'Hoje',
      clear: 'Limpar',
      dateFormat: 'dd/mm/yy',
      weekHeader: 'Sm'
    };
  }

  criarForms(): void {
    this.buscaForm = this.formBuilder.group({
      nome: [''],
      data: [''],
      motivo: [''],
      situacao: [''],
      usuario: ['']
    })
  }

  mostrar(id: number): void {
    this.eventoService.mostrarPorId(id).subscribe(element => {
      this.eventoCompleto = element;
      this.listaSemEventoSelecionado = this.eventos.filter(evento => evento.id != id);
      this.isEventoAtivo = this.eventoCompleto.situacao.value == 1 ? true : false;
      this.eventoTroca = undefined;
      this.mostrarEvento = true;
    });
  }

  filtrar(): void {
    var dadosForm = this.buscaForm.getRawValue();
    if (dadosForm.data != "" && dadosForm.data != null) {
      dadosForm.data = this.buscaForm.get('data').value.toJSON().split('T')[0];
    }
    this.eventoService.filter(dadosForm).subscribe(element => this.eventos = element);
  }

  trocar(id: number): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja trocar entre os dois eventos?',
      accept: () => {
        this.eventoService.trocarEventos(id, this.eventoTroca.id).subscribe(() => {
          this.mostrarEvento = false
          this.eventoService.filter({ 'situacao': 'Em Espera' }).subscribe(element => this.eventos = element);
        });
      }
    });

  }
  adiar(id: number): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja adiar o evento?',
      accept: () => {
        this.eventoService.adiarEvento(id).subscribe(() => {
          this.mostrarEvento = false
          this.eventoService.filter({ 'situacao': 'Em Espera' }).subscribe(element => this.eventos = element);
        });
      }
    });
  }
  cancelar(id: number): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja cancelar este evento?',
      accept: () => {
        this.eventoService.cancelarEvento(id).subscribe(() => {
          this.mostrarEvento = false
          this.eventoService.filter({ 'situacao': 'Em Espera' }).subscribe(element => this.eventos = element);
        });
      }
    });
  }

}
