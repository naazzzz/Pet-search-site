for (let i = 0; i < window.localStorage.length; i++) {
if (window.localStorage.key(i)==="photo"){
    var parentDiv = document.getElementById('preview');
    document.getElementById('form_download').className="btn_download";
    document.getElementById('miss_form').className="miss_form_active";
    document.getElementById('invisible_block').className="invisible_block_active";
    document.getElementById('photo_download').src = localStorage.getItem("photo");
    var button = document.createElement("button");
    button.innerHTML = "Удалить фото";
    button.setAttribute('class', 'deleteButton');
    button.setAttribute('id', 'deleteButton');
    button.onclick=function(){
        localStorage.removeItem('photo');
        document.getElementById('form_download').className="";
        document.getElementById('miss_form').className="miss_form";
        document.getElementById('invisible_block').className="invisible_block";
        document.getElementById("deleteButton").remove();
    };
    parentDiv.append(button);
}
}
document.getElementById('input__file').addEventListener('change', function (elementId){
    var reader = new FileReader();
    var name = event.target.files[0].name;

    reader.addEventListener("load", function () {
        if (this.result && localStorage) {
            window.localStorage.setItem("photo", this.result);
        } else {
            alert();
        }
    });
    reader.readAsDataURL(event.target.files[0]);
    if( this.value ){
      document.getElementById('form_download').className="btn_download";
      document.getElementById('miss_form').className="miss_form_active";
      document.getElementById('invisible_block').className="invisible_block_active";
      let f = document.getElementById('input__file').files[0];
        var parentDiv = document.getElementById('preview');
        if (f) {
              document.getElementById('photo_download').src = URL.createObjectURL(f);
              const res = document.getElementById('photo_download').src;
              var button = document.createElement("button");
              button.innerHTML = "Удалить фото";
              button.setAttribute('class', 'deleteButton');
              button.setAttribute('id', 'deleteButton');
              button.onclick=function(){
                  localStorage.removeItem('photo');
                  document.getElementById('form_download').className="";
                  document.getElementById('miss_form').className="miss_form";
                  document.getElementById('invisible_block').className="invisible_block";
                  document.getElementById("deleteButton").remove();
              };
              parentDiv.append(button);

    }
    } else { // Если после выбранного тыкнули еще раз, но дальше cancel
      console.log( "Файл не выбран" ); 
    }

  });

document.getElementById("miss_form").addEventListener("click", function (){
   localStorage.setItem("photo","none");
});

document.getElementById('btn_next1').addEventListener('click',function(){


    let params = window
        .location
        .search
        .replace('?','')
        .split('&')
        .reduce(
            function(p,e){
                let a = e.split('=');
                p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                return p;
            },
            {}
        );

    document.location='/MainWindow/CreateAd?form='+ params['form'] +'&step=3';
});
document.getElementById("close_btn").addEventListener("onclick", function (){

    document.location='/MainWindow/CreateAd';

});