import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MdButtonModule, MdCheckboxModule} from '@angular/material';

import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { UploadComponent } from './upload/upload.component';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import 'rxjs/Rx';

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    FileSelectDirective,
    FileDropDirective
  ],
  imports: [
    routing,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MdButtonModule, 
    MdCheckboxModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
