<template>
	<scroll-view class="container" id="container" ref="scrollContainer" scroll-y="true" @scroll="handleScroll">
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
		<view class="bottom-container" :class="isUpToTop" @click="isUp()">
			<view class="bottom-nav-container" style="position: sticky;top: 150rpx;z-index: 11;">
				<view @click="changeIndex(0)" class="active-style" to="/pages/friend/friend"
					:class="index==0?'router-link-active ':''">
					好友
				</view>
				<view @click="changeIndex(1)" class="active-style" to="/pages/group/group"
					:class="index==1?'router-link-active ':''">
					群聊
				</view>
			</view>
			<view class="">
				<view class="" v-if="index==0" @scroll="parentScroll">
					<friend @child-to-parent="handleDataFromChild()" @update-child="handleScroll"
						:isTop="dataFromParent" :headHeight="headHeight"></friend>
				</view>
				<view class="" v-if="index==1">
					<group></group>
				</view>
			</view>
			<!-- <router-view></router-view> -->
		</view>
	</scroll-view>
</template>

<script>
	// import searchBox from '@/components/searchBox.vue';
	import {
		selectUser,
		selectAllFriends
	} from "../api/user.js"
	import friend from './friend.vue'
	import group from './group.vue'
	export default {

		name: "my",
		components: {

			'friend': friend,
			'group': group
		},
		data() {

			return {
				user11: {},
				headPicture: '',
				username: '',
				index: 0,
				isUpToTop: '',
				dataFromParent: false,
				lastScroll: 0,
				maxScroll: 1111,
				isUp1: 1,
				maxValue: 1111,
				screenHeight: 0,
				isScroll: true,
				headHeight: 0
			}
		},

		methods: {
			isUp() {
				console.log(11123);
			},
			updateChildData(newData) {
				this.dataFromParent = newData; // 更新父组件数据
			},
			handleScroll(event) {
				if (this.isScroll) {
					const maxScroll = this.$refs.scrollContainer.scrollHeight - this.$refs.scrollContainer.clientHeight;
					console.log(event);
					console.log(maxScroll);
					this.maxScroll = event.detail.scrollTop
					console.log(event.detail.scrollHeight, this.screenHeight);
					console.log(event.detail.scrollTop, event.detail.scrollHeight - this.screenHeight);
					console.log(this.maxScroll == event.detail.scrollHeight - this.screenHeight);
					this.dataFromParent = this.maxScroll == event.detail.scrollHeight - this.screenHeight
					// if (event.detail.scrollTop > this.lastScroll) {
					// 	this.lastScroll = event.detail.scrollTop
					// 	// this.maxScroll = this.lastScroll
					// 	this.dataFromParent = false
					// } else if (this.maxScroll == event.detail.scrollHeight - this.screenHeight) {
					// 	this.maxValue = event.detail.scrollTop
					// 	this.isUp1 = 0
					// 	// this.lastScroll = event.detail.scrollTop
					// 	this.dataFromParent = true
					// 	console.log(1, this.dataFromParent, this.lastScroll == event.detail.scrollTop);
					// 	console.log(1233);
					// } else {
					// 	this.dataFromParent = false
					// }
					// if (this.isUp1 == 1) {
					// 	console.log(111);
					// 	if (this.maxScroll == event.detail.scrollTop) {
					// 		this.dataFromParent = true
					// 	}
					// }

				}
				this.isScroll = true

			},
			handleDataFromChild() {
				this.isUpToTop = 'up'

			},
			changeIndex(ind) {
				this.index = ind
			},
			init() {
				this.username = uni.getStorageSync('uid');
				uni.getSystemInfo({
					success: function(res) {
						const screenHeight = res.screenHeight
						console.log(this.screenHeight);
						console.log('屏幕高度：' + res.screenHeight);
					}
				});
				this.screenHeight = uni.getStorageSync('screenHeight')
				console.log(this.screenHeight);

				selectUser(this.username).then(res => {
					this.user = res.data.data
					console.log(res.data.data);
					// console.log(this.baseUrl);
					this.headPicture = `${this.baseUrl+this.user.url}`
					// console.log(this.headPicture);
				}).catch(err => {
					console.log(err);
				})
			},
			parentScroll(event) {
				event.stopPropagation(); // 阻止事件继续传播
			}
		},
		mounted() {
			var height1 = 0
			var height2 = 0
			uni.createSelectorQuery().select('.head-container').boundingClientRect(res => {
				height1 = res.height
				console.log(res.height);
				uni.createSelectorQuery().select('.bottom-nav-container').boundingClientRect(res => {
					height2 = res.height
					console.log(res.height);
					this.headHeight = height1+height2
					console.log(this.headHeight);
				}).exec();
			}).exec();
		
	
			this.init()
			console.log(this.screenHeight);
			const h = uni.getStorageSync('screenHeight');
			console.log(h);
		},
		onLoad() {
			this.init().then(res => {
				console.log(123);
			});
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

	.up {
		/* transform: translateY(-240rpx); */
		transition: 1s;
	}

	.router-link-active {
		color: #12B7F5;
		border-bottom: 3rpx solid #12B7F5;
	}

	::-webkit-scrollbar {
		display: none !important;
	}

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
		height: 150rpx;
		padding: 15rpx 30rpx;
		padding-top: 50rpx;
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
