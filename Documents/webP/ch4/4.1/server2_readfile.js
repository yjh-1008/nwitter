/** 
 * server2_readfile.js
 */

const fs = require('fs');

fs.readFile('./server2.html', (err, data) => {
    if (err) {
        throw err;
    }
    console.log(data.toString());
});
