<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
 <meta charset="utf-8">
    <title>设置</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sign-in1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/set.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/jquery-weui.css">
</head> 
    
    <header>设置</header>
    <div id="page-container">
            <h1 id="gongkai"><em>1</em> <strong>公开</strong></h1>
            <input type="hidden" value="${check_id }" id="check_id"/>
        <ul class="open">
            <li><em>账 号：</em>  ${public_data.account }</li>
            <li><em>密 码</em>  <input class="caname" type='text' disabled = 'true' value='${public_data.password }'/>  <i class="chu"></i>
            </li>
            <li><em>二 维 码</em>
                <br><i id="code1" style="font-size: 60px; padding-left: 8px;"></i><i id="code2" style="font-size:150px; display:none;"></i>
            </li>
            <li class="clear" style="list-style:none;"><em class="lt">不 再 公 开</em>
            
                <div class="checkboxThree lt">
                    <input type="checkbox" id="checkboxThreeInput" name="status" <c:if test="${public_data.status==0 }">checked</c:if>>
                    <label for="checkboxThreeInput"></label>
                </div>
            </li>
            <li><em id="no">查 看 者 名 单</em>  <i></i>

                <ul class="close">
                    <c:forEach items="${viewer_data.list }" var="item">
                    	<li><em>${item.name }</em><em>${item.sysnum }</em><input type="hidden" value="${item.user_id }" /><i class="delete"></i>
                    	</li>
                    </c:forEach>
                    
                </ul>
            </li>
        </ul>
            <h1 id="fuzhu"><em>2</em> <strong>分配辅助</strong></h1>

        <div id="assign">
        	<div id="helper" style="display: block;">
            <c:forEach items="${helper_data.list }" var="item">
            	<ul class="create">
            	<li class="size">名称：<span>${item.manager_class}</span></li><li class="size">姓名：<span>${item.name}</span></li><li class="size">管理者名单 ${item.num}人</li>
            	<input type="hidden" value="${item.sysnum }" class="sysnum"/>
            	<span class = 'create-delete'>x</span>
            	</ul>
            </c:forEach>
            </div>
            <ul class="shuru">
                <li>
                    <p>手机号码: <input type="tel" placeholder="tel" id="tel"></p>
                </li>
                <input type="submit" class="submit" value="确认"> <em class="shan">x</em>
            </ul>
            
            <input type="text" class="add" value="+ 添加管理人员">
        </div>
        <div id="meng"></div>
        <form>
            <ul id="kuang">
                <li class="banji">
                	<c:forEach items="${manager_data.data }" var="item">
                	<p><i></i> ${item.class_name}</p>
                    <div class="checkboxFour" id="all">
                        <label for="checkboxFourInput"></label>
                    </div>
                    <p class="number"><span>${item.num }</span> </p>
                    <ul class="list">
                    <c:forEach items="${item.list }" var="data">
                    	
                        <li class="list-item"><em>${data.name }</em><em>${data.sysnum }</em>
							<input type="hidden" value="${data.user_id }" />
                            <div class="checkboxFour">
                                <label for="checkboxFourInput"></label>
                            </div>
                        </li>
                        
                    
                    </c:forEach>
                    </ul>
                	</c:forEach>
                    
                </li>
                <p class="que">
                    <input type="button" value="确定">
                </p>
            </ul>
        </form>
            
