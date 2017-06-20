import { Component } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
    selector: 'app-upload',
    templateUrl: 'app/upload/upload.component.html'

})
export class UploadComponent {
    public uploader: FileUploader = new FileUploader({ url: 'http://localhost:8080/fft' });
    welcome: string;
    imageData: string;
    constructor() {
        this.welcome = "The Rodrigo Project";
    };
};