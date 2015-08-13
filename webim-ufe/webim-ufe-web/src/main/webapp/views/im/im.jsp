<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en_US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.appname" /> - <spring:message code="app.im.title.im" /></title>
        <link rel="stylesheet" type="text/css" href="static/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/base.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/common.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/webim.css" />
    </head>
    <body>
        <div class="container wraper">
            <div class="col-md-3 main-container">
                <div class="panel panel-default im-main-panel">
                    <div class="panel-heading panel-header">
                        <div class="list-item">
                            <a href="#" class="list-item-avatar"><img src="static/static/img/avatar/default_roster_avatar.png" /></a>
                            <p class="list-item-name">${user.username}</p>
                        </div>
                    </div>
                    <div class="panel-body tab-content">
                        <div id="dialogues" class="tab-pane active">
                            dialogue
                        </div>
                        <div id="contacts" class="tab-pane">
                            <ul class="nav nav-justified nav-flat tabs">
                                <li role="presentation" class="active"><a href="#rosters">好友</a></li>
                                <li role="presentation"><a href="#groups">群</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="rosters" class="tab-pane active">
                                    <div class="panel-group" id="rosterAccordion" role="tablist" aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="friendRosters">
                                                <span class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#rosterAccordion" href="#friendCollapse" aria-expanded="true" aria-controls="friendCollapse">我的好友</a>
                                                </span>
                                            </div>
                                            <div id="friendCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="friendRosters">
                                                <div id="friends" class="panel-body"></div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="strangerRosters">
                                                <span class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#rosterAccordion" href="#strangerCollapse" aria-expanded="true" aria-controls="strangerCollapse">陌生人</a>
                                                </span>
                                            </div>
                                            <div id="strangerCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="strangerRosters">
                                                <div id="strangers" class="panel-body"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="groups" class="tab-pane"></div>
                            </div>
                        </div>
                        <div id="settings" class="tab-pane">
                        </div>
                    </div>
                    <div class="panel-footer">
                        <ul class="nav nav-justified tabs">
                            <li role="presentation" class="active first-tab"><a href="#dialogues"><i class="icon glyphicon glyphicon-comment"></i>会话</a></li>
                            <li role="presentation"><a href="#contacts"><i class="icon glyphicon glyphicon-user"></i>联系人</a></li>
                            <li role="presentation" class="last-tab"><a href="#settings"><i class="icon glyphicon glyphicon-cog"></i>设置</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-8 chat-container" style="display: none;">
                <div class="panel panel-default chat-main-panel">
                    <div class="panel-heading panel-header">
                        <div class="panel-header-title"></div>
                        <div class="panel-close"><i class="glyphicon glyphicon-remove"></i></div>
                    </div>
                    <div class="panel-body">
                    </div>
                    <div class="panel-footer">
                        <div class="chat-toolbar">
                            <div id="add_emoji_btn" class="chat-toolbar-btn"><span class="chat-emoji"></span></div>
                            <div id="add_file_btn" class="chat-toolbar-btn"><span class="chat-file"></span></div>
                            <div class="chat-input"><textarea id="chat_textarea" class="chat-textarea"></textarea></div>
                            <button type="button" class="btn btn-primary chat-send-btn">发送</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="static/assets/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="static/assets/jquery.form.js"></script>
    <script type="text/javascript" src="static/assets/bootstrap/js/bootstrap.min.js"></script>

    <!-- 环信webim sdk -->    
    <script type="text/javascript" src="static/assets/easymob/strophe-custom-2.0.0.js"></script>
    <script type="text/javascript" src="static/assets/easymob/easemob.im-1.0.5.js"></script>
    <script type="text/javascript">
        var im = im || {
        	hxAppKey: '${hxAppKey}',
        	conn: null,  // 环信连接对象
        	user: {  // 当前用户对象
        		userid: '',
        		username: '${user.username}',
        		password: '${user.hxPassword}',
        		nickname: '${user.nickname}'
        	},
        	populateRoster: function(){  //构造联系人列表
        		//获取当前登录人的联系人列表
    			im.conn.getRoster({
    				success: function(rosters){
    					if(rosters && rosters.length>0){
    						// 我的好友, 陌生人
							var friendRosters = [], strangerRosters = [];
    						for (var i=0; i<rosters.length; i++){
        						var roster = rosters[i];
        						if(roster){
        							// both: 双方互为好友, from: 我是对方的单向好友, to: 联系人是我的单向好友
            						if (roster.subscription == 'both' || roster.subscription == 'to') {
            							friendRosters.push(roster);
            						} else if (roster.subscription == 'from') {
            							strangerRosters.push(roster);
            						}
        						}
        					}
    						// 构造好友界面
    						$('#friends').empty().html(function(){
    							var $html = '';
    							for(var i=0; i<friendRosters.length; i++){
    								var roster = friendRosters[i];
    								$html += '<div class="list-item" data-id="' + roster['name'] + '" data-name="' + roster['name'] + '"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/default_roster_avatar.png" /></a><p class="list-item-name">' + roster['name'] + '</p></div>';
    							}
    							return $html;
    						}).children('.list-item').click(im.selectedUserToChating);
    						$('#strangers').empty().html(function(){
    							var $html = '';
    							for(var i=0; i<strangerRosters.length; i++){
    								var roster = strangerRosters[i];
    								$html += '<div class="list-item" data-id="' + roster['name'] + '" data-name="' + roster['name'] + '"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/default_roster_avatar.png" /></a><p class="list-item-name">' + roster['name'] + '</p></div>';
    							}
    							return $html;
    						}).children('.list-item').click(im.selectedUserToChating);
    					}
    				}
    			});
        	},
        	populateGroup: function(){  //构造群组
        		//获取当前登录人的群组列表
				im.conn.listRooms({
					success: function(groups) {
						if (groups && groups.length > 0) {
							$('#groups').empty().html(function(){
    							var $html = '';
    							for(var i=0; i<groups.length; i++){
    								var group = groups[i];
    								if(group){
    									$html += '<div class="roster_item"><a href="#"><img class="roster_item_avatar" src="static/static/img/avatar/default_roster_avatar.png" /></a><p>' + group['name'] + '</p></div>';
    								}
    							}
    							return $html;
    						});
							for(var i=0; i<groups.length; i++){
								var group = groups[i];
								if(group){
								}
							}
						}
					}
				});
        	},
        	populateChatWin: function(chatUserId, chatUserName){  // 构造聊天窗口
        		var $html = '<div class="list-item" data-id="' + chatUserId + '" data-name="' + chatUserName + '"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/default_roster_avatar.png" /></a><p class="list-item-name">' + chatUserId + '</p></div>';
        		$('.panel-header-title', '.chat-container').html($html);
        	
        	    $('.chat-container').show();
        	},
            selectedUserToChating: function(){  // 选择用户进行聊天
            	//当前聊天用户
            	var chatUserId = $(this).attr('data-id');
            	var chatUserName = $(this).attr('data-name');
            	if(chatUserId && chatUserName){
            		im.populateChatWin(chatUserId, chatUserName);
            	}
            },
        	handleConnOpen: function(){  //连接打开时回调处理
        		//从连接中获取到当前的登录人注册帐号名
    			im.user.userid = im.conn.context.userId;
        	    //构造联系人列表
        	    im.populateRoster();
        	    //构造群组列表
        	    im.populateGroup();
        	    //设置用户上线状态，必须调用
        	    conn.setPresence();
        	}
        };
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
		    im.conn = new Easemob.im.Connection();
		    // 初始化连接对象
		    im.conn.init({
			    https : false,
			    onOpened : function() {  //当连接成功时的回调方法
				    im.handleConnOpen();
			    },
			    onClosed : function() {  //当连接关闭时的回调方法
				    handleClosed();
			    },
			    onTextMessage : function(message) {  //收到文本消息时的回调方法
				    handleTextMessage(message);
			    },
			    onEmotionMessage : function(message) {  //收到表情消息时的回调方法
				    handleEmotion(message);
			    },
			    onPictureMessage : function(message) { //收到图片消息时的回调方法
				    handlePictureMessage(message);
			    },
			    onAudioMessage : function(message) {  //收到音频消息的回调方法
				    handleAudioMessage(message);
			    },
			    onLocationMessage : function(message) {  //收到位置消息的回调方法
				    handleLocationMessage(message);
			    },
			    onFileMessage : function(message) {  //收到文件消息的回调方法
				    handleFileMessage(message);
			    },
			    onVideoMessage : function(message) {  //收到视频消息的回调方法
				    handleVideoMessage(message);
			    },
			    onPresence : function(message) {  //收到联系人订阅请求的回调方法
				    handlePresence(message);
			    },
			    onRoster : function(message) {  //收到联系人信息的回调方法
				    handleRoster(message);
			    },
			    onInviteMessage : function(message) {  //收到群组邀请时的回调方法
				    handleInviteMessage(message);
			    },
			    onError : function(message) {  //异常时的回调方法
				    handleError(message);
			    }
		    });
		    
		    // 实现环信登录
			im.conn.open({
				user: im.user.username,
				pwd: im.user.password,
				appKey: im.hxAppKey  //连接时提供appkey
			});
	    });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
        	$('.tabs a').click(function (e) {
                e.preventDefault();
        		$(this).tab('show');
        	});
        	$('.collapse').collapse({});
        });
    </script>
</html>