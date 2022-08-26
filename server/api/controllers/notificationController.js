let router = require('express').Router();
let model = require('../models/notificationModel');

// Create user notification
router.post('/users/:public/notifications', (req, res) => {
    model.createNotification(req).then(() => {
        res.status(200).end();
    }).catch(err => {
        console.error(err);
        res.status(err.code).json({reason: err.reason});
    })
})

// Get user notifications
router.get('/users/:public/notifications', (req, res) => {
    model.getUserNotifications(req).then(data => {
        res.status(200).json(data);
    }).catch(err => {
        console.error(err);
        res.status(err.code).json({reason: err.reason});
    })
})

module.exports = router;