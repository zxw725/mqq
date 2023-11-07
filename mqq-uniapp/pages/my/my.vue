<template>
	<view class="container">
		<view class="head-container">
			<img :src="headPicture" class="head-picture" alt="">
			<view class="head-title">
				联系人
			</view>
			<view class="iconfont icon-tianjiahaoyou tianjiahaoyou">

			</view>
		</view>
		<view class="search-container">
			<view class="new-friend">
				新朋友
			</view>
			<view class="group-inform">
				群通知
			</view>
		</view>
		<view class="bottom-container">
			<view class="bottom-nav-container" style="position: sticky;top: 100rpx;">
				<router-link class="active-style" to="/pages/friend/friend" >
					好友
				</router-link>
				<router-link class="active-style" to="/pages/group/group">
					群聊
				</router-link>
			</view>
			<router-view></router-view>
		</view>
	</view>
</template>

<script>
	import searchBox from '@/components/searchBox.vue';
	import {
		selectUser,
		selectAllFriends
	} from "../../api/user.js"
	export default {
		components: {

			'search-box': searchBox
		},
		data() {
			return {
				user: {},
				headPicture: '',
				username: ''
			}
		},
		methods: {
			init() {
				this.username = uni.getStorageSync('uid');
				selectUser(this.username).then(res => {
					this.user = res.data.data
					console.log(res.data.data);
					// console.log(this.baseUrl);
					this.headPicture = `${this.baseUrl+this.user.url}`
					// console.log(this.headPicture);
				}).catch(err => {
					console.log(err);
				})
			}
		},
		mounted() {
			this.init();
		},
		onLoad() {
			
			console.log(123);
			this.init();
			// uni.navigateTo({
			// 	url: `/pages/home/home`
			// })
		}
	}
</script>

<style scoped>
	.active-style {
		text-decoration: none;
		color: black;
		display: flex;
		align-items: center;
		flex-direction: column;
		padding-bottom: 8rpx;
		box-sizing: border-box;
		font-size: 33rpx;
		margin-right: 40rpx;
	}

	.router-link-active {
		color: #12B7F5;
		border-bottom: 3rpx solid #12B7F5;
	}
 ::-webkit-scrollbar { display:none !important; } 
	.bottom-nav-container {
		display: flex;
		padding: 30rpx 45rpx;
		box-sizing: border-box;
	}

	.bottom-container {
		/* padding: 0 25rpx; */
		box-sizing: border-box;
		background: #FFFFFF;
		margin-top: 25rpx;
	}

	.tianjiahaoyou {
		font-size: 60rpx;
		color: white;
	}

	.head-title {
		color: white;
		font-size: 36rpx;
		font-weight: 540;
	}

	
	.container,
	.container1 {
		width: 100%;
		background: #F8F9F9;
		height: 100vh;
	}

	.search-container view {
		padding: 30rpx 0;
		color: black;
		display: flex;
		justify-content: space-between;
		box-sizing: border-box;
		font-size: 33rpx;
	}

	.search-container {
		padding: 0 25rpx;
		box-sizing: border-box;
		background: #FFFFFF;
	}

	.head-container {
		background: linear-gradient(to right, #90d7f8, #8b91f8);
		width: 100%;
		height: 110rpx;
		padding: 15rpx 30rpx;
		box-sizing: border-box;
		display: flex;
		justify-content: space-between;
		align-items: center;
		position: sticky;
		top: 0;
	}

	.username {
		color: #ffffff;
		margin-left: 15rpx;
	}


	.head-picture {
		height: 80rpx;
		width: 80rpx;
		border-radius: 50%;
	}
</style>
