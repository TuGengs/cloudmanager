// JavaScript Document
function addLoadEvent(func){
	var oldfunc=window.onload;
	if(typeof window.onload!="function"){
		window.onload=func;
		}else{
			window.onload=function(){
				oldfunc();
				func();
				}
			}
	}

