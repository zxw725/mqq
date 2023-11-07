<template>
	<view class="container">
		<view class="head-container">
			<img :src="headPicture" class="head-picture" alt="">
			<view class="head-title">
				动态
			</view>
			<view class="iconfont icon-tianjiahaoyou tianjiahaoyou">

			</view>
		</view>
	</view>
</template>

<script>
	// import searchBox from '@/components/searchBox.vue';
	import {
		selectUser,
		selectAllFriends
	} from "../api/user.js"
	export default {
		name: "zone",
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
			this.init();
			uni.navigateTo({
				url: `/pages/home/home`
			})
		}
	}
</script>

<style scoped>
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

	.search-container {
		padding: 30rpx 25rpx;
		box-sizing: border-box;
		background: #FFFFFF;
	}

	.head-container {
		background: linear-gradient(to right, #90d7f8, #8b91f8);
		width: 100%;
		height: 150rpx;
		padding: 15rpx 30rpx;
		padding-top: 50rpx;
		box-sizing: border-box;
		display: flex;
		justify-content: space-between;
		align-items: center;
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
