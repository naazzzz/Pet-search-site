
document.getElementById("submit").addEventListener('click', function (){
    var name = document.getElementById("name").value;
    var telephone = document.getElementById("telephone").value;
    var email = document.getElementById("email").value;
    if(!(name==="" || telephone==="" || email==="")) {


    }
    else alert("Для перехода дальше заполните все подпункты в форме");
});
