var gx = {
    ajax: (function(){
        function createXhr(){
            var methods = [
                function () {return new XMLHttpRequest() },
                function () {return new ActiveXObject('MSXML2.XMLHttp.6.0') },
                function () {return new ActiveXObject('MSXML2.XMLHttp.3.0') },
                function () {return new ActiveXObject('MSXML2.XMLHttp') }
            ];
            for(var i=0;i<methods.length;i++){
                try {
                    methods[i]();
                } catch(ex) {
                    continue;
                }
                createXhr = methods[i];
                return methods[i]();
            }
        }
        // req object 
        //     {
        //         data: object,
        //         method: string, 
        //         url: {string,required},
        //         success: function,
        //         error: function,
        //     }
        // req string
        return  function ajaxRequest(req){
            req = typeof req === 'string' ? {url:req}: req;
            req = typeof req === 'object' ? req : {};
            var xhr = createXhr();
            xhr.open(req.method || 'get',req.url || '',true);
            req.data ? xhr.send(req.data) : xhr.send(null);
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if((xhr.status >= 200 && xhr.status <300) || xhr.status == 304 ){
                        if(typeof req.success === 'function') req.success(JSON.parse(xhr.responseText));
                    } else{
                        console.error('response error status:',xhr.status);
                        if(typeof req.error === 'function') req.error('response error status:'+ xhr.status);
                    }
                }
            }
            xhr.upload.onprogress = req.progress;
        }
    })(),
    // 暂时写一块儿
    getFormData: function (form,attrArr){
        var data = {};
        attrArr = attrArr || [];
        for(var i=0; i< attrArr.length; i++){
            var attr = attrArr[i];
            data[attr] = form['elements'][attr].value;
        }
        return data;
    },
}

gx.register = (function(){
    var formContent = {};
    (function initFromContent(){
        formContent = getRegisterInfo()
    })();
    function storageRegisterInfo(data){
        var registerInfo = Object.assign({},formContent,data);
        try{
            localStorage.setItem('register',JSON.stringify(registerInfo));
        }catch(err){
            console.error(err);
            alert('存储注册信息失败');
        }
    }
    function getRegisterInfo(){
        try{
            var registerInfo = JSON.parse(localStorage.getItem('register'));
        }catch(err){
            console.error(err);
            alert('JSON 解析失败');
        }
        return registerInfo;
    }
    function deleteRegisterInfo(){
        delete localStorage.register;
    }
    return {
        storageRegisterInfo: storageRegisterInfo,
        getRegisterInfo: getRegisterInfo,
        deleteRegisterInfo: deleteRegisterInfo
    }
})();
