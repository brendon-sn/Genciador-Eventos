import { DatePipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { UsuarioStatusPipe } from '../pipe/status.pipe';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations:[
        UsuarioStatusPipe,

    
    ],
    imports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule
        
    ],
    providers: [
        UsuarioStatusPipe,

    ],
    exports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        UsuarioStatusPipe,      
    ]
})
export class SharedModule { }
