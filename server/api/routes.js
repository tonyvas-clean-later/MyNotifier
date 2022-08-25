let router = require('express').Router();

// Attach the controllers to the router
router.use(require('./controllers/userController'));
router.use(require('./controllers/notificationController'));

module.exports = router;