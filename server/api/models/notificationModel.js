let DatabaseConnection = require('../../myDB/db');

function createNotification(req){
    return new Promise((resolve, reject) => {
        // Get public key from params (url)
        let {public} = req.params;
        // Get private key and notification data from headers
        let {private, title, content} = req.headers;
        // Set current date for notification date
        let date = new Date();

        // Get user id from public/private key pair
        new DatabaseConnection().getUserIDByKeyPair(public, private).then(uid => {
            if (uid !== null){
                // If user exists, create notification for it
                new DatabaseConnection().createNotification(uid, title, content, date).then(() => {
                    // Resolve if success
                    resolve();
                }).catch(err => {
                    reject({code: 500, reason: 'Database error!', error: err});
                })
            }
            else{
                // If user matching the key pair does not exist, assume user provided wrong data
                reject({code: 401, reason: 'Missing or invalid public/private keys!'});
            }
        }).catch(err => {
            reject({code: 500, reason: 'Database error!', error: err});
        })
    })
}

function getUserNotifications(req){
    return new Promise((resolve, reject) => {
        // Get public key from params (url)
        let {public} = req.params;
        // Get private key from headers
        let {private} = req.headers;

        // Get user id from public/private key pair
        new DatabaseConnection().getUserIDByKeyPair(public, private).then(uid => {
            if (uid !== null){
                // If user exists, get all its notifications
                new DatabaseConnection().getUserNotifications(uid).then(notifs => {
                    let filtered = [];

                    // Loop over all notifications from database
                    for (let nid in notifs){
                        let notif = notifs[nid];
                        // Filter out the ids, and only keep data relevant to the user
                        filtered.push({
                            title: notif.title,
                            content: notif.content,
                            date: notif.date
                        })
                    }
                    
                    resolve(filtered);
                }).catch(err => {
                    reject({code: 500, reason: 'Database error!', error: err});
                })
            }
            else{
                // If user matching the key pair does not exist, assume user provided wrong data
                reject({code: 401, reason: 'Missing or invalid public/private keys!'});
            }
        }).catch(err => {
            reject({code: 500, reason: 'Database error!', error: err});
        })
    })
}

module.exports = {createNotification, getUserNotifications};