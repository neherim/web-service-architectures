var http = require('http');
var url = require('url');

http.createServer(function (req, res) {

    res.writeHead(200, {'Content-Type': 'application/json'});
    var pathName = url.parse(req.url).pathname;
    console.log(pathName);
    if (pathName == '/reddit') {
        setTimeout(function () {
            res.end('{"user": "Carl", "title":"greet", "text": ["Hello! My first reddit post!", "My second reddit post!"]}');
        }, 1000);
    } else {
        res.end('No no no...');
    }

}).listen(9001, 'localhost');

console.log('Server running at http://127.0.0.1:9001/');
