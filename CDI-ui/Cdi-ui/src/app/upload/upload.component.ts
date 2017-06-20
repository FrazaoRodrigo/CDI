import { Component, OnInit } from '@angular/core';
import {  FileUploader } from 'ng2-file-upload';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  public uploader:FileUploader = new FileUploader({url: `${environment.baseUrl}/upload`});

  ngOnInit() {
   this.uploader.onAfterAddingFile = (file)=> { file.withCredentials = false; };
       this.uploader.onCompleteItem = (item:any, response:any, status:any, headers:any) => {
            console.log("ImageUpload:uploaded:", item, status, response);
  }

}
}
