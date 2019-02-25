<template>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">Sign Up</h5>
                        <form @submit="signUp">
                            <div class="form-label-group">
                                <label for="inputEmail">Email address</label>
                                <input v-model="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                            </div>

                            <div class="form-label-group">
                                <label for="inputName">Username</label>
                                <input v-model="name" id="inputName" class="form-control" placeholder="Username" required>
                            </div>

                            <div class="form-label-group">
                                <label for="inputPassword">Password</label>
                                <input v-model="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                            </div>

                            <button class="btn btn-primary btn-block text-uppercase mt-2rem" type="submit">Sign Up</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SignUp",
        data () {
            return {
                email: null,
                name: null,
                password: null
            }
        },
        methods: {
            signUp: function (event) {

                event.preventDefault();

                let signUpRequest = {
                    email : this.email,
                    name : this.name,
                    password : this.password
                };

                this.$store.dispatch('signUp', signUpRequest);
            }
        }
    }
</script>

<style scoped>
    .form-label-group {
        margin-bottom: 1rem;
    }
    .mt-2rem {
        margin-top: 2rem;
    }
</style>