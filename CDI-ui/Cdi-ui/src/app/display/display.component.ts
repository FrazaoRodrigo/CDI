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
  private image : any;



  constructor(public http: Http,public _DomSanitizer: DomSanitizer) { }



  getImage() {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers : headers });
    return this.http.get('http://localhost:8080/display').map(res => res.json(),options);

  }

  displayImage(){
    this.getImage().subscribe(res => {this.rest_response = res;
    this.image ='data:image/jpg;base64,' + this.rest_response["response"];
    })
  }




}
