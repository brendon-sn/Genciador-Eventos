import { Select } from "../../models/select.model";

export class UsuarioModel{
    public id : number;
    public nome : string;
    public cpf : string;
    public dataNascimento : Date;
    public email : string;
    public telefone : string;
    public status : boolean;
    public cargo : Select;
}