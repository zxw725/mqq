<template>
	<view class="">
		<view class="container" @click="showModel=false">
			<view class="head-container">
				<img :src="headPicture" class="head-picture" alt="">
				<view class="username">
					{{user.username}}
				</view>
				<view class="iconfont icon-jiahao jiahao" @click="showModelJiahao()">
				</view>
			</view>
			<view v-for="(item,index) in userFriends" :key="index">
				<view class="friend-container" @click="goChat(item.id,item.username,user.id)">
					<img :src="baseUrl+item.url" class="friend-head-picture" alt="">
					<view class="friend-username">
						{{item.username}}
					</view>
				</view>
			</view>
		</view>

		<view class="container1" v-if="showModel" @click="closedJiahao()">
			<view class="jiahao-container">
				<view class="container-item" @click="createGroup()">
					<view class="iconfont icon-qunzu">

					</view>
					创建群聊
				</view>
				<view class="container-item" @click="applyFriend()">
					<view class="iconfont icon-tianjiahaoyou">

					</view>
					加好友/群
				</view>
				<view class="container-item">
					<view class="iconfont icon-saoyisao">

					</view>
					扫一扫
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		log
	} from "console"
	import {
		selectUser,
		selectAllFriends
	} from "../../api/user.js"
	export default {
		data() {
			return {
				username: this.$route.params.userId,
				user: {},
				headPicture: '',
				friends: [],
				userFriends: [],
				showModel: false,
			}
		},
		methods: {
			closedJiahao() {
				this.showModel = false
			},
			showModelJiahao() {
				console.log(333);
				console.log(this.showModel);
				var timer = setInterval(() => {
					this.showModel = true
					clearInterval(timer)
				}, 100)

			},
			goChat(id, username, uid) {
				uni.redirectTo({
					url: `/pages/chat/chat?id=${id}&username=${username}&uid=${uid}`
				})
			},
			createGroup() {
				console.log("创建群聊");
			},
			applyFriend(){
				
			},
			init() {
				selectUser(this.username).then(res => {
					this.user = res.data.data
					console.log(res.data.data);
					// console.log(this.baseUrl);
					this.headPicture = `${this.baseUrl+this.user.url}`
					// console.log(this.headPicture);
				}).catch(err => {
					console.log(err);
				})
				selectAllFriends(this.username).then(res => {
					this.userFriends = res.data.data
					console.log(res.data.data[0].username);
				})
			}
		},
		onHide() {
			this.showModel = false
			console.log("hide");
		},
		onShow() {
			console.log("show");
			this.showModel = false
		},
		mounted() {
			this.username = uni.getStorageSync('uid');
			this.init()
		},
		onLoad(options) {
			this.username = uni.getStorageSync('uid');
			this.init()
			uni.navigateTo({
				url: `/pages/home/home`
			})
		}
	}
</script>

<style scoped>
	.container-item {
		display: flex;
		align-items: center;
		font-size: 35rpx;
		font-weight: 550;
		margin-bottom: 40rpx;
	}

	.container-item:nth-last-child(1) {
		margin-bottom: 0;
	}

	.container-item .iconfont {
		font-size: 50rpx;
		margin-right: 20rpx;
	}

	.jiahao-container {
		position: absolute;
		background-color: white;
		padding: 50rpx;
		box-sizing: border-box;
		border-radius: 18rpx;
		top: 125rpx;
		right: 15rpx;
	}

	.jiahao {
		font-size: 60rpx;
		color: white;
		position: absolute;
		right: 30rpx;
	}

	.friend-container {
		display: flex;
		padding: 20rpx 30rpx;
		box-sizing: border-box;
		width: 100%;
		/* height: 150rpx; */
		background: white;
		align-items: center;
	}

	.username {
		color: #ffffff;
		margin-left: 15rpx;
	}

	.friend-username {
		color: black;
		margin-left: 20rpx;
	}

	.head-container {
		background: linear-gradient(to right, #90d7f8, #8b91f8);
		width: 100%;
		height: 110rpx;
		padding: 15rpx 30rpx;
		box-sizing: border-box;
		display: flex;
		align-items: center;
	}

	.friend-head-picture {
		height: 110rpx;
		width: 110rpx;
		border-radius: 50%;
	}

	.head-picture {
		height: 80rpx;
		width: 80rpx;
		border-radius: 50%;
	}

	.container,
	.container1 {
		background: #F8F9F9;
		height: 100vh;
	}
	.container{
	
	}
	.container1 {
		width: 100%;
		position: absolute;
		left: 0;
		top: 0;
		background: #22222211;
		z-index: 1111;
	}

	* {
		padding: 0;
		margin: 0;
	}
</style>
