// Clear console out
// console.clear does not work too well
console.log('\n'.repeat(20));

let express = require('express');
let http = require('http');

// Temporary way of getting port
// TODO - Fix this
process.env.PORT = process.env.PORT || 9876;

// Create express app
let app = express();

// Start an http server
http.createServer(app).listen(process.env.PORT, () => {
    console.log(`Server running on port ${process.env.PORT}`);
})

// Use JSON handler for http bodies
app.use(express.json());

// Log all http requests
app.all('*', (req, res, next) => {
    console.log(req.url);
    next();
})

// Route API
app.use('/api', require('./api/routes'));