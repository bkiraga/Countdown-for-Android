const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initialiseApp();

exports.addMessage = functions.https.onRequest(async (req, res) => {
    const playerId = req.querry.txt;
    const snapshot = await admin.database().ref('/lobby').push({original: playerId});
    res.redirect(303, snapshot.ref.toString());
});