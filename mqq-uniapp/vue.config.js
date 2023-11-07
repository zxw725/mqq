//vue.config.js
const TransformPages = require('uni-read-pages')
const {
	webpack
} = new TransformPages()
module.exports = {
    transpileDependencies:['uni-simple-router']
}

