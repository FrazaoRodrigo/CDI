import { Component } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageService } from '../services/imageService';

@Component({
    selector: 'app-upload',
    templateUrl: 'app/upload/upload.component.html'

})
export class UploadComponent {
    public uploader: FileUploader = new FileUploader({ url: 'http://localhost:8080/fft' });
    welcome: string;
    imageData: string;
    constructor(private imageService:ImageService) {
        this.welcome = "The Rodrigo Project";
        imageService.fft().then( 
            response => { 
            this.imageData='data:image/png;base64,'+response.content;
        })

    };
};