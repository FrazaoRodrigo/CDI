import { Component, OnInit   } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http'; 
import { NgForm } from '@angular/forms'; 
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {

  public base64textString: string;
  
    constructor(public http: Http) { 
    } 

    changeListener($event): void {
        this.handleFileSelect($event);
    }
    
    handleFileSelect(evt){
      var files = evt.target.files;
      var file = files[0];

    if (files && file) {
        var reader = new FileReader();

        reader.onload =this._handleReaderLoaded.bind(this);

        reader.readAsBinaryString(file);
    }
  }


  _handleReaderLoaded(readerEvt) {
     var binaryString = readerEvt.target.result;
            this.base64textString= btoa(binaryString);
            this.postFile(this.base64textString);
    }

  
     
    postFile(inputValue: any): void { 
        let headers = new Headers({ 'Content-Type': 'application/json' }); 
        let options = new RequestOptions({ headers: headers }); 
        this.http.post('http://localhost:8080/upload', inputValue, options)
        .subscribe( 
            (inputValue) => { 
                console.log('Response received')
            }, 
            (err) => { console.log(err); }, 
            () => console.log('Authentication Complete') 
            ); 
    } 

}
