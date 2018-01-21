pragma solidity ^0.4.18;

import "./basic/token/StandardToken.sol";

contract LabToken is StandardToken {

  string public name = "CommerceLAB-Token";
  string public symbol = "LAB";
  uint256 public decimals = 0;

  uint256 public INITIAL_SUPPLY = 10 * 10000 * 10000 ;
  

  function LabToken() public {     
    totalSupply = INITIAL_SUPPLY;
    balances[msg.sender] = INITIAL_SUPPLY;
  }

}
