import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import { Headers, Http, RequestOptions } from '@angular/http'; 

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent {

  rest_response: any;
  showImage: any;

  constructor(private _DomSanitizationService: DomSanitizer,public http: Http) { }
  
 
  getImage(){
    let headers = new Headers({ 'Content-Type': 'application/json' }); 
    let options = new RequestOptions({ headers : headers }); 
    return this.http.get('http://localhost:8080/display').map(res => res.json().base64,options)
    .subscribe(res => this.rest_response = res );
  }

  displayImage(){
    this.getImage();
     this.showImage = this.rest_response;
  }
}
