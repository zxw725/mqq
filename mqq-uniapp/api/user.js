import request from "../utils/request.js";

const md5 = require('blueimp-md5')

// 登录
export function login(data) {
	const id = data.username
	const password = md5(data.password)
	return request({
		url: '/user/login',
		method: 'post',
		data: {
			id,
			password
		}
	})
}

// 注册
export function register(data) {
	const username = data.username
	const password = md5(data.password)

	return request({
		url: '/user/register',
		method: 'post',
		data: {
			username,
			password
		}
	})
}

// 获取用户信息
export function selectUser(id) {
	
	return request({
		url: '/user/selectUser',
		method: 'post',
		data: id
	})
}

export function selectAllFriends(id){
	return request({
		url: '/userFriend/selectAllFriends',
		method: 'post',
		data: id
	})
}