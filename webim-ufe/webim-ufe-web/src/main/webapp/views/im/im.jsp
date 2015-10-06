<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en_US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.appname" /> - <spring:message code="app.im.title.im" /></title>
        <link rel="stylesheet" type="text/css" href="static/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="static/assets/bootstrap3-dialog/css/bootstrap-dialog.min.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/base.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/common.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/webim.css" />
        <link rel="stylesheet" type="text/css" href="static/assets/jquery-fileupload/css/jquery.fileupload.css" />
    </head>
    <body>
        <div class="container wraper">
            <div class="col-md-3 main-container">
                <div class="panel panel-default im-main-panel">
                    <div class="panel-heading panel-header">
                        <div class="list-item">
                            <a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a>
                            <span class="list-item-name">${user.username}</span>
                            <span class="list-item-operate"><i class="glyphicon glyphicon-align-justify"></i></span>
                            <div class="operate-panel" style="display: none;">
                                <div class="operate-content">
                                    <div class="operate operate-launchchat"><i class="glyphicon glyphicon-comment"></i> <spring:message code="app.im.action.launchchat" /></div>
                                    <div class="operate operate-addfriend"><i class="glyphicon glyphicon-user"></i> <spring:message code="app.im.action.addfriend" /></div>
                                    <div class="operate operate-joingroup"><i class="glyphicon glyphicon-menu-hamburger"></i> <spring:message code="app.im.action.joingroup" /></div>
                                    <div class="operate operate-addgroup"><i class="glyphicon glyphicon-plus"></i> <spring:message code="app.im.action.addgroup" /></div>
                                    <div class="operate operate-signout"><i class="glyphicon glyphicon-off"></i> <spring:message code="app.user.action.signout" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body im-body">
                        <div class="im-body-content tab-content">
                        <div id="dialogues" class="tab-pane active"></div>
                        <div id="contacts" class="tab-pane">
                            <ul class="nav nav-justified nav-flat tabs">
                                <li role="presentation" class="active"><a href="#rosters"><spring:message code="app.im.title.rosters" /></a></li>
                                <li role="presentation"><a href="#groups"><spring:message code="app.im.title.groups" /></a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="rosters" class="tab-pane active">
                                    <div class="panel-group" id="rosterAccordion" role="tablist" aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="friendRosters">
                                                <span class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#rosterAccordion" href="#friendCollapse" aria-expanded="true" aria-controls="friendCollapse"><spring:message code="app.im.title.friend" /></a>
                                                </span>
                                            </div>
                                            <div id="friendCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="friendRosters">
                                                <div id="friends" class="panel-body"></div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="strangerRosters">
                                                <span class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#rosterAccordion" href="#strangerCollapse" aria-expanded="true" aria-controls="strangerCollapse"><spring:message code="app.im.title.stranger" /></a>
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
                        <div id="settings" class="tab-pane"></div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <ul class="nav nav-justified tabs">
                            <li role="presentation" class="active first-tab"><a href="#dialogues"><i class="icon glyphicon glyphicon-comment"></i><spring:message code="app.im.title.dialogues" /></a></li>
                            <li role="presentation"><a href="#contacts"><i class="icon glyphicon glyphicon-user"></i><spring:message code="app.im.title.contacts" /></a></li>
                            <li role="presentation" class="last-tab"><a href="#settings"><i class="icon glyphicon glyphicon-cog"></i><spring:message code="app.im.title.settings" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-8 chat-container">
                <div class="emotion-panel" style="display: none;">
                    <div class="emotion-content"></div>
                </div>
                <div style="display: none;"><input type="file" id="chatFileInput" name="file"></div>
            </div>
        </div>
        
        <div id="chat-window-template" class="chat-window" style="display: none;">
            <div class="panel panel-default chat-main-panel">
                <div class="panel-heading panel-header">
                    <div class="panel-header-title"></div>
                    <div class="panel-close"><i class="glyphicon glyphicon-remove"></i></div>
                </div>
                <div class="panel-body chat-body">
                    <div class="message-more-btn"><i class="glyphicon glyphicon-time"></i> <spring:message code="app.im.action.moremessage" /></div>
                    <div class="chat-message-list"></div>
                </div>
                <div class="panel-footer">
                    <div class="chat-toolbar">
                        <div class="chat-toolbar-btn add-emotion-btn" title="<spring:message code="app.im.action.addemotion" />"><span class="chat-emotion"></span></div>
                        <div class="chat-toolbar-btn add-file-btn" title="<spring:message code="app.im.action.addefile" />"><span class="chat-file"></span></div>
                        <div class="chat-input"><textarea class="chat-textarea"></textarea></div>
                        <button type="button" class="btn btn-primary chat-send-btn"><spring:message code="app.common.action.send" /></button>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="static/assets/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="static/assets/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="static/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/assets/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
    <!-- 环信webim sdk -->
    <script type="text/javascript" src="static/assets/easymob/strophe-custom-2.0.0.js"></script>
    <script type="text/javascript" src="static/assets/easymob/easemob.im-1.0.5.js"></script>
    
    <!-- 滚动条 -->
    <script type="text/javascript" src="static/assets/easyscroll/js/easyscroll.js"></script>
    <script type="text/javascript" src="static/assets/easyscroll/js/mousewheel.js"></script>
    
    <!-- 文件上传 -->
    <script type="text/javascript" src="static/assets/jquery-fileupload/js/vendor/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="static/assets/jquery-fileupload/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="static/assets/jquery-fileupload/js/jquery.iframe-transport.js"></script>
    
    <script type="text/javascript" src="static/static/js/Utils.js"></script>
    <script type="text/javascript">
        var im = im || {
        	config: {
        		hxAppKey: '${hxAppKey}',
        		timstampSpacing: Number('${timstampSpacing}')
        	},
        	conn: null,  // 环信连接对象
        	user: {  // 当前用户对象
        		userid: '${user.userId}',
        		username: '${user.hxUsername}',
        		password: '${user.hxPassword}',
        		nickname: '${user.nickname}'
        	},
        	chatingUser: {  // 当前聊天用户对象, 如果是群组，该值表示群组对象
        		userid: '', 
        		type: ''  // chat/groupchat, 单用户聊天/群组聊天
        	},
        	messageType: {
        		'Text': [],
        		'Image': ['.png', '.jpg', '.jpeg', '.bpm', 'gif'],
        		'Audio': [],
        		'File': ['.doc', '.docx', '.pdf']
        	},
        	resolveMessageType: function(suffix){ // 根据后缀获取message类型
        		if(suffix){
        			for(var type in im.messageType){
            			var suffixs = im.messageType[type];
            			if(suffixs && suffixs.length > 0){
            				for(var i=0; i<suffixs.length; i++){
            					var _suffix = suffixs[i];
            					if(_suffix && suffix == _suffix){
            						return type;
            					}
            				}
            			}
            		}
        		}
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
    								$html += '<div class="list-item" data-id="' + roster['name'] + '" data-name="' + roster['name'] + '" data-type="chat"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a><p class="list-item-name">' + roster['name'] + '</p></div>';
    							}
    							return $html;
    						}).children('.list-item').click(im.selectedUserToChating);
    						$('#strangers').empty().html(function(){
    							var $html = '';
    							for(var i=0; i<strangerRosters.length; i++){
    								var roster = strangerRosters[i];
    								$html += '<div class="list-item" data-id="' + roster['name'] + '" data-name="' + roster['name'] + '" data-type="chat"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a><p class="list-item-name">' + roster['name'] + '</p></div>';
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
    									$html += '<div class="roster_item"><a href="#"><img class="roster_item_avatar" src="static/static/img/avatar/group_avatar.png" /></a><p>' + group['name'] + '</p></div>';
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
        		var chatingWindow = $('#chat-window-' + chatUserId);
        		if(!chatingWindow || chatingWindow.length <= 0){
        			chatingWindow = $('#chat-window-template').clone(true).attr({
        				id: 'chat-window-' + chatUserId
        			}).appendTo('.chat-container');
        		}
        		
        		//聊天窗口标题
        		var $html = '<div class="list-item" data-id="' + chatUserId + '" data-name="' + chatUserName + '"><a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a><p class="list-item-name">' + chatUserId + '</p></div>';
        		$('.panel-header-title', chatingWindow).html($html);
        	},
        	populateEmotionPanel: function(){  //构造表情面板
        		// Easemob.im.Helper.EmotionPicData设置表情的json数组
        		var emotionDatas = Easemob.im.Helper.EmotionPicData;
        		for (var key in emotionDatas) {
        			var emotionData = emotionDatas[key];
        			if(emotionData){
        				$('<a>', {
        					'id': '' + key,
        					'class': 'emotion',
            				html: '<img src="' + emotionData + '" />'
            			}).click(im.onSelectedEmotion).appendTo('.emotion-content');
        			}
        		}
        	},
        	onSelectedEmotion: function(){  // 选中表情
        		// 获取选择中表情
        		var emotion = $(this).attr('id');
        	    if(emotion){
        	    	var value = $('.chat-textarea', '#chat-window-' + im.chatingUser.userid).val();
        	    	$('.chat-textarea', '#chat-window-' + im.chatingUser.userid).val(value + '' + emotion);
        	    }
        	},
        	createDialogue: function(user, chatUser){  // 创建会话
        		if(user && user.username && chatUser && chatUser.userid){
        			var dialogue = $('#dialogue-' + user.username + '-' + chatUser.userid, '#dialogues');
        			if(!dialogue || dialogue.length <= 0){
        				dialogue = $('<div>', {
        					id: 'dialogue-' + user.username + '-' + chatUser.userid,
        					'class': 'list-item',
        					html: '<a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a><p class="list-item-name">' + chatUser.username || '' + '</p>'
        				}).attr({'data-id': chatUser.userid, 'data-name': chatUser.username, 'data-type': chatUser.type || 'chat'}).click(im.selectedUserToChating).appendTo('#dialogues');
        			}
        		}
        	},
            selectedUserToChating: function(){  // 选择用户进行聊天
            	//当前聊天用户
            	var chatUserId = $(this).attr('data-id');
            	var chatUserName = $(this).attr('data-name');
            	var chatUserType = $(this).attr('data-type');
            	if(chatUserId && chatUserName){
            		$(this).addClass('active').siblings().removeClass('active');
            		im.chatingUser = {
            			userid: chatUserId,
            			username: chatUserName,
            			type: chatUserType || 'user'
            		};
            		im.populateChatWin(chatUserId, chatUserName);
            		$('#chat-window-' + chatUserId).show().siblings().hide();
            		
            		// 创建会话
            		im.createDialogue(im.user, im.chatingUser);
            	}
            },
            overChating: function(){  // 结束聊天
            	// 当前聊天窗口
            	$('#chat-window-' + im.chatingUser.userid).hide();
                im.resetChatingUI();
            	im.chatingUser = {};
        	},
        	latestMessageTimestamp: new Date().getTime(),  // 最近一条消息的时间戳
        	showChatHistory: function(e){   // 显示聊天记录消息
        		var that = this;
        		if(im.latestMessageTimestamp){
        			$.ajax({
        				type: 'POST',
        				url: 'im/chatHistory',
        				data: {mfrom: im.user.username, mto: im.chatingUser.userid, mtimestamp: im.latestMessageTimestamp},
        				success: function(result){
        					if(result && result['resultCode'] == '000000'){
        						var datas = result['data'];
        						if(!datas){
        							return;
        						}
        						if(datas.length <= 0){
    								$(that).remove();
    							} else{
    								// 将数据按照时间排序
    								var messages = {}, messageTimestamps = [];
    								for(var i=0; i<datas.length; i++){
    									var message = datas[i];
    									if(!message){
    										continue;
    									}
    									var timestamp = message.mtimestamp;
    									$.extend(message, {
									    	from: message.mfrom,
									    	to: message.mto,
									    	data: function(bodies){
									    		var data = [];
									    		if(bodies && bodies.length>0){
									    			for(var j=0; j<bodies.length; j++){
									    				var body = bodies[j];
									    				if(body){
									    					if(body.msg){
									    						return body.msg;
									    					} else if(body.filepath){
									    						data.push({type: body.type, filepath: body.filepath, filename: body.filename});
									    					}
									    				}
									    			}
									    		}
									    		return data;
									    	}(message.messageBodies),
									    	ext: {
									    		timestamp: timestamp
									    	}
									    });
    									
    									if(!messages[timestamp]){
    										messages[timestamp] = [];
    									}
    									messages[timestamp].push(message);
    									messageTimestamps.push(timestamp);
    								}
    								
    								// 消息时间戳按照从大到小排序
    								messageTimestamps.sort(function(a, b){
    									return b - a;
    								});
    								for(var i=0; i<messageTimestamps.length; i++){
    									var timestamp = messageTimestamps[i];
    									if(timestamp){
    										var tempMessages = messages[timestamp];
        									$.each(tempMessages, function(i, message){
        										im.appendMessage(message, (message.mfrom == im.user.username ? message.mto : message.mfrom), -1);
        									});
    									}
    								}
    								
    								// 取最小的时间戳
    								im.latestMessageTimestamp = messageTimestamps[messageTimestamps.length - 1];
    							}
        					}
        				},
        				error: function(){
        				}
        			});
        		}
        	},
        	sendingMessage: false, // 标志是否正在发送消息
        	sendTextMessage: function(txtMessage){  // 发送文本聊天消息
        		if(im.sendingMessage){
        			return;
        		}
        	    im.sendingMessage = true;
        	    
        	    // 添加发送人和发送时间
        	    $.extend(txtMessage, {
        	    	from: im.user.username, 
        	    	ext: {
        	    	    timestamp: new Date().getTime()
        	        }
        	    });
//         	    // 发送消息
//         	    $('.chat-form', '#chat-window-' + im.chatingUser.userid).ajaxSubmit({
//         	    	type: 'POST',
//        		        url: 'im/persistent',
//        		        data : {mfrom: txtMessage.from, mto: txtMessage.to, chatType: txtMessage.type, 'messageBodies[0].msg': txtMessage.msg, 'messageBodies[0].type': 'Text'},
//        		        success: function(result){
//        		    	    // 成功响应
//        		    	    if(result && result['resultCode'] == '000000' && result['data']){
//        		    		    var message = result['data'];
//        		    	    	$.extend(txtMessage, {
//        		    			    ext: {
//        		    			    	timestamp: message['mtimestamp']|| (),
//        		    				    messageId: message['messageId']
//        		    			    }
//        		    		    });
//        		    	    }
//        		        },
//        		        error: function(){
//        		        	// 如果发送消息失败，标志后台服务有问题，直接发送环信消息
//        		        	// im.conn.sendTextMessage(txtMessage);
//        		        	console.log('error');
//        		        	$.extend(txtMessage, {data: txtMessage.msg, sent: false, ext: {timestamp: new Date().getTime()}});
//        		        	im.appendMessage(txtMessage, txtMessage.to);
//        		        	im.resetChatingUI();
//        		        }
//         	    });

        	    // easemobwebim-sdk发送文本消息的方法 to为发送给谁，meg为文本消息对象
	    		im.conn.sendTextMessage(txtMessage);
    		    $.extend(txtMessage, {
    		    	data: txtMessage.msg, 
    		    	sent: true
    		    });
    		    
	    		im.appendMessage(txtMessage, txtMessage.to);
	    		im.resetChatingUI();
        	},
        	sendFileMessage: function(fileMessage){ // 发送文件消息
        		if(im.sendingMessage){
        			return;
        		}
        	    im.sendingMessage = true;
        	    
        	    // 添加发送人
        	    $.extend(fileMessage, {
        	    	from: im.user.username,
        	    	ext: {
        	    	    timestamp: new Date().getTime()
        	        }
        	    });
//         	    // 发送消息
//         	    $('.chat-form', '#chat-window-' + im.chatingUser.userid).ajaxSubmit({
//         	    	type: 'POST',
//        		        url: 'im/persistent',
//        		        data : {mfrom: fileMessage.from, mto: fileMessage.to, chatType: fileMessage.type, 'messageBodies[0].filename': fileMessage.data.fileName, 'messageBodies[0].filepath': fileMessage.data.filePath, 'messageBodies[0].fileLength': fileMessage.data.size, 'messageBodies[0].type': messageType},
//        		        success: function(result){
//        		    	    // 成功响应
//        		    	    if(result && result['resultCode'] == '000000' && result['data']){
//        		    	    	var message = result['data'];
//        		    	    	$.extend(fileMessage, {
//        		    			    ext: {
//        		    			    	timestamp: message['mtimestamp']|| (new Date().getTime()),
//        		    				    messageId: message['messageId']
//        		    			    }
//        		    		    });
//        		    	    }
//        		        },
//        		        error: function(){
//        		        	// 如果发送消息失败，标志后台服务有问题，直接发送环信消息
//        		        	console.log('error');
//        		        	$.extend(fileMessage, {sent: false, ext: {timestamp: new Date().getTime()}});
//        		        	im.appendMessage(fileMessage, fileMessage.to);
//        		        	im.resetChatingUI();
//        		        }
//         	    });
                
                var fileInput = $('#chatFileInput');
   				if (fileInput && fileInput.length > 0 && fileInput[0].files) {
   				    var file = fileInput[0].files[0];
   				    if(file){
   				    	var objectURL = Utils.$objectURL(file);
   				    	var filename = file.name;
   				    	if(objectURL && filename){
   				    	    // 获取消息类型
   	   		        	    var messageType = im.resolveMessageType(filename) || 'File';
   	   		        	    if('Image' == messageType || 'Audio' == messageType || 'File' == messageType){
   	   		        	    	$.extend(fileMessage, {
   	   		        	    	    data: [{
									    type: messageType, 
									    filepath: objectURL, 
									    filename: filename || ''
								    }],
   	   			    		    	fileInputId : 'chatFileInput',
   	   			    		    	onFileUploadError : function(error) {
   	   			    		    		$.extend(fileMessage, {sent: false});
   	   			    		    		im.appendMessage(fileMessage, fileMessage.to);
   	   			    		    		im.resetChatingUI();
   	   		   						},
   	   		   						onFileUploadComplete : function(data) {
   	   		   					        $.extend(fileMessage, {sent: true});
  									    im.appendMessage(fileMessage, fileMessage.to);
  									    im.resetChatingUI();
   	   		   						}
   	   		        	    	});
   	   		        	    	if('Image' == messageType){
   	   			    		    	im.conn.sendPicture(fileMessage);
   	   			    		    } else if('Audio' == messageType){
   	   			    		    	im.conn.sendAudio(fileMessage);
   	   			    		    } else{
   	   			    		    	im.conn.sendFile(fileMessage);
   	   			    		    }
   	   		        	    }
   				    	}
   				    }
   				}
        	},
        	resetChatingUI: function(){  // 重置聊天界面
        		$('.chat-textarea', '#chat-window-' + im.chatingUser.userid).val('');
        	    $('.emotion-panel').hide();
        		setTimeout(function(){
        	    	im.sendingMessage = false;
        	    }, 1000);
        	},
            appendMessage: function(message, chatWindow, direction){  // 将消息添加到展示消息框
            	var messageItem = $('<div>', {
            		'class': 'chat-message-list-item',
            		html: [$('<p>', {
            			'class': 'datetime',
            			'data-time': (message.ext && message.ext.timestamp) || new Date().getTime(),
            			html: function(){
            				var mtimestamp = (message.ext && message.ext.timestamp) || new Date().getTime();
            				// 获取前一条消息的timestamp，如果在显示间隔之类，则不显示时间
            				var datetime = null;
            				if(direction < 0){
            					datetime = $('.datetime', $('.chat-message-list-item:first', $('.chat-message-list', '#chat-window-' + chatWindow)));
            				} else{
            					datetime = $('.datetime', $('.chat-message-list-item:last', $('.chat-message-list', '#chat-window-' + chatWindow)));
            				}
            				if(datetime && datetime.length > 0){
            					var prevTimestamp = $(datetime).attr('data-time');
            					// 超时显示间隔之后，显示时间
            					if(prevTimestamp && (Number(prevTimestamp) + Number(im.config.timstampSpacing)) < mtimestamp){
            						return Utils.$datetimeFormat((message.ext && message.ext.timestamp) || new Date().getTime(), 'HH:mm:ss') || '';
            					}
            				} else{
            					return Utils.$datetimeFormat((message.ext && message.ext.timestamp) || new Date().getTime(), 'HH:mm:ss') || '';
            				}
            			}
            		}), $('<div>', {
            			'class': 'avatar' + ' ' + (message.from == im.user.username ? 'fr' : 'fl'),
            			html: '<img src="static/static/img/avatar/roster_avatar_male.png" />'
            		}), $('<div>', {
            			'class': 'bubble',
            			html: $('<div>', {
                			'class': 'bubble-content' + ' ' + (message.from == im.user.username ? 'right fr' : 'left fl'),
                			html: [$('<pre>', {
                				'class': 'content',
                				html: function(){
                					var $html = '';
                    				if(message){
                                		// 消息体 {isemotion:true; body:[{type:txt,data:ssss}{type:emotion,msg:imgdata}]}
                                		var localeMessage = null;
                                		if(message.data && (typeof message.data == 'string')){
                                			localeMessage = Easemob.im.Helper.parseTextMessage(message.data);
                                			localeMessage = localeMessage.body;
                                		} else{
                                			localeMessage = message.data;
                                		}
                                		
                                		var messageBodies = localeMessage;
                                		if(messageBodies){
                                			for (var i = 0; i < messageBodies.length; i++) {
                                    			var messageBody = messageBodies[i];
                                    			var type = messageBody.type;
                                    			var data = messageBody.data;
                                    			// 表情消息
                                    			if (type == 'emotion') {
                                    				$html += '<img class="emotion" src="' + (data || '') + '">';
                                    			} else if (type == 'Image' || type == 'Audio' || type == 'File') {
                                    				var filename = messageBody.filename;
                                    				if(type == 'Image'){
                                    					$html += '<img class="picture" src="' + messageBody.filepath + '">';
                                    				} else{
                                    					$html += '' + ('<a target="_blank" href="' + messageBody.filepath + '">' + filename + '</a>' || '');
                                    				}
                                    			} else {
                                    				$html += '' + (data || '');
                                    			}
                                    		}
                                		}
                                	}
                    				return $html;
                    			}
                			}), $('<i>', {  // 重发按钮
                				'class': ''
                			})]
                		})
            		})]
            	});
            
                if(direction < 0){
                	$(messageItem).prependTo($('.chat-message-list', '#chat-window-' + chatWindow));
                	// 滚动到聊天框顶部
                	$('.chat-message-list', '#chat-window-' + chatWindow).scrollTop(0);
                } else{
                	$(messageItem).appendTo($('.chat-message-list', '#chat-window-' + chatWindow));
                	// 滚动到聊天框底部，保证展示最新的消息
                	$('.chat-message-list', '#chat-window-' + chatWindow).scrollTop($('.chat-message-list', '#chat-window-' + chatWindow)[0].scrollHeight);
                }
        	},
        	onSendTextMessage: function(e){  // 发送按钮事件
        		var mto = im.chatingUser && im.chatingUser.userid;
        	    if(!mto){
        	    	return;
        	    }
        		// 获取输入框数据
        		var textMsg = $('.chat-textarea', '#chat-window-' + im.chatingUser.userid).val();
        	    if(!textMsg){
        	    	return;
        	    }
        	    var textMessage = {
        	    	to: mto,
        	    	type: im.chatingUser.type || 'chat',
        	    	msg: textMsg
        	    };
        	    im.sendTextMessage(textMessage);
        	},
        	onSendFileMessage: function(){  // 发送文件
        		var mto = im.chatingUser && im.chatingUser.userid;
        	    if(!mto){
        	    	return;
        	    }
        	    var fileMessage = {
        	    	to: mto,
        	    	type: im.chatingUser.type || 'chat'
        	    };
        	    im.sendFileMessage(fileMessage);
        	},
        	onAddEmotion: function(e){  // 添加表情
        		$('.emotion-panel').toggle();
        	},
        	onAddFriend: function(){  // 添加好友
        		im.populateSearchDialog();
        	},
        	addFriendApply: function(){  // 添加好友申请
        		var friendUsername = $(this).parents('.list-item').attr('data-id');
        	    if(friendUsername){
        	    	BootstrapDialog.show({
        	    		size: BootstrapDialog.SIZE_NORMAL,
        				draggable: true,
        	            title: '<div class="model-header-title"><i class="glyphicon glyphicon-user"></i> <spring:message code="app.common.action.add" />' + friendUsername + '<spring:message code="app.im.title.addFriend" /></div>',
        	            message: function(dialogRef){
        	            	var $message = $('<div>', {
        	            		html: '<div class="list-item" data-id="' + friendUsername + '">'
					                + '<a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a>'
					                + '<span class="list-item-name">' + friendUsername + '</span>'
					                + '<div class="list-item-operate">'
					                + '<textarea class="form-control" id="friendApply_' + friendUsername + '"></textarea>'
					                + '</div>'
					                + '</div>'
        	            	});
        	            	return $message;
        	            },
        	            buttons: [{
        	                label: '<spring:message code="app.common.action.confirm" />',
        	                action: function(dialogRef) {
        	                	if($('#friendApply_' + friendUsername, dialogRef.getMessage()).length>0){
        	                		var applyMessage = $('#friendApply_' + friendUsername, dialogRef.getMessage()).val();
        	                		if(applyMessage){
        	                			//发送添加好友请求
                	        	    	im.conn.subscribe({
                	        	    		to: friendUsername,
                	        	    		message: '' + applyMessage
                	        	    	});
                	        	    	dialogRef.close();
        	                		}
        	                	}
        	                }
        	            }, {
        	                label: '<spring:message code="app.common.action.cancel" />',
        	                action: function(dialogRef) {
        	                	dialogRef.close();
        	                }
        	            }]
        	        });
        	    }
        	},
        	onJoinGroup: function(){  // 加入群
        	},
        	onLaunchChat: function(){  // 发起聊天
        		//用户好友窗口
        	},
        	searchDialog: null,
        	populateSearchDialog: function(){  // 构造查找(联系人、群)窗口
        		im.searchDialog = new BootstrapDialog({
    				size: BootstrapDialog.SIZE_WIDE,
    				type: BootstrapDialog.TYPE_DEFAULT,
    				draggable: true,
    				closable: true,
    	            title: '<div class="model-header-title"><i class="glyphicon glyphicon-search"></i> <spring:message code="app.im.title.searchrosters"/>、<spring:message code="app.im.title.searchgroups"/></div>',
    	            message: $('<div></div>').load('search?userid=' + im.user.userid)
    	        });
        	    im.searchDialog.open();
        	},
        	onAddGroup: function(){  // 创建群
        	},
        	onSignout: function(e){  // 退出
        		im.conn.close();
        		// 调用后台退出
        		location.href= 'user/signout';
        	},
        	handleReceiveMessage: function(easemobMessage){  //处理接收的消息
        		// 发送人
        		var from = easemobMessage.from;
        		if(from && from != im.user.username){
        			if(im.chatingUser && im.chatingUser.userid != from){
        				// 添加会话，消息提醒
        				im.createDialogue(im.user, {userid: from, username: from});
        				im.populateChatWin(from, from);
        			}
        		}
        		// Object {type: "chat", from: "jsyc", to: "anttribe", data: "aBC", ext: {messageId: "0190ddc6a7b44ff5a9d69176ad8cfef8"}}
        		im.appendMessage(easemobMessage, from);
        	},
        	handleReceiveFileMessage: function(easemobMessage){  //处理接收文件的消息
        	    //easemobwebim-sdk包装的下载文件对象的统一处理方法
        	    var retryTimes = 0;
        	    var options = $.extend(easemobMessage, {
        	    	onFileDownloadComplete: function(response, xhr){
    		        	var objectURL = Utils.$objectURL(response);
    		        	console.log(objectURL);
    				    if (objectURL) {
    				    	// 根据文件名获取文件后缀
    				    	var suffix = Utils.$getFileSuffix(easemobMessage.filename);
    				    	var messageType = im.resolveMessageType(suffix) || 'File';
    						$.extend(easemobMessage, {data: [{type: messageType, filepath: objectURL, filename: easemobMessage.filename}]});
    						im.handleReceiveMessage(easemobMessage);
    					}
    		        },
    		        onFileDownloadError: function(e){
    		        	// 下载失败时只重新下载一次
    		            if(retryTimes < 1){
    		             	retryTimes++;
    		                options.accessToken = options_c;
    		                Easemob.im.Helper.download(options);
    		            } else{
    		            	retryTimes = 0;
    		            }
    		        }
        	    });
		        Easemob.im.Helper.download(options);
        	},
        	presenceMessageHandlers: {  // 订阅请求消息类型
        		subscribe: {  //(发送者希望订阅接收者的出席信息), 即别人申请加你为好友
        			agreeAddFriend: function(message){
        				im.conn.subscribed({
	        	    		to: message['from'],
	        	    		message: '[resp:true]'
	        	    	});
        			},
        			handle: function(message){
        				if(message && message.status && message.status.indexOf('resp:true') > -1){
        					this.agreeAddFriend(message);
        					return;
        				}
        				var that = this;
        				BootstrapDialog.show({
            	    		size: BootstrapDialog.SIZE_NORMAL,
            				draggable: true,
            	            title: '<div class="model-header-title"><i class="glyphicon glyphicon-user"></i> ' + message['from'] + '<spring:message code="app.im.title.addFriendApply" /></div>',
            	            message: function(dialogRef){
            	            	var $message = $('<div>', {
            	            		html: '<div class="list-item" data-id="' + message['from'] + '">'
    					                + '<a href="#" class="list-item-avatar"><img src="static/static/img/avatar/roster_avatar_male.png" /></a>'
    					                + '<span class="list-item-name">' + message['from'] + '</span>'
    					                + '<div class="list-item-operate">' + (message['status'] || '') + '</div>'
    					                + '</div>'
            	            	});
            	            	return $message;
            	            },
            	            buttons: [{
            	                label: '<spring:message code="app.common.action.accept" />',
            	                action: function(dialogRef) {
            	                	that.agreeAddFriend(message);
            	        	    	//反向添加对方好友
            	        	    	im.conn.subscribe({
            	    					to : message['from'],
            	    					message: '[resp:true]'
            	    				});
            	        	    	dialogRef.close();
            	                }
            	            }, {
            	                label: '<spring:message code="app.common.action.refuse" />',
            	                action: function(dialogRef) {
            	                	// 拒绝添加好友
            	                	conn.unsubscribed({
            	            			to : message['from'],
            	            			message : (new Date()).getTime()
            	            		});
            	                }
            	            }]
            	        });
        			}
        		},
        		subscribed: {  //(发送者允许接收者接收他们的出席信息), 即别人同意你加他为好友
        		},
        		unsubscribe: {  //(发送者取消订阅另一个实体的出席信息), 即删除现有好友
        		},
        		unsubscribed: {  //(订阅者的请求被拒绝或以前的订阅被取消), 即对方单向的删除了好友
        		}
        	},
        	handlePresenceMessage: function(message){  // 处理订阅请求消息
        		if(message && message.type){
        			if(im.presenceMessageHandlers[message.type]){
        				var handler = im.presenceMessageHandlers[message.type];
        				handler.handle(message);
        			}
        		}
        	},
        	handleConnOpen: function(){  //连接打开时回调处理
        		//从连接中获取到当前的登录人注册帐号名
    			//im.user.username = im.conn.context.userId;
        	    //构造联系人列表
        	    im.populateRoster();
        	    //构造群组列表
        	    im.populateGroup();
        	    //设置用户上线状态，必须调用
        	    im.conn.setPresence();
        	},
        	handleConnClosed: function(){  //连接关闭时回调处理
        	},
        	handleOnError: function(e) {  //异常情况下的处理方法
        		console.log(e);
        		alert(e.msg);
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
				    im.handleConnClosed();
			    },
			    onTextMessage : function(message) {  //收到文本消息时的回调方法
				    im.handleReceiveMessage(message);
			    },
			    onEmotionMessage : function(message) {  //收到表情消息时的回调方法
			    	im.handleReceiveMessage(message);
			    },
			    onPictureMessage : function(message) { //收到图片消息时的回调方法
			    	im.handleReceiveFileMessage(message);
			    },
			    onAudioMessage : function(message) {  //收到音频消息的回调方法
			    	im.handleReceiveFileMessage(message);
			    },
			    onLocationMessage : function(message) {  //收到位置消息的回调方法
			    	im.handleReceiveMessage(message);
			    },
			    onFileMessage : function(message) {  //收到文件消息的回调方法
			    	im.handleReceiveFileMessage(message);
			    },
			    onVideoMessage : function(message) {  //收到视频消息的回调方法
			    	im.handleReceiveFileMessage(message);
			    },
			    onPresence : function(message) {  //收到联系人订阅请求的回调方法
				    im.handlePresenceMessage(message);
			    },
			    onRoster : function(message) {  //收到联系人信息的回调方法
				    console.log(message);
			    },
			    onInviteMessage : function(message) {  //收到群组邀请时的回调方法
				    handleInviteMessage(message);
			    },
			    onError : function(e) {  //异常时的回调方法
				    im.handleOnError(e);
			    }
		    });
		    
		    // 实现环信登录
			im.conn.open({
				user: im.user.username,
				pwd: im.user.password,
				appKey: im.config.hxAppKey  //连接时提供appkey
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
        	
        	//$('.im-body-content').scroll_absolute({arrows:true});
        	// 用户操作
        	$('.list-item-operate').click(function(){
        		$('.operate-panel').toggle();
        	});
        	$('.operate-launchchat').click(im.onLaunchChat);
        	$('.operate-addfriend').click(im.onAddFriend);
        	$('.operate-joingroup').click(im.onJoinGroup);
        	$('.operate-addgroup').click(im.onAddGroup);
        	// 退出
        	$('.operate-signout').click(im.onSignout);
        	
        	// 初始化表情列表
        	im.populateEmotionPanel();
        	// 聊天窗口关闭
        	$('.panel-close').click(im.overChating);
        	// 查看更多消息
        	$('.message-more-btn').click(im.showChatHistory);
        	// 发送按钮事件
        	$('.chat-send-btn').click(im.onSendTextMessage);
        	// 添加表情按钮事件处理
        	$('.add-emotion-btn').click(im.onAddEmotion);
        	// 添加文件按钮
        	$('.add-file-btn').click(function(){
        		$('#chatFileInput').click();
        	});
        	// 上传文件
        	$('input[name="file"]').fileupload({
    			url : '',
    			replaceFileInput: false,
    			autoUpload: false,
    			progressall: function(e, data){},
                add: function(e, data){
                	if (e.isDefaultPrevented()) {
                        return false;
                    }
                	im.onSendFileMessage();
                }
    	    });
        });
    </script>
</html>