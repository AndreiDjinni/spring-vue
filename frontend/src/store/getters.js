export const getters = {
    isAuthenticated: state => {
        return state.isAuthenticated
    },
    getUser: state => {
        return state.user
    }
};