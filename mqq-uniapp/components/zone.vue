<template>
	<view class="container">
		<view class="head-container">
			<img :src="headPicture" class="head-picture" alt="">
			<view class="head-title">
				动态
			</view>
			<view class="iconfont icon-tianjiahaoyou tianjiahaoyou" @click="editZone()">

			</view>
		</view>
		<view class="zone-container-box">
			<view class="zone-container">
				<view class="zone-container-item" v-for="(item,index) in zones">
					<view class="zone-top-container">
						<image class="zone-picture" :src="baseUrl+item.url" mode=""></image>
						<view class="zone-name-time">
							<view class="zone-name">
								{{item.username}}
							</view>
							<view class="zone-time">
								{{filterTime(item.time)}}
							</view>
						</view>
					</view>
					<view class="zone-content">
						<view class="">
							{{item.zone.content}}
						</view>
					</view>
					<view class="zone-container-picture">
						<view class="img-container" v-for="(item1,index1) in item.zone.pictures"
							:class="'img'+(item.zone.pictures.length!=1?0:1)">

							<view v-if="item1.type=='image'" @click="previewImage(index,index1)">
								<image :src="item1.url" :mode="item.zone.pictures.length==1?'widthFix':item1.size"
									class=""></image>
							</view>

							<!-- 	<view class="">
								{{baseUrl+item1.url}}
							</view> -->
						</view>
					</view>
				</view>

			</view>

		</view>
		<view class="preview" v-show="previewShow" @touchstart="onTouchStart" @touchmove="onTouchMove"
			@touchend="onTouchEnd">
			<view class="preview-container">
				<view class="" v-if="preview.type=='image'">
					<image mode="widthFix" :src="baseUrl+preview.url" @click="changeWidth()"></image>
				</view>
				<view class="" v-else v-html="">
					<video :src="preview.url">
					</video>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	// import MyEditorWithFormula from './components/MyEditorWithFormula'
	// import MyEditorWithMention from './components/MyEditorWithMention'
	import {
		callWithErrorHandling
	} from "vue"
	import {
		selectUser,
		selectAllFriends
	} from "../api/user.js"
	import {
		getAllZones
	} from '../api/zone.js'
	export default {

		data() {
			return {
				headPicture: '',
				zones: [],
				username: '',
				content: '',
				// 背景图展示
				imgBg: [],
				// 背景图预览
				imgBgArray: [],
				// 背景图路径
				imgBgArr: '',
				// 上传的背景临时路径
				imgBgUrl: '',
				// 上传的背景图绝对路径
				imgBgArrUrl: '',
				preview: '',
				previewShow: false,
				previewVideo: '',
				imageSize: {
					width: 750,
					height: 0,
				},
				primarySize: {
					width: 750,
					height: 0,
				},
				startX: 0,
				startY: 0,
				startDistance: 0,
				scaling: false,
				initWidth: false,
				startX1: 0,
				startY1: 0,
				offsetX: 0,
				offsetY: 0,
				dragging: false,
				lastOffsetX: 0,
				lastOffsetY: 0,
				previewIndex: 0,
				scale: 0,
				publiserContent: {},
				publiserImg: [],
				loading: true,
				isPublisher: true
			}
		},
		methods: {
			changeWidth() {
				if (this.initWidth) {
					this.imageSize.width = this.primarySize.width
					this.imageSize.height = this.primarySize.height
					this.initPreview()
				}
				this.initWidth = true
				setTimeout(() => {
					this.initWidth = false
				}, 300)
			},
			changePreview() {
				this.previewShow = false
				this.initPreview()
				console.log(123);
			},
			closed() {
				uni.navigateTo({
					url: '/pages/home/home'
				})
			},
			previewImage: function(index, index1) {
				this.previewShow = true
				// console.log(1111, this.imgBg[e]);
				this.preview = this.zones[index].zone.pictures[index1]
				this.previewIndex = index1
				console.log(this.preview.url);
				console.log(this.previewIndex);
			},
			// 选择背景图片
			chooseVideoImage() {
				uni.showActionSheet({
					title: "选择上传类型",
					itemList: ['图片', '视频'],
					success: (res) => {
						console.log(res)
						if (res.tapIndex == 0) {
							this.chooseImg()
						} else {
							this.chooseVideo()
						}
					}
				})
			},
			initPreview() {
				this.startX = 0
				this.startY = 0
				this.startX1 = 0
				this.startY1 = 0
				this.offsetX = 0
				this.offsetY = 0
				this.lastOffsetX = 0
				this.lastOffsetY = 0
				this.scale = 0
				var obj = {}
				obj.width = this.primarySize.width
				obj.height = this.primarySize.height
				this.imageSize = obj
			},
			chooseVideo() {
				uni.chooseVideo({
					sourceType: ['album', 'camera'],
					compressed: true,
					success: (res) => {
						console.log(res);
						this.video1 = res.tempFilePath;
						const videoPath = res.tempFilePath;
						console.log(videoPath);
						var obj = {}
						obj.url = videoPath
						obj.type = 'video'
						obj.size = '1'
						this.imgBg.push(obj)
						// 在这里可以处理选择视频的逻辑
						// this.uploadVideo(videoPath);
					},
				});
			},
			onTouchStart(event) {

				this.startX1 = event.touches[0].clientX;
				this.startY1 = event.touches[0].clientY;
				this.dragging = true;

				if (event.touches.length >= 2) {
					const x1 = event.touches[0].clientX;
					const y1 = event.touches[0].clientY;
					const x2 = event.touches[1].clientX;
					const y2 = event.touches[1].clientY;
					const deltaX = x1 - x2;
					const deltaY = y1 - y2;
					const distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
					this.startX = (x1 + x2) / 2;
					this.startY = (y1 + y2) / 2;
					this.startDistance = distance;
					this.scaling = true;
				}
			},
			onTouchMove(event) {

				if (this.dragging) {
					const moveX = event.touches[0].clientX;
					const moveY = event.touches[0].clientY;
					const deltaX = moveX - this.startX1;
					const deltaY = moveY - this.startY1;
					this.offsetX += deltaX;
					this.offsetY += deltaY;
					this.startX1 = moveX;
					this.startY1 = moveY;
				}

				if (this.scaling && event.touches.length >= 2) {
					const x1 = event.touches[0].clientX;
					const y1 = event.touches[0].clientY;
					const x2 = event.touches[1].clientX;
					const y2 = event.touches[1].clientY;
					const deltaX = x1 - x2;
					const deltaY = y1 - y2;
					const distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
					const scale = distance / this.startDistance;
					// this.scale = scale
					this.imageSize.width *= scale;
					this.imageSize.height *= scale;

					this.startDistance = distance;
					// this.offsetX = 0
					// this.offsetY = 0
					// this.lastOffsetX = 0
					// this.lastOffsetY = 0
				}
			},
			onTouchEnd() {
				if (this.imgBg[this.previewIndex].width <= this.imageSize.width) {
					if (this.offsetX * 2 >= (this.imageSize.width - this.imgBg[this.previewIndex].width)) {
						this.offsetX = (this.imageSize.width - this.imgBg[this.previewIndex].width) / 2
					} else if (this
						.offsetX * -2 >= (this.imageSize.width - this.imgBg[this.previewIndex].width)) {
						this.offsetX = -(this.imageSize.width - this.imgBg[this.previewIndex].width) / 2
					}
				} else {
					this.offsetX = this.lastOffsetX
				}
				// if (this.imageSize.width == this.primarySize.width) {
				// 	this.initPreview()
				// }
				var imgheight = this.imgBg[this.previewIndex].height * (this.imageSize.width / this.primarySize.width)
				console.log(imgheight, this.primarySize.height);
				if (imgheight >= this.primarySize.height) {

					if (this.offsetY * 2 >= (imgheight - this.primarySize.height)) {
						this.offsetY = ((imgheight - this.primarySize.height) >= 0 ? (imgheight - this.primarySize
							.height) : 0) / 2
					} else if (this.offsetY * -2 >= (imgheight - this.primarySize.height)) {
						this.offsetY = -((imgheight - this.primarySize.height) >= 0 ? (imgheight - this.primarySize
							.height) : 0) / 2
					}

				} else {
					this.offsetY = this.lastOffsetY
				}

				this.lastOffsetY = this.offsetY
				this.lastOffsetX = this.offsetX
				// this.offsetX = 

				this.imageSize.width = this.imageSize.width <= this.primarySize.width * 1 ? this.primarySize.width *
					1 : this.imageSize.width
				this.imageSize.width = this.imageSize.width >= this.primarySize.width * 3.5 ? this.primarySize.width *
					3.5 : this.imageSize.width
				this.scaling = false;
				this.dragging = false;
			},
			pictureLength(item) {
				return item.length
			},
			filterTime(time) {
				var time1 = time.replace("T", " ")
				return time1
			},
			init() {

				this.username = uni.getStorageSync('uid');
				uni.getSystemInfo({
					success: function(res) {
						const screenHeight = res.screenHeight
					}
				});
				console.log(this.username);
				if(uni.getStorageSync("zones")){
					this.zones = uni.getStorageSync("zones")
				}
				// getAllZones(this.username).then(res => {
				// 	this.zones = res.data.data
				// 	this.zones.sort((b, a) => new Date(a.time) - new Date(b.time));
				// 	this.zones.filter((item,index) => {
				// 		if (item.zone.pictures != null) {
				// 			item.zone.pictures = JSON.parse(item.zone.pictures)
				// 			item.zone.pictures.filter((item1,index1) => {
				// 				uni.downloadFile({
				// 					url: this.baseUrl + item1.url, // 服务器图片地址
				// 					success: (res) => {
				// 						if (res.statusCode === 200) {
				// 							console.log('下载成功', res.tempFilePath);
				// 							item1.url = res.tempFilePath
				// 							// if()
				// 							// console.log(this.zones);
				// 							uni.setStorageSync("zones",this.zones)
				// 							this.$forceUpdate()
				// 							// 下载成功后将图片保存到相册
				// 							//      uni.saveImageToPhotosAlbum({
				// 							//          filePath: res.tempFilePath,
				// 							//          success: function () {
				// 							//              uni.showToast({
				// 							//                  title: '图片保存成功'
				// 							//              });
				// 							//          },
				// 							//          fail: function (err) {
				// 							//              console.log('图片保存失败', err);
				// 							//              // 处理没有权限的情况
				// 							//              uni.showModal({
				// 							//                  content: '检测到没有权限，需要你确认授权',
				// 							//                  confirmText: '去设置',
				// 							//                  success: modalRes => {
				// 							//                      if (modalRes.confirm) {
				// 							//                          // 打开设置页面让用户自行开启
				// 							//                          uni.openSetting();
				// 							//                      }
				// 							//                  }
				// 							//              });
				// 							//          }
				// 							//      });

				// 						}
				// 					},
				// 					fail: (err) => {
				// 						console.log('下载失败', err);
				// 						uni.showToast({
				// 							title: '下载失败',
				// 							icon: 'none'
				// 						});
				// 					}
				// 				});
				// 			})
				// 		}
				// 	})
				// 	this.$forceUpdate()

				// 	console.log(this.zones);
				// })
				
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
			editZone() {
				uni.navigateTo({
					url: '/pages/editZone/editZone'
				})
			}
		},
		mounted() {
			this.init()
		}
	}
</script>

<style scoped>
	.preview-container image,
	.preview-container video {
		width: 750rpx;
		z-index: 1 !important;
		opacity: 0.9;
		height: calc(100vh - 150rpx);
		z-index: 111;
	}

	.preview-container video {
		height: calc(100vh - 150rpx);
	}

	.preview-container {
		width: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		height: calc(100vh - 150rpx);
		overflow: hidden;
		position: absolute;
		top: 150rpx;
		z-index: 1;


	}

	.preview {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: black;
		z-index: 11;
	}

	.img-container view {
		width: 100%;
		height: 100%;
		background-repeat: no-repeat;
		/* overflow: hidden; */
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
	}

	.img-container image,
	.img-container video {
		position: absolute;
		height: 100%;
		width: 100%;
	}

	.img0 {
		width: 220rpx;
		height: 220rpx;
		margin-right: 11rpx;
		margin-top: 11rpx;
		overflow: hidden;

	}

	.img1 {
		margin-right: 11rpx;
		margin-top: 11rpx;
		width: 100%;
		height: 450rpx;
		/* overflow: hidden; */

	}

	/* 	image:nth-child(1) {
		height: 220rpx;
	}
 */
	.zone-container-picture {
		display: flex;
		flex-wrap: wrap;
	}

	.zone-content {
		margin-top: 25rpx;
	}

	.zone-time {
		font-size: 30rpx;
		color: #818282;
	}

	.zone-name-time {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.zone-container-item {
		background-color: white;
		padding: 30rpx;
		box-sizing: border-box;
		margin-bottom: 20rpx;

	}

	.zone-picture {
		width: 90rpx;
		height: 90rpx;
		border-radius: 50%;
		margin-right: 20rpx;
	}

	.zone-top-container {
		display: flex;

	}

	.zone-container {
		width: 100%;
		overflow-y: scroll;

	}

	.zone-container-box {
		padding-bottom: 120rpx;
		box-sizing: border-box;
		background: #F8F9F9;
	}

	.box-card {
		width: 800px;

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
		color: white;
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
		z-index: 111;
	}

	.head-title {
		color: white;
		font-size: 36rpx;
		font-weight: 540;
	}

	.tianjiahaoyou {
		font-size: 60rpx;
		color: white;
	}

	.container,
	.container1 {
		width: 100%;
		background: #F8F9F9;
		height: 100vh;
	}

	.head-picture {
		height: 80rpx;
		width: 80rpx;
		border-radius: 50%;
	}
</style>
