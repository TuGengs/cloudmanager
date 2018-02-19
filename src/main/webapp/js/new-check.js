//添加检查方式 签到检查；寝室检查；系统导入
function addCheckInfo(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI=document.getElementsByClassName("add-more-info")[0];
	var oDiv=document.getElementsByClassName("font add-more")[0];
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
addLoadEvent(addCheckInfo);
