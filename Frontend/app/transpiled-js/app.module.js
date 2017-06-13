"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var app_component_1 = require("./app.component");
var upload_component_1 = require("./upload/upload.component");
var display_component_1 = require("./display/display.component");
var app_routing_1 = require("./app.routing");
var ng2_file_upload_1 = require("ng2-file-upload");
var photoservice_1 = require("./services/photoservice");
var http_1 = require("@angular/http");
require("rxjs/Rx");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [platform_browser_1.BrowserModule, app_routing_1.routing, http_1.HttpModule],
        declarations: [app_component_1.AppComponent, display_component_1.DisplayComponent, upload_component_1.UploadComponent, ng2_file_upload_1.FileSelectDirective, ng2_file_upload_1.FileDropDirective],
        bootstrap: [app_component_1.AppComponent],
        providers: [photoservice_1.PhotoService]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map