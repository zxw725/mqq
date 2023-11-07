<template>

	<view
		style="width: 100%;height: calc(100vh - 260rpx);overflow: hidden;position: sticky;top: 220rpx;box-sizing: border-box;padding-bottom: 150rpx;"
		:class="upClass">
		<view class="right-nav" style="z-index: 1111;">
			<view class="right-nav-item" v-for="(item,index) in sort" @click="changeViewInto(item)"
				:class="scrollIntoView1=='view'+item?'right-nav-active':''">{{item}}</view>
		</view>
		<view class="show-key">
			{{activeKey}}
		</view>
		<scroll-view style="z-index: 1111;" class="friend-box" :scroll-y="isTop" @scroll="handleScroll"
			:scroll-into-view="scrollIntoView" show-scrollbar="false">

			<ul v-for="(item, index) in phone" :key="index" class="friend-box-item">

				<li v-for="(value, key) in item" :key="key">

					<view :ref="'view'+key" :id="'view'+key" :class="scrollIntoView1=='view'+key?'right-nav-active':''">
						{{ key }}
					</view>
					<ul>
						<li v-for="info in value" :key="info.name">
							<p> {{ info.name }}</p>
							<!-- <p>Phone: {{ info.phone }}</p> -->
						</li>
					</ul>
				</li>
			</ul>
			<ul>
				<li v-for="(item, index) in myArray" :key="index">
					{{ item }}
				</li>
			</ul>
			<view class="friend-bottom">
				{{friendNum}}位联系人
			</view>
		</scroll-view>

	</view>

</template>

<script>
	import {
		groupSort
	} from '@/utils/groupSort.js'
	export default {
		name: 'friend',
		props: ['isTop', 'headHeight'],
		data() {
			return {
				sort: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
					'W', 'X', 'Y', 'Z', '#'
				],
				scrollIntoView: "viewC",
				lastScrollTop: 0,
				arr: {
					height: " calc(100vh - 220rpx)"
				},
				index: 0,
				upClass: '',
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
				list: [],
				friendNum: 0,
				scrollIntoView1: "viewC",
				myArray: [],
				isScroll: true,
				activeKey: 'C'
			}
		},
		watch: {
			index() {
				console.log('数据变化:', this.index);
				// 在这里执行实时监听数据变化后的操作
				return this.index;
			}
		},
		methods: {
			changeViewInto(item) {

				if (this.isTop) {
					this.isScroll = false
					this.activeKey = item
					// this.scrollIntoView = "view" + C
					setTimeout(()=>{
						this.scrollIntoView = "view" + item
						this.scrollIntoView1 = "view" + item
					},100)
					
					
					console.log(this.scrollIntoView);
				}
			},
			upToTop() {
				// console.log(event);
				console.log(111111);
				this.upClass = 'up'
			},
			childScroll(event) {
				if (event.target.scrollTop === 0) {
					// Reach top of child component, scroll the parent
					this.$parent.$el.scrollTop -= 10; // Adjust the scrolling amount
				} else if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
					// Reach bottom of child component, scroll the parent
					this.$parent.$el.scrollTop += 10; // Adjust the scrolling amount
				}
			},
			handleScroll(event) {
				if (this.isScroll) {
					const scrollTop = event.detail.scrollTop;
					let element = this.$refs
					var el = this.$refs['viewC']
					// var str = JSON.stringify(element) + ''
					// console.log(str);

					var cache = [];

					var lastLetter = 'viewC'
					Object.keys(element).filter(item => {
						// console.log(item);
						if (item != 'viewphone' && item != 'viewname') {
							var item1 = element[item]
							var item2 = item1[0]
							var str1 = '#' + item

							// console.log(item);
							// console.log(element[lastLetter][0].$el.offsetTop,scrollTop,item2.$el.offsetTop,item);
							uni.createSelectorQuery().select(str1).boundingClientRect(res => {
								//这里会获取到选择器中元素距离顶部的距离，以便后续定位或吸顶效果实现

								// this.stickyScrollTop =res.top;
								if (res.height > res.top - this.headHeight && 0 - res.height < res.top -
									this.headHeight) {
									this.scrollIntoView1 = item
									this.activeKey = item.replace('view','')
									console.log(this.scrollIntoView1);
									console.log(str1);
									console.log(res);
								}
							}).exec();

							// if (element[lastLetter][0].$el.offsetTop < scrollTop && scrollTop < item2.$el
							// 	.offsetTop) {
							// 	this.scrollIntoView1 = item

							// }
							// if (scrollTop == 0) {
							// 	this.scrollIntoView1 = 'viewC'
							// }
							// lastLetter = item
						}

						// console.log(element[item][0].$el);
					})
					// console.log(event.target, scrollTop);
					if (scrollTop > this.lastScrollTop) {
						// 向下滑动
						this.$emit('child-to-parent');
					} else if (scrollTop < this.lastScrollTop) {
						// 向上滑动
					}

					// 更新上一次滚动位置
					this.lastScrollTop = scrollTop;
					if (this.index == 0) {
						console.log(123);

						this.index = 1
					}
				}
				this.isScroll = true

			},
		},
		onLoad() {
			uni.navigateTo({
				url: `/pages/home/home`
			})
		},
		mounted() {
			console.log(this.headHeight);
			this.myArray = this.$refs['viewC'];
			// this.isTop=false
			var that = this
			that.phone = groupSort(that.phone, 'name')
			Object.keys(that.phone).forEach(function(key) {
				that.indexList.push(key)
				that.itemArr.push(that.list[key])
			});
			const sortedKeys = Object.keys(that.phone).sort();
			const sortedData = {};
			sortedKeys.forEach(key => {
				sortedData[key] = that.phone[key];
			});
			const dataArray = Object.entries(sortedData).map(([key, value]) => ({
				[key]: value
			}));
			dataArray.forEach(item => {
				for (let key in item) {
					that.friendNum += item[key].length;
				}
			});
			that.phone = dataArray
		}
	}
</script>

<style scoped>


	ul {
		list-style: none;
	}

	.friend-box-item {
		padding-left: 25rpx;
		box-sizing: border-box;

	}

	.friend-box {
		width: 100%;
		height: 100%;
	}

	.friend-bottom {
		display: flex;
		justify-content: center;
	}

	.f-container {
		position: relative;

		background-color: #F8F9F9;
	}

	.right-nav-active {
		color: #12B7F5;
	}

	.up {
		top: -220rpx;
		transition: 2s;
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
		right: 5rpx;
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
	.show-key {
		position: absolute;
		width: 750rpx;
		/* height: 100rpx; */
		background: white;
		z-index: 11;
		color: #12B7F5;
		padding-left: 25rpx;
	}
</style>
