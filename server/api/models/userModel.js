// Constants for generating keys
const CHARS = "abcdefghijklmnopqrstuvwxyz";
const KEY_LENGTH = 16;

let DatabaseConnection = require('../../myDB/db');

function createUser(){
    return new Promise((resolve, reject) => {
        // Generate public key
        let public = randomStringGenerator(KEY_LENGTH);
        // Check if its available
        isPublicKeyAvailable(public).then(res => {
            if (res){
                // If it is, generate a private key
                let private = randomStringGenerator(KEY_LENGTH);
                // Create new user with keys in database
                new DatabaseConnection().createUser(private, public).then(uid => {
                    // Resolve new user information
                    resolve({uid, public, private});
                }).catch(reject);
            }
            else{
                // If it exists, recursively try again
                createUser().then(resolve).catch(reject);
            }
        }).catch(reject);
    })
}

function isPublicKeyAvailable(public){
    return new Promise((resolve, reject) => {
        // Get all users
        new DatabaseConnection().getAllUsers().then(users => {
            // Loop over all users and check if any match the public key
            for (let uid in users){
                if (users[uid].public_key == public){
                    // If one does, resolve unavailable
                    return resolve(false)
                }
            }

            // If none match, resolve available
            resolve(true);
        }).catch(reject);
    })
}

function randomStringGenerator(length){
    let str = "";
    // Loop and generate string
    for (let i = 0; i < length; i++){
        // concatenate random character
        str += CHARS[Math.floor(Math.random() * CHARS.length)];
    }

    return str;
}

module.exports = {createUser};