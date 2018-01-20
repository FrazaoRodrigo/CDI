import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from '@angular/material';
import {BusyModule} from 'angular2-busy';

import 'hammerjs';


import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { UploadComponent } from './upload/upload.component';
import { SidebarComponent} from './library/sidebar.component';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import 'rxjs/Rx';
import { DisplayComponent } from './display/display.component';
import { FftComponent } from './fft/fft.component';

@NgModule({
  schemas: [NO_ERRORS_SCHEMA],
  declarations: [
    AppComponent,
    UploadComponent,
    DisplayComponent,
    FileSelectDirective,
    FileDropDirective,
    SidebarComponent,
    FftComponent
  ],
  imports: [
    routing,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterialModule,
    BusyModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
