import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-display',
    templateUrl: 'app/display/display.component.html' 
})
export class DisplayComponent  implements OnInit {
    welcome: string;
    fileLists:Array<string>;
    errorMessage: string;
    selectedRow : Number;


    constructor() {
        this.fileLists=[];
        this.welcome = "Choose which Image to process."
       
        
    };

    ngOnInit() {
      
    }
setClickedRow(index){
        this.selectedRow = index;
}

};