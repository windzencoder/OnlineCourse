/*TMODJS:{"version":15,"md5":"a250ef0d539eeb0edc59c77eec261bac"}*/
template('classify',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,allClassify=$data.allClassify,item=$data.item,$index=$data.$index,$escape=$utils.$escape,subItem=$data.subItem,$out='';$out+=' <div class=main-category> <div class="main-bg"> <div class="main-bg-item " style="background-image: url(\'./res/i/c1.jpg\');"> <div class="main-title-1"></div> </div> <div class="main-bg-item " style="background-image: url(\'./res/i/c2.jpg\');"> <div class="main-title-2"></div> </div> <div class="main-bg-item " style="background-image: url(\'./res/i/c3.jpg\');"> <div class="main-title-3"></div> </div> </div> <div class="f-nav-box"> <div class="bg-nav"> <a class="cur"></a> <a></a> <a></a> </div> </div> <div class="main-category-menu">  ';
$each(allClassify,function(item,$index){
$out+=' <div class="category" c-id="';
$out+=$escape(item.code);
$out+='"> <a><div class="group">';
$out+=$escape(item.name);
$out+='</div></a> </div> ';
});
$out+=' </div>  ';
$each(allClassify,function(item,$index){
$out+=' <div class="main-category-submenu-content" id="';
$out+=$escape(item.code);
$out+='"> ';
$each(item.subClassify,function(subItem,$index){
$out+=' <a class="submenu-item" > ';
$out+=$escape(subItem.name);
$out+=' / </a> ';
});
$out+=' </div> ';
});
$out+=' </div> ';
return new String($out);
});