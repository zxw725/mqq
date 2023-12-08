<script>
	export default {
		data(){
			return {
			        backButtonPress:0,              //2次退出应用计时
			    }
		},
		onLaunch: function() {
			console.log('App Launch')
			
			    // 监听返回按钮事件
			
			  
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
		@import "@/static/iconfont/iconfont.css";
	/*每个页面公共css */
	*{
		padding: 0;
		margin: 0;
	}
</style>
