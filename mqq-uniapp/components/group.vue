<template>
	<view style="height: calc(100vh - 260rpx);">
		群聊
	</view>
</template>

<script>
	export default {
		name:"group",
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		onLoad() {
			uni.navigateTo({
				url: `/pages/home/home`
			})
		}
	}
</script>

<style>

</style>
