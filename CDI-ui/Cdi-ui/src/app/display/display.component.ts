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
    return this.http.get('${environment.baseUrl}/display').map(res => res.json().base64)
    .subscribe(res => this.rest_response = res );
  }

  displayImage(){
     this.showImage = "data:image/png;base64," + this.rest_response;
  }
}
