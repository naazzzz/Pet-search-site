$(function() {
	$(".btn").click(function() {
		$(".form-signin").toggleClass("form-signin-left");
    $(".form-signup").toggleClass("form-signup-left");
    $(".frame").toggleClass("frame-long");
    $(".signup-inactive").toggleClass("signup-active");
    $(".signin-active").toggleClass("signin-inactive");
    $(".forgot").toggleClass("forgot-left");   
    $(this).removeClass("idle").addClass("active");
	});
});


    $('#form_singin').submit(function (e) {

        e.preventDefault();

        var form = $(this);
        var actionUrl = "/user/" + $('#username').val();



        $.ajax({
            url: actionUrl,
            type: "POST",
            data: form.serialize(),
            success: function (data) {
                console.log(data);
                if (data) {
                    $(".btn-animate").toggleClass("btn-animate-grow");
                    $(".welcome").toggleClass("welcome-left");
                    $(".cover-photo").toggleClass("cover-photo-down");
                    $(".frame").toggleClass("frame-short");
                    $(".profile-photo").toggleClass("profile-photo-down");
                    $(".btn-goback").toggleClass("btn-goback-up");
                    $(".forgot").toggleClass("forgot-fade");
                    document.getElementById("hello_name").textContent = "Привет, " + $('#username').val();
                } else {
                    alert("Ошибка авторизации. Пожалуйста, проверьте логин и пароль");
                }

            },
            error: function (er) {
                alert("Ошибка авторизации. Пожалуйста, проверьте логин и пароль");
                console.error(er);
            }

        })
    })

$('#form_singup').submit(function (e){

    var form = $(this);
    var field = [];
    var ret=true;
    form.find('input[data-validate]').each(function () {
        field.push('input[data-validate]');
        var value = $(this).val();

        for(var i=0;i<field.length;i++) {
            if( !value ) {
                setTimeout(function() {
                }.bind(this),2000);
                event.preventDefault();

                ret=false;
            }
        }
    });
if ($('form input[name="pass"]').val()===$('form input[name="confirmpassword"]').val() && ret){



    e.preventDefault();

    var form = $(this);
    var actionUrl = "/user/registration";



     var data = JSON.stringify({ "username": $('form input[name="usernameup"]').val(), "pass": $('form input[name="pass"]').val(),"email":$('form input[name="email"]').val() });
    console.log(data);
    $.ajax({
        url:actionUrl,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type:"POST",
        data:data,
        success: function () {
            alert("Успешная регистрация" );
            $(".nav").toggleClass("nav-up");
            $(".form-signup-left").toggleClass("form-signup-down");
            $(".success").toggleClass("success-left");
            $(".frame").toggleClass("frame-short");
            $(".btn-goback").toggleClass("btn-goback-up");
        },
        error:function (er) {
            alert("Ошибка регистрации");
            console.error(er);
        }

    })
}else {
    alert("Проверьте корректность введенных данных");
    return false;
}
})



