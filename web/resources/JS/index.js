$(document).ready(function(){
    
    var modal = $('#modal-signup');
    
    // show modal for signup
    $("#sign-up").click(function(){
          modal.each(function () {
        this.style.setProperty( 'display', 'block', 'important' );
          });
    });
    
    modal.click(function(event){
        if(event.target == modal[0]){
            this.style.setProperty( 'display', 'none', 'important' );
        }
    });
    
    var loginBtn = $('#login');
    var modalLogin = $('#modal-login');
    
    // show modal for login
     loginBtn.click(function(){
         console.log(modalLogin.first());
            modalLogin[0].style.setProperty( 'display', 'block', 'important' );
        });
    
       modalLogin.click(function(event){
        if(event.target == modalLogin[0]){
            this.style.setProperty( 'display', 'none', 'important' );
        }
    });

 

});

function closePopOverWhenFocus(selector) {
    $(selector).focusin(function () {
        $(this).popover('hide');
    });
}