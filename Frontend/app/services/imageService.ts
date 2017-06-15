import { Component, OnInit, Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { environment } from '../environments/environment';
import {Observable} from 'rxjs/Observable';



@Injectable()
export class ImageService {
constructor(private _http: Http){}
fft(){
        return this._http.get(`${environment.baseUrl}/fft`)
        .toPromise()
        .then(response => response.json())
    }

}