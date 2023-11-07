import request from "../utils/request.js";

const md5 = require('blueimp-md5')

// 发送消息
export function send(data) {

	return request({
		url: '/message/send',
		method: 'post',
		data
	})
}

// 获取聊天信息
export function getAllMessage(fid, uid) {

	return request({
		url: '/message/getAllMessage',
		method: 'post',
		data: {
			uid,
			fid
		}
	})
}
