import { Pipe } from "@angular/core";

@Pipe({
    name: 'usuarioStatusPipe'
})
export class UsuarioStatusPipe{

    transform(status:boolean){
        if(status){
            return 'Ativo'
        }else {
            return 'Inativo'
        }

    }
}