import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UploadComponent } from './upload/upload.component'; //import upload components

const appRoutes: Routes = [
  { path: 'upload', component: UploadComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);