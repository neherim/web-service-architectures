var http = require('http');
var url = require('url');

http.createServer(function (req, res) {

    res.writeHead(200, {'Content-Type': 'application/json'});
    var pathName = url.parse(req.url).pathname;
    console.log(pathName);
    if (pathName == '/tweet') {
        setTimeout(function () {
            res.end('{"user": "Carl", "text": ["Hello! My first tweet!", "Second tweet!"]}');
        }, 1000);
    } else {
        res.end('No no no...');
    }

}).listen(9000, 'localhost');

console.log('Server running at http://127.0.0.1:9000/');
