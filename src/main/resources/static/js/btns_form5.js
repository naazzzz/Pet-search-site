
//showImages();
function showImages() {
    var img = document.getElementById("img");

    var parentDiv = document.getElementById('preview');
    if (parentDiv.contains(img)) {
        parentDiv.prepend(img);
    } else if (parentDiv.contains(img) === false) {
        for (let i = 0; i < window.localStorage.length; i++) {
            let res = window.localStorage.getItem(window.localStorage.key(i));
            var image = new Image();
            var button = document.createElement("button");
            button.innerHTML = "Delete";
            button.setAttribute('class' , 'deleteButton');
            //button.setAttribute('onclick',DeleteImage('${window.localStorage.key(i)}'));
                    image.src = res;
                    image.setAttribute('id', 'img');
                    parentDiv.append(image);
                    parentDiv.append(button);
                }
        }
}