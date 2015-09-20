<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<ul class="nav nav-justified nav-flat tabs">
    <li role="presentation" class="active"><a href="#search-rosters"><spring:message code="app.im.title.searchrosters" /></a></li>
    <li role="presentation"><a href="#search-groups"><spring:message code="app.im.title.searchgroups" /></a></li>
</ul>
<div class="tab-content">
    <div id="search-rosters" class="tab-pane active">
        <div class="input-group">
            <input type="text" id="input-search-rosters" class="form-control" placeholder="<spring:message code="app.im.title.searchrosters" />...">
            <span class="input-group-btn">
                <button id="btn-search-rosters" class="btn btn-default" type="button"><spring:message code="app.common.action.search" /></button>
            </span>
        </div>
    </div>
    <div id="search-groups" class="tab-pane">
        search-groups
    </div>
</div>
<script type="text/javascript">
    var search = search || {
    	user: {
    		userid: '${userid}',
    	},
        searchRosters: function(){  //搜索联系人
        	var keys = $('#input-search-rosters').val();
            if(keys){
            	$.ajax({
            		type: 'POST',
            		url: 'search/doSearch',
            		data: {type: 'roster', currentUser: search.user.userid, keys: keys},
            		success: function(result){
            			console.log(result);
            		}
            	});
            }
        }
    };
</script>
<script type="text/javascript">
    $(document).ready(function(){
    	$('.tabs a').click(function (e) {
            e.preventDefault();
    		$(this).tab('show');
    	});
    	
    	$('#btn-search-rosters').click(search.searchRosters);
    });
</script>