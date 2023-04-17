
$('#submit').click(function (e){
    var name = document.getElementById("name").value;
    var telephone = document.getElementById("telephone").value;
    var email = document.getElementById("email").value;
    if(!(name==="" || telephone==="" || email==="")) {

        var actionUrl = "/user/description";
        var data = JSON.stringify({ "name": name, "telephone_number": telephone,"email":email });
        console.log(data);

        $.ajax({
            url:actionUrl,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type:"POST",
            data:data,
            success: function (response) {


                var actionUrl = "/ad/create";
                var actionUrlUpload = "/ad/uploadfile";
                var kind = localStorage.getItem("kind");
                var sex= localStorage.getItem("sex");
                var photo = localStorage.getItem("photo_name");
                var photo_data= localStorage.getItem("photo_data");
                var date= localStorage.getItem("date");
                var place = localStorage.getItem("place");
                var description = localStorage.getItem("description");
                var user_description= response.id;


                if(photo==="none" || photo_data==="none"){

                    var data = JSON.stringify({ "kind": kind, "sex": sex,"photo":"none" , "date":date, "place":place, "description":description, "user_description":user_description});

                    $.ajax({
                        url:actionUrl,
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        type:"POST",
                        data:data,
                        success: function () {

                            alert("Успешно, объявление отправленно на проверку, после проверки модератором оно появится в каталоге.");
                            document.location="/MainWindow"
                        },
                        error:function (er) {
                            alert("Ошибка ответа сервера"+er);
                            console.error(er);
                        }
                    })


                }else{
                    var image = new Image();
                    image.src=photo_data;


                    var form_data = new FormData();
                    form_data.append('file', (dataURLtoFile(photo_data, photo)));

                    var photo_location;
                    $.ajax({
                        url:actionUrlUpload,
                        cache: false,
                        contentType: false,
                        type:"POST",
                        data:form_data,
                        processData: false,
                        success: function (result) {
                            console.log(result);
                            var data = JSON.stringify({ "kind": kind, "sex": sex,"photo":result, "date":date, "place":place, "description":description, "user_description":user_description});

                            $.ajax({
                                url:actionUrl,
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                type:"POST",
                                data:data,
                                success: function () {

                                    alert("Успешно, объявление отправленно на проверку, после проверки модератором оно появится в каталоге.");
                                    document.location="/MainWindow"
                                },
                                error:function (er) {
                                    alert("Ошибка ответа сервера"+er);
                                    console.error(er);
                                }
                            })

                        },
                        error:function (er) {
                            alert("Ошибка загрузки фото"+er);
                            console.error(er);
                        }

                    })
                }




            },
            error:function (er) {
                alert("Ошибка регистрации");
                console.error(er);
            }

        })

    }
    else alert("Для перехода дальше заполните все подпункты в форме");




});

function dataURLtoFile(dataurl, filename) {
    let arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
}