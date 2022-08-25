let Connection = require('./myDB/db');

async function test(){
    let connection = new Connection();

    let uids = [];
    for (let i = 0; i < 5; i++){
        let user = randomUser();
        uids.push(await connection.createUser(user.private, user.public));
    }

    let nids = [];
    for (let uid of uids){
        for (let i = 0; i < 3; i++){
            let notif = randomNotification();
            nids.push(await connection.createNotification(uid, notif.title, notif.content, notif.date));
        }
    }

    for (let uid of uids){
        console.log(await connection.getUser(uid));
        console.log(await connection.getNotifications(uid));
    }
}

function randString(length){
    const CHARS = "abcdefghijklmnopqrstuvwxyz"
    let str = "";
    for (let i = 0; i < length; i++){
        str += CHARS[Math.floor(Math.random() * CHARS.length)];
    }

    return str;
}

function randomUser(){
    return {
        private: randString(5), 
        public: randString(5)
    };
}

function randomNotification(){
    return {
        title: randString(5),
        content: randString(20),
        date: new Date()
    }
}

module.exports = {test};