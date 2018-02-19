//添加检查方式 签到检查；寝室检查；系统导入
function addCheckWay(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI=document.getElementById("add-check1-way");
	var oDiv=document.getElementsByClassName("check1-way")[0];
	var oIdWay=document.getElementsByClassName("id-way")[0];
	var check=document.getElementsByClassName("syt-import")[0];
	var info=document.getElementsByClassName("check1-way1")[0];
oI.onclick=function(){
		if(oDiv.style.display=="block"){
			oDiv.style.display="none";
			oI.style.color="#666";
			}
		else{
			oDiv.style.display="block";
			oI.style.color="#0871e7";
			} 
		}
check.onclick=function(){
		if(info.style.display=="block"){
			info.style.display="none";
			}
		else{
			info.style.display="block";
			} 
		}
	}
addLoadEvent(addCheckWay);

