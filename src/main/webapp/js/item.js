/**
 * Created by victor on 2017/1/21 0021.
 */

(function(){
    var actionDatas = [{
        text: "卫生脏乱",
        onClick: function() {
            //do something
            var text = this.text;
            insertItem(text);
        }
    },{
        text: "人太丑",
        onClick: function(e) {
            var text = this.text;
            insertItem(text);
        }
    }]
    function insertItem(text){
        removeEvent(containar.find(".delete"),'click',deleteItem)
        $(domDatas.result).append('<div class="add-item clearfix">\
                    <div class="name col-6">'+domDatas.name+'</div>\
                    <div class="col-6 delete" > '+text+'<span class="ico subtract">+</span></div>\
                    </div>');
        containar.find(".save").css('display','block');
        addEvent(containar.find(".delete"),'click',deleteItem)

        containar.find(".subtract").show();
    }
    var domDatas={
        result:null,
        name:null,
        item:null
    };
    var containar = null;
    $(".addItem").click(function(e){
        ele = $(e.target);
        if(ele.hasClass('addAction')){
            containar = $(e.currentTarget);
            domDatas.result = containar.find(".add-result");
            domDatas.name = ele.prev().text()
            if(ele.hasClass('add-all')){
                domDatas.name = "全寝";
            };
            //actionDatas[0].onClick()
            $.actions({
                actions:actionDatas
            });
        }

    });
    $.fn.isBound = function(type, fn) {
        var data = jQuery._data(this[0], 'events');
        //var data = this.data('events')[type];

        if (data === undefined || data[type]==undefined|| data[type].delegateCount === 0) {
            return false;
        }

        return true;
    };

    function deleteItem(e){
        var ele = $(e.currentTarget);
        ele.parent().remove();
    }
    $(".save").click(function(){
        //ajax，保存到server

        $(".save").hide();
        $(".subtract").hide();
        removeEvent(containar.find(".delete"),'click',deleteItem)
    })

    function addEvent(ele,type,fn){
        if(ele.length>0){
            for(var i = 0,length = ele.length;i<length;i++){
                ele[i].addEventListener(type,fn);
            }
        }
        return ele;
    }
    function removeEvent(ele,type,fn){
        if(ele.length>0){
            for(var i = 0,length = ele.length;i<length;i++){
                ele[i].removeEventListener(type,fn);
            }
        }
        return ele;
    }
})()
