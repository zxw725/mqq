<template>

	<view style="width: 100%;height: calc(100vh - 220rpx);overflow: hidden;position: sticky;top: 220rpx;">
		<view class="right-nav">
			<view class="right-nav-item" v-for="(item,index) in sort">{{item}}</view>
		</view>
		<scroll-view style="width: 100%;height: 100%;" :scroll-y="true" :scroll-into-view="scrollIntoView"
			show-scrollbar="false">
			<view class="" v-for="(item,index) in 111">
				{{item}}
			</view>
		</scroll-view>
	</view>

</template>

<script>
	import {
		groupSort
	} from '@/utils/groupSort.js'
	export default {
		data() {
			return {
				sort: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
					'W', 'X', 'Y', 'Z', '#'
				],
				scrollIntoView: "view-A",
				lastScrollTop: 0,
				arr: {
					height: " calc(100vh - 220rpx)"
				},
				index: 0,
				phone: [{
						"name": "宋江1",
						"phone": "123456789"
					},
					{
						"name": "卢俊义1",
						"phone": "123456789"
					},
					{
						"name": "吴用1",
						"phone": "123456789"
					},
					{
						"name": "公孙胜1",
						"phone": "123456789"
					},
					{
						"name": "关胜1",
						"phone": "123456789"
					},
					{
						"name": "林冲1",
						"phone": "123456789"
					},
					{
						"name": "秦明1",
						"phone": "123456789"
					},
					{
						"name": "呼延灼1",
						"phone": "123456789"
					},
					{
						"name": "花荣1",
						"phone": "123456789"
					},
					{
						"name": "柴进1",
						"phone": "123456789"
					},
					{
						"name": "李应1",
						"phone": "123456789"
					},
					{
						"name": "朱仝1",
						"phone": "123456789"
					},
					{
						"name": "鲁智深1",
						"phone": "123456789"
					},
					{
						"name": "武松1",
						"phone": "123456789"
					},
					{
						"name": "董平1",
						"phone": "123456789"
					},
					{
						"name": "张清1",
						"phone": "123456789"
					},
					{
						"name": "杨志1",
						"phone": "123456789"
					},
					{
						"name": "徐宁1",
						"phone": "123456789"
					},
					{
						"name": "索超1",
						"phone": "123456789"
					},
					{
						"name": "戴宗1",
						"phone": "123456789"
					},
					{
						"name": "刘唐1",
						"phone": "123456789"
					},
					{
						"name": "李逵1",
						"phone": "123456789"
					},
					{
						"name": "史进1",
						"phone": "123456789"
					},
					{
						"name": "穆弘1",
						"phone": "123456789"
					},
					{
						"name": "雷横1",
						"phone": "123456789"
					},
					{
						"name": "李俊1",
						"phone": "123456789"
					}
				],
				indexList: [],
				itemArr: [],
				list: []
			}
		},
		methods: {
			handleScroll(event) {
				const scrollTop = event.detail.scrollTop;
				if (scrollTop > this.lastScrollTop) {
					// 向下滑动
					console.log('向下滑动');
					this.arr = {
						height: "calc(100vh - 220rpx)"
					}
				} else if (scrollTop < this.lastScrollTop) {
					// 向上滑动
					console.log('向上滑动');
				}

				// 更新上一次滚动位置
				this.lastScrollTop = scrollTop;
				if (this.index == 0) {
					console.log(123);
					this.arr = {
						height: "100%"
					}
					this.index = 1
				}

				// 获取当前滚动位置
				// const scrollTop = event.detail.scrollTop;


			},
		},
		onLoad() {
			uni.navigateTo({
				url: `/pages/home/home`
			})
		},
		mounted() {
			this.phone = groupSort(this.phone, 'name')
			Object.keys(this.phone).forEach(function(key) {
				this.indexList.push(key)
				this.itemArr.push(this.list[key])
			});
			console.log(this.itemArr);
		}
	}
</script>

<style scoped>
	.f-container {
		position: relative;
		background-color: #F8F9F9;
	}

	::-webkit-scrollbar {
		display: none !important;
	}

	.right-nav-item {
		margin-bottom: 7rpx;
	}

	.right-nav {
		padding-bottom: 550rpx;
		box-sizing: border-box;
		width: 50rpx;
		display: flex;
		flex-direction: column;
		position: absolute;
		right: 25rpx;
		/* justify-content: flex-end; */
		align-items: center;
		height: 100%;
		font-size: 25rpx;
		color: #6e6f6f;
	}

	.friend-container {
		width: 100%;
		/* height: calc(100vh - 220rpx); */
		background: white;
		/* overflow: hidden; */
		position: sticky;
	}
</style>
