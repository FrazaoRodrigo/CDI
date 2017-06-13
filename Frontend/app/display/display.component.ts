import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../services/photoservice'

@Component({
    selector: 'app-display',
    templateUrl: 'app/display/display.component.html' 
})
export class DisplayComponent  implements OnInit {
    welcome: string;
    fileLists:Array<string>;
    errorMessage: string;
    selectedRow : Number;


    constructor(private photoService: PhotoService) {
        this.fileLists=[];
        this.welcome = "Choose which Image to process."
       
        
    };

    ngOnInit() {
        this.photoService.getPhotos()
            .subscribe(
            photos => this.fileLists = photos,
            error => this.errorMessage = <any>error);
    }
setClickedRow(index){
        this.selectedRow = index;
}

};