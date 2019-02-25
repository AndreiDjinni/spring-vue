export const mutations = {
    setUser (state, payload) {

        state.user = {
            name: payload.data.name,
            email: payload.data.email,
            imageUrl: payload.data.imageUrl,
            emailVerified: payload.data.emailVerified,
            provider: payload.data.provider
        }
    },
    clearUser (state) {

        state.user = {
            name : '',
            email : '',
            imageUrl : '',
            emailVerified : false,
            provider : ''
        }
    },
    setAuth (state) {
        state.isAuthenticated = true
    },
    clearAuth(state) {
        state.isAuthenticated = null
    },
};