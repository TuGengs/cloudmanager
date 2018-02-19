//添加检查方式 签到检查；寝室检查；系统导入
function importCheck(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI1=document.getElementsByClassName("ok")[0];
	var oDiv1=document.getElementsByClassName("input-num")[0];
	var aI=oDiv1.getElementsByTagName("i");
	var oa=document.getElementsByClassName("import")[0];
	var info=document.getElementsByClassName("check1-way1")[0];
	var oI=document.getElementById("add-check1-way");
	var oDiv=document.getElementsByClassName("check1-way")[0];
	//var m1=1;
	var n1=1;
	oI1.onclick=function(){
		if(oDiv1.style.display=="block"){
			oDiv1.style.display="none";
			oI1.style.color="#666";
			//m1=0;
			}
		else{
			oDiv1.style.display="block";
			oI1.style.color="#0871e7";
			//m1=1;
			} 
		}
	for(var i=0;i<aI.length;i++){
		aI[i].style.color="#535353";
		aI[i].onclick=function(){
			if(n1){
			this.style.color="#0871e7";
			n1=0;
			}else{
			this.style.color="#535353";	
			n1=1;
				}
			}
		}
	oa.onclick=function(){
		this.parentNode.style.display="none";
		info.style.display="none";
		oDiv.style.display="none";
		oI.style.color="#666";
		oI1.style.color="#666";
		for(var i=0;i<aI.length;i++){
		aI[i].style.color="#535353";
			}
		}
	}

addLoadEvent(importCheck);
