const http = require('http');

const server=http.createServer((rep,res)=>{
    res.writeHead(
        200,{'Content-type':'text/html; charset=utf-8'}
    )
    res.write(
         '<h1> Hello world</h1>'
    )
    res.end(
        '<p>Hello server</p>'
    )
})

server.listen(8080);
server.on('listening',()=>{
    console.log('port open')
})
server.on('error',(err)=>{
    console.log(err)
})