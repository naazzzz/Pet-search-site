//Запрос на получение объявлений
var actionUrl = "/ad/allActive?status=ACTIVE";
var data = "status=Active"
$.ajax({
    url:actionUrl,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    type:"POST",
    // data:data,
    success: function (response) {
        // console.log(response[1].place);

        // Функция ymaps.ready() будет вызвана, когда
// загрузятся все компоненты API, а также когда будет готово DOM-дерево.
        ymaps.ready(init);
        function init(){
            // Создание карты.
            var geolocation = ymaps.geolocation,
                myMap = new ymaps.Map('map', {
                    center: [55, 34],
                    zoom: 1
                }, {
                    searchControlProvider: 'yandex#search'
                });


            response.forEach(function(item, i, arr) {
                var address = item.place;
                if(item.kind==='cat') {
                    ymaps.geocode(address).then(function (res) {
                        var coord = res.geoObjects.get(0).geometry.getCoordinates();

                        var myPlacemark = new ymaps.Placemark(coord, null, {
                            // Опции.
                            // Необходимо указать данный тип макета. Показываем что это изображение.
                            iconLayout: 'default#image',
                            // Своё изображение иконки метки. Указываем путь до картинки
                            iconImageHref: '/img/cat.png',
                            // Размеры метки.
                            iconImageSize: [40, 40]
                        });
                        myMap.geoObjects.add(myPlacemark);
                    });
                }
                if(item.kind==='dog') {
                    ymaps.geocode(address).then(function (res) {
                        var coord = res.geoObjects.get(0).geometry.getCoordinates();

                        var myPlacemark = new ymaps.Placemark(coord, null, {
                            // Опции.
                            // Необходимо указать данный тип макета. Показываем что это изображение.
                            iconLayout: 'default#image',
                            // Своё изображение иконки метки. Указываем путь до картинки
                            iconImageHref: '/img/dog.png',
                            // Размеры метки.
                            iconImageSize: [40, 40]
                        });
                        myMap.geoObjects.add(myPlacemark);
                    });
                }
            });

            // Сравним положение, вычисленное по ip пользователя и
            // положение, вычисленное средствами браузера.
            geolocation.get({
                provider: 'yandex',
                mapStateAutoApply: true
            }).then(function (result) {
                // Красным цветом пометим положение, вычисленное через ip.
                result.geoObjects.options.set('preset', 'islands#redCircleIcon');
                result.geoObjects.get(0).properties.set({
                    balloonContentBody: 'Мое местоположение'
                });
                myMap.geoObjects.add(result.geoObjects);
            });
            geolocation.get({
                provider: 'browser',
                mapStateAutoApply: true
            }).then(function (result) {
                // Синим цветом пометим положение, полученное через браузер.
                // Если браузер не поддерживает эту функциональность, метка не будет добавлена на карту.
                result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
                // result.geoObjects.options.set('iconImageHref', './cat.png');
                myMap.geoObjects.add(result.geoObjects);
            });
        }


    },
    error:function (er) {
        alert("Ошибка регистрации");
        console.error(er);
    }

})



