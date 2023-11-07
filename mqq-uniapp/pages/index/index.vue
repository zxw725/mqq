<template>
	<view class="content" @click="initIndex()" :style="{ paddingTop: statusBarHeight + 'rpx' }">
		<view class="icon-style">
			<image class="logo" src="/static/logo.png"></image>
			<text>MQQ</text>
		</view>
		<view class="uni-form-item uni-column" @click="showCharacter(1)">
			<input class="uni-input" type="number" :focus="index==1" :placeholder="(index!=1)?'请输入账号':''"
				@focus="showCharacter(1)" @blur="hidenCharacter()" v-model="username" />
			<uni-icons :class="'icon-eye'+(username!=''?0:1)" size="25" class="closeempty" type="closeempty"
				color="#b7b7b7" @click="clear(0)"></uni-icons>
		</view>
		<view class="uni-form-item uni-column" @click="showCharacter1(2)">
			<uni-icons @click="showPwd()" :type="showPassword?  'eye-slash-filled':'eye-filled'" size="30"
				class="icon-eye" :class="'icon-eye'+(password!=''?0:1)" color="#b7b7b7"></uni-icons>
			<input class="uni-input" :password="showPassword" :focus="index==2" type="text"
				:placeholder="(index!=2)?'请输入密码':''" @focus="showCharacter(2)" @blur="hidenCharacter()"
				v-model="password" />
			<uni-icons @click="clear(1)" :class="'icon-eye'+(password!=''?0:1)" size="25" class="closeempty"
				type="closeempty" color="#b7b7b7"></uni-icons>
		</view>
		<text class="protocol">已阅读并同意服务协议和MQQ隐私保护指引</text>
		<view class="enter-icon" :class="'enter-icon'+(isEnter?1:0)" @click="loginUser()">
			<uni-icons type="arrow-right" size="30" color="white"></uni-icons>
		</view>
		<view class="bottom-container">
			<text>手机号登录</text>
			<text>|</text>
			<text>新用户注册</text>
			<text>|</text>
			<text>更多选项</text>
		</view>
	</view>
</template>
<script>
	import {login} from "../../api/user.js"
	import {setToken} from "../../utils/auth.js"
	export default {
		data() {
			return {
				title: 'input',
				focus: false,
				inputValue: '',
				changeValue: '',
				index: 0,
				showPassword: true,
				password: '',
				username: '',
				isEnter: false,
				index1: 0,
				statusBarHeight: 0,
			}
		},
		created() {
			this.statusBarHeight = uni.getSystemInfoSync()["statusBarHeight"];
		},
		methods: {
			loginUser() {
				// console.log(this.username,this.password);
				uni.navigateTo({
					url: `/pages/home/home`
				})
				// login({username:this.username,password:this.password}).then(res=>{
				// 	if(res.data.code==200){
				// 		console.log(res.data.data);
				// 		setToken(res.data.data);
				// 	}
				// 	uni.setStorageSync('uid',this.username);
				// 	uni.showToast({
				// 		title: '登录成功', //显示的文字
				// 		icon: 'success' //显示的图标
				// 	});
				// 	console.log(123);
				// 	uni.navigateTo({
				// 		url: `/pages/home/home`
				// 	})
				// }).catch((err)=>{
				// 	uni.showToast({
				// 		title:"用户名不存在或账号密码不一致",
				// 		icon:"error"
				// 	})
					
				// 	console.log(err);
				// })
				// uni.request({
				// 	url:
				// })
				
			},
			clear(index1) {
				if (index1 == 0) {
					this.username = ''
				}
				if (index1 == 1) {
					this.password = ''
				}
			},
			showPwd() {
				this.showPassword = (this.showPassword == true ? false : true)
				this.index = 2
			},
			showCharacter(index) {
				this.index = index
			},
			showCharacter1(index) {
				this.index = index
				this.index = 2
			},
			hidenCharacter() {
				this.index = 0;
			},
			onKeyInput: function(event) {
				this.inputValue = event.target.value
			},
			replaceInput: function(event) {
				var value = event.target.value;
				if (value === '11') {
					this.changeValue = '2';
				}
			},
			hideKeyboard: function(event) {
				if (event.target.value === '123') {
					uni.hideKeyboard();
				}
			},
			initIndex() {
				this.index1 = 0
			}
		},
		watch: {
			password(newVal, oldVal) {
				if (newVal != '' && this.username != '') {
					this.isEnter = true
				} else {
					this.isEnter = false
				}
			},
			username(newVal, oldVal) {
				if (newVal != '' && this.password != '') {
					this.isEnter = true
				} else {
					this.isEnter = false
				}
			}

		}
	}
</script>

<style scoped>
	@import url("../../uni_modules/uni-icons/components/uni-icons/uniicons.css");

	.content {
		box-sizing: border-box;

	}

	.protocol {
		font-size: 22rpx;
	}

	.bottom-container {
		box-sizing: border-box;
		padding: 0 0 70rpx;
		display: flex;
		width: 90%;
		justify-content: space-around;
		margin-top: 350rpx;
		position: absolute;
		bottom: 50rpx;
	}

	.bottom-container text:nth-child(2n-1) {
		font-size: 22rpx;
		font-weight: 800;
	}

	.bottom-container text:nth-child(2n) {
		font-size: 22rpx;
	}

	.enter-icon {
		width: 150rpx;
		height: 150rpx;
		border-radius: 50%;
		background-color: #e8e8ec;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 100rpx;
	}

	.enter-icon1 {
		background: linear-gradient(to bottom right,#136ff855,#136ff8aa,#136ff8cc,#136ff8ee,#136ff8ee,#136ff8aa,#136ff899) ;
	}

	.icon-eye1 {
		display: none;
	}

	.icon-eye {
		position: absolute;
		left: 40rpx;
		top: 25rpx;
		z-index: 123;
	}

	.closeempty {
		position: absolute;
		right: 40rpx;
		top: 25rpx;
		z-index: 123;
	}

	.uni-column {
		position: relative;
	}

	.icon-style {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 240rpx;
		width: 100%;
		font-size: 42rpx;
		font-weight: 800;
	}

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 100rpx;
		width: 100rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}


	.uni-input {
		text-align: center;
	}

	.uni-input {
		border-radius: 50rpx;
		background-color: #e8e8ec;
		padding: 30rpx 160rpx;
		box-sizing: border-box;
		height: 100rpx;
		width: 650rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 0 0 30rpx;
	}
</style>
