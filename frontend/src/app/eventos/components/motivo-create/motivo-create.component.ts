import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { element } from 'protractor';
import { Motivo } from 'src/app/models/motivo.model';
import { MotivoService } from 'src/app/service/motivo.service';

@Component({
  selector: 'app-motivo-create',
  templateUrl: './motivo-create.component.html',
  styleUrls: ['./motivo-create.component.css']
})
export class MotivoCreateComponent implements OnInit {

  constructor(private motivoSvc: MotivoService) { }

  motivoForm: FormGroup;
  formBuilder: FormBuilder = new FormBuilder();
  motivos: Motivo[] = [];
  motivoSelecionado: Motivo;

  ngOnInit(): void {
    this.criarForm();
    this.listarMotivos();

  }

  criarForm(): void{
    this.motivoForm = this.formBuilder.group({
      id: [''],
      motivo: [''],
      descricao: ['']
    })
  }

  criaMotivo():void {
    this.motivoSvc.criarMotivo(this.motivoForm.getRawValue()).subscribe(()=>{
      this.criarForm();
      this.listarMotivos();
    });

  }

  listarMotivos(): void{
    this.motivoSvc.listarMotivos().subscribe(element => this.motivos = element)

  }

  deletarMotico(): void{
    this.motivoSvc.deletarMotivo(this.motivoSelecionado.id).subscribe(() => {
      this.listarMotivos();
    });
  }

}
