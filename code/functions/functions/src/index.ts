import * as functions from 'firebase-functions';

export const generateSmallNum = functions.https.onRequest((request, response) => {
    response.status(200).json({
        "small": getSmall()
    });

});

export const generateLargeNum = functions.https.onRequest((request, response) =>{
    response.status(200).json({
        "large": getBig()
    });
});


export const generateVowel = functions.https.onRequest((request,response) => {
    response.status(200).json({
        "vowel":getVowel()
    });
});

export const generateConsonant = functions.https.onRequest((request, response) => {
    response.status(200).json({
        "consonant":getConsonant()
    });
});

function getSmall(){
    const smallNums:number[] = [1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10];
    return smallNums[Math.floor(Math.random() * 20)];
};

function getBig(){
    const bigNums:number[] = [25,50,75,100];
    return bigNums[Math.floor(Math.random() * 4)];
}


function getVowel(){
    const vowels:string[] = ["A", "E", "I", "O", "U"];
    return vowels[Math.floor(Math.random() * 5)];
}

function getConsonant(){
    const consonants:string[] = ["B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"];
    return consonants[Math.floor(Math.random() * 21)];
}