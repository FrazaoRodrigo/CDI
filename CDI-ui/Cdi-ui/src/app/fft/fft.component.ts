import { Component, OnInit } from '@angular/core';
import {  Http,Headers,RequestOptions } from '@angular/http'; 

@Component({
  selector: 'fft',
  templateUrl: './fft.component.html',
  styleUrls: ['./fft.component.css']
})
export class FftComponent implements OnInit {

  imagefft:any;
  imageLowPass:any;
  imageshifted:any;
  imageinvfft:any;
  loading: boolean = false;


  constructor(public http:Http) { }

  ngOnInit() {
  }

   fft() {
     console.log(this.loading)
    this.onLoad()
    console.log(this.loading)
    let headers = new Headers({ 'Content-Type': 'application/json' }); 
    let options = new RequestOptions({ headers : headers }); 
    this.http.get('http://localhost:8080/fft').map(res => res.json(),options).subscribe(res => {this.imagefft ='data:image/jpg;base64,' + res["response"];  this.onLoad()});
    console.log(this.loading)
  }

  invfft(){
    let headers = new Headers({ 'Content-Type': 'application/json' }); 
    let options = new RequestOptions({ headers : headers }); 
    this.http.get('http://localhost:8080/inv_fft').map(res => res.json(),options).subscribe(res => {this.imageinvfft ='data:image/jpg;base64,' + res["response"]; });
  }

  lowpassfilter(){
    let headers = new Headers({ 'Content-Type': 'application/json' }); 
    let options = new RequestOptions({ headers : headers });
    this.http.get('http://localhost:8080/low_pass') .map(res =>res.json(),options).subscribe(res => {this.imageLowPass='data:image/jpg;base64,' + res["response"];});
  }

  shiftOrigin(){
    let headers = new Headers({ 'Content-Type': 'application/json' }); 
    let options = new RequestOptions({ headers : headers });
    this.http.get('http://localhost:8080/fftshift') .map(res =>res.json(),options).subscribe(res => {this.imageshifted='data:image/jpg;base64,' + res["response"];});
  }

  onLoad() {
    this.loading = !this.loading;
}

}
