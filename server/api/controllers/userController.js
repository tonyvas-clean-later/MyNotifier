let router = require('express').Router();
let model = require('../models/userModel');

// Create a new user
// Should return the user's public/private key pair for future authentication
router.post('/api/users', (req, res) => {
    res.status(200).end();
})

module.exports = router;