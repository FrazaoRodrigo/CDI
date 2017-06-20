import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UploadComponent } from './upload/upload.component'; //import upload components
import { DisplayComponent } from './display/display.component';

const appRoutes: Routes = [
  { path: 'upload', component: UploadComponent },
  { path: 'display', component: DisplayComponent },
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);