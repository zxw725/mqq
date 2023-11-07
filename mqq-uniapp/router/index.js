// router.js
import {
	createRouter,
	__dynamicImportComponent__
} from 'uni-simple-router'

const routes = [{
		path: '/',
		name: 'Index',
		component: () => import('@/pages/index/index.vue')
	},
	{
		path: '/pages/chat/chat',
		name: 'Chat',
		component: () => import('@/pages/chat/chat.vue')
	},

	{
		path: '/pages/home/home',
		name: 'Home',
		component: () => import('@/pages/home/home.vue'),
		children: [{
				path: '/pages/user/user',
				// aliasPath: '/pages/user/user',
				name: 'User',
				component: () => import('@/pages/user/user.vue')
			},
			{
				path: '/pages/my/my',
				name: 'My',
				component: () => import('@/pages/my/my.vue'),
				children: [{
					path: '/pages/friend/friend',
					// aliasPath: '/pages/user/user',
					name: 'Friend',
					component: () => import('@/pages/friend/friend.vue')
				}, 
				{
					path: '/pages/group/group',
					// aliasPath: '/pages/user/user',
					name: 'Group',
					component: () => import('@/pages/group/group.vue')
				},
				]
			},
			{
				path: '/pages/zone/zone',
				name: 'Zone',
				component: () => import('@/pages/zone/zone.vue')
			}
		]

	}
	// 添加其他路由
]
const router = createRouter({
	h5: {
		vueRouterDev: true, //完全使用vue-router开发 默认 false
		useUniConfig: false
	},
	platform: process.env.VUE_APP_PLATFORM,
	routes
})
export default router
