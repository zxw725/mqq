const getters = {
    token: state => state.user.token,
    avatar: state => state.user.avatar,
    name: state => state.user.name,
    roles: state => state.user.roles,
    menus: state => state.user.menus,
    rolecode: state => state.user.rolecode,
    userid: state => state.user.userid,
    keepAlive:state => state.keepAlive
}
export default getters
