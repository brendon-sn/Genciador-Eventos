import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SelectItem } from 'primeng';
import { UsuarioModel } from 'src/app/usuarios/models/usuario.model';
import { UsuarioListagem } from 'src/app/usuarios/models/usuarioListagem.model';
import { CargoService } from 'src/app/service/cargo.service';
import { UsuarioService } from 'src/app/usuarios/service/usuario.service';

@Component({
  selector: 'app-usuarios-create',
  templateUrl: './usuarios-create.component.html',
  styleUrls: ['./usuarios-create.component.scss']
})
export class UsuariosCreateComponent implements OnInit {

  usuarios: UsuarioListagem[] = [];
  usuarioSelecionado: UsuarioListagem;
  form: FormGroup;
  formBuilder: FormBuilder = new FormBuilder;
  cargos: SelectItem[] = [];
  habilitador: boolean = false;
  usuarioCriar: UsuarioModel;
  constructor(private cargoService: CargoService, private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.criarFormulario();
    this.gerarListaDeCargos();
  }

  criarFormulario(): void {
    this.form = this.formBuilder.group({
      id: [''],
      nome: [''],
      cpf: [''],
      dataNascimento: [''],
      email: [''],
      telefone: [''],
      cargo: ['']
    });
  }


  gerarListaDeCargos(): void {
    this.cargoService.buscarTodos().subscribe((element: SelectItem[]) => this.cargos = [{ label: 'Selecione o cargo', value: null } as SelectItem].concat(element))
  }  

  mostrarLista(): void{
    this.usuarioService.buscarUsuariosPorStatus(false).subscribe(element => this.usuarios = element)
    this.habilitador = true;
  }

  ativarUsuario(): void{
    this.usuarioService.ativarUsuario(this.usuarioSelecionado.id).subscribe();
    this.habilitador = false;
  }

  criarUsuario(): void{
   this.usuarioCriar = this.form.getRawValue();
   this.usuarioCriar.cargo = {value: this.form.get('cargo').value}
   this.usuarioCriar.status = true;
   console.log(this.usuarioCriar)
   this.usuarioService.criarUsuario(this.usuarioCriar).subscribe((element) => {
     this.usuarioCriar = element;
    this.criarFormulario();
    this.gerarListaDeCargos();
    })
  }
}
