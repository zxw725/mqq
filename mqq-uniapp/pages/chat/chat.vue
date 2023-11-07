<template>
	<view>
		<view class="head-container">
			<view class="iconfont icon-fanhui fanhui" @click="goBack(uid)"></view>
			<!-- <img :src="headPicture" class="head-picture" alt=""> -->
			{{username}}
		</view>
		<view class="chat-box">
			<scroll-view class="content" :scroll-into-view="scrollIntoView" scroll-y="true" with-animation="false"
				show-scrollbar="false">
				<view class="message" v-for="(item,index) in messageBox" :id="'content'+index"
					:class="(item.uid == userInfo.id?'user-context':'friend-context')">
					<img :src="baseUrl+(item.uid == userInfo.id?userInfo.url:friendInfo.url)" alt=""
						class="head-picture">
					<view class="message-box">
						<view class="chat-time">
							{{item.time}}
						</view>
						<view class="chat-message">
							{{item.message}}
						</view>
					</view>
				</view>
			</scroll-view>

		</view>
		<view class="bottom-container">
			<input type="text" v-model="chatText" @confirm="handleEnterKey()" class="chat-input">
			<button :class="chatText!=''?'chat-submit-ing':'chat-submit'" @click="sendMessage()">发送</button>
		</view>
	</view>
</template>

<script>
	import {
		log
	} from "console"
	import {
		get
	} from "https"
	import {
		stdout
	} from "process"
	import {
		timeOut
	} from "uni-simple-router"
	import {
		getAllMessage,
		send
	} from "../../api/message.js"
	import {
		selectUser,
		selectAllFriends
	} from "../../api/user.js"
	export default {
		data() {
			return {
				username: '',
				id: 0,
				uid: 0,
				chatText: '',
				messageBox: [],
				userInfo: {},
				friendInfo: {},
				socket: '',
				scrollIntoView: 'content0'
			}
		},
		methods: {
			handleEnterKey() {
				// 处理回车键触发的事件
				console.log(111111);
				console.log("Enter key pressed!");
				this.sendMessage()
			},
			goBack() {
				uni.switchTab({
					url: `/pages/user/user`
				})
			},
			filterTime(time) {
				return time.split('T')[0] + " " + time.split('T')[1]
			},
			closeWebSocket() {
				// 关闭WebSocket连接
				if (this.socket) {
					this.socket.close({
						code: 1000,
						reason: '用户关闭页面',
						success: () => {
							console.log('WebSocket连接已关闭')
						}
					})
				}
			},
			initWebSocket() {
				this.socket = uni.connectSocket({
					url: `ws://127.0.0.1:8088/chat?userId=${this.id}`,
					success: () => {
						console.log('WebSocket 连接成功')
					}
				})

				// 监听 WebSocket 连接打开事件
				this.socket.onOpen(() => {
					console.log('WebSocket 连接已打开')
				})

				// 监听 WebSocket 接收到消息事件
				this.socket.onMessage((res) => {
					// 处理消息的逻辑
					var message = {}
					message.time = JSON.parse(res.data).time
					message.uid = this.uid
					message.fid = this.id
					message.message = JSON.parse(res.data).content
					this.messageBox.push(message)
					// 滚动到最新的聊天记录
					this.$nextTick(() => {
						this.scrollIntoView = `content${this.messageBox.length - 1}`
						console.log(this.scrollIntoView);
					})
					console.log('收到消息', res)
					// 在这里处理接收到的消息
					this.handleMessage(res)
				})
			},
			// 处理接收到的消息
			handleMessage(res) {
				console.log('处理消息', JSON.parse(res.data).content)
			},
			filterDate(num) {
				return num < 10 ? "0" + num : num
			},
			filterSfm(num) {
				num = num + ""

				return num.length == 1 ? "0" + num : num
			},
			getNow() {
				// 获取当前时间对象
				var currentDate = new Date();
				// 获取年份、月份、日期、小时、分钟、秒						
				var year = currentDate.getFullYear();
				var month = currentDate.getMonth() + 1; // 月份从 0 开始，所以要加 1
				var day = currentDate.getDate();
				var hours = currentDate.getHours();
				var minutes = currentDate.getMinutes();
				var seconds = currentDate.getSeconds();
				return year + "-" + this.filterDate(month) + "-" + this.filterDate(day) + " " + this.filterSfm(
					hours) + ":" + this.filterSfm(minutes) + ":" + this.filterSfm(seconds);
			},
			sendMessage() {
				if (this.chatText != "") {

					var timestamp = this.getNow()
					console.log(timestamp);
					send({
						fid: this.uid,
						uid: this.id,
						message: this.chatText,
						time: timestamp
					})
					var message = {}
					message.time = this.getNow()
					message.uid = this.id
					message.fid = this.uid
					message.message = this.chatText
					this.messageBox.push(message)
					// 滚动到最新的聊天记录
					this.$nextTick(() => {
						this.scrollIntoView = `content${this.messageBox.length - 1}`
						console.log(this.scrollIntoView);
					})

					// 发送消息到WebSocket服务器
					if (this.socket) {
						console.log(this.uid);
						if (this.chatText != "") {
							this.socket.send({
								data: JSON.stringify({
									targetUserId: this.uid,
									content: this.chatText,
									type: 1,
									time: this.getNow()
								}),
								success: () => {
									console.log(this.chatText)
									// getAllMessage(this.id, this.uid).then(res => {
									// 	this.messageBox = res.data.data
									// 	console.log(res);
									// })
									// 可以在发送成功后更新聊天界面等
								}
							})
						}
						this.chatText = ""
					}

				}
			},
			scrollToBottom() {
				this.$nextTick(() => {
					console.log(this.$refs.content);
					uni.pageScrollTo({
						scrollTop: this.$refs.content.scrollHeight,
						duration: 300
					})
				})
			},
			fetchDataFromServer(){
				getAllMessage(this.id, this.uid).then(res => {
					this.messageBox = res.data.data
					console.log(res);
					// 滚动到最新的聊天记录
					this.$nextTick(() => {
						this.scrollIntoView = `content${this.messageBox.length - 1}`
						console.log(this.scrollIntoView);
					})
				})
				selectUser(this.id).then(res => {
					this.userInfo = res.data.data
				})
				selectUser(this.uid).then(res => {
					this.friendInfo = res.data.data
				})
			}
		},
		mounted() {
			this.username = this.$route.query.username
			this.id = this.$route.query.uid
			this.uid = this.$route.query.id
			this.initWebSocket()
			console.log(123111);
			console.log(this.$route.query.uid);
			this.fetchDataFromServer()

		},
		onLoad(options) {
			
			this.username = options.username
			this.id = options.uid
			this.uid = options.id
			this.fetchDataFromServer()
		},
		onShow() {
			this.initWebSocket()
		},
		onHide() {
			// this.closeWebSocket()
		},
	}
