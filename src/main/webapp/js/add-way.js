// 添加签到方式：扫一扫和二维码
function addSignWay(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI=document.getElementById("add-sign-way");
	var oDiv=document.getElementsByClassName("sign-way")[0];
	var m=1;
	oI.onclick=function(){
		if(m){
			oDiv.style.display="block";
			oI.style.color="#0871e7";
			m=0;
			}
		else{
			oDiv.style.display="none";
			oI.style.color="#666";
			m=1;
			} 
		}
	}
//添加检查方式 签到检查；寝室检查；系统导入
function addCheckWay(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI1=document.getElementById("add-check1-way");
	var oDiv1=document.getElementsByClassName("check1-way")[0];
	var m1=1;
	oI1.onclick=function(){
		if(m1){
			oDiv1.style.display="block";
			oI1.style.color="#0871e7";
			m1=0;
			}
		else{
			oDiv1.style.display="none";
			oI1.style.color="#666";
			m1=1;
			} 
		}
	}
addLoadEvent(addSignWay);
addLoadEvent(addCheckWay);