"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var upload_component_1 = require("./upload/upload.component"); //import upload components
var display_component_1 = require("./display/display.component"); //import display component
var appRoutes = [
    { path: 'upload', component: upload_component_1.UploadComponent },
    { path: 'display', component: display_component_1.DisplayComponent },
    { path: '', component: upload_component_1.UploadComponent, pathMatch: 'full' } // redirect to upload page on load
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map