SchoolComboBox = function (selectedSchool, schoolListContainer) {

    this.selectedSchool = selectedSchool;
    this.schoolListContainer = schoolListContainer;
    
    selectedSchool.addEventListener('focus', function () {
    	if(schoolListContainer.innerHTML !== '') {
    		schoolListContainer.style.visibility = "visible";
    	}
    });
    
    selectedSchool.addEventListener('blur', function () {
    	setTimeout(function() {
			schoolListContainer.style.visibility = "hidden";
		}, 250);
    });

}

SchoolComboBox.prototype.init = function () {
    this.schoolListContainer.innerHTML = '';
    this.schoolListContainer.style.visibility = 'hidden';
}

SchoolComboBox.prototype.addSchools = function (schools) {
	
	var _this = this;
	
	if(schools.length === 0) {
		this.init();
        return;
    }

    var content = '<ul class="school-list">';
    for(var i = 0; i < schools.length; i++) {
        content += '<li class="school-item">' + schools[i] + '</li>';
    }
    content.concat('</ul>');
    this.schoolListContainer.innerHTML = content;
    this.selectedSchool.focus();
    this.schoolListContainer.style.visibility = "visible";
    var items = document.getElementsByClassName('school-item');
    for(var i = 0; i < items.length; i++) {
        items[i].addEventListener('click', function(e) {
        	var li = e.target;
            _this.selectedSchool.value = li.innerHTML;
            li.style.backgroundColor = "#d0d0d0";
            setTimeout(function(){
            	li.style.backgroundColor = "";
            }, 100);
        });
    }
}