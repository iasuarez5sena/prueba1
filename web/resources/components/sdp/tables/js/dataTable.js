/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var com = {};
if (!com.dataTable) {
    com.dataTable = {
        clientId: "",
        init: function (clientId) {
            this.clientId = clientId;
        },
        showLoading: function(){
            alert(this.clientId);
        },
        ajaxExecutingDelete: function(data){
            if (data.status === 'begin' && com.dataTable.ajaxBeginDelete !== null) {
                com.dataTable.ajaxBeginDelete();
            } else if (data.status === 'success' && com.dataTable.ajaxSuccessDelete !== null) {
                com.dataTable.ajaxSuccessDelete();
            }
            
        },
        ajaxBeginDelete: null,
        ajaxSuccessDelete: null,
        ajaxExecutingEdit: function(data){
            if (data.status === 'begin' && com.dataTable.ajaxBeginEdit !== null) {
                com.dataTable.ajaxBeginEdit();
            } else if (data.status === 'success' && com.dataTable.ajaxSuccessEdit !== null) {
                com.dataTable.ajaxSuccessEdit();
            }
            
        },
        ajaxBeginEdit: null,
        ajaxSuccessEdit: null,
        selectItem: function(btn){
            $(btn).parent('td').parent('tr').children('td:first-child').children('a').click();
        }
    };
}

if (!com.dataTable.paginator) {
    com.dataTable.paginator = {
        clientId: "",
        init: function (clientId) {
            this.clientId = clientId;
        },
        ajaxExecuting: function(data){
            if (data.status === 'begin') {
                document.getElementById(com.dataTable.paginator.clientId + '-loading').classList.add('show');
            } else if (data.status === 'success') {
            }
            
        }
    };
}



