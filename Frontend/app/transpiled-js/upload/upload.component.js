"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var ng2_file_upload_1 = require("ng2-file-upload");
var imageService_1 = require("../services/imageService");
var UploadComponent = (function () {
    function UploadComponent(imageService) {
        var _this = this;
        this.imageService = imageService;
        this.uploader = new ng2_file_upload_1.FileUploader({ url: 'http://localhost:8080/fft' });
        this.welcome = "The Rodrigo Project";
        imageService.fft().then(function (response) {
            _this.imageData = 'data:image/png;base64,' + response.content;
        });
    }
    ;
    return UploadComponent;
}());
UploadComponent = __decorate([
    core_1.Component({
        selector: 'app-upload',
        templateUrl: 'app/upload/upload.component.html'
    }),
    __metadata("design:paramtypes", [imageService_1.ImageService])
], UploadComponent);
exports.UploadComponent = UploadComponent;
;
//# sourceMappingURL=upload.component.js.map