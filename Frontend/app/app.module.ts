import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { UploadComponent } from './upload/upload.component';
import { DisplayComponent } from './display/display.component';
import { routing } from './app.routing';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';
import { HttpModule } from '@angular/http';
import 'rxjs/Rx';


@NgModule({
  imports: [BrowserModule, routing, HttpModule], //other modules the app depends on
  declarations: [AppComponent, DisplayComponent, UploadComponent, FileSelectDirective, FileDropDirective], // declare all derectives and components
  bootstrap: [AppComponent], // root component to bootstarp
 
})
export class AppModule { }
