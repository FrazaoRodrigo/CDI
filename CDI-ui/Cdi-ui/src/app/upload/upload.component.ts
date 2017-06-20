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

  public filestring: string;
  
    constructor(public http: Http) { 
    } 

    changeListener($event): void {
        this._readThis=$event.target;
        }
    
    _readThis(inputValue: any):void {
    var file:File = inputValue.files[0]; 
    var myReader:FileReader = new FileReader();
    myReader.readAsBinaryString(file);
    this._handleReaderLoaded(myReader);
    this.postFile(this.filestring);  
    }
 
    _handleReaderLoaded(readerEvt) { 
        var binaryString = readerEvt; 
        this.filestring = btoa(binaryString);  // Converting binary string data. 
   } 
  
     
    postFile(inputValue: any): void { 
        var formData = new FormData();
        formData.append("name", "Name");
        formData.append("file",  inputValue.files[0]);

        let headers = new Headers({ 'Content-Type': 'application/json' }); 
        let options = new RequestOptions({ headers: headers }); 
        this.http.post('http://localhost:8080/upload', JSON.stringify({inputValue }), options).                
            subscribe( 
            (data) => { 
                console.log('Response received'); 
            }, 
            (err) => { console.log(err); }, 
            () => console.log('Authentication Complete') 
            ); 
    } 

}