<h1 id="zhouqi"><em>3</em> <strong>设置周期</strong></h1>
 
            <ul class="period">
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="time4" class="weui-label">开始日期</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" id="time4" type="text" style="border:none;" value="${cycle_data.start }">
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd">
                            <label for="date" class="weui-label">周期天数</label>
                        </div>
                        <div class="weui-cell__bd">
                            <input class="weui-input" id="mobile" type="text" style="border:none;" value="${cycle_data.date }">
                        </div>
                    </div>
                </div>
                <div id="time-container"></div>
                <li class="clear">
                    <p id="kou">扣分规则</p>
                    <ul class="zuo lt">
                        <li class="clear"><em class="lt">缺到</em>
 
                            <div class="one lt">
                                <label for="oneInput" class="label"></label>
                            </div>
                        </li>
                        <li class="clear"><em class="lt">迟到</em>
 
                            <div class="one lt">
                                <label for="oneInput"></label>
                            </div>
                        </li>
                        <li class="clear"><em class="lt">请假</em>
 
                            <div class="one lt">
                                <label for="oneInput"></label>
                            </div>
                        </li>
                    </ul>
                    <ul class="rule rt">
                        <li id="rule">
                            <p class="clear"><em>缺到次数</em><em>扣除分值</em></p>
                            <div class="decutMark-container">
                            	<c:if test="${cycle_data!=null && cycle_data.absence!=null }">
                            	<c:forEach items="${cycle_data.absence }" var="item">
                                <p class="decutMark absence"> 
                                    <em class="num">${item.key }</em>
                                    <em class="score">${item.value }</em>
                                    <i class="decute"></i>
                                </p>
                                </c:forEach>
                                </c:if>
                            </div>
                            <div class="reson-container">
	                            <input class="weui-input reson" id="absence" type="text" style="border:none;" value="1">
	                            <input class= "weui-input reson" id="absence-num" type="text" />
	                            <i id="submit-absence"></i>
                            </div>
                        </li>
                        <li class="plus"><i></i> 添加</li>
                    </ul>
                    <ul class="rule rt">
                        <li id="rule">
                            <p class="clear"><em>迟到次数</em><em>扣除分值</em></p>
                            <div class="decutMark-container">
                            	<c:if test="${cycle_data!=null && cycle_data.late!=null }">
                            	<c:forEach items="${cycle_data.late }" var="item">
                                <p class="decutMark late"> 
                                    <em class="num">${item.key }</em>
                                    <em class="score">${item.value }</em>
                                    <i class="decute"></i>
                                </p>
                                </c:forEach>
                                </c:if>
                            </div>
                            <form class="add-decutMark" action="" method="">
                                <input class="weui-input reson" id="late" type="text" style="border:none;" value="1">
                                <input class= "weui-input reson" id="late-num" type="text" />
                            </form>
                        </li>
                        <li class="plus"><i></i> 添加</li>
                    </ul>
                   
                   <ul class="rule rt">
                        <li id="rule">
                            <p class="clear"><em>请假次数</em><em>扣除分值</em></p>
                            <div class="decutMark-container">
                            	<c:if test="${cycle_data!=null && cycle_data.leave!=null }">
                            	<c:forEach items="${cycle_data.leave }" var="item">
                                <p class="decutMark leave"> 
                                    <em class="num">${item.key }</em>
                                    <em class="score">${item.value }</em>
                                    <i class="decute"></i>
                                </p>
                                </c:forEach>
                                </c:if>
                            </div>
                            <form class="add-decutMark" action="" method="">
                                <input class="weui-input reson" id="leave" type="text" style="border:none;" value="1">
                                <input class= "weui-input reson" id="leave-num" type="text" />
                            </form>
                        </li>
                        <li class="plus"><i></i> 添加</li>
                    </ul>
                   
                   
                    
                </li>
            </ul>
<a href="setting/edit?check_id=${check_id }"><h1><em>4</em> <strong>编辑课程</strong></h1></a>
 
             
<h1 id="san"><em>5</em> <strong>解散课程</strong></h1>
 
            <div id="meng1"></div>
            <div class="kuang1">
                <form action="setting/dis?check_id=${check_id }" method="post">
                    <p>是否删除该课程？</p>
                    <p class="btn">
                        <input type="cancel" value="取消">
                        <input type="submit" value="确定">
                    </p>
                </form>
            </div>
        </div>
        
        
    <div class="bottom">
        <ul>
            <li id="assistant-sign"> 
               <i></i>
				<a href="${pageContext.request.contextPath }/check/sign/single/${check_id}">
                <p>检查</p>
                </a>
            </li>
            
            <li>   
             <i></i>
				<a href="${pageContext.request.contextPath }/check/sign/single/list?check_id=${check_id}">
                <p>名单</p>
                </a>
            </li>
            <li>    <i></i>

                <p>设置</p> <em></em>

            </li>
        </ul>
    </div>

    <script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath }/js/set.js"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-weui.js"></script>
    <script src="${pageContext.request.contextPath }/js/fastclick.js"></script>
</body>
</html>
