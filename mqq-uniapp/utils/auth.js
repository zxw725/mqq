import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
// 认证令牌
export function getToken() {
	return uni.getStorageSync(TokenKey)
}

export function setToken(token) {
	// console.log(uni.getStorageSync(TokenKey));
	return uni.setStorageSync(TokenKey, token)
}

export function removeToken() {
	return uni.removeStorageSync(TokenKey)
}