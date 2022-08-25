let router = require('express').Router();
let model = require('../models/notificationModel');

// Create a new notification for user with uid
// Require authentication
router.post('/users/:uid/notifications', (req, res) => {
    res.status(200).end();
})

// Get all notifications for user with uid
// Require authentication
router.get('/users/:uid/notifications', (req, res) => {
    res.status(200).end();
})

// Get specific notification with nid for user with nid
// Require authentication
router.get('/users/:uid/notification/:nid', (req, res) => {
    res.status(200).end();
})

module.exports = router;