</script>

<style scoped>
	@import url("../../uni_modules/uni-icons/components/uni-icons/uniicons.css");
	@import "@/static/iconfont/iconfont.css";

	.friend-context {
		display: flex;
		margin-left: 20rpx;
	}

	.friend-context .message-box {
		margin-left: 15rpx;
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		margin-bottom: 30rpx;

	}

	.friend-context .chat-message {
		background: white;
		margin-top: 15rpx;
		padding: 20rpx;
		box-sizing: border-box;
		display: inline-block;
		border-radius: 18rpx;
		/* borderleft */
		/* display: flex; */
	}

	.chat-message {
		max-width: 500rpx;
		word-wrap: break-word;
	}

	.user-context .message-box {
		margin-right: 15rpx;
		display: flex;
		flex-direction: column;
		align-items: flex-end;
		margin-bottom: 30rpx;

	}

	.user-context .chat-message {
		background: #019AFE;
		color: white;
		margin-top: 15rpx;
		padding: 20rpx;
		box-sizing: border-box;
		display: inline-block;
		border-radius: 18rpx;
		/* display: flex; */
	}

	.chat-time {
		color: #656565;
	}

	.head-picture {
		height: 80rpx;
		width: 80rpx;
		border-radius: 50%;
	}

	.user-context {
		display: flex;
		flex-direction: row-reverse;
		margin-right: 20rpx;
		/* justify-content: flex-end; */
	}

	.chat-box {
		background: #F1F1F1;
		width: 100%;
		height: calc(100vh - 180rpx);
		box-sizing: border-box;
		overflow: hidden;
		padding: 0 !important;
	}

	* {
		padding: 0;
	}

	.content {
		background: #F1F1F1;
		width: 100%;
		box-sizing: border-box;
		overflow-y: auto;
		padding-top: 20rpx;
		max-height: calc(100vh - 180rpx);
		box-sizing: border-box;

	}

	.message:nth-last-child(1) {
		margin-bottom: 50rpx;
	}

	::webkit-scrollbar {
		display: none;
	}

	::webkit-scrollbar-horizontal {
		display: none;
	}

	::-webkit-scrollbar {
		display: none !important;
	}


	::webkit-scrollbar-track {
		display: none;
	}

	.chat-input {
		background: white;
		height: 80rpx;
		padding: 0 20rpx;
		box-sizing: border-box;
		font-size: 35rpx;
		caret-color: #4772ff55;
		width: 100%;
	}

	.chat-submit-ing,
	.chat-submit {
		width: 140rpx;
		height: 80rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: #019AFE55;
		margin-left: 20rpx;
		color: white;
		font-size: 30rpx;
	}

	.chat-submit-ing {
		background: #019AFE;
	}

	.bottom-container {
		display: flex;
		position: absolute;
		bottom: 0;
		width: 100%;
		padding: 18rpx 30rpx;
		box-sizing: border-box;
		background: #F8F9F9;
		align-items: center;
	}

	* {
		padding: 0;
		margin: 0;
	}

	.fanhui {
		position: absolute;
		left: 30rpx;
		font-size: 55rpx;
	}

	.head-container {
		background: linear-gradient(to right, #90d7f8, #8b91f8);
		width: 100%;
		height: 110rpx;
		padding: 15rpx 30rpx;
		box-sizing: border-box;
		justify-content: center;
		display: flex;
		align-items: center;
		color: white;
		font-size: 38rpx;
	}
</style>
