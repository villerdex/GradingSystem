/**
 * Created by Didoy on 4/5/2016.
 JS library for utility use.
 

 */

/* 
return true if the element is visible under
the window viewport
*/ 

function visible(elem) {
    var el = $(elem);
    var win = $(window);
    var isAppear = false;

        var elemOffSetTop =  el.offset().top;
        var elemHeight = elemOffSetTop + el.outerHeight();

        var windowTop = win.scrollTop();
        var windowHeight  = windowTop + win.height();

                if( windowHeight > elemOffSetTop ){
                    isAppear = true;
                }
                if (windowTop >= elemHeight){
                    isAppear = false;
                }

    return isAppear;
}

/*
return true if the element is ontop of the window
specially use to check if an div.offset().top == windowTop
*/
function isOnTop(elem){
        var el = $(elem);
        var win = $(window);
        
        var elemOffSetTop =  el.offset().top;
        var elemHeight = elemOffSetTop + el.outerHeight();

        var windowTop = win.scrollTop();
        var windowHeight  = windowTop + win.height();
        
        var isonTop = false;
        
        if(windowTop <= elemOffSetTop && windowHeight >= elemHeight){
            isonTop = true;
        }else{
            isonTop = false;
        }
    return isonTop;
}

// apply css object to an elements
function applyCss(elements, css) {
    $.each(elements, function ( index, element) {
        $(element).css(css);
    });
}

// add class to element Array
function addClass(elements, cssClass) {
    $.each(elements, function ( index, element) {
        $(element).addClass(cssClass);
    })
};

// add class to element Array using selector
function addClassFromSelector(selector, cssClass) {
    $.each(selector, function ( index, element) {
        $(element).addClass(cssClass);
    })
};

// remove class from element
function removeClass(elements, cssClass) {
    $.each(elements, function ( index, element) {
                if($.isArray(element)){
                    console.log("is Array");
                    //removeClass(element, cssClass)
                }else {
                    $(element).removeClass(cssClass);
                }
    });
}

// remove Class from elements using selector
function removeClassFromSelector(selector, cssClass) {
    
    $(selector).each( function(index, element){
        $(element).removeClass(cssClass);
    });
};


function Test(element) {
    element.css('border', '1px solid red');
};

function isOnAnimate(element){
    var isAnimate = false;
    if( element.is(':animated')){
        isAnimate = true;
    }else{
        isAnimate = false;
    }
    console.log("isAnimate: " + isAnimate);
    return isAnimate;
}

/**
basic mouse parallax function 
    overlaySelector : top lay of target element
    target : the target element to perform parallax
    
    usage: create a class with overflow hidden that will contain the 
    target element. 
    
    note: change the strength of parallax by change the values of divisor
*/
function mouseInteractive(initiatorElement, target, strength){
    
          $(initiatorElement).on({
            mousemove: function (e) {
                var amountMovedX = (e.pageX * -1 / strength);
                var amountMovedY = (e.pageY * -1 / 20);

                $(target).css({
                    '-webkit-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-moz-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-ms-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-o-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    'transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)'
                });

                amountMovedX = (e.pageX * -1 / strength);
                amountMovedY = (e.pageY * -1 / 20);
                
                $(target).css({
                    '-webkit-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-moz-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-ms-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    '-o-transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)',
                    'transform': 'translate3d(' + amountMovedX + 'px,' + amountMovedY + 'px, 0)'
                });
            },

            mouseleave: function (e) {
                 $(target).css({});
            }   
        });

}

/**
initiatorElement : element which will trigger the event
selector : all the elements which will apply mouseparallax
strength : array which will be strength of mouse distance conce
*/
function mouseParallax(initiatorElement, selector, strength){
                
        $(selector).each(function(index, element){
             mouseInteractive(initiatorElement, element, strength[index] );
        });
}

// ANIMATIONS //
function setElementsPropertiesForAnimation(element, cssAnimate){
      if(cssAnimate == "fade-in" ){
            $(element).css("opacity", "0");
       }
       else if(cssAnimate == "fade-out"){
            $(element).css("opacity", "1");
       }
       else if(cssAnimate.indexOf("slide") != -1){
            $(element).css({
                "left": "0px",
                "right": "0px",
                "bottom": "0px",
                "top": "0px"
            });
           
           console.log("slide animation found");
       }else{
            console.log("animation class not found");
       }
}

function animate(selector, cssAnimate){
      var wintop = $(window).scrollTop(); 
 
                $(selector).each(function(index , element){
                    
                if($(element).hasClass(cssAnimate)) { return true; } 
                setElementsPropertiesForAnimation(element, cssAnimate);
                    
                              var topcoords = $(element).offset().top;  
                              var winheight = $(window).height();

                              if(wintop > (topcoords - (winheight* .75))) {
                                $(element).addClass(cssAnimate);

                              }
                });
        
}

// GENERATING DOM //

function addOverLay(target, title, CSSclass, marginTop ){
    var targetElement = $(target);
    var parentHeight = targetElement.height();
    
    var div = "<div> <h3>"+ title +"</h3></div>";
     
    div = $(div).addClass(CSSclass);
    div = $(div).css({
        'height': parentHeight + "px",
        'top': marginTop + "px"
        });
    
    $(targetElement).css('overflow', 'hidden');
    $(targetElement).append(div);
    console.log("append is done");
}


function clearForm() {
    return this.each(function () {
        var tag = this.tagName.toLocaleLowerCase();
        var type = this.type();

        if (tag == 'form')
            return $(':input',this).clearForm();
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = '';
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
}

$.fn.clearForm = function () {
     this.each(function () {
        var tag = this.tagName.toLocaleLowerCase();
        var type = this.type;

        if (tag == 'form')
            return $(':input',this).clearForm();
        if (type == 'text' || type == 'password' || tag == 'textarea')
            this.value = '';
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
}