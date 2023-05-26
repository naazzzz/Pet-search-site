
$("#user_desc_submit").click(function (p){
    var name = $("#name").val();
    var email = $("#email").val();
    var telephone = $("#telephone").val();

    var actionUrl = "/user/addDescriptionToUser";


    var data = JSON.stringify({ "name": name, "telephone_number": telephone,"email":email });

    $.ajax({
        url: actionUrl,
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: data,
        success: function (data) {
            alert("Описание успешно добавлено");

        },
        error: function (er) {
            alert("Ошибка добавления, проверьте корректность введенных данных");
            console.error(er);
        }

    })

});


//Запрос на получение объявлений
var actionUrl = "/ad/allAdsByUser";
$.ajax({
    url: actionUrl,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    type: "GET",
    success: function (response) {
        // console.log(response);
        var articleDiv = document.querySelector("div.all_ads");
        response.forEach(function (item, i, arr) {

            var div= document.createElement("div");
            var div_atr_1=document.createAttribute("id");
            div_atr_1.value="div_"+item.id;
            div.setAttributeNode(div_atr_1);
            var div_atr_2=document.createAttribute("class");
            div_atr_2.value="mod_div";
            div.setAttributeNode(div_atr_2);
            articleDiv.appendChild(div);

            var elem_9 = document.createElement("img");
            var elem_atr_9_1=document.createAttribute("id");
            elem_atr_9_1.value="ad_img"+item.id;
            elem_9.setAttributeNode(elem_atr_9_1);
            var elem_atr_9_2=document.createAttribute("src");
            elem_atr_9_2.value=item.photo;
            elem_9.setAttributeNode(elem_atr_9_2);
            var elem_atr_9_3=document.createAttribute("class");
            elem_atr_9_3.value="mod_img";
            elem_9.setAttributeNode(elem_atr_9_3);
            div.appendChild(elem_9);
// создаем элемент
            var form = document.createElement("div");
            var form_atr_1=document.createAttribute("id");
            form_atr_1.value="form_"+item.id;
            form.setAttributeNode(form_atr_1);
            var form_atr_4=document.createAttribute("class");
            form_atr_4.value="mod_form";
            form.setAttributeNode(form_atr_4);


            div.appendChild(form);


            // var elem_1 = document.createElement("input");
            // var elem_atr_1=document.createAttribute("id");
            // elem_atr_1.value="ad_"+item.id;
            // elem_1.setAttributeNode(elem_atr_1);
            // var elem_atr_2=document.createAttribute("type");
            // elem_atr_2.value="text";
            // elem_1.setAttributeNode(elem_atr_2);
            // var elem_atr_3=document.createAttribute("value");
            // elem_atr_3.value=item.id;
            // elem_1.setAttributeNode(elem_atr_3);
            // var elem_atr_4=document.createAttribute("readonly");
            // elem_1.setAttributeNode(elem_atr_4);
            // form.appendChild(elem_1);

            var elem_2 = document.createElement("input");
            var elem_atr_2_1=document.createAttribute("id");
            elem_atr_2_1.value="ad_date"+item.id;
            elem_2.setAttributeNode(elem_atr_2_1);
            var elem_atr_2_2=document.createAttribute("type");
            elem_atr_2_2.value="datetime-local";
            elem_2.setAttributeNode(elem_atr_2_2);
            var elem_atr_2_3=document.createAttribute("value");
            elem_atr_2_3.value=item.date;
            elem_2.setAttributeNode(elem_atr_2_3);
            var elem_atr_2_4=document.createAttribute("readonly");
            elem_2.setAttributeNode(elem_atr_2_4);
            form.appendChild(elem_2);

            var elem_3 = document.createElement("input");
            var elem_atr_3_1=document.createAttribute("id");
            elem_atr_3_1.value="ad_description"+item.id;
            elem_3.setAttributeNode(elem_atr_3_1);
            var elem_atr_3_2=document.createAttribute("type");
            elem_atr_3_2.value="text";
            elem_3.setAttributeNode(elem_atr_3_2);
            var elem_atr_3_3=document.createAttribute("value");
            elem_atr_3_3.value=item.description;
            elem_3.setAttributeNode(elem_atr_3_3);
            var elem_atr_3_4=document.createAttribute("readonly");
            elem_3.setAttributeNode(elem_atr_3_4);
            form.appendChild(elem_3);


            var elem_4 = document.createElement("input");
            var elem_atr_4_1=document.createAttribute("id");
            elem_atr_4_1.value="ad_kind"+item.id;
            elem_4.setAttributeNode(elem_atr_4_1);
            var elem_atr_4_2=document.createAttribute("type");
            elem_atr_4_2.value="text";
            elem_4.setAttributeNode(elem_atr_4_2);
            var elem_atr_4_3=document.createAttribute("value");
            if (item.kind==="dog"){
                elem_atr_4_3.value="Собака";
            }else {
                elem_atr_4_3.value="Кошка";
            }
            elem_4.setAttributeNode(elem_atr_4_3);
            var elem_atr_4_4=document.createAttribute("readonly");
            elem_4.setAttributeNode(elem_atr_4_4);
            form.appendChild(elem_4);

            var elem_5 = document.createElement("input");
            var elem_atr_5_1=document.createAttribute("id");
            elem_atr_5_1.value="ad_place"+item.id;
            elem_5.setAttributeNode(elem_atr_5_1);
            var elem_atr_5_2=document.createAttribute("type");
            elem_atr_5_2.value="text";
            elem_5.setAttributeNode(elem_atr_5_2);
            var elem_atr_5_3=document.createAttribute("value");
            elem_atr_5_3.value=item.place;
            elem_5.setAttributeNode(elem_atr_5_3);
            var elem_atr_5_4=document.createAttribute("readonly");
            elem_5.setAttributeNode(elem_atr_5_4);
            form.appendChild(elem_5);

            var elem_6 = document.createElement("input");
            var elem_atr_6_1=document.createAttribute("id");
            elem_atr_6_1.value="ad_sex"+item.id;
            elem_6.setAttributeNode(elem_atr_6_1);
            var elem_atr_6_2=document.createAttribute("type");
            elem_atr_6_2.value="text";
            elem_6.setAttributeNode(elem_atr_6_2);
            var elem_atr_6_3=document.createAttribute("value");

            switch (item.sex){
                case "boy":
                    elem_atr_6_3.value="Мальчик";
                    break;
                case "girl":
                    elem_atr_6_3.value="Девочка";
                    break;
                case "indefined":
                    elem_atr_6_3.value="Не определен";
                    break;
            }
            elem_6.setAttributeNode(elem_atr_6_3);
            var elem_atr_6_4=document.createAttribute("readonly");
            elem_6.setAttributeNode(elem_atr_6_4);
            form.appendChild(elem_6);


            var elem_8 = document.createElement("input");
            var elem_atr_8_1=document.createAttribute("id");
            elem_atr_8_1.value="ad_situation"+item.id;
            elem_8.setAttributeNode(elem_atr_8_1);
            var elem_atr_8_2=document.createAttribute("type");
            elem_atr_8_2.value="text";
            elem_8.setAttributeNode(elem_atr_8_2);
            var elem_atr_8_3=document.createAttribute("value");
            if (item.situation==="find"){
                elem_atr_8_3.value="Нашелся(-ась)";
            }else{
                elem_atr_8_3.value="Потерялся(-ась)";
            }
            elem_8.setAttributeNode(elem_atr_8_3);
            var elem_atr_8_4=document.createAttribute("readonly");
            elem_8.setAttributeNode(elem_atr_8_4);
            form.appendChild(elem_8);


            var elem_7 = document.createElement("p");
            var elem_atr_7_1=document.createAttribute("id");
            elem_atr_7_1.value="ad_text"+item.id;
            elem_7.setAttributeNode(elem_atr_7_1);

            if(item.status==="INACTIVE"){
                var textElem = document.createTextNode("Объявление будет добавлено после проверки");
            }else
            {
                var textElem = document.createTextNode("Активно");
            }
            elem_7.appendChild(textElem);
            form.appendChild(elem_7);

            var br = document.createElement("br");
            form.appendChild(br);


            var elem_11 = document.createElement("button");
            var elem_atr_11_1=document.createAttribute("id");
            elem_atr_11_1.value="ad_delete"+item.id;
            elem_11.setAttributeNode(elem_atr_11_1);
            var elem_11_Text = document.createTextNode("Удалить");
            elem_11.appendChild(elem_11_Text);
            form.appendChild(elem_11);


/*ПЕРЕПИСАТЬ ЛОГИКУ КНОПОК*/


            $("#"+elem_atr_11_1.value).click(function (e){
                var actionUrl = "/ad/delete";
                var data = JSON.stringify({ "id":item.id,"kind": item.kind, "sex": item.sex,"photo":item.photo , "date":item.date, "place":item.place, "situation":item.situation , "description":item.description, "user_description":item.user_description});
                $.ajax({
                    url:actionUrl,
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type:"POST",
                    data:data,
                    success: function () {

                        alert("Объявление успешно удалено");
                        document.location="/MainWindow"
                    },
                    error:function (er) {
                        alert("Ошибка ответа сервера"+er);
                        console.error(er);
                    }
                })
            });



// // создаем для него текст
//             var elemText = document.createTextNode(item.id);
// // добавляем текст в элемент в качестве дочернего элемента
//             elem.appendChild(elemText);
// добавляем элемент в блок div

            // var br = document.createElement("br");
            articleDiv.appendChild(br);
        });

    },
    error: function (er) {
        alert("Ошибка получения объявлений");
        console.error(er);
    }
});