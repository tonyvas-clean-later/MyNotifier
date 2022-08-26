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
        // Get nid csv from query
        let nidCsv = req.query.nid

        // Get user id from public/private key pair
        new DatabaseConnection().getUserIDByKeyPair(public, private).then(uid => {
            if (uid !== null){
                // If user exists, get all its notifications
                new DatabaseConnection().getUserNotifications(uid).then(notifs => {
                    let filtered = {};
                    // Filter uid out of notifications
                    for (let nid in notifs){
                        let notif = notifs[nid];
                        filtered[nid] = {
                            title: notif.title,
                            content: notif.content,
                            date: notif.date
                        }
                    }

                    // Check if nid csv was provided
                    if (nidCsv){
                        // Split csv into list
                        nidList = nidCsv.trim().split(',');

                        // Filter out only the ids requested
                        let requested = {};
                        for (let nid of nidList){
                            requested[nid] = filtered[nid];
                        }

                        // Resolve with the requested notifications
                        resolve(requested);
                    }
                    else{
                        // Resolve with the filtered notifications
                        resolve(filtered);
                    }
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