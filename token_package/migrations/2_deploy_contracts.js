var LabToken = artifacts.require("./LabToken.sol");

module.exports = function (deployer) {
    deployer.deploy(LabToken);
};