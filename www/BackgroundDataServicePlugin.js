var exec = require('cordova/exec');

exports.startDataService = function(arg0, success, error) {
    console.log("Start Data Service being invoked");
    exec(success, error, "BackgroundDataServicePlugin", "STARTDATASERVICE", [arg0]);
};
