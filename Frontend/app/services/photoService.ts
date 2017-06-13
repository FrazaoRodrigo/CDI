import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PhotoService {

  constructor (private http: Http) {}

  private photosUrl = 'http://localhost:3001/photos';  // URL to web API

  getPhotos() : Observable<Array<string>> {
    return this.http.get(this.photosUrl)
                    .map(response => response.json())
                    .catch(this.handleError);
  }

  private handleError (error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}