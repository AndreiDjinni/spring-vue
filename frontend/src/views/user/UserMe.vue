<template>
    <div class="d-flex justify-content-between">
        <div class="left-panel w-24">
            <div class="w-100 user-info">
                <div class="rounded-circle user-icon-fone">
                    <icon class="user-icon" name="user"></icon>
                </div>
                <div>
                    <div class="user-name text-center">{{currentUser.name}}</div>
                </div>
            </div>
            <div class="w-100 user-nav mt-2rem">
                <ul class="list-group list-group-flush p-3">
                    <li class="list-group-item pointer nav-padding" @click="signOut">Sign Out</li>
                    <li class="list-group-item"></li>
                </ul>
            </div>
        </div>
        <div class="right-panel w-75 d-flex justify-content-center align-content-center">
            <div class="user-email text-center my-auto">{{currentUser.email}}</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserMe",

        data () {
            return {

            }
        },

        computed: {

            currentUser() {
                return this.$store.getters.getUser;
            }
        },

        mounted () {

        },

        methods: {

            signOut() {
                this.$store.dispatch('signOut')
            }
        }
    }
</script>

<style scoped>
    .w-24 {
        width: 24% !important;
    }
    .mt-2rem {
        margin-top: 1rem;
    }
    .left-panel {

    }
    .right-panel {
        border: 1px solid lightgray;
        border-radius: 0.3rem;
        height: 25rem;
    }
    .user-info {
        display: flex;
        border: 1px solid lightgray;
        border-radius: 0.3rem;
        height: 5rem;
    }
    .user-name {
        margin-top: 0.5rem;
        width: 11rem;
    }
    .user-email {
        width: 11rem;
    }
    .user-nav {
        border: 1px solid lightgray;
        border-radius: 0.3rem;
        height: 19rem;
    }
    .user-icon-fone {
        width: 4rem;
        height: 4rem;
        background-color: aliceblue;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        margin: 0.5rem;
    }
    .user-icon {
        color: white;
        width: 2rem;
        height: 2rem;
        cursor: pointer;
    }
    .pointer {
        cursor: pointer;
    }
    .nav-padding {
        padding-top: 0.1rem;
        padding-bottom: 0.1rem;
    }
</style>