const DATA_DIRECTORY = `${__dirname}/data`

// JSON files for storing 'tables'
const USER_DATA = `${DATA_DIRECTORY}/user.json`
const NOTIFICATION_DATA = `${DATA_DIRECTORY}/notification.json`

let fs = require('fs');

class DatabaseConnection{
    // Read from 'table' JSON file
    _read(file){
        return new Promise((resolve, reject) => {
            fs.readFile(file, 'utf-8', (err, data) => {
                if (err){
                    reject(err)
                }
                else{
                    // If file is empty, return empty object
                    if (data.length == 0){
                        resolve({});
                    }
                    else{
                        try {
                            // Try parsing text to json object
                            resolve(JSON.parse(data));
                        } catch (error) {
                            reject(error);
                        }
                    }
                }
            })
        })
    }

    // Write to 'table' JSON file
    _write(file, data){
        return new Promise((resolve, reject) => {
            try {
                // Try converting json object to string
                let dataStr = JSON.stringify(data);
                // Write string to file
                fs.writeFile(file, dataStr, 'utf-8', (err) => {
                    if (err){
                        reject(err)
                    }
                    else{
                        resolve();
                    }
                })
            } catch (error) {
                reject(error);
            }
        })
    }

    // Get next available id for 'table'
    _getNextId(file){
        return new Promise((resolve, reject) => {
            // Get 'table'
            this._read(file).then(data => {
                let max = 0;
                // Loop over all ids and get the max id
                for (let id in data){
                    max = Math.max(max, id);
                }

                // Resolve the next id after the current max
                resolve(max + 1);
            }).catch(reject);
        })
    }

    createUser(privKey, pubKey){
        return new Promise((resolve, reject) => {
            // Get all users
            this._read(USER_DATA).then(users => {
                // Get next available user id
                this._getNextId(USER_DATA).then(uid => {
                    // Insert user at the next available id
                    users[uid] = {
                        private_key: privKey,
                        public_key: pubKey
                    };
    
                    // Write modified users object
                    this._write(USER_DATA, users).then(() => {
                        // Resolve with new uid
                        resolve(uid);
                    }).catch(reject)
                }).catch(reject)
            }).catch(reject)
        })
    }

    getAllUsers(){
        return new Promise((resolve, reject) => {
            // Get all users
            this._read(USER_DATA).then(users => {
                resolve(users);
            })
        })
    }

    getUser(uid){
        return new Promise((resolve, reject) => {
            // Get all users
            this.getAllUsers().then(users => {
                // Return user with uid
                resolve(users[uid])
            })
        })
    }

    createNotification(uid, title, content, date){
        return new Promise((resolve, reject) => {
            // Get all notifications
            this._read(NOTIFICATION_DATA).then(notifs => {
                // Get next available notification id
                this._getNextId(NOTIFICATION_DATA).then(nid => {
                    // Insert notification at the next available id
                    notifs[nid] = {
                        uid: uid,
                        title: title,
                        content: content,
                        date: date
                    };
    
                    // Write modified notifications object
                    this._write(NOTIFICATION_DATA, notifs).then(() => {
                        // Resolve with new nid
                        resolve(nid);
                    }).catch(reject)
                }).catch(reject)
            }).catch(reject)
        })
    }

    getAllNotifications(){
        return new Promise((resolve, reject) => {
            // Get all notifications
            this._read(NOTIFICATION_DATA).then(notifs => {
                for (let nid in notifs){
                    // Recreate the date field as Date instance (Saving to json converts it to string)
                    notifs[nid].date = new Date(notifs[nid].date);
                }

                resolve(notifs);
            })
        })
    }

    getUserNotifications(uid){
        return new Promise((resolve, reject) => {
            this.getAllNotifications().then(allNotifs => {
                let userNotifs = {};

                // Loop over all notifications and seperate out the ones with the uid
                for (let nid in allNotifs){
                    let notif = allNotifs[nid];
                    if (notif.uid == uid){
                        userNotifs[nid] = notif;
                    }
                }

                resolve(userNotifs);
            })
        })
    }

    getUserNotification(uid, nid){
        return new Promise((resolve, reject) => {
            // Get user notifications
            this.getUserNotifications(uid).then(notifs => {
                // Resolve the one with the nid
                resolve(notifs[nid]);
            })
        })
    }
}

module.exports = DatabaseConnection;