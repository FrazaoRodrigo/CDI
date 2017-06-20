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
  public url: string;
    constructor(public http: Http,
    url='http://localhost:8080/upload') { 
    } 

    changeListener($event): void {
        let files =$event.target.files; 
        var reader = new FileReader(); 
        reader.onload = this._handleReaderLoaded.bind(this); 
        reader.readAsBinaryString(files[0]);
        this._handleReaderLoaded(reader);
        this.postFile(this.filestring);
}
 
    _handleReaderLoaded(readerEvt) { 
        var binaryString = readerEvt.target.result; 
        this.filestring = btoa(binaryString);  // Converting binary string data. 
   } 
  
     
    postFile(inputValue: any): void { 
        var formData = new FormData();
        formData.append("name", "Name");
        formData.append("file",  inputValue.files[0]);

        let headers = new Headers({ 'Content-Type': 'application/json' }); 
        let options = new RequestOptions({ headers: headers }); 
        this.http.post(this.url, JSON.stringify({body: this.filestring }), options).                
            subscribe( 
            (data) => { 
                console.log('Response received'); 
            }, 
            (err) => { console.log(err); }, 
            () => console.log('Authentication Complete') 
            ); 
    } 

}
