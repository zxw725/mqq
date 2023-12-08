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
				screenHeight: 0,
				backButtonPress: 0
			}
		},
		methods: {
			changeIndex(ind) {
				this.index = ind
				console.log(this.index);
			},
			changeShowModal(val) {
				this.showModel = val
			},
			handleMessage(res) {
				console.log('处理消息', JSON.parse(res.data).content)
			},
		},

		onBackPress(options) {
			uni.showModal({
				title: '提示',
				content: '是否退出？',
				success: function(res) {
					if (res.confirm) {
						//退出当前应用，改方法只在App中生效  
						plus.runtime.quit();
					} else if (res.cancel) {
						console.log('用户点击取消');
					}
				}
			});

			return true;

		},
		onUnload() {
			window.removeEventListener("popstate", this.browserBack);
		},
		watch: {
			pages(newVal, oldVal) {
				// 监听文本框值的改变
				if (newVal !== oldVal) {
					console.log(newVal);
					// 执行需要在文本框值改变时执行的方法
					console.log('文本框的值发生改变');
					// 调用其他方法
					this.otherMethod();
				}
			}
		},
		created(options) {
			this.index = options.index
			console.log(options);
			console.log(options);
		},
		onLoad(options) {
			this.socket = uni.connectSocket({
				url: `ws://127.0.0.1:8088/chat?userId=${uni.getStorageSync("uid")}`,
				success: () => {
					console.log('WebSocket 连接成功')
				}
			})

			// 监听 WebSocket 连接打开事件
			this.socket.onOpen(() => {
				console.log('WebSocket 连接已打开')
			})
			this.socket.onMessage((res) => {
				// 处理消息的逻辑
				console.log(res);
				this.handleMessage(res)
			})

			if (options.index) {
				this.index = options.index
				console.log(options);
			}

			// uni.redirectTo({
			// 	url: `/pages/index/index`,
			// 	success(res) {
			// 		console.log('成功啦', res);

			// 	},
			// 	fail(err) {
			// 		console.log('失败啦', err);
			// 	}
			// })
			uni.getSystemInfo({
				success: res => {
					this.screenHeight = res.screenHeight; // 将屏幕高度保存在页面数据中
					// 这里可以将屏幕高度保存到本地存储或服务器等，根据实际需求处理
					// uni.setStorage({ key: 'screenHeight', data: res.screenHeight });
					uni.setStorageSync('screenHeight', res.screenHeight);
					uni.setStorageSync('screenWidth', res.screenWidth);

					console.log(res.screenHeight);

				},
				fail: err => {
					console.error('获取屏幕高度失败', err);
				}
			});
			window.history.pushState(null, null, document.URL + '#1') //往浏览器历史中添加一次记录
			window.onhashchange = (e) => {
				uni.showModal({
					title: '提示',
					content: '返回后此页面的操作将不作保留！',
					cancelText: '确定返回',
					confirmText: '留在此页',
					success: (res) => {
						if (res.confirm) {
							window.history.pushState(null, null, document.URL + '#1')
						} else if (res.cancel) {
							window.history.back();
							window.onhashchange =
								null //这里不取消监听那么上面的history.back（）也会触发一次uni.showModal，就会有两个弹窗
						}
					}
				});
			}

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
