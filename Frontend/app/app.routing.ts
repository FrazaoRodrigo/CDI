import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UploadComponent } from './upload/upload.component'; //import upload components
import { DisplayComponent } from './display/display.component'; //import display component

const appRoutes: Routes = [
  { path: 'upload', component: UploadComponent },
  { path: 'display', component: DisplayComponent },
  { path: '', component: UploadComponent, pathMatch: 'full'} // redirect to upload page on load
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);