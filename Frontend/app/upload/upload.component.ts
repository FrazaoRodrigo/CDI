import { Component } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';

@Component({
    selector: 'app-upload',
    templateUrl: 'app/upload/upload.component.html'

})
export class UploadComponent {
    public uploader: FileUploader = new FileUploader({ url: 'http://localhost:3001/upload' });
    welcome: string;
    constructor() {
        this.welcome = "The Rodrigro Project";
    };
};