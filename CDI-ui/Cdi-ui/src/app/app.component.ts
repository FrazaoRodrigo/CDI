import { Component, Optional, OnInit,ViewEncapsulation } from '@angular/core';
import {MdDialog} from '@angular/material'
import { Router } from '@angular/router';
import { DialogComponent } from './dialog/dialog.component';



@Component({
  encapsulation: ViewEncapsulation.None,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

constructor(public dialog: MdDialog) {}
openDialog() {
    this.dialog.open(DialogComponent);
  }
  
}
