
  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyB5UP9NSWN_y8HLz4OBpASZ7j00CwrPNvs",
    authDomain: "uber-clone-b1762.firebaseapp.com",
    databaseURL: "https://uber-clone-b1762.firebaseio.com",
    projectId: "uber-clone-b1762",
    storageBucket: "uber-clone-b1762.appspot.com",
    messagingSenderId: "703695689024",
    appId: "1:703695689024:web:9597840b1df96efc5a56a5",
    measurementId: "G-57P8D4XEKH"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();
  const auth=firebase.auth();


var div_login=document.getElementById('login_div');
var after_login=document.getElementById('after_login');
var register = document.getElementById('register');
var aboutus = document.getElementById('aboutus');
var contactus = document.getElementById('contactus');

after_login.style.display = "none";
div_login.style.display = "none";
aboutus.style.display = "none";
contactus.style.display = "none";



var data = [];
var i=0;
var id = [];
var pass= [];



async function view(){
  firebase.database().ref("Users/Hospitals/").once('value',function(snapshot){
    snapshot.forEach(function(childsnapshot) {
      var childkey = childsnapshot.key;
      var childdata = childsnapshot.val();
      data.push(Object.values(childdata));
    });
  });
}
view();


function signin() {
  after_login.style.display = "none";
  div_login.style.display = "block";
  aboutus.style.display = "none";
  register.style.display = "none";
  contactus.style.display = "none";

}

function contact() {
  after_login.style.display = "none";
  div_login.style.display = "none";
  aboutus.style.display = "none";
  register.style.display = "none";
  contactus.style.display = "block";
}

function about() {
  after_login.style.display = "none";
  div_login.style.display = "none";
  aboutus.style.display = "block";
  register.style.display = "none";
  contactus.style.display = "none";
}

function verification() {
  var login_id=document.getElementById('login_id').value;
  var password = document.getElementById('passkey').value;
  var count=0;
  console.log(login_id);
  console.log(password);
  for (var i = 0; i < data.length; i++) {
    // console.log(password);
    if (login_id==data[i][0]) {
      if(password==data[i][1]){
        after_login.style.display = "block";
        div_login.style.display = "none";
      }
      else {
        console.log("password incorrect");
      }
    }
    else {
      count+=1;
    }
  }
  if (count==data.length) {
    console.log("Username not found");
  }
}

function signOut() {
  after_login.style.display = "none";
  div_login.style.display = "block"
}

var details = document.getElementById('details');




function get_details() {
  var pat_details = [];
  var pat_id = document.getElementById('pat_id').value;

  var name = document.getElementById('name');
  var age = document.getElementById('age')
  var contact = document.getElementById('contact');
  var emergency_contact = document.getElementById('emergency_contact');
  var notes = document.getElementById('notes');


  firebase.database().ref("Users/Customers/").once('value',function(snapshot){
    snapshot.forEach(function(childsnapshot) {
      var childkey = childsnapshot.key;
      if (childkey==pat_id) {

        var childdata = childsnapshot.val();



        pat_details.push(Object.values(childdata));

        console.log(pat_details);

        console.log(pat_details[0][1]);


        var span = document.createElement('span');
        name.innerHTML=`Name : `;
        span.innerHTML=pat_details[0][3];
        name.append(span);

        var span = document.createElement('span');
        age.innerHTML=`Age : `;
        span.innerHTML=pat_details[0][0];
        age.append(span);

        var span = document.createElement('span');
        contact.innerHTML=`Patient contact : `;
        span.innerHTML=pat_details[0][1];
        contact.append(span);

        var span = document.createElement('span');
        emergency_contact.innerHTML=`Emergency contact : `;
        span.innerHTML=pat_details[0][5];
        emergency_contact.append(span);

        var span = document.createElement('span');
        notes.innerHTML=`Notes : `;
        span.innerHTML=pat_details[0][4];
        notes.append(span);

        console.log(name);
        console.log(details);
        }
      });
    });
}
//write data================
/*
console.log(id.length);

function writeUserData(login_id,key) {
  firebase.database().ref('Users/Hospitals/'+ key).set({
    id: login_id,
    password: login_id
  });
}

console.log(id.length);
for (var i = 0; i < 50; i++) {

  var key=firebase.database().ref('Users/Hospitals').push().key;
  writeUserData(id[i].UNIQUE_HOSPITAL_ID,key)
  console.log(id[i].UNIQUE_HOSPITAL_ID);

}
*/

//==============================
//Delete data
/*for (var i = 0; i < 100; i++) {

  firebase.database().ref("Users/Hospitals").once('value',function(snapshot){
    snapshot.forEach(function(childsnapshot) {
      key=childsnapshot.key;
      console.log(key);
      task_to_remove=firebase.database().ref("Users/Hospitals/"+key);
      task_to_remove.remove();

  });

});
}*/
