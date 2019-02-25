<template>
    <div class="container">
        <div class="mx-auto w-50">
            <div class="card my-5">
                <div class="card-body">
                    <div class="rounded-circle user-icon-fone my-3 m-auto">
                        <icon class="user-icon" name="user"></icon>
                    </div>
                    <h5 class="card-title text-center my-3">User info</h5>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>Username</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ currentUser.name }}</p>
                        </div>
                    </div>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>Email</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ currentUser.email }}</p>
                        </div>
                    </div>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>Email verified</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ currentUser.emailVerified }}</p>
                        </div>
                    </div>
                </div>
            </div>
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

        }
    }
</script>

<style scoped>
    .user-icon-fone {
        width: 10rem;
        height: 10rem;
        background-color: aliceblue;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }

    .user-icon {
        color: white;
        width: 6rem;
        height: 6rem;
        cursor: pointer;
    }
</style>