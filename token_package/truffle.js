module.exports = {
  networks: {
    development: {
      host: "127.0.0.1",
      port: 7545, 
      network_id: "*", 
      from: "0x627306090abaB3A6e1400e9345bC60c78a8BEf57",
      // gas: 4712388,
      // gasPrice:100000000000 
    },
    live: {
      host: "127.0.0.1",
      port: 7545, 
      network_id: "*", 
      from: "0x627306090abaB3A6e1400e9345bC60c78a8BEf57",
      gas: 200000,
      gasPrice:500 
    }
  }
};
