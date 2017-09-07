import { Component } from '@angular/core';

@Component({
    selector: 'sidebar',
    template: `
    <md-sidenav-container fullscreen>
       <md-toolbar class="background-primary-color">   
            <button md-icon-button (click)="sidenav.toggle()">
                <md-icon class="md-24" >menu</md-icon>
            </button>
        </md-toolbar>
    <md-sidenav #sidenav mode="side">
    <md-toolbar (class)="background-accent-color">
    
   Menu?

    <md-toolbar-row>
    <md-button-toggle (click)="uploadChange()">
       <span>Upload</span>
    </md-button-toggle>
    </md-toolbar-row>   

     <md-toolbar-row>  
     <md-button-toggle (click)="displayChange()">
       <span >Display</span>
       </md-button-toggle>
    </md-toolbar-row>

    <md-toolbar-row>  
     <md-button-toggle (click)="fftChange()">
       <span >FFT</span>
       </md-button-toggle>
    </md-toolbar-row>
    
    </md-toolbar>
    </md-sidenav>
     <div *ngIf="uploadState"> <app-upload></app-upload> </div>
     <div *ngIf="displayState"> <app-display></app-display> </div> 
     <div *ngIf="fftState"> <fft></fft> </div>
  <router-outlet></router-outlet>
</md-sidenav-container>

    `,
  styles:['.mat-sidenav.mat-sidenav{ background-color: #f5ffa3!important;}']
})

export class SidebarComponent{

 uploadState: boolean;
 displayState: boolean;
 fftState:boolean;

constructor(){
}

uploadChange(){
    this.uploadState = !this.uploadState;
}
displayChange(){
    this.displayState = !this.displayState;
}
fftChange(){
   this.fftState = !this.fftState;
}

}
