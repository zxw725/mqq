<template>
	<view>

		<view style="width: 750rpx;">
			<view class="" v-if="index==0">
				<user :showModel="showModel" @changeShowModal="changeShowModal"></user>
			</view>
			<view class="" v-if="index==1">
				<my :dataFromParent="screenHeight"></my>
			</view>
			<view class="" v-if="index==2">
				<zone></zone>
			</view>
		</view>
		<view class="bottom-tarbar">

			<view class="" @click="changeIndex(0)">
				<view class="active-style" to="/pages/user/user" :class="index==0?'active-class':''">
					<view class="iconfont icon-xiaoxi">

					</view>
					消息
				</view>
			</view>
			<view class="" @click="changeIndex(1)">
				<view class="active-style" to="/pages/my/my" :class="index==1?'active-class':''">
					<view class="iconfont icon-touxiang">

					</view>联系人
				</view>
			</view>
			<view class="" @click="changeIndex(2)">
				<view class="active-style" to="/pages/zone/zone" :class="index==2?'active-class':''">
					<view class="iconfont icon-QQkongjian">

					</view>动态
				</view>
			</view>


		</view>
	</view>
</template>

<script>
	// import SimpleRouterView from 'some-package'
	import my from "../../components/my.vue"
	import user from "../../components/user.vue"
	import zone from "../../components/zone.vue"
	export default {
		components: {

			'my': my,
			'user': user,
			'zone': zone
		},
		data() {
			return {
				index: 0,
				showModel: 0,
				screenHeight: 0
			}
		},
		methods: {
			changeIndex(ind) {
				this.index = ind
				console.log(this.index);
			},
			changeShowModal(val){
				this.showModel = val
			}
		},
		onLoad() {
			console.log(123111);
			uni.getSystemInfo({
				success: res => {
					this.screenHeight = res.screenHeight; // 将屏幕高度保存在页面数据中
					// 这里可以将屏幕高度保存到本地存储或服务器等，根据实际需求处理
					// uni.setStorage({ key: 'screenHeight', data: res.screenHeight });
					uni.setStorageSync('screenHeight', res.screenHeight);
					console.log( res.screenHeight);

				},
				fail: err => {
					console.error('获取屏幕高度失败', err);
				}
			});
		}
	}
</script>

<style scoped>
	.bottom-tarbar {
		bottom: 0;
		position: fixed;
		display: flex;
		width: 100%;
		justify-content: space-around;
		padding: 15rpx 0;
		box-sizing: border-box;
		background: #fcfdfdf9;

	}

	.active-style {
		text-decoration: none;
		color: black;
		display: flex;
		align-items: center;
		flex-direction: column;
	}

	.router-link-active,
	.active-class {
		color: #12B7F5;
	}

	.active-style .iconfont {
		font-size: 50rpx;
		margin-bottom: 5rpx;
		/* 添加你的激活时的样式 */
	}
</style>
