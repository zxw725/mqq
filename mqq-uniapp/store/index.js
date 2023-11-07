import getters from './getters'
import user from './modules/user'

import {createStore} from "vuex";


const store = createStore({
    state: {
        keepAlive: ['Index'] //设置组件缓存，必须设置组件name一致
    },
    mutations: {
        /**
         * 设置需要缓存的组件
         */
        SET_KEEP_ALIVE(state, component) {
            const index = state.keepAlive.indexOf(component)
            if (index === -1) {
                state.keepAlive.push(component)
            }
        },
        /**
         * 移除需要缓存的组件
         */
        REMOVE_KEEP_ALIVE(state, component) {
            const index = state.keepAlive.indexOf(component)
            if (index !== -1) {
                state.keepAlive.splice(index, 1)
            }
        }
    },

    modules: {
        user,
    },

    getters
})

export default store
