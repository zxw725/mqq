// 引用用户请求api
import {login} from '../../api/user';
import {setToken} from '../../utils/auth';

//下面我是示例的微信小程序的参数，可自行修改请求参数
const actions = {
	login({commit}, param) {
	    return new Promise((resolve, reject) => {
	        login({
				iv: param.iv,		// 请求参数(可自行修改)
				code: param.code	// 请求参数(可自行修改)
			}).then(response => {
				// 这里可以自己添加方法
				// 保存获取到的token到本地之类的
				// 必须在resolve之前执行
				setToken(response.token)	//将获取到的token保存到本地
	            resolve(response)
	        }).catch(error => {
	            console.log(error)
	        })
	    })
	}
};

export default {
    namespaced: true,
    actions
}
