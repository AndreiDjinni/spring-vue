import router from '../router'
import axios from "axios"

export const actions = {

    signIn ({ commit, dispatch }, payload) {

        let params = new URLSearchParams();
        params.append('username', payload.username);
        params.append('password', payload.password);

        axios.post('/api/login', params)
            .then(() => {

                dispatch('currentUser');

                router.push('/')
            })
            .catch(error => { console.log(error) })
    },

    signOut ({ commit }) {

        axios.post('/api/logout')
            .then(() => {

                commit('clearAuth');
                commit('clearUser');

                router.push('/sign-in')
            })
            .catch(error => { console.log(error) })
    },

    signUp({ commit }, payload) {

        axios
            .post('/api/sign-up', payload)
            .then(() => { router.push('/sign-in') })
            .catch(e => { console.log(e) });
    },

    currentUser({ commit }) {

        axios
            .post('/api/user/me')
            .then(response => {

                commit('setAuth');
                commit('setUser', response)
            })
            .catch(e => { console.log(e) });
    }
};