let router = require('express').Router();
let model = require('../models/userModel');

router.post('/users', (req, res) => {
    model.createUser().then(user => {
        // Create user and send its info
        res.status(200).json(user);
    }).catch(err => {
        // If failed to create log error and send server error code
        console.error(err);
        res.status(500).end();
    })
})

module.exports = router;