/**
 * Created by Franklord on 2016/10/25.
 */
$(window).scroll(function() {
    $(".banner").css("position","fixed");
    $(".center").css({ 'position':'relative',"top":"960px"});
    var sc=$(document);
    if(sc.scrollTop()>=60) {
        $(".top").css("background","rgba(0, 0, 0, 0.66)");
        $(".trademark-list img").css("opacity","0");
    } else {
        $(".top").css("background","rgba(0, 0, 0, 0.66)");
        $(".trademark-list img").css("opacity","1");
    }
})

$(function() {
    $(".more-box").hover(function() {
        $(this).find("img").stop().animate({top:-58},500);
        $(".a_txt").css( "color","#cd0707")
    },function() {
        $(this).find("img").stop().animate({top:0},500);
        $(".a_txt").css( "color","#fff")
    });
    $(".more-box1").hover(function(){
        $(this).find("img").stop().animate({top:-58},500);
        $(".a_txt1").css( "color","#cd0707")
    },function(){
        $(this).find("img").stop().animate({top:0},500);
        $(".a_txt1").css( "color","#fff")
    });
})







// slide  鼠标经过滑动
$(document).ready(function(){


    //nav sliding
    $('ul.nav0>li').hover(function(){
        $(".cn",this).stop().animate({top:'-30px',left:'0'},{queue:false,duration:300});
    },function(){
        $(".cn",this).stop().animate({top:'0px',left:'0px'},{queue:false,duration:300});

    });

    $('ul.nav0>li').hover(function(){
        $(".en",this).stop().animate({bottom:'0',left:'0'},{queue:false,duration:300});
    },function(){
        $(".en",this).stop().animate({bottom:'-30px',left:'0px'},{queue:false,duration:300});
    });


})

//nav 导航指向栏目ID
$(document).ready(function($){

//index
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_index').click(function(){
        $body.animate({scrollTop:($('#indexs').offset().top-100)},500);
        return false;
    });
//about
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_about').click(function(){
        $body.animate({scrollTop:($('#about').offset().top-100)},500);
        return false;
    });
//food
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_food').click(function(){
        $body.animate({scrollTop:($('#food').offset().top-100)},500);
        return false;
    });

//news
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_news').click(function(){
        $body.animate({scrollTop:($('#news').offset().top-100)},500);
        return false;
    });
//store
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_store').click(function(){
        $body.animate({scrollTop:($('#store').offset().top-100)},500);
        return false;
    });


//contact
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_contact').click(function(){
        $body.animate({scrollTop:($('#contact').offset().top-100)},500);
        return false;
    });

//join
    $body=(window.opera)?(document.compatMode=="CSS1Compat"?$('html'):$('body')):$('html,body');
    $('#nav_join').click(function(){
        $body.animate({scrollTop:($('#join').offset().top-100)},500);
        return false;
    });

})







