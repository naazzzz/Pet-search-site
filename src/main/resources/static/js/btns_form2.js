document.getElementById('input__file').addEventListener('change', function(){
    if( this.value ){
      document.getElementById('form_download').className="btn_download";
      document.getElementById('miss_form').className="miss_form_active";
      document.getElementById('invisible_block').className="invisible_block_active";
      let f = document.getElementById('input__file').files[0];
      if (f) {
        document.getElementById('photo_download').src = URL.createObjectURL(f);
        localStorage.setItem('myImage', photo_download.src);
        
    }      
    } else { // Если после выбранного тыкнули еще раз, но дальше cancel
      console.log( "Файл не выбран" ); 
    }
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
  });

