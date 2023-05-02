// var myMap, myPlacemark;
//
// ymaps.ready(init);
//
// function init() {
//     var location = ymaps.geolocation;
//     var coord;
//     // Получение местоположения и автоматическое отображение его на карте.
//     location.get({
//         mapStateAutoApply: true
//     })
//         .then(
//             function(result) {
//                 // Получение местоположения пользователя.
//                 var userCoodinates = document.location.getElementById("map_coord").textContent;
//
//                 console.log(userCoodinates);
//                 myMap = new ymaps.Map("map", {
//                     center: [userCoodinates[0], userCoodinates[1]],
//                     zoom: 14,
//                     behaviors: ['scrollZoom', 'drag']
//                 }, {
//                     searchControlProvider: 'yandex#search'
//                 });
//
//                 myPlacemark = new ymaps.Placemark([userCoodinates[0], userCoodinates[1]], {}, {});
//                 myMap.geoObjects.add(myPlacemark);
//
//             },
//             function(err) {
//                 console.log('Ошибка: ' + err)
//             }
//         );
//
// }

var myMap, myPlacemark;

ymaps.ready(init);

function init() {
    var location = ymaps.geolocation;
    var coord;
    // Получение местоположения и автоматическое отображение его на карте.
    location.get({
        mapStateAutoApply: true
    })
        .then(
            function(result) {
                // Получение местоположения пользователя.
                var userAddress = result.geoObjects.get(0).properties.get('text');
                var userCoodinates = document.getElementById("map_coord").textContent.split(",");


                // Пропишем полученный адрес в балуне.
                result.geoObjects.get(0).properties.set({
                    balloonContentBody: 'Адрес: ' + userAddress +
                        '<br/>Координаты:' + userCoodinates
                });
                coord=userCoodinates;
                // console.log("Адрес:" + userAddress +"\n" +
                //     "<br/>Координаты:" + userCoodinates);
                // // myMap.geoObjects.add(result.geoObjects)
                myMap = new ymaps.Map("map", {
                    center: [userCoodinates[0], userCoodinates[1]],
                    zoom: 14,
                    behaviors: ['scrollZoom', 'drag']
                }, {
                    searchControlProvider: 'yandex#search'
                });

                myPlacemark = new ymaps.Placemark([userCoodinates[0], userCoodinates[1]], {}, {
                    draggable: true
                });
                $("#place").attr("value", userCoodinates);
                myMap.geoObjects.add(myPlacemark);

                myPlacemark.events.add("dragend", function (e) {
                    coords = this.geometry.getCoordinates();
                    savecoordinats();
                }, myPlacemark);
            },
            function(err) {
                console.log('Ошибка: ' + err)
            }
        );
    // var coord=userCoodinates.split(",");
    // console.log(coord);






}

function savecoordinats() {
    var new_coords = [coords[0].toFixed(6), coords[1].toFixed(6)];
    //console.log(new_coords);
    document.getElementById("place").value = new_coords;
